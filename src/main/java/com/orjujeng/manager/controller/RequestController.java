package com.orjujeng.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.orjujeng.manager.entity.ChangePendingRequestStatusVo;
import com.orjujeng.manager.entity.RegisterVo;
import com.orjujeng.manager.service.RequestService;
import com.orjujeng.manager.utils.Result;

@Controller
@ResponseBody
@RequestMapping("/manager/request")
public class RequestController {
	@Autowired
	RequestService requestService;
	
	@PostMapping("/authRequest")
	public Result newAuthRequest(@RequestBody RegisterVo registerVo) {
		Result result = requestService.newAuthRequest(registerVo);
		return result;
	}
	
	@GetMapping("/getRequestInfo")
	public Result getRequestInfo(@RequestParam("status") String status) {
		Result result = requestService.checkRequestInfo(status);
		return result;
	}
	
	@PostMapping("/changePendingRequestStatus")
	public Result changePendingRequestStatus(@RequestBody ChangePendingRequestStatusVo changePendingRequestStatusVo) {
		Result result = requestService.changePendingRequestStatus(changePendingRequestStatusVo);
		return result;
	}
}
