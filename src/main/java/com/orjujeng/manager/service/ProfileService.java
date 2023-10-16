package com.orjujeng.manager.service;

import org.springframework.stereotype.Service;

import com.orjujeng.manager.entity.ProjectInfo;
import com.orjujeng.manager.utils.Result;

@Service
public interface ProfileService {
	Result getAllProfileInfo(String accountNum);

	Result getAllProjectInfo();

	Result createProject(ProjectInfo projectinfo);

}
