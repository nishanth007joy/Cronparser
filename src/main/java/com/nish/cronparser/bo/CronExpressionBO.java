package com.nish.cronparser.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CronExpressionBO {

	private String minutes;

	private String hours;

	private String dayOfTheMonth;

	private String month;

	private String dayOfWeek;

	private String commandToBeExecuted;

}
