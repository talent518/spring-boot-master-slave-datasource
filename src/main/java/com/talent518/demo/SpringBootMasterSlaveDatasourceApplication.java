package com.talent518.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.talent518.demo.mapper")
@SpringBootApplication
public class SpringBootMasterSlaveDatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMasterSlaveDatasourceApplication.class, args);
	}

}
