package com.orjujeng.manager.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ProfileInfoVo {
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
	private String name;
	private String accountNum;
	private String managerId;
	private String managerName;
	private String Perm;
	private Date joinDate;
	private Date expireDate;
	private String deleteFlag;
	private List<String> projectCode= new ArrayList<>();
	private List<Integer> proportion= new ArrayList<>();
	private List<String> projectName= new ArrayList<>();
	private List<Date> projectMemberExpireDate = new ArrayList<>();
	private List<Date> projectMemberStartDate= new ArrayList<>();
	private List<Date> projectExpireDate= new ArrayList<>();
	
}
