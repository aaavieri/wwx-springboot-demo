package com.wwx.tob;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.wwx.tob.dao"})
public class WwxTobApplication {

	public static void main(String[] args) {
		SpringApplication.run(WwxTobApplication.class, args);
	}

}
