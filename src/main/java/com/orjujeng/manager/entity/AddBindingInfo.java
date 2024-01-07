package com.orjujeng.manager.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBindingInfo implements Serializable{
	private Integer id;
	@NotNull
	private Integer memberId;
	@Length(min = 8, max = 8, message = "Account Number Must 8 Digs")
	private String  accountNum;
	@NotNull
	private String projectCode;
	@NotNull
	private Date startDate;
	@NotNull
	private Date expireDate;
//	@Pattern (regexp="^([1-9]|[1-9]\\\\d|100)$",message = "proportion From 1 - 100")
	private Integer proportion;
	private Date lastUpdateDate;
	@NotNull
	private String changedBy;
}
