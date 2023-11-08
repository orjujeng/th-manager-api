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
public class NewProfileVo {
	String englishName;
	String chineseName;
	String managerName;
	@Pattern (regexp="|Y|N")
	String perm;
	Date joinDate;
	Date expiredDate;
	@Pattern (regexp="|Y|N")
	String authOfBackend;
	List<String> projectCode;
	List<Date> projectStartDate;
	List<Date> projectExpireDate;
}
