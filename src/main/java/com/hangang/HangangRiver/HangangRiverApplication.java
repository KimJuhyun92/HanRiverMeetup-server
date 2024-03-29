package com.hangang.HangangRiver;

import com.hangang.HangangRiver.common.web.ExceptionController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HangangRiverApplication {
	private static final Logger logger = LogManager.getLogger(ExceptionController.class);

	public static void main(String[] args) {
		logger.debug("Start");
		SpringApplication.run(HangangRiverApplication.class, args);
	}
}