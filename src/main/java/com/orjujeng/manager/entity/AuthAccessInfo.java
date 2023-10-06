package com.orjujeng.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthAccessInfo {
	private Integer id;
	private Integer memberId;
	private String 	authAccess;
	private String  profileAccess;
	private String  timesheetAccess;
	private String  requestAccess;
}
