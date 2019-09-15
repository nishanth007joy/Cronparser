package com.nish.cronparser.facade;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.nish.cronparser.basetest.BaseTest;
import com.nish.cronparser.bo.CronExpressionBO;
import com.nish.cronparser.output.IOutputWriter;
import com.nish.cronparser.parser.IInputParser;

public class ICronParserFacadeTest extends BaseTest {

	@InjectMocks
	private ICronParserFacade cronParserFacade = new CronParserFacade();

	@Mock
	private IInputParser inputParser;

	@Mock
	private IOutputWriter outputWriter;

	@Test
	public void testProcessCronExpression() {
		CronExpressionBO cronExpressionBO = CronExpressionBO.builder().build();
		when(inputParser.parseCronExpressionAndCreateExecutionPattern(ArgumentMatchers.anyString()))
				.thenReturn(cronExpressionBO);
		doNothing().when(outputWriter).printOutput(ArgumentMatchers.any(CronExpressionBO.class));
		CronExpressionBO actual = cronParserFacade.processCronExpression("2 3 4 5 quad");
		assertThat(actual).isEqualToComparingFieldByFieldRecursively(cronExpressionBO);
		verify(outputWriter, times(1)).printOutput(ArgumentMatchers.eq(cronExpressionBO));

	}

}
