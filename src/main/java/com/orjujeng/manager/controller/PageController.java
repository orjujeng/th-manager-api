package com.orjujeng.manager.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager/page")
public class PageController {
	@RequestMapping("/signin")
	public String toSignin() {
		return "sign-in";
	}
	@RequestMapping("/register")
	public String toRegister() {
		return "register";
	}
	
	@RequestMapping("/main")
	public String toMain(HttpSession session) {
		if(session.getAttribute("loginUser")!= null) {
			return "main";
		}
		return "sign-in";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		if(session.getAttribute("loginUser")!= null) {
			session.removeAttribute("loginUser");
		}
		return "index";
	}
	@RequestMapping("/authowner")
	public String toAuth(HttpSession session) {
		if(session.getAttribute("loginUser")!= null) {
			return "auth";
		}
		return "sign-in";
	}
	
}
