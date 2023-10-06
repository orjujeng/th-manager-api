package com.orjujeng.manager.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.orjujeng.manager.entity.LoginInfo;
import com.orjujeng.manager.utils.Result;


@FeignClient("TH-AuthApi")
public interface AuthApiFeginService {
	@RequestMapping("auth/vaildLogin")
	public Result vaildLogin(@RequestBody LoginInfo logininfo);
	
	@RequestMapping("auth/getAuthList")
	public Result getAuthList(@RequestParam(required = true) Integer memberId);
}
