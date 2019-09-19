package com.nish.cronparser.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.nish.cronparser.basetest.BaseTest;
import com.nish.cronparser.converter.HourExpressionConverter;
import com.nish.cronparser.converter.IConverter;
import com.nish.cronparser.validation.IValidateExpression;

@RunWith(SpringRunner.class)

public class HourExpressionConverterTest extends BaseTest {

	@InjectMocks
	private IConverter hourExpressionConvertor = new HourExpressionConverter();

	@Mock
	private IValidateExpression validateExpression;

	@Test
	public void testConvertExpressionRange() {
		when(validateExpression.validateHourRange(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
				.thenReturn(true);
		List<String> monthsActual = hourExpressionConvertor.convertExpression("2-6").get();
		assertThat(monthsActual).containsExactly("2", "3", "4", "5", "6");
	}

	@Test
	public void testConvertExpressionAll() {
		when(validateExpression.validateHourRange(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
				.thenReturn(true);
		List<String> monthsActual = hourExpressionConvertor.convertExpression("*").get();
		assertThat(monthsActual).containsExactlyElementsOf(
				IntStream.rangeClosed(0, 23).mapToObj(month -> String.valueOf(month)).collect(Collectors.toList()));
	}

	@Test
	public void testConvertExpressionComaSeparated() {
		when(validateExpression.validateHour(ArgumentMatchers.anyList())).thenReturn(true);
		List<String> monthsActual = hourExpressionConvertor.convertExpression("2,6").get();
		assertThat(monthsActual).containsExactly("2", "6");

	}

	@Test
	public void testConvertExpressionSingleValue() {
		when(validateExpression.validateHour(ArgumentMatchers.anyList())).thenReturn(true);
		List<String> monthsActual = hourExpressionConvertor.convertExpression("2").get();
		assertThat(monthsActual).containsExactly("2");
	}

}
