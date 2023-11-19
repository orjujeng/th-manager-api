package com.orjujeng.manager.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.orjujeng.manager.entity.MemberInfo;
import com.orjujeng.manager.entity.NewTimesheetDTO;
import com.orjujeng.manager.entity.NewTimesheetVo;
import com.orjujeng.manager.entity.TimesheetInfoDetail;
import com.orjujeng.manager.fegin.ProfileApiFeginService;
import com.orjujeng.manager.fegin.TimesheetFeginService;
import com.orjujeng.manager.service.TimesheetService;
import com.orjujeng.manager.utils.Result;
import com.orjujeng.manager.utils.ResultCode;

@Service
public class TimesheetServiceImpl implements TimesheetService{
	@Autowired
	ProfileApiFeginService profileApiFeginService;
	
	@Autowired
	TimesheetFeginService timesheetFeginService;
	@Override
	public Result newTimesheet(NewTimesheetVo newTimesheetVo) {
		//date check
		Boolean isExclude = false;
		String bankHolidayToDTO = null;
		if(newTimesheetVo.getStartDate().after(newTimesheetVo.getEndDate())&&newTimesheetVo.getExceptFinishedDate().before(newTimesheetVo.getStartDate())&&newTimesheetVo.getExceptFinishedDate().before(newTimesheetVo.getEndDate())) {
			return Result.error(ResultCode.FAIL.code, "Please Check New TimeSheet Date Info", null);
		}
		List<Date> bankHolidayList = newTimesheetVo.getBankHolidayList();
		for (Date date : bankHolidayList) {
			if(date == null) {
				break;
			}
			if(date.before(newTimesheetVo.getStartDate())||date.after(newTimesheetVo.getEndDate())) {
				return Result.error(ResultCode.FAIL.code, "Please Check New TimeSheet Date Info", null);
			}else {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				String str=sdf.format(date);
				if(bankHolidayToDTO==null) {
					bankHolidayToDTO = str;
				}else {
					bankHolidayToDTO+=","+str;
				}	 
			}
		}
		List<NewTimesheetDTO> input = new ArrayList<NewTimesheetDTO>();
		List<String> excludeNameList = newTimesheetVo.getExcludeName();
		//justifity name exist and set to th api
		Result memberInfoList = profileApiFeginService.getMemberInfoByAccountNum(null);
		String s = JSON.toJSONString(memberInfoList.getData());
		List<MemberInfo> memberInfoLists = JSONObject.parseArray(s, MemberInfo.class);
		for (MemberInfo memberInfo : memberInfoLists) {
			NewTimesheetDTO newTimesheetDTO = new NewTimesheetDTO();
			newTimesheetDTO.setStartDate(newTimesheetVo.getStartDate());
			newTimesheetDTO.setEndDate(newTimesheetVo.getEndDate());
			newTimesheetDTO.setExceptFinishDate(newTimesheetVo.getExceptFinishedDate());
			newTimesheetDTO.setBankHolidayDate(bankHolidayToDTO);
			newTimesheetDTO.setMemberId(memberInfo.getId());
			newTimesheetDTO.setMemberName(memberInfo.getNameZh());
			newTimesheetDTO.setAccountNum(memberInfo.getAccountNum());
			newTimesheetDTO.setWorkDays(null);
			newTimesheetDTO.setFinished("N");
			newTimesheetDTO.setBankHolidayDays(bankHolidayList.size()+"");
			
			for (String memberName : excludeNameList) {
				if(memberInfo.getNameZh()!=null && memberInfo.getNameZh().equals(memberName)) {
					isExclude = true;
				}
			}
			if(isExclude) {
				isExclude = false;
			}else {
				input.add(newTimesheetDTO);
			}
		}
		if(memberInfoLists.size()-input.size() != excludeNameList.size()) {
			return Result.error(ResultCode.FAIL.code, "Please Check New TimeSheet Exclude Name", null);
		}else {
			Result result = timesheetFeginService.newTimesheet(input);
			return result;
		}
	}
	
	@Override
	public Result getDefaultInProgressTimesheetInfo(String seqId, String finished) {
		Result result = timesheetFeginService.getTimesheetInfo(seqId, finished);
		return result;
	}
	
	@Override
	public Result finishTimesheet(String seqId) {
		Result result = timesheetFeginService.finishTimesheet(seqId);
		return result;
	}

	@Override
	public Result updateTimesheetById(TimesheetInfoDetail timesheetInfoDetail) {
		Result result = timesheetFeginService.updateTimesheetById(timesheetInfoDetail);
		return result;
	}

	@Override
	public Result updateTimesheetFinshedStatusById(TimesheetInfoDetail timesheetInfoDetail) {
		Result result = timesheetFeginService.updateTimesheetFinshedStatusById(timesheetInfoDetail);
		return result;
	}

	@Override
	public Result openTimesheet(String seqId) {
		Result result = timesheetFeginService.openTimesheet(seqId);
		return result;
	}
	
}
