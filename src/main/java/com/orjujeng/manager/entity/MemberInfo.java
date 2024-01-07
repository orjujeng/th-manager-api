package com.orjujeng.manager.entity;



import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MemberInfo implements Serializable{
	private Integer id;
	private String nameZh;
	private String nameCn;
	@Length(min = 8, max = 8, message = "Account Number Must 8 Digs")
	private String accountNum;
	private String managerId;
	@Pattern (regexp="|Y|N")
	private String perm;
	private Date joinDate;
	private Date expiredDate;
	private Date lastUpdatedDate;
	@Pattern (regexp="|Y|N")
	private String authOfBackend;
	@Pattern (regexp="|Y|N")
	private String deleteFlag;
//	{   
//	    "id":"1",
//	    "nameZh":"1" ,
//	    "nameCn":"1" ,
//	    "accountNum":"1",
//	    "managerId": 1,
//	    "perm":"Y",
//	    "joinDate": null,
//	    "expiredDate": null,
//	    "lastUpdatedDate": null
//	}
}
