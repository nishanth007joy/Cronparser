package com.nish.cronparser.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nish.cronparser.bo.CronExpressionBO;
import com.nish.cronparser.output.IOutputWriter;
import com.nish.cronparser.parser.IInputParser;

@Service
public class CronParserFacade implements ICronParserFacade {
	@Autowired
	private IInputParser inputParser;

	@Autowired
	private IOutputWriter outputWriter;

	public CronExpressionBO processCronExpression(String iputExpression) {
		CronExpressionBO cronExpressionBO = inputParser.parseCronExpressionAndCreateExecutionPattern(iputExpression);
		outputWriter.printOutput(cronExpressionBO);
		return cronExpressionBO;
	}

}
