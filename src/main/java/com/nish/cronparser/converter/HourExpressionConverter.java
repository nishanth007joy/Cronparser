package com.nish.cronparser.converter;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.nish.cronparser.exception.ParseException;
import com.nish.cronparser.types.CRONTypes;
import com.nish.cronparser.validation.IValidateExpression;

@Service(value = "hourConverter")
public class HourExpressionConverter implements IConverter {

	@Autowired
	private IValidateExpression validateExpression;

	public Optional<List<String>> convertExpression(String indivudualExpression) {
		if (CharMatcher.anyOf(CRONTypes.ASTERISK.getTypeSymbol()).matchesAllOf(indivudualExpression)) {

			return Optional.of(IntStream.rangeClosed(LocalTime.MIN.getHour(), LocalTime.MAX.getHour())
					.mapToObj(hour -> String.valueOf(hour)).collect(Collectors.toList()));

		} else if (CharMatcher.anyOf(CRONTypes.COMMA.getTypeSymbol()).matchesAnyOf(indivudualExpression)) {
			List<String> hours = Splitter.on(CRONTypes.COMMA.getTypeSymbol()).splitToList(indivudualExpression);
			if (!validateExpression.validateHour(hours)) {
				throw new ParseException(ParseException.ErrorCode.NOT_VALID_EXPRESSION);
			}
			return Optional.of(hours);

		} else if (CharMatcher.anyOf(CRONTypes.HYPHEN.getTypeSymbol()).matchesAnyOf(indivudualExpression)) {

			List<String> range = Splitter.on(CRONTypes.HYPHEN.getTypeSymbol()).splitToList(indivudualExpression);
			if (!validateExpression.validateHourRange(range.get(0), range.get(1))) {
				throw new ParseException(ParseException.ErrorCode.NOT_VALID_EXPRESSION);
			}
			return Optional.of(IntStream.rangeClosed(Integer.parseInt(range.get(0)), Integer.parseInt(range.get(1)))
					.mapToObj(minute -> String.valueOf(minute)).collect(Collectors.toList()));

		} else if (CharMatcher.anyOf(CRONTypes.FORWARD_SLASH.getTypeSymbol()).matchesAnyOf(indivudualExpression)) {

			List<String> range = Splitter.on(CRONTypes.FORWARD_SLASH.getTypeSymbol()).splitToList(indivudualExpression);
			return calculateWithFrequency(range, LocalTime.MAX.getHour());

		} else if (validateExpression.validateHour(Arrays.asList(indivudualExpression))) {
			return Optional.of(Arrays.asList(indivudualExpression));
		} else {

			return Optional.empty();
		}
	}

}
