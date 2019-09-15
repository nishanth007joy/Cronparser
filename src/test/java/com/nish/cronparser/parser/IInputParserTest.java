package com.nish.cronparser.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.nish.cronparser.basetest.BaseTest;
import com.nish.cronparser.bo.CronExpressionBO;
import com.nish.cronparser.convertor.IConvertor;

public class IInputParserTest extends BaseTest {

	@InjectMocks
	private IInputParser inputParse = new InputParser();

	@Mock
	private IConvertor minuteConverter;

	@Mock
	private IConvertor dayOfMonthConverter;

	@Mock
	private IConvertor hourConverter;

	@Mock
	private IConvertor dayOfWeekConverter;

	@Mock
	private IConvertor monthConverter;

	@Test
	public void testParseCronExpressionAndCreateExecutionPattern() {
		when(minuteConverter.convertExpression(ArgumentMatchers.anyString()))
				.thenReturn(Optional.of(Arrays.asList("2")));

		when(minuteConverter.convertExpression(ArgumentMatchers.anyString()))
				.thenReturn(Optional.of(Arrays.asList("2")));

		when(hourConverter.convertExpression(ArgumentMatchers.anyString())).thenReturn(Optional.of(Arrays.asList("3")));

		when(dayOfMonthConverter.convertExpression(ArgumentMatchers.anyString()))
				.thenReturn(Optional.of(Arrays.asList("4")));

		when(monthConverter.convertExpression(ArgumentMatchers.anyString()))
				.thenReturn(Optional.of(Arrays.asList("5")));

		when(dayOfWeekConverter.convertExpression(ArgumentMatchers.anyString()))
				.thenReturn(Optional.of(Arrays.asList("6")));

		CronExpressionBO cronExpressionBO = inputParse.parseCronExpressionAndCreateExecutionPattern("2 3 4 5 6 quad");

		CronExpressionBO expected = CronExpressionBO.builder().commandToBeExecuted("quad").minutes("2").hours("3")
				.dayOfTheMonth("4").month("5").dayOfWeek("6").build();
		assertThat(cronExpressionBO).isEqualToComparingFieldByFieldRecursively(expected);

	}

}
