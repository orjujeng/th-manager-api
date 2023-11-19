package com.orjujeng.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.orjujeng.manager.entity.NewTimesheetVo;
import com.orjujeng.manager.entity.TimesheetInfoDetail;
import com.orjujeng.manager.service.TimesheetService;
import com.orjujeng.manager.utils.Result;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/manager/timesheet")
@Slf4j
@ResponseBody
public class TimesheetController {
	@Autowired
	TimesheetService timesheetService;
	
	@PostMapping("/newTimesheet")
	public Result newTimesheet(@RequestBody NewTimesheetVo newTimesheetVo) {
		Result result = timesheetService.newTimesheet(newTimesheetVo);
		return result;
	}
	
	@GetMapping("/getTimesheetInfo")
	public Result getTimesheetInfo(@RequestParam(required = false,value="seqId") String seqId,@RequestParam(required = true,value="finished") String finished) {
		if(seqId==null||seqId.equals("")) {
			Result result = timesheetService.getDefaultInProgressTimesheetInfo(null,finished);
			return result;
		}else {
			Result result = timesheetService.getDefaultInProgressTimesheetInfo(seqId,finished);
			return result;
		}	
	}
	
	@PostMapping("/finishTimesheet")
	public Result finishTimesheet(@RequestParam(required = true,value="seqId") String seqId) {
		Result result = timesheetService.finishTimesheet(seqId);
		return result;
	}
	
	@PostMapping("/openTimesheet")
	public Result openTimesheet(@RequestParam(required = true,value="seqId") String seqId) {
		Result result = timesheetService.openTimesheet(seqId);
		return result;
	}
	
	@PostMapping("/updateTimesheetById")
	public Result updateTimesheetById(@RequestBody TimesheetInfoDetail timesheetInfoDetail) {
		Result result = timesheetService.updateTimesheetById(timesheetInfoDetail);
		return result;
	}
	
	@PostMapping("/updateTimesheetFinshedStatusById")
	public Result updateTimesheetFinshedStatusById(@RequestBody TimesheetInfoDetail timesheetInfoDetail) {
		Result result = timesheetService.updateTimesheetFinshedStatusById(timesheetInfoDetail);
		return result;
	}
}
