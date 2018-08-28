package com.hangang.HangangRiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HangangRiverApplication {
	public static void main(String[] args) {
		SpringApplication.run(HangangRiverApplication.class, args);
	}
}
