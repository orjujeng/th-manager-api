package com.orjujeng.manager.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.orjujeng.manager.entity.ChangePendingRequestStatusVo;
import com.orjujeng.manager.entity.MemberInfo;
import com.orjujeng.manager.utils.Result;


@FeignClient("TH-RequestApi")
public interface RequestFeginService {
	@RequestMapping("request/requestBeAuth")
	public Result requestBeAuth(@RequestBody MemberInfo memberInfo);
	
	@GetMapping("request/checkRequstInfo")
	public Result checkRequstInfo(@RequestParam("status") String status);
	
	@PostMapping("request/changeRequestStatus")
	public Result changeRequestStatus(@RequestBody ChangePendingRequestStatusVo changePendingRequestStatusVo);
}
