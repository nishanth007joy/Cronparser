package com.nish.cronparser.facade;

import com.nish.cronparser.bo.CronExpressionBO;

/**
 * Facade for processing cron expression
 * 
 * @author Nishanth Mathew Joy
 *
 */
public interface ICronParserFacade {

	/**
	 * 
	 * This calls different services and process input expression
	 * 
	 * @param iputExpression
	 */
	public CronExpressionBO processCronExpression(String iputExpression);
}
