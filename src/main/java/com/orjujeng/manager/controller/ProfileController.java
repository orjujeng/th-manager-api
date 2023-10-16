package com.orjujeng.manager.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.orjujeng.manager.entity.MemberInfo;
import com.orjujeng.manager.entity.ProjectInfo;
import com.orjujeng.manager.service.ProfileService;
import com.orjujeng.manager.utils.Result;


import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/manager/profile")
@Slf4j
@ResponseBody
public class ProfileController {
	@Autowired
	ProfileService profileService;
	@RequestMapping("/profileList")
	public Result getProfileList(HttpSession session) {
		//TODO
		String s = JSON.toJSONString(session.getAttribute("loginUser"));
		MemberInfo memberInfo = JSONObject.parseObject(s, MemberInfo.class);
		String accountNum = memberInfo.getAccountNum();
		Result result = profileService.getAllProfileInfo(accountNum);
		return result;
	}
	@RequestMapping("/projectList")
	public Result getProfileList() {
		Result result = profileService.getAllProjectInfo();
		return result;
	}
	
	@RequestMapping("/createProject")
	public Result createProject(@RequestBody ProjectInfo projectinfo) {
		Result result = profileService.createProject(projectinfo);
		return result;
	}
}
