package com.orjujeng.manager;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
@EnableFeignClients(basePackages = "com.orjujeng.manager.fegin")
@SpringBootApplication
@EnableDiscoveryClient
@EnableRedisHttpSession
public class ThManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThManagerApiApplication.class, args);
	}
}
