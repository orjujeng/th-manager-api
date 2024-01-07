package com.orjujeng.manager.entity;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterVo {
	@NotNull
	private String username;
	@NotNull
	private String password;
	private String requestReason;
}
