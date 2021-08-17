package com.marondalgram.marondal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/marondalgram")
public class MarondalController {
	
	/**
	 * ȸ������������
	 * @param model
	 * @return
	 */
	@RequestMapping("/log/sign_up")
	public String signUp(Model model) {
		model.addAttribute("marondal", "/marondal/signup");
		return "templete/layout";
	}
	
	
	/**
	 * �α��� ������
	 * @param model
	 * @return
	 */
	@RequestMapping("/log/sign_in")
	public String signIn(Model model) {
		model.addAttribute("marondal", "/marondal/signin");
		return "templete/layout";
	}
	
	
	
	/**
	 *  �α׾ƿ����
	 * @param request
	 * @return
	 */
	@RequestMapping("/log/log_out")
	public String log_out(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("userName");
		session.removeAttribute("userloginId");
		session.removeAttribute("userId");
		
		return "redirect:/marondalgram/log/sign_in";
	
	}
	
	
}
