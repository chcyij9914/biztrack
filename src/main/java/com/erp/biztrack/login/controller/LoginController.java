package com.erp.biztrack.login.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.biztrack.login.model.dto.LoginDto;
import com.erp.biztrack.login.model.service.LoginService;
import com.erp.biztrack.utils.MailUtil;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	// 로그인 페이지
	@RequestMapping("login.do")
	public String loginPage() {
		return "login/login";
	}

	// 로그인 검증 (POST로 처리)
	@RequestMapping(value = "loginCheck.do", method = RequestMethod.POST)
	public String loginCheck(@RequestParam("empId") String empId, @RequestParam("empPwd") String empPwd,
			HttpSession session, Model model) throws Exception {
		LoginDto dto = new LoginDto();
		dto.setEmpId(empId);
		dto.setEmpPwd(empPwd);

		LoginDto result = loginService.login(dto);

		if (result != null) {
			// 🔒 퇴사자 로그인 차단
			if ("Y".equals(result.getRetireYN())) {
				model.addAttribute("error", "퇴사자는 로그인이 불가능합니다.");
				return "login/login";
			}

			session.setAttribute("loginInfo", result);

			if ("Y".equals(result.getIsDefaultPwd())) {
				return "redirect:/changePassword.do";
			} else {
				return "redirect:/main.do";
			}
		} else {
			model.addAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다.");
			return "login/login";
		}
	}

	// 로그아웃
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate(); // 세션 무효화 (로그아웃)
		return "redirect:/login.do"; // 로그인 페이지로 이동
	}

	// 비밀번호 찾기 페이지------------------------------------------------------------
	// 로그인 페이지
	@RequestMapping("findPassword.do")
	public String findPassword() {
		return "login/findPassword";
	}

	// 비밀번호 변경 페이지
	@RequestMapping("/changePassword.do")
	public String changePasswordPage(HttpSession session, Model model) {
		LoginDto loginUser = (LoginDto) session.getAttribute("loginInfo");
		if (loginUser == null)
			return "redirect:/login.do";
		model.addAttribute("empId", loginUser.getEmpId());
		return "login/updatePassword";
	}

	@RequestMapping(value = "updatePassword.do", method = RequestMethod.POST)
	public String updatePassword(@RequestParam("empId") String empId, @RequestParam("newPwd") String newPwd,
			@RequestParam("confirmPwd") String confirmPwd, Model model, HttpSession session) {

		if (!newPwd.equals(confirmPwd)) {
			model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
			model.addAttribute("empId", empId);
			return "login/updatePassword";
		}

		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@!#$%&])[A-Za-z\\d@!#$%&]{8,}$";
		if (!newPwd.matches(regex)) {
			model.addAttribute("error", "비밀번호는 영문 대소문자, 숫자, 특수문자 포함 8자 이상이어야 합니다.");
			model.addAttribute("empId", empId);
			return "login/updatePassword";
		}

		LoginDto dto = new LoginDto();
		dto.setEmpId(empId);
		dto.setEmpPwd(newPwd);
		dto.setIsDefaultPwd("N");

		int result = loginService.updatePassword(dto);
		if (result > 0) {
			session.invalidate();
			return "redirect:/login.do";
		} else {
			model.addAttribute("error", "비밀번호 변경 실패. 다시 시도해주세요.");
			model.addAttribute("empId", empId);
			return "login/updatePassword";
		}
	}

	@RequestMapping(value = "sendAuthCode.do", method = RequestMethod.POST)
	@ResponseBody
	public String sendAuthCode(@RequestParam String empId, @RequestParam String email, HttpSession session) {
		if (!loginService.isValidEmployeeEmail(empId, email)) {
			return "invalid";
		}

		String code = RandomStringUtils.randomNumeric(6);
		session.setAttribute("authCode", code);
		session.setAttribute("authCodeExpire", System.currentTimeMillis() + (5 * 60 * 1000));
		session.setAttribute("findPwdEmpId", empId);

		MailUtil.sendAuthCodeMail(email, code); // 인증번호 발송 메서드
		return "sent";
	}

	// 비밀번호 찾기 페이지 ( --------------------------------------------------
	@RequestMapping(value = "verifyCode.do", method = RequestMethod.POST)
	public String verifyAuthCode(@RequestParam String authCode, HttpSession session, Model model) {
		String sessionCode = (String) session.getAttribute("authCode");
		Long expire = (Long) session.getAttribute("authCodeExpire");

		if (sessionCode != null && sessionCode.equals(authCode) && System.currentTimeMillis() < expire) {
			// 로그인 세션 대신 findPwdEmpId 사용
			model.addAttribute("empId", session.getAttribute("findPwdEmpId"));
			return "login/updatePassword";
		} else {
			model.addAttribute("msg", "인증번호가 일치하지 않거나 시간이 초과되었습니다.");
			return "login/findPassword";
		}
	}

}
