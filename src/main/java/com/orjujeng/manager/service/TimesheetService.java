package com.orjujeng.manager.service;

import org.springframework.stereotype.Service;

import com.orjujeng.manager.entity.NewTimesheetVo;
import com.orjujeng.manager.entity.TimesheetInfoDetail;
import com.orjujeng.manager.utils.Result;

@Service
public interface TimesheetService {

	Result newTimesheet(NewTimesheetVo newTimesheetVo);

	Result getDefaultInProgressTimesheetInfo(String seqId, String inProgress);

	Result finishTimesheet(String seqId);

	Result updateTimesheetById(TimesheetInfoDetail timesheetInfoDetail);

	Result updateTimesheetFinshedStatusById(TimesheetInfoDetail timesheetInfoDetail);

	Result openTimesheet(String seqId);

}
