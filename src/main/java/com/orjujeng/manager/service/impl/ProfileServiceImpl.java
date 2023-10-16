package com.orjujeng.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orjujeng.manager.entity.ProjectInfo;
import com.orjujeng.manager.fegin.ProfileApiFeginService;
import com.orjujeng.manager.service.ProfileService;
import com.orjujeng.manager.utils.Result;
@Service
public class ProfileServiceImpl implements ProfileService{
	@Autowired
	ProfileApiFeginService profileApiFeginService;
	@Override
	public Result getAllProfileInfo(String accountNum) {
		//member_info
		//Name	
		//Account Num	
		//Manager Id	
		//Manager Name	
		//Perm	
		//Join Date	
		//Expire Date	
		//Delete Flag	
		
		//Project Code	
		//Proportion	
		//Project Name	
		//Project Expire Date	
		//Edit	
		//Delete
		return null;
	}

	@Override
	public Result getAllProjectInfo() {
		Result result = profileApiFeginService.getAllProject(false);
		return result;
	}

	@Override
	public Result createProject(ProjectInfo projectinfo) {
		Result result = profileApiFeginService.addProject(projectinfo);
		return result;
	}
}
