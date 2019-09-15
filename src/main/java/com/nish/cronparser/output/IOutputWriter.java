package com.nish.cronparser.output;

import com.nish.cronparser.bo.CronExpressionBO;

/**
 * 
 * @author Nishanth Mathew Joy
 *
 */
public interface IOutputWriter {
	/**
	 * 
	 * @param cronExpressionBO
	 */
	public void printOutput(CronExpressionBO cronExpressionBO);
}
