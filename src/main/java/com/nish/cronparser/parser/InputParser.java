package com.nish.cronparser.parser;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.google.common.base.Splitter;
import com.nish.cronparser.bo.CronExpressionBO;
import com.nish.cronparser.converter.IConverter;
import com.nish.cronparser.exception.ParseException;

@Service
public class InputParser implements IInputParser {
	@Autowired
	@Qualifier(value = "minuteConvertor")
	private IConverter minuteConverter;

	@Autowired
	@Qualifier(value = "dayOfMonthConverter")
	private IConverter dayOfMonthConverter;

	@Autowired
	@Qualifier(value = "hourConverter")
	private IConverter hourConverter;

	@Autowired
	@Qualifier(value = "dayOfWeekConverter")
	private IConverter dayOfWeekConverter;

	@Autowired
	@Qualifier(value = "monthConverter")
	private IConverter monthConverter;

	public CronExpressionBO parseCronExpressionAndCreateExecutionPattern(String cronExpression) {

		if (StringUtils.isEmpty(cronExpression)) {
			throw new ParseException(ParseException.ErrorCode.NOT_VALID_EXPRESSION);
		}

		List<String> splittedExpression = Splitter.on(" ").trimResults().splitToList(cronExpression);

		if (CollectionUtils.isEmpty(splittedExpression)) {
			throw new ParseException(ParseException.ErrorCode.NOT_VALID_EXPRESSION);
		}
		if (splittedExpression.size() != 6) {
			throw new ParseException(ParseException.ErrorCode.NOT_VALID_EXPRESSION);
		}
		String minuteConverted = minuteConverter.convertExpression(splittedExpression.get(0))
				.orElseThrow(() -> new ParseException(ParseException.ErrorCode.NOT_VALID_EXPRESSION)).stream()
				.collect(Collectors.joining(" "));

		String hourConverted = hourConverter.convertExpression(splittedExpression.get(1))
				.orElseThrow(() -> new ParseException(ParseException.ErrorCode.NOT_VALID_EXPRESSION)).stream()
				.collect(Collectors.joining(" "));

		String dayOfMonthConverted = dayOfMonthConverter.convertExpression(splittedExpression.get(2))
				.orElseThrow(() -> new ParseException(ParseException.ErrorCode.NOT_VALID_EXPRESSION)).stream()
				.collect(Collectors.joining(" "));

		String monthConverted = monthConverter.convertExpression(splittedExpression.get(3))
				.orElseThrow(() -> new ParseException(ParseException.ErrorCode.NOT_VALID_EXPRESSION)).stream()
				.collect(Collectors.joining(" "));

		String dayOfWeekConverted = dayOfWeekConverter.convertExpression(splittedExpression.get(4))
				.orElseThrow(() -> new ParseException(ParseException.ErrorCode.NOT_VALID_EXPRESSION)).stream()
				.collect(Collectors.joining(" "));

		return CronExpressionBO.builder().minutes(minuteConverted).dayOfTheMonth(dayOfMonthConverted)
				.hours(hourConverted).dayOfWeek(dayOfWeekConverted).month(monthConverted)
				.commandToBeExecuted(splittedExpression.get(5)).build();
	}

}
