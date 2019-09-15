package com.nish.cronparser.output;

import org.springframework.stereotype.Service;

import com.nish.cronparser.bo.CronExpressionBO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OutputWriter implements IOutputWriter {

	@Override
	public void printOutput(CronExpressionBO cronExpressionBO) {

		log.info("Minutes {}", cronExpressionBO.getMinutes());
		log.info("Hours {}", cronExpressionBO.getHours());
		log.info("Day Of Month {}", cronExpressionBO.getDayOfTheMonth());
		log.info("Month {}", cronExpressionBO.getMonth());
		log.info("Day of Week {}", cronExpressionBO.getDayOfWeek());
	}

}
