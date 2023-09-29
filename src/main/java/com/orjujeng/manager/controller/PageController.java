package com.orjujeng.manager.controller;

import org.springframework.stereotype.Controller;
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
	public String toMain() {
		return "main";
	}
}
