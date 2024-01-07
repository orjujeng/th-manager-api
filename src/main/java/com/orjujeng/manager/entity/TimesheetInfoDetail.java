package com.orjujeng.manager.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimesheetInfoDetail {
	private Integer id;
	private Integer seqId;
	private Date startDate;  
	private Date endDate;
	private Date exceptFinishDate;
	private	String bankHolidayDate;
	private Integer memberId;
	private String memberName;
	private String accountNum;
	private String annualLeavingDate;
	private String sickLeavingDate;
	private String workDays;
	private String bankHolidayDays;
	private	String actWorkDays;
	private String annualLeavingDays;
	private String sickLeavingDays;
	private String finished;
}
