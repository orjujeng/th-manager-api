package com.orjujeng.manager.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.orjujeng.manager.entity.AuthUpdate;
import com.orjujeng.manager.entity.LoginInfo;
import com.orjujeng.manager.utils.Result;



@FeignClient(name="th-auth-api",url="orjujeng-lb-2098452421.ap-northeast-1.elb.amazonaws.com")
public interface AuthApiFeginService {
	@RequestMapping("auth/vaildLogin")
	public Result vaildLogin(@RequestBody LoginInfo logininfo);
	
	@RequestMapping("auth/getAuthList")
	public Result getAuthList(@RequestParam(required = true) Integer memberId);
	
	@RequestMapping("auth/updateAuthInfo")
	public Result updateAuthInfo(@RequestBody AuthUpdate authUpdate);
	
	@RequestMapping("auth/check")
	public Result checkAuthOfAll(@RequestParam(required = true) Integer memberId,@RequestParam(required = true) String type);	
	
	@RequestMapping("auth/addAuthInfo")
	public Result addAuthInfo(@RequestParam(required = true) Integer memberId,@RequestParam(required = true) String accountNum);
	
	@RequestMapping("auth/deleteAuthInfo")
	public Result deleteAuthInfo(@RequestParam(required = true) Integer memberId,@RequestParam(required = true) String accountNum);	
}
