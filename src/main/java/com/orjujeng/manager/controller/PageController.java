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
	
	@RequestMapping("/projectowner")
	public String toProfile(HttpSession session) {
		if(session.getAttribute("loginUser")!= null) {
			return "profile";
		}
		return "sign-in";
	}
	
	@RequestMapping("/mangagerowner")
	public String toProfileManager(HttpSession session) {
		if(session.getAttribute("loginUser")!= null) {
			return "profile-mangager";
		}
		return "sign-in";
	}
	
	@RequestMapping("/thNew")
	public String toThNew(HttpSession session) {
		if(session.getAttribute("loginUser")!= null) {
			return "timesheet-new";
		}
		return "sign-in";
	}
	
	@RequestMapping("/thProgress")
	public String toThProgress(HttpSession session) {
		if(session.getAttribute("loginUser")!= null) {
			return "timesheet-progress";
		}
		return "sign-in";
	}
	
	@RequestMapping("/thDone")
	public String toThDone(HttpSession session) {
		if(session.getAttribute("loginUser")!= null) {
			return "timesheet-done";
		}
		return "sign-in";
	}
	
	@RequestMapping("/requestPending")
	public String toRequestPending(HttpSession session) {
		if(session.getAttribute("loginUser")!= null) {
			return "request-pending";
		}
		return "sign-in";
	}
	
	@RequestMapping("/requestDenyed")
	public String toRequestDenyed(HttpSession session) {
		if(session.getAttribute("loginUser")!= null) {
			return "request-denyed";
		}
		return "sign-in";
	}
	
	@RequestMapping("/requestAllowed")
	public String toRequestAllowed(HttpSession session) {
		if(session.getAttribute("loginUser")!= null) {
			return "request-allowed";
		}
		return "sign-in";
	}
}
