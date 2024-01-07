package com.orjujeng.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePendingRequestStatusVo {
	private String memberId;
	private String requestId;
	private String status;
	private String type;
}
