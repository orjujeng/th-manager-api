package com.orjujeng.manager.entity;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewTimesheetDTO {
	private Date startDate;  
	private Date endDate;
	private Date exceptFinishDate;
	private	String bankHolidayDate;
	private Integer memberId;
	private String memberName;
	private String accountNum;
	private String bankHolidayDays;
	private String workDays;
	private String finished;
	
	private String annualLeavingDate;
	private String sickLeavingDate;
	private	String actWorkDays;
	private String annualLeavingDays;
	private String sickLeavingDays;
}
