package com.orjujeng.manager.entity;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewTimesheetVo {
	private Date startDate;
	private Date endDate;
	private Date exceptFinishedDate;
	private List<Date> bankHolidayList;
	private List<String> excludeName;
}
