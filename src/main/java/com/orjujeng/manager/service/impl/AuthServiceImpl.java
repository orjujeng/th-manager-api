package com.orjujeng.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.orjujeng.manager.entity.AuthAccessInfo;
import com.orjujeng.manager.entity.LoginInfo;
import com.orjujeng.manager.entity.LoginVo;
import com.orjujeng.manager.entity.MemberInfo;
import com.orjujeng.manager.fegin.AuthApiFeginService;
import com.orjujeng.manager.service.AuthService;
import com.orjujeng.manager.utils.Result;
import com.orjujeng.manager.utils.ResultCode;
@Service
public class AuthServiceImpl implements AuthService{
	@Autowired 
	AuthApiFeginService authApiFeginService;
	@Override
	public Result vaildLoginInfo(LoginVo login) {
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setAccountNum(login.getAccountNum());
		loginInfo.setPassword(login.getPassword());
		Result vaildLogin = authApiFeginService.vaildLogin(loginInfo);
		return vaildLogin;
	}
	@Override
	public Result getAuthList(Integer memberId) {
		Result result = authApiFeginService.getAuthList(memberId);
		return result;
	}
}
