package com.nish.cronparser.validation;

import java.time.Month;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;
import org.springframework.util.PatternMatchUtils;

@Service
public class ValidateExpression implements IValidateExpression {

	@Override
	public boolean validateCRONExpression(String expression) {
		return PatternMatchUtils.simpleMatch("", expression);

	}

	@Override
	public boolean validateMonths(List<String> months) {
		return months.stream().map(month -> Integer.parseInt(month))
				.allMatch(month -> month <= Month.DECEMBER.getValue() && month >= Month.JANUARY.getValue());
	}

	@Override
	public boolean validateMonthsRange(String lowerRange, String upperRange) {
		Predicate<Integer> checkValid = month -> month <= Month.DECEMBER.getValue()
				&& month >= Month.JANUARY.getValue();
		return checkValid.test(Integer.parseInt(lowerRange)) && checkValid.test(Integer.parseInt(upperRange));

	}

	@Override
	public boolean validateDaysRange(String lowerDay, String upperDay) {
		Predicate<Integer> checkValid = day -> day <= 31 && day >= 1;
		return checkValid.test(Integer.parseInt(lowerDay)) && checkValid.test(Integer.parseInt(upperDay));
	}

	@Override
	public boolean validateDays(List<String> days) {
		return days.stream().map(day -> Integer.parseInt(day)).allMatch(day -> day <= 31 && day >= 1);
	}

	@Override
	public boolean validateSecondsRange(String lowerSec, String upperSec) {
		Predicate<Integer> checkValid = sec -> sec <= 59 && sec >= 1;
		return checkValid.test(Integer.parseInt(lowerSec)) && checkValid.test(Integer.parseInt(upperSec));
	}

	@Override
	public boolean validateSeconds(List<String> seconds) {
		return seconds.stream().map(second -> Integer.parseInt(second)).allMatch(second -> second <= 59 && second >= 1);
	}

	@Override
	public boolean validateWeekRange(String lowerWeek, String upperWeek) {
		Predicate<Integer> checkValid = week -> week <= 7 && week >= 1;
		return checkValid.test(Integer.parseInt(lowerWeek)) && checkValid.test(Integer.parseInt(upperWeek));
	}

	@Override
	public boolean validateWeeks(List<String> weeks) {
		return weeks.stream().map(second -> Integer.parseInt(second)).allMatch(second -> second <= 7 && second >= 1);

	}

	@Override
	public boolean validateHourRange(String lowerHourk, String upperHour) {
		Predicate<Integer> checkValid = hour -> hour <= 23 && hour >= 1;
		return checkValid.test(Integer.parseInt(lowerHourk)) && checkValid.test(Integer.parseInt(upperHour));
	}

	@Override
	public boolean validateHour(List<String> hours) {
		return hours.stream().map(second -> Integer.parseInt(second)).allMatch(second -> second <= 23 && second >= 1);

	}

	@Override
	public boolean validateMinuteRange(String lowerHourk, String upperHour) {
		Predicate<Integer> checkValid = hour -> hour <= 59 && hour >= 1;
		return checkValid.test(Integer.parseInt(lowerHourk)) && checkValid.test(Integer.parseInt(upperHour));
	}

	@Override
	public boolean validateMinute(List<String> hours) {
		return hours.stream().map(second -> Integer.parseInt(second)).allMatch(second -> second <= 59 && second >= 1);

	}

}
