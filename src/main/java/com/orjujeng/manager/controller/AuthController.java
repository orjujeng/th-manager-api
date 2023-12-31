package com.orjujeng.manager.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.orjujeng.manager.entity.AuthUpdateVo;
import com.orjujeng.manager.entity.LoginVo;
import com.orjujeng.manager.entity.MemberInfo;
import com.orjujeng.manager.entity.RegisterVo;
import com.orjujeng.manager.service.AuthService;
import com.orjujeng.manager.service.ProfileService;
import com.orjujeng.manager.utils.Result;
import com.orjujeng.manager.utils.ResultCode;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/manager/auth")
@Slf4j
@ResponseBody
public class AuthController {
	@Autowired
	AuthService authService;
	
	@Autowired
	ProfileService profileService;
	
	@RequestMapping("/login")
	public Result login(@RequestBody LoginVo login,HttpSession session) {
		Result result = authService.vaildLoginInfo(login);
		if(result.getCode().equals(ResultCode.SUCCESS.code)) {
			session.setAttribute("loginUser", result.getData());
		}
		return result;
	}
	
	@RequestMapping("/register")
	public Result register(RegisterVo register) {
		log.error(register.getUsername());
		log.error(register.getRequestReason());
		//TODO Auth
		return Result.successWithMsg(register, null);
	}
	
	@RequestMapping("/authList")
	public Result authCheck(HttpSession session) {
		String s = JSON.toJSONString(session.getAttribute("loginUser"));
		MemberInfo memberInfo = JSONObject.parseObject(s, MemberInfo.class);
		Integer memberId = memberInfo.getId();
		Result authResult =authService.getAuthList(memberId);
		return authResult;
	}
	
	@RequestMapping("/authUpdate")
	public Result authUpdate(@RequestBody AuthUpdateVo authUpdateVo) {
		Result result = authService.updateAuthSetting(authUpdateVo);
		return result;
	}
	
	@RequestMapping("/check")
	public Result profileCheck(HttpSession session,@RequestParam(required = true) String type) {
		String s = JSON.toJSONString(session.getAttribute("loginUser"));
		MemberInfo memberInfo = JSONObject.parseObject(s, MemberInfo.class);
		Integer memberId = memberInfo.getId();
		if(type!=null&&type.equals("manager_access")) {
			Result authResult = profileService.checkManagmentInfo(memberId);
			return authResult;
		}else {
			Result authResult =authService.checkAuth(memberId,type);
			return authResult;
		}
		
	}	
}
