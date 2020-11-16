package com.lxq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms //启用jsm
@MapperScan(value = "com.lxq.mapper")
public class ActivemqBootMysqlRedisMiaoshaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivemqBootMysqlRedisMiaoshaApplication.class, args);
	}

}
