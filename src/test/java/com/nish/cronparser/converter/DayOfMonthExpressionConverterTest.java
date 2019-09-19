package com.nish.cronparser.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.nish.cronparser.basetest.BaseTest;
import com.nish.cronparser.converter.DayOfMonthExpressionConverter;
import com.nish.cronparser.converter.IConverter;
import com.nish.cronparser.validation.IValidateExpression;

public class DayOfMonthExpressionConverterTest extends BaseTest{

	@InjectMocks
	private IConverter dayOfMonthExpressionConvertor = new DayOfMonthExpressionConverter();

	@Mock
	private IValidateExpression validateExpression;

	@Test
	public void testConvertExpressionRange() {
		when(validateExpression.validateDaysRange(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
				.thenReturn(true);
		List<String> monthsActual = dayOfMonthExpressionConvertor.convertExpression("2-6").get();
		assertThat(monthsActual).containsExactly("2", "3", "4", "5", "6");
	}

	@Test
	public void testConvertExpressionAll() {
		when(validateExpression.validateDaysRange(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
				.thenReturn(true);
		List<String> monthsActual = dayOfMonthExpressionConvertor.convertExpression("*").get();
		assertThat(monthsActual).containsExactlyElementsOf(
				IntStream.rangeClosed(1, 31).mapToObj(month -> String.valueOf(month)).collect(Collectors.toList()));
	}

	@Test
	public void testConvertExpressionComaSeparated() {
		when(validateExpression.validateDays(ArgumentMatchers.anyList())).thenReturn(true);
		List<String> monthsActual = dayOfMonthExpressionConvertor.convertExpression("2,6").get();
		assertThat(monthsActual).containsExactly("2", "6");

	}

	@Test
	public void testConvertExpressionSingleValue() {
		when(validateExpression.validateDays(ArgumentMatchers.anyList())).thenReturn(true);
		List<String> monthsActual = dayOfMonthExpressionConvertor.convertExpression("2").get();
		assertThat(monthsActual).containsExactly("2");
	}

}
