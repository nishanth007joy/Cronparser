package com.nish.cronparser;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Nishanth Mathew Joy
 * 
 *         Application class to execute spring boot command line runner
 *         application to parse CRON expression to time
 *
 */
@Slf4j
@SpringBootApplication
public class CronParserApplication implements CommandLineRunner {
	public void run(String... args) throws Exception {
		log.info("Input expression location is {}", args[0]);

		// locationToTimezoneService.convertLocationToTimezone(args[0]);
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CronParserApplication.class);
		app.run(args);
	}
}
