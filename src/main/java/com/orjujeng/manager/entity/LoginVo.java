package com.orjujeng.manager.entity;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginVo {
	@NotNull
	private String accountNum;
	@NotNull
	private String password;
}
