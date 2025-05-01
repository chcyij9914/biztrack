package com.erp.biztrack.schedule.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.biztrack.schedule.model.dto.Schedule;
import com.erp.biztrack.schedule.model.service.ScheduleService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    @Autowired
    private ScheduleService scheduleService;

    // 일정 등록 (자동 ID + 세션 사번 + datetime-local 파싱)
    @RequestMapping("/AutoAddSchedule.do")
    public String insertIdSchedule(HttpServletRequest request, HttpSession session) {
        String nextId = scheduleService.selectNextScheduleId(); // 자동ID 생성

        // 로그인한 사용자(empId) 세션에서 가져와서 세팅
        String loginEmpId = (String) session.getAttribute("empId");

        // [테스트용] 로그인 안 했을 경우 임시 사번 세팅
        if (loginEmpId == null || loginEmpId.isEmpty()) {
            loginEmpId = "SB19100502"; // 개발 중인 테스트용 사번
            session.setAttribute("empId", loginEmpId);
        }

        // 오류 방지용 세션 만료 또는 로그인 없이 접근한 경우 차단
        if (loginEmpId == null || loginEmpId.isEmpty()) {
            throw new IllegalStateException("로그인 정보가 없습니다. EMP_ID가 세션에 없습니다.");
        }

        // 폼 값 추출
        String scTitle = request.getParameter("scTitle");
        String calColor = request.getParameter("calColor");
        String scType = request.getParameter("scType");
        String place = request.getParameter("place");

        // 날짜+시간 문자열(T)을 Timestamp로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime start = LocalDateTime.parse(request.getParameter("startDatetime"), formatter);
        LocalDateTime end = LocalDateTime.parse(request.getParameter("endDatetime"), formatter);

        Timestamp startTimestamp = Timestamp.valueOf(start);
        Timestamp endTimestamp = Timestamp.valueOf(end);

        // Schedule 객체 생성 및 값 설정
        Schedule schedule = new Schedule();
        schedule.setScId(nextId);
        schedule.setEmpId(loginEmpId);
        schedule.setScTitle(scTitle);
        schedule.setCalColor(calColor);
        schedule.setScType(scType);
        schedule.setPlace(place);
        schedule.setStartDatetime(startTimestamp);
        schedule.setEndDatetime(endTimestamp);

        scheduleService.insertSchedule(schedule); // DB 저장
        return "redirect:/schedule/ListSchedule.do";
    }

    // 일정 목록 
    @RequestMapping("/ListSchedule.do")
    public String listSchedule(HttpSession session, Model model) {
        String loginEmpId = (String) session.getAttribute("empId"); // 로그인한 사용자 사번 가져오기
        List<Schedule> scheduleList = scheduleService.selectScheduleByEmpId(loginEmpId); // 필터링된 일정만 조회
        model.addAttribute("scheduleList", scheduleList);
        return "schedule/scheduleListView";
    }

    // 등록 화면 보여주기
    @RequestMapping("/AddForm.do")
    public String showAddForm() {
        return "schedule/scheduleAddView";
    }

    // 일정 수정
    @RequestMapping("/UpdateSchedule.do")
    public String updateSchedule(Schedule schedule) {
        scheduleService.updateSchedule(schedule);
        return "redirect:/schedule/ListSchedule.do";
    }

    // 일정 수정 폼 보기 
    @RequestMapping("/UpdateForm.do")
    public String showUpdateForm(@RequestParam("scId") String scId, Model model) {
        Schedule schedule = scheduleService.selectOneSchedule(scId);
        model.addAttribute("schedule", schedule);
        return "schedule/scheduleUpdateView"; // JSP 파일 경로
    }

    // 일정 삭제 
    @RequestMapping("/DeleteSchedule.do")
    public String deleteSchedule(@RequestParam("scId") String scId) {
        scheduleService.deleteSchedule(scId);
        return "redirect:/schedule/ListSchedule.do";
    }

    // 일정 등록/목록/수정/삭제 컨트롤러 내부에 추가
    @RequestMapping("/events.do")
    @ResponseBody
    public List<Map<String, Object>> getScheduleEvents(HttpSession session) {
        String loginEmpId = (String) session.getAttribute("empId");

        List<Schedule> scheduleList = scheduleService.selectScheduleByEmpId(loginEmpId);
        List<Map<String, Object>> eventList = new ArrayList<>();

        // 날짜 포맷 정의
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        for (Schedule schedule : scheduleList) {
            Map<String, Object> event = new HashMap<>();

            // FullCalendar 필수 필드
            event.put("id", schedule.getScId());
            event.put("title", schedule.getScTitle());
            event.put("start", schedule.getStartDatetime());
            event.put("end", schedule.getEndDatetime());
            event.put("color", schedule.getCalColor());

            // 모달에서 사용할 확장 정보
            event.put("scId", schedule.getScId());
            event.put("place", schedule.getPlace());
            event.put("scType", schedule.getScType());

            // 포맷팅된 문자열 날짜 추가
            event.put("startDatetime", schedule.getStartDatetime() != null ? sdf.format(schedule.getStartDatetime()) : "-");
            event.put("endDatetime", schedule.getEndDatetime() != null ? sdf.format(schedule.getEndDatetime()) : "-");

            eventList.add(event);
        }

        return eventList;
    }

    // 일정 검색
    @RequestMapping("/SearchSchedule.do")
    public String searchSchedule(HttpServletRequest request, HttpSession session, Model model) {
        String loginEmpId = (String) session.getAttribute("empId");
        String type = request.getParameter("searchType");
        String keyword = request.getParameter("keyword");
        String begin = request.getParameter("beginDate");
        String end = request.getParameter("endDate");

        List<Schedule> scheduleList = new ArrayList<>();

        if ("title".equals(type)) {
            Schedule param = new Schedule();
            param.setEmpId(loginEmpId);
            param.setScTitle(keyword);
            scheduleList = scheduleService.searchByTitle(param);

        } else if ("type".equals(type)) {
            Schedule param = new Schedule();
            param.setEmpId(loginEmpId);
            param.setScType(keyword);
            scheduleList = scheduleService.searchByType(param);

        } else if ("date".equals(type)) {
            Schedule param = new Schedule();
            param.setEmpId(loginEmpId);

            if (begin != null && !begin.isEmpty() && end != null && !end.isEmpty()) {
                param.setStartDatetime(Timestamp.valueOf(begin + " 00:00:00"));
                param.setEndDatetime(Timestamp.valueOf(end + " 23:59:59"));
                scheduleList = scheduleService.searchByDate(param);
            }
        }

        model.addAttribute("scheduleList", scheduleList);
        return "redirect:/schedule/ListSchedule.do";
    }

}
