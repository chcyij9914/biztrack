package com.erp.biztrack.schedule.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.erp.biztrack.schedule.model.dto.Schedule;
import com.erp.biztrack.schedule.model.service.ScheduleService;
import com.erp.biztrack.login.model.dto.LoginDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    // 일정 등록
    @RequestMapping("/AutoAddSchedule.do")
    public String insertIdSchedule(HttpServletRequest request, HttpSession session) {
        String nextId = scheduleService.selectNextScheduleId();
        String loginEmpId = (String) session.getAttribute("empId");

        if (loginEmpId == null || loginEmpId.isEmpty()) {
            loginEmpId = "2505003"; // 테스트용 기본 사번
            session.setAttribute("empId", loginEmpId);
        }

        String scTitle = request.getParameter("scTitle");
        String calColor = request.getParameter("calColor");
        String scType = request.getParameter("scType");
        String place = request.getParameter("place");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime start = LocalDateTime.parse(request.getParameter("startDatetime"), formatter);
        LocalDateTime end = LocalDateTime.parse(request.getParameter("endDatetime"), formatter);

        Schedule schedule = new Schedule();
        schedule.setScId(nextId);
        schedule.setEmpId(loginEmpId);
        schedule.setScTitle(scTitle);
        schedule.setCalColor(calColor);
        schedule.setScType(scType);
        schedule.setPlace(place);
        schedule.setStartDatetime(Timestamp.valueOf(start));
        schedule.setEndDatetime(Timestamp.valueOf(end));

        scheduleService.insertSchedule(schedule);
        return "redirect:/schedule/ListSchedule.do";
    }

    // 일정 목록
    @RequestMapping("/ListSchedule.do")
    public String listSchedule(HttpSession session, Model model) {
        String loginEmpId = (String) session.getAttribute("empId");
        LoginDto loginInfo = (LoginDto) session.getAttribute("loginInfo");
        String adminYN = loginInfo != null ? loginInfo.getAdminYN() : "N";

        List<Schedule> scheduleList = scheduleService.selectScheduleByEmpId(loginEmpId);

        model.addAttribute("scheduleList", scheduleList);
        model.addAttribute("loginEmpId", loginEmpId);
        model.addAttribute("adminYN", adminYN);
        return "schedule/scheduleListView";
    }

    // 등록 폼 이동
    @RequestMapping("/AddForm.do")
    public String showAddForm() {
        return "schedule/scheduleAddView";
    }

    // 일정 수정 폼
    @RequestMapping("/UpdateForm.do")
    public String showUpdateForm(@RequestParam("scId") String scId, Model model) {
        Schedule schedule = scheduleService.selectOneSchedule(scId);
        model.addAttribute("schedule", schedule);
        return "schedule/scheduleUpdateView";
    }

    // 일정 수정
    @PostMapping("/UpdateSchedule.do")
    public String updateSchedule(HttpServletRequest request) {
        Schedule schedule = new Schedule();

        schedule.setScId(request.getParameter("scId"));
        schedule.setScTitle(request.getParameter("scTitle"));
        schedule.setPlace(request.getParameter("place"));
        schedule.setScType(request.getParameter("scType"));
        schedule.setCalColor(request.getParameter("calColor"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime start = LocalDateTime.parse(request.getParameter("startDatetime"), formatter);
        LocalDateTime end = LocalDateTime.parse(request.getParameter("endDatetime"), formatter);

        schedule.setStartDatetime(Timestamp.valueOf(start));
        schedule.setEndDatetime(Timestamp.valueOf(end));

        scheduleService.updateSchedule(schedule);
        return "redirect:/schedule/ListSchedule.do";
    }

    // 일정 삭제
    @RequestMapping("/DeleteSchedule.do")
    public String deleteSchedule(@RequestParam("scId") String scId) {
        scheduleService.deleteSchedule(scId);
        return "redirect:/schedule/ListSchedule.do";
    }

    // FullCalendar 연동용 이벤트 반환
    @RequestMapping("/events.do")
    @ResponseBody
    public List<Map<String, Object>> getScheduleEvents(HttpSession session) {
        String loginEmpId = (String) session.getAttribute("empId");
        List<Schedule> scheduleList = scheduleService.selectScheduleByEmpId(loginEmpId);

        List<Map<String, Object>> eventList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        for (Schedule schedule : scheduleList) {
            Map<String, Object> event = new HashMap<>();
            event.put("id", schedule.getScId());
            event.put("title", schedule.getScTitle());
            event.put("start", schedule.getStartDatetime());
            event.put("end", schedule.getEndDatetime());
            event.put("color", schedule.getCalColor());

            event.put("scId", schedule.getScId());
            event.put("place", schedule.getPlace());
            event.put("scType", schedule.getScType());
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
        LoginDto loginInfo = (LoginDto) session.getAttribute("loginInfo");
        String adminYN = loginInfo != null ? loginInfo.getAdminYN() : "N";

        String type = request.getParameter("searchType");
        String keyword = request.getParameter("keyword");
        String begin = request.getParameter("beginDate");
        String end = request.getParameter("endDate");

        List<Schedule> scheduleList = new ArrayList<>();
        Schedule param = new Schedule();
        param.setEmpId(loginEmpId);

        if ("title".equals(type)) {
            param.setScTitle(keyword);
            scheduleList = scheduleService.searchByTitle(param);
        } else if ("type".equals(type)) {
            param.setScType(keyword);
            scheduleList = scheduleService.searchByType(param);
        } else if ("date".equals(type)) {
            if (begin != null && !begin.isEmpty() && end != null && !end.isEmpty()) {
                param.setStartDatetime(Timestamp.valueOf(begin + " 00:00:00"));
                param.setEndDatetime(Timestamp.valueOf(end + " 23:59:59"));
                scheduleList = scheduleService.searchByDate(param);
            }
        }

        model.addAttribute("scheduleList", scheduleList);
        model.addAttribute("loginEmpId", loginEmpId);
        model.addAttribute("adminYN", adminYN);
        return "schedule/scheduleListView";
    }
}
