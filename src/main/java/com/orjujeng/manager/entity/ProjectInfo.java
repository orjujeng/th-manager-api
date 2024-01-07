package com.orjujeng.manager.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectInfo implements Serializable{
	@NotNull
	private Integer id;
	private String projectCode;
	private String projectName;
	private Date expireDate;
	private Date lastUpdatedDate;
}
