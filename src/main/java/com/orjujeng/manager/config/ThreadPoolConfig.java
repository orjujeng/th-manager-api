package com.orjujeng.manager.config;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ThreadPoolConfig {
	@Bean
	public ThreadPoolExecutor threadPoolExecutor() {
		return new ThreadPoolExecutor(50,
				200,
				10,
				TimeUnit.SECONDS,
				new LinkedBlockingDeque<Runnable>(100000),
				Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.AbortPolicy());
	}
}
