package com.orjujeng.manager.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.orjujeng.manager.entity.BindingInfo;
import com.orjujeng.manager.entity.MemberInfo;
import com.orjujeng.manager.entity.NewProfileVo;
import com.orjujeng.manager.entity.ProjectDeleteVo;
import com.orjujeng.manager.entity.ProjectInfo;
import com.orjujeng.manager.service.ProfileService;
import com.orjujeng.manager.utils.Result;
import com.orjujeng.manager.utils.ResultCode;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/manager/profile")
@Slf4j
@ResponseBody
public class ProfileController {
	@Autowired
	ProfileService profileService;
	@RequestMapping("/profileList")
	public Result getProfileList(HttpSession session) throws InterruptedException, ExecutionException {
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
	
	@RequestMapping("/updateProject")
	public Result updateProject(@RequestBody @Valid ProjectInfo projectinfo) {
		Result result = profileService.updateProject(projectinfo);
		return result;
	}
	
	@RequestMapping("/deleteProject")
	public Result updateProject(@RequestBody ProjectDeleteVo projectDeleteVo) {
		List<BindingInfo> checkProjectBindingInfo= profileService.checkProjectBindingInfo(projectDeleteVo.getProjectCode());
		if(checkProjectBindingInfo!= null &&checkProjectBindingInfo.size()>0) {
			List<String> result = new ArrayList<String>(); 
			for (BindingInfo bindingInfo : checkProjectBindingInfo) {
				Date expireDate = bindingInfo.getExpireDate();
				if(expireDate.before(new Date())) {
					//this project code is using and has been expired in binding info
					continue;
				}else {
					result.add(bindingInfo.getAccountNum());
				}
			}
			return Result.error(ResultCode.PROJECT_OCCUPIED.code,"PROJECT HAS BEEN OCCUPIED",result);
		}else {
			Result result = profileService.deleteProject(projectDeleteVo.getId());
			return result;
		}
	}
	@RequestMapping("/addNewProfile")
	public Result addNewProfile(@RequestBody @Valid NewProfileVo newProfileVo,HttpSession session) {
		Result result = profileService.addNewProfile(newProfileVo,session);
		return result;
	}
}
