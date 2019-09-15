package com.nish.cronparser.convertor;

import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Splitter;
import com.nish.cronparser.exception.ParseException;
import com.nish.cronparser.types.CRONTypes;
import com.nish.cronparser.validation.IValidateExpression;

@Service(value = "monthConverter")
public class MonthExpressionConvertor implements IConvertor {

	@Autowired
	private IValidateExpression validateExpression;

	public Optional<List<String>> convertExpression(String indivudualExpression) {
		if (CRONTypes.ASTERISK.getCheckType().test(indivudualExpression)) {

			return calculateForAsterisk(Month.JANUARY.getValue(), Month.DECEMBER.getValue());

		} else if (CRONTypes.COMMA.getCheckType().test(indivudualExpression)) {

			List<String> months = Splitter.on(CRONTypes.COMMA.getTypeSymbol()).splitToList(indivudualExpression);
			if (!validateExpression.validateMonths(months)) {
				throw new ParseException(ParseException.ErrorCode.NOT_VALID_EXPRESSION);
			}
			return Optional.of(months);

		} else if (CRONTypes.HYPHEN.getCheckType().test(indivudualExpression)) {

			List<String> range = Splitter.on(CRONTypes.HYPHEN.getTypeSymbol()).splitToList(indivudualExpression);

			if (!validateExpression.validateMonthsRange(range.get(0), range.get(1))) {
				throw new ParseException(ParseException.ErrorCode.NOT_VALID_EXPRESSION);
			}

			return Optional.of(IntStream.rangeClosed(Integer.parseInt(range.get(0)), Integer.parseInt(range.get(1)))
					.mapToObj(minute -> String.valueOf(minute)).collect(Collectors.toList()));

		} else if (CRONTypes.FORWARD_SLASH.getCheckType().test(indivudualExpression)) {

			List<String> range = Splitter.on(CRONTypes.FORWARD_SLASH.getTypeSymbol()).splitToList(indivudualExpression);
			return calculateWithFrequency(range, Month.DECEMBER.getValue());

		} else if (validateExpression.validateMonths(Arrays.asList(indivudualExpression))) {

			return Optional.of(Arrays.asList(indivudualExpression));

		}
		return Optional.empty();
	}

}
