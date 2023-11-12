package com.orjujeng.manager.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.orjujeng.manager.entity.BindingInfo;
import com.orjujeng.manager.entity.EditProfileVo;
import com.orjujeng.manager.entity.NewProfileVo;
import com.orjujeng.manager.entity.ProjectInfo;
import com.orjujeng.manager.utils.Result;

@Service
public interface ProfileService {
	Result getAllProfileInfo(String accountNum) throws InterruptedException, ExecutionException;

	Result getAllProjectInfo();

	Result createProject(ProjectInfo projectinfo);

	Result updateProject(ProjectInfo projectinfo);

	List<BindingInfo> checkProjectBindingInfo(String projectCode);

	Result deleteProject(Integer id);

	Result addNewProfile(@Valid NewProfileVo newProfileVo, HttpSession session);

	Result editProfile(@Valid EditProfileVo editProfileVo, HttpSession session);

	Result deleteProfile(String accountNum, HttpSession session);

	Result checkManagmentInfo(Integer memberId);

	Result getMemberByManagerId(HttpSession session) throws InterruptedException, ExecutionException;

}
