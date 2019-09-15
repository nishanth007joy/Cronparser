package com.nish.cronparser.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class IValidateExpressionTest {

	private IValidateExpression validateExpression = new ValidateExpression();

	@Test
	public void testValidateCRONExpression() {
	}

	@Test
	public void testValidateMonthsInCorrect() {
		String[] months = { "2", "44" };
		boolean valResult = validateExpression.validateMonths(Arrays.asList(months));
		assertFalse(valResult);
	}

	@Test
	public void testValidateMonthsRange() {
		boolean valResult = validateExpression.validateMonthsRange("5", "8");
		assertTrue(valResult);

	}

	@Test
	public void testValidateMonthsCorrect() {
		String[] months = { "2", "11" };
		boolean valResult = validateExpression.validateMonths(Arrays.asList(months));
		assertTrue(valResult);
	}

	@Test
	public void testValidateDaysRange() {
		boolean valResult = validateExpression.validateDaysRange("5", "8");
		assertTrue(valResult);
	}

	@Test
	public void testValidateDays() {
		String[] months = { "2", "11" };
		boolean valResult = validateExpression.validateDays(Arrays.asList(months));
		assertTrue(valResult);
	}

	@Test
	public void testValidateSecondsRange() {
		boolean valResult = validateExpression.validateSecondsRange("5", "8");
		assertTrue(valResult);
	}

	@Test
	public void testValidateSeconds() {
		String[] months = { "2", "11" };
		boolean valResult = validateExpression.validateSeconds(Arrays.asList(months));
		assertTrue(valResult);
	}

	@Test
	public void testValidateDaysOfWeekRange() {
		boolean valResult = validateExpression.validateWeekRange("5", "7");
		assertTrue(valResult);
	}

	@Test
	public void testValidateDaysOfWeek() {
		String[] months = { "2", "7" };
		boolean valResult = validateExpression.validateWeeks(Arrays.asList(months));
		assertTrue(valResult);
	}

	@Test
	public void testValidateHourRange() {
		boolean valResult = validateExpression.validateHourRange("5", "8");
		assertTrue(valResult);
	}

	@Test
	public void testValidateHour() {
		String[] months = { "2", "11" };
		boolean valResult = validateExpression.validateHour(Arrays.asList(months));
		assertTrue(valResult);
	}

}
