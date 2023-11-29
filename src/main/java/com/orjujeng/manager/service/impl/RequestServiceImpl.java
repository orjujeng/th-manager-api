package com.orjujeng.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.orjujeng.manager.entity.ChangePendingRequestStatusVo;
import com.orjujeng.manager.entity.LoginInfo;
import com.orjujeng.manager.entity.MemberInfo;
import com.orjujeng.manager.entity.RegisterVo;
import com.orjujeng.manager.fegin.AuthApiFeginService;
import com.orjujeng.manager.fegin.RequestFeginService;
import com.orjujeng.manager.service.RequestService;
import com.orjujeng.manager.utils.Result;
import com.orjujeng.manager.utils.ResultCode;
@Service
public class RequestServiceImpl implements RequestService{
	@Autowired
	AuthApiFeginService authApiFeginService;
	@Autowired
	RequestFeginService requestFeginService;
	@Override
	public Result newAuthRequest(RegisterVo registerVo) {
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setAccountNum(registerVo.getUsername());
		loginInfo.setPassword(registerVo.getPassword());
		Result vaildLoginResult = authApiFeginService.vaildLogin(loginInfo);
		if(!vaildLoginResult.getCode().equals("008")) {
			return Result.error(ResultCode.PASSWORD_ERROR.code, "account num or pw error or donot have access", null);
		}
		String s = JSON.toJSONString(vaildLoginResult.getData());
		MemberInfo memberInfo = JSONObject.parseObject(s, MemberInfo.class);
		Result result = requestFeginService.requestBeAuth(memberInfo);
		return result;
	}
	@Override
	public Result checkRequestInfo(String status) {
		Result result =requestFeginService.checkRequstInfo(status);
		return result;
	}
	
	@Override
	public Result changePendingRequestStatus(ChangePendingRequestStatusVo changePendingRequestStatusVo) {
		Result result =requestFeginService.changeRequestStatus(changePendingRequestStatusVo);
		return result;
	}

}
