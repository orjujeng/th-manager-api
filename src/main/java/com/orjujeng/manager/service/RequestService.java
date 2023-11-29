package com.orjujeng.manager.service;

import org.springframework.stereotype.Service;

import com.orjujeng.manager.entity.ChangePendingRequestStatusVo;
import com.orjujeng.manager.entity.RegisterVo;
import com.orjujeng.manager.utils.Result;
@Service
public interface RequestService {

	Result newAuthRequest(RegisterVo registerVo);

	Result checkRequestInfo(String status);

	Result changePendingRequestStatus(ChangePendingRequestStatusVo changePendingRequestStatusVo);

}
