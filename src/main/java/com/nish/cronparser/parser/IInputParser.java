package com.nish.cronparser.parser;

import com.nish.cronparser.bo.CronExpressionBO;

/**
 * 
 * @author Nishanth Mathew Joy
 *
 */
public interface IInputParser {
	/**
	 * 
	 * @param cronExpression
	 * @return
	 */
	public CronExpressionBO parseCronExpressionAndCreateExecutionPattern(String cronExpression);

}
