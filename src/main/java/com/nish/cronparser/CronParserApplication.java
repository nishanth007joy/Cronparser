package com.nish.cronparser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nish.cronparser.facade.ICronParserFacade;

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

	@Autowired
	private ICronParserFacade cronParserFacade;

	@Value("${expression}")
	private String expression;

	public void run(String... args) throws Exception {
		log.info("iNPUT expression is {}", expression);
		cronParserFacade.processCronExpression(expression);
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CronParserApplication.class);
		app.run(args);
	}
}
