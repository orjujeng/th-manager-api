package com.orjujeng.manager.fegin;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.orjujeng.manager.entity.NewTimesheetDTO;
import com.orjujeng.manager.entity.TimesheetInfoDetail;
import com.orjujeng.manager.utils.Result;



@FeignClient("TH-TimesheetApi")
public interface TimesheetFeginService {
	@PostMapping("/timesheet/newTimesheet")
	public Result newTimesheet(@RequestBody List<NewTimesheetDTO> newTimesheetDTO);
	@GetMapping("/timesheet/getTimesheetInfo")
	public Result getTimesheetInfo(@RequestParam(required = false,value="seqId") String seqId,@RequestParam(required = true,value="finished") String finished);
	
	@PostMapping("/timesheet/finishTimesheet")
	public Result finishTimesheet(@RequestParam(required = true,value="seqId") String seqId);
	
	@PostMapping("/timesheet/updateTimesheetById")
	public Result updateTimesheetById(@RequestBody TimesheetInfoDetail timesheetInfoDetail);
	
	@PostMapping("/timesheet/updateTimesheetFinshedStatusById")
	public Result updateTimesheetFinshedStatusById(@RequestBody TimesheetInfoDetail timesheetInfoDetail);
	
	@PostMapping("/timesheet/openTimesheetByseqId")
	public Result openTimesheet(@RequestParam(required = true,value="seqId") String seqId);
}
