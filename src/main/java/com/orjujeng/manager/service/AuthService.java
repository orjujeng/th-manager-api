package com.orjujeng.manager.service;

import org.springframework.stereotype.Service;

import com.orjujeng.manager.entity.AuthUpdateVo;
import com.orjujeng.manager.entity.LoginVo;
import com.orjujeng.manager.utils.Result;

@Service
public interface AuthService {

	Result vaildLoginInfo(LoginVo login);

	Result getAuthList(Integer memberId);

	Result updateAuthSetting(AuthUpdateVo authUpdateVo);

	Result checkAuth(Integer memberId, String type);

}
