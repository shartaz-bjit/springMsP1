package com.feeham.msteacher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsteacherApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsteacherApplication.class, args);
	}

}
