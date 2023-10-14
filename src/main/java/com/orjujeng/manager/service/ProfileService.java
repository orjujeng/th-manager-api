package com.orjujeng.manager.service;

import org.springframework.stereotype.Service;

import com.orjujeng.manager.utils.Result;

@Service
public interface ProfileService {

	Result getAllProfilInfo(String accountNum);

}
