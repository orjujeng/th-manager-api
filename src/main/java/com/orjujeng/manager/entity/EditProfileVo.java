package com.orjujeng.manager.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditProfileVo {
	//Manager Name
			//Perm
			//Join Date
			//Expire Date
			//Project Code
			//Member Project Expire Date
			//Member Project Start Date
			//account num
	String accountNum;
	String managerName;
	@Pattern (regexp="|Y|N")
	String perm;
	Date joinDate;
	Date expiredDate;
	List<String> projectCode;
	List<Date> projectStartDate;
	List<Date> projectExpireDate;
}
