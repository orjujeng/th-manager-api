package com.orjujeng.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.orjujeng.manager.entity.LoginVo;
import com.orjujeng.manager.entity.RegisterVo;
import com.orjujeng.manager.utils.Result;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/manager/auth")
@Slf4j
@ResponseBody
public class AuthController {
	@RequestMapping("/login")
	public Result login(LoginVo login) {
		log.error(login.getUsername());
		//TODO Auth
		return Result.successWithMsg(login, null);
	}
	
	@RequestMapping("/register")
	public Result register(RegisterVo register) {
		log.error(register.getUsername());
		log.error(register.getRequestReason());
		//TODO Auth
		return Result.successWithMsg(register, null);
	}
}
