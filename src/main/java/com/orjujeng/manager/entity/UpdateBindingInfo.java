package com.orjujeng.manager.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBindingInfo implements Serializable{
	private Integer id;
	@NotNull
	private Integer memberId;
	private String  accountNum;
	private List<String> projectCode;
	private List<Date> startDate;
	private List<Date> expireDate;
	private Integer proportion;
	private Date lastUpdateDate;
	private String changedBy;
}
