package com.orjujeng.manager.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.orjujeng.manager.entity.AddBindingInfo;
import com.orjujeng.manager.entity.BindingInfo;
import com.orjujeng.manager.entity.MemberInfo;
import com.orjujeng.manager.entity.NewProfileVo;
import com.orjujeng.manager.entity.ProfileInfoVo;
import com.orjujeng.manager.entity.ProjectInfo;
import com.orjujeng.manager.fegin.AuthApiFeginService;
import com.orjujeng.manager.fegin.ProfileApiFeginService;
import com.orjujeng.manager.service.ProfileService;
import com.orjujeng.manager.utils.Result;
import com.orjujeng.manager.utils.ResultCode;

@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	ProfileApiFeginService profileApiFeginService;
	@Autowired
	AuthApiFeginService authApiFeginService;
	@Autowired
	ThreadPoolExecutor threadPoolExecutor;

	@Override
	public Result getAllProfileInfo(String accountNum) throws InterruptedException, ExecutionException {
		Map<Integer,ProfileInfoVo> result = new HashMap<Integer,ProfileInfoVo>();
		
		// null is all member info		
		CompletableFuture<List<MemberInfo>> f1 = CompletableFuture.supplyAsync(() -> {
			Result memberResult = profileApiFeginService.getMemberInfoByAccountNum(null);
			String s = JSON.toJSONString(memberResult.getData());
			List<MemberInfo> memberInfoList = JSONObject.parseArray(s, MemberInfo.class);
			return memberInfoList;
		}, threadPoolExecutor);

		// binding info
		CompletableFuture<List<BindingInfo>> f2 = CompletableFuture.supplyAsync(() -> {
			Result bindingResult = profileApiFeginService.findbinding(null, null, null, null);
			List<BindingInfo> bindingInfoList = JSONObject.parseArray(JSON.toJSONString(bindingResult.getData()),
					BindingInfo.class);
			return bindingInfoList;
		}, threadPoolExecutor);
		
		// project info
		CompletableFuture<List<ProjectInfo>> f3 = CompletableFuture.supplyAsync(() -> {
			Result projectResult = profileApiFeginService.getAllProject(true);
			List<ProjectInfo> projectInfoList = JSONObject.parseArray(JSON.toJSONString(projectResult.getData()),
					ProjectInfo.class);
			return projectInfoList;
		}, threadPoolExecutor);
		
		List<MemberInfo> memberInfoList = f1.get();
		
		for (MemberInfo memberInfo : memberInfoList) {
			ProfileInfoVo element= new ProfileInfoVo();
			element.setAccountNum(memberInfo.getAccountNum());
			element.setDeleteFlag(memberInfo.getDeleteFlag());
			element.setManagerId(memberInfo.getManagerId());
			element.setName(memberInfo.getNameZh());
			element.setPerm(memberInfo.getPerm());
			element.setJoinDate(memberInfo.getJoinDate());
			element.setExpireDate(memberInfo.getExpiredDate());
			for (MemberInfo managerNameList: memberInfoList) {
				if((memberInfo.getManagerId()!=null&&!memberInfo.getManagerId().equals("null")) && (!memberInfo.getManagerId().equals("") && managerNameList.getId() == Integer.parseInt(memberInfo.getManagerId()))) {
					element.setManagerName(managerNameList.getNameZh());
				}
			}
			result.put(memberInfo.getId(),element);
			}
		
		List<BindingInfo> bindingInfoList = f2.get();
		
		for (BindingInfo bindingInfo : bindingInfoList) {
			Integer key = bindingInfo.getMemberId();
			ProfileInfoVo profileInfoVo = result.get(key);
			profileInfoVo.getProjectCode().add(bindingInfo.getProjectCode());
			profileInfoVo.getProjectMemberExpireDate().add(bindingInfo.getExpireDate());
			profileInfoVo.getProjectMemberStartDate().add(bindingInfo.getStartDate());
			profileInfoVo.getProportion().add(bindingInfo.getProportion());
		}
		List<ProjectInfo> projectInfoList = f3.get();
		for (Entry<Integer, ProfileInfoVo> entry : result.entrySet()) {
			List<String> projectCode = entry.getValue().getProjectCode();
			for (String pc : projectCode) {
				for (ProjectInfo projectInfo : projectInfoList) {
					if(pc.equals(projectInfo.getProjectCode())) {
						entry.getValue().getProjectName().add(projectInfo.getProjectName());
						entry.getValue().getProjectExpireDate().add(projectInfo.getExpireDate());
					}
				}
			}	
		}
		// member_info
		// Name_zh
		// Account Num
		// Manager Id
		// Manager Name --
		// Perm
		// Join Date
		// Expire Date
		// Delete Flag

		// Project Code
		// Proportion
		// Project Name
		// Project Expire Date
		// Edit
		// Delete
		return Result.success(result);
	}

	@Override
	public Result getAllProjectInfo() {
		// false not got expired date
		Result result = profileApiFeginService.getAllProject(false);
		return result;
	}

	@Override
	public Result createProject(ProjectInfo projectinfo) {
		Result result = profileApiFeginService.addProject(projectinfo);
		return result;
	}

	@Override
	public Result updateProject(ProjectInfo projectinfo) {
		Result result = profileApiFeginService.updateProject(projectinfo);
		return result;
	}

	@Override
	public List<BindingInfo> checkProjectBindingInfo(String projectCode) {
		Result result = profileApiFeginService.findbinding(null, null, null, projectCode);
		String s = JSON.toJSONString(result.getData());
		List<BindingInfo> projectBindingInfoList = JSONObject.parseArray(s, BindingInfo.class);
		return projectBindingInfoList;
	}

	@Override
	public Result deleteProject(Integer id) {
		Result result = profileApiFeginService.disableProject(id);
		return result;
	}

	@Override
	public Result addNewProfile(@Valid NewProfileVo newProfileVo,HttpSession session) {
		MemberInfo addMemberInfo = new MemberInfo();
		addMemberInfo.setNameCn(newProfileVo.getChineseName());
		addMemberInfo.setNameZh(newProfileVo.getEnglishName());
		addMemberInfo.setDeleteFlag("N");
		addMemberInfo.setPerm(newProfileVo.getPerm());
		addMemberInfo.setAuthOfBackend(newProfileVo.getAuthOfBackend());
		addMemberInfo.setJoinDate(newProfileVo.getJoinDate());
		addMemberInfo.setExpiredDate(newProfileVo.getExpiredDate());
		Result allMemberInfo = profileApiFeginService.getMemberInfoByAccountNum(null);
		String s = JSON.toJSONString(allMemberInfo.getData());
		Integer managerId = null;
		List<MemberInfo> allMemberInfoList = JSONObject.parseArray(s, MemberInfo.class);
		for (MemberInfo memberInfo : allMemberInfoList) {
			if(!newProfileVo.getManagerName().equals("")&&memberInfo.getNameZh().equals(newProfileVo.getManagerName())) {
				managerId = memberInfo.getId();
			}
		}
		//need check
		Result addNewProfileResult = null;
		if((!newProfileVo.getManagerName().equals("") && managerId!=null)||(newProfileVo.getManagerName().equals("") && managerId==null)) {
			addMemberInfo.setManagerId(managerId+"");
			addNewProfileResult = profileApiFeginService.addNewProfile(addMemberInfo);
		}else {
			return Result.error(ResultCode.FAIL.code, "Manager Name Not Exist", null);
		}
		int newId = (int) addNewProfileResult.getData();
		//TODO
		Result postAddMemberInfo = profileApiFeginService.getMemberInfoById(newId);
		s = JSON.toJSONString(postAddMemberInfo.getData());
		List<MemberInfo> postAddMemberInfoList = JSONObject.parseArray(s, MemberInfo.class);
		String newAccountNum = postAddMemberInfoList.get(0).getAccountNum();
		
		Result allProjectInfo = profileApiFeginService.getAllProject(false);
		s = JSON.toJSONString(allProjectInfo.getData());
		List<ProjectInfo> projectInfoList = JSONObject.parseArray(s, ProjectInfo.class);
		
		
		for(int i=0;i<newProfileVo.getProjectCode().size();i++) {
			AddBindingInfo newBindingInfo = new AddBindingInfo();
			newBindingInfo.setMemberId(newId);
			newBindingInfo.setAccountNum(newAccountNum);
			newBindingInfo.setStartDate(newProfileVo.getProjectStartDate().get(i));
			newBindingInfo.setExpireDate(newProfileVo.getProjectExpireDate().get(i));
			for (ProjectInfo projectInfo : projectInfoList) {
				if(projectInfo.getProjectCode().equals(newProfileVo.getProjectCode().get(i))) {
					newBindingInfo.setProjectCode(newProfileVo.getProjectCode().get(i));
				}
			}
			s = JSON.toJSONString(session.getAttribute("loginUser"));
			MemberInfo memberInfo = JSONObject.parseObject(s, MemberInfo.class);
			newBindingInfo.setChangedBy(memberInfo.getAccountNum());
			newBindingInfo.setProportion(100/newProfileVo.getProjectCode().size());
			if(newBindingInfo.getProjectCode()!=null && !newBindingInfo.getProjectCode().equals("")) {
				Result addNewBindingResult= profileApiFeginService.addbinding(newBindingInfo);
			}else {
				return Result.error(ResultCode.FAIL.code, "Project Code Not Exist", null);
			}
		}
		
		Result addAuthInfoResult =authApiFeginService.addAuthInfo(newId, newAccountNum);
		
		return Result.success(null);
	}

}
