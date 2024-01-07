package com.orjujeng.manager.utils;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable{
	private String code;
	private String message;
	private T data;
	
	public static <T> Result<T> success(T data){
        return new Result<T>(ResultCode.SUCCESS.code,"Successful",data);
    }
	
	public static <T> Result<T> successWithMsg(T data,String message){
        return new Result<T>(ResultCode.SUCCESS.code,message,data);
    }
	
	public static <T> Result<T> error(String errorCode,String message,T data){
        return new Result<T>(errorCode,message,data);
    }
}
