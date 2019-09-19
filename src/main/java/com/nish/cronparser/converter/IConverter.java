package com.nish.cronparser.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.common.base.CharMatcher;
import com.nish.cronparser.types.CRONTypes;

/**
 * This interface is implemented by different types of converter
 * @author Nishanth Mathew Joy
 *
 */
public interface IConverter {
	/**
	 * 
	 * @param indivudualExpression
	 * @return
	 */
	public Optional<List<String>> convertExpression(String indivudualExpression);

	/**
	 * Calculation for repetition with a frequency
	 * 
	 * @param range
	 * @return
	 */
	public default Optional<List<String>> calculateWithFrequency(List<String> range, int upperRange) {
		int seedValue = 0;
		int frequency = Integer.parseInt(range.get(1));
		if (!CharMatcher.anyOf(CRONTypes.ASTERISK.getTypeSymbol()).matchesAllOf(range.get(0))) {
			seedValue = Integer.parseInt(range.get(0));
		}
		List<String> output = new ArrayList<>();
		IntConsumer addToList = minute -> output.add(String.valueOf(minute));
		IntStream.iterate(seedValue, i -> i + frequency).peek(addToList).allMatch(val -> val < upperRange);

		return Optional.of(output.stream().limit(output.size() - 1).collect(Collectors.toList()));
	}

	/**
	 * This is to calculate values for Asterisk expression in CRON
	 * 
	 * @param lowerLimit
	 * @param upperLimit
	 * @return
	 */
	public default Optional<List<String>> calculateForAsterisk(int lowerLimit, int upperLimit) {
		return Optional.of(IntStream.rangeClosed(lowerLimit, upperLimit).mapToObj(month -> String.valueOf(month))
				.collect(Collectors.toList()));
	}

}
