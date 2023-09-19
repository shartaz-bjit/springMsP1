package com.feeham.msstudent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsstudentApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsstudentApplication.class, args);
	}
}