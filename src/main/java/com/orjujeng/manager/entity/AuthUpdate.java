package com.orjujeng.manager.entity;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUpdate {
	private String aid;
	private String memberId;
	private String type;
    private String value;
	private String accountNum;
}
