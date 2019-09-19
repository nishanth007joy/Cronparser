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
import com.nish.cronparser.converter.IConverter;
import com.nish.cronparser.converter.WeekExpressionConverter;
import com.nish.cronparser.validation.IValidateExpression;

public class WeekExpressionConverterTest extends BaseTest {

	@InjectMocks
	private IConverter weekExpressionConvertor = new WeekExpressionConverter();

	@Mock
	private IValidateExpression validateExpression;

	@Test
	public void testConvertExpressionRange() {
		when(validateExpression.validateWeekRange(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
				.thenReturn(true);
		List<String> monthsActual = weekExpressionConvertor.convertExpression("2-6").get();
		assertThat(monthsActual).containsExactly("2", "3", "4", "5", "6");
	}

	@Test
	public void testConvertExpressionAll() {
		when(validateExpression.validateWeekRange(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
				.thenReturn(true);
		List<String> monthsActual = weekExpressionConvertor.convertExpression("*").get();
		assertThat(monthsActual).containsExactlyElementsOf(
				IntStream.rangeClosed(1, 7).mapToObj(month -> String.valueOf(month)).collect(Collectors.toList()));
	}

	@Test
	public void testConvertExpressionComaSeparated() {
		when(validateExpression.validateWeeks(ArgumentMatchers.anyList())).thenReturn(true);
		List<String> monthsActual = weekExpressionConvertor.convertExpression("2,6").get();
		assertThat(monthsActual).containsExactly("2", "6");

	}

	@Test
	public void testConvertExpressionSingleValue() {
		when(validateExpression.validateWeeks(ArgumentMatchers.anyList())).thenReturn(true);
		List<String> monthsActual = weekExpressionConvertor.convertExpression("2").get();
		assertThat(monthsActual).containsExactly("2");
	}

}
