package com.nish.cronparser.validation;

import java.util.List;

public interface IValidateExpression {
	public boolean validateCRONExpression(String expression);

	public boolean validateMonths(List<String> months);

	public boolean validateMonthsRange(String lowerRange, String upperRange);

	public boolean validateDaysRange(String lowerDay, String upperDay);

	public boolean validateDays(List<String> days);

	public boolean validateSecondsRange(String lowerSec, String upperSec);

	public boolean validateSeconds(List<String> days);

	public boolean validateWeekRange(String lowerWeek, String upperWeek);

	public boolean validateWeeks(List<String> weeks);

	public boolean validateHourRange(String lowerHourk, String upperHour);

	public boolean validateHour(List<String> hours);

	public boolean validateMinuteRange(String lowerHourk, String upperHour);

	public boolean validateMinute(List<String> hours);

}
