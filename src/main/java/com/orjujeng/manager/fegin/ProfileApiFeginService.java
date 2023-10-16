package com.orjujeng.manager.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.orjujeng.manager.entity.ProjectInfo;
import com.orjujeng.manager.utils.Result;

@FeignClient("TH-ProfileApi")
public interface ProfileApiFeginService {
	
	@GetMapping("/profile/project/getAllProject")
	public Result getAllProject(@RequestParam(required = false) Boolean expiredDate);
	
	@PostMapping("/profile/project/addProject")
	public Result addProject(@RequestBody ProjectInfo projectInfo); 
}
