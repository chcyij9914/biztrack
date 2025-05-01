package com.erp.biztrack.admin.controller;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.biztrack.admin.model.service.AdminService;
import com.erp.biztrack.login.model.dto.LoginDto;
import com.erp.biztrack.utils.MailUtil;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/adminMain.do")
    public String adminMainPage(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "page", defaultValue = "1") int page,
            Model model) {

        int pageSize = 10;
        int offset = (page - 1) * pageSize;

        List<LoginDto> employeeList = adminService.searchEmployees(keyword, offset, pageSize);
        int totalCount = adminService.countEmployees(keyword);
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);

        model.addAttribute("employeeList", employeeList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("keyword", keyword);

        return "admin/empmanagement";
    }

    @PostMapping("/employeeInsert.do")
    public String employeeInsert(LoginDto dto) {
        String randomPwd = RandomStringUtils.randomAlphanumeric(10);
        String encPwd = passwordEncoder.encode(randomPwd);
        dto.setEmpPwd(encPwd);
        dto.setIsDefaultPwd("Y");

        System.out.println("DB 저장용 비밀번호(암호화 전): " + randomPwd);

        adminService.insertEmployee(dto);

        try {
        	MailUtil.sendMail(dto.getEmail(), dto.getEmpId(), randomPwd);;
        } catch (Exception e) {
            System.out.println("메일 전송 실패: " + e.getMessage());
            e.printStackTrace();
        }

        return "redirect:/adminMain.do";
    }

    @GetMapping("/employeeDetail.do")
    @ResponseBody
    public LoginDto getEmployeeDetail(@RequestParam("empId") String empId) {
        return adminService.getEmployeeById(empId);
    }
    
    @PostMapping("/employeeUpdate.do")
    public String updateEmployee(LoginDto dto, HttpSession session) {
        // 세션 체크
        if (session.getAttribute("loginInfo") == null) {
            return "redirect:/login.do"; // 로그인 안 되어 있으면 로그인 페이지로
        }

        adminService.updateEmployee(dto);
        return "redirect:/adminMain.do";
    }
    
    @PostMapping("/employeeDelete.do")
    @ResponseBody
    public String deleteEmployee(@RequestParam("empId") String empId, HttpSession session) {
        if (session.getAttribute("loginInfo") == null) return "fail"; // 세션 체크

        int result = adminService.retireEmployee(empId);
        return result > 0 ? "success" : "fail";
    }
    
 
    
    
}