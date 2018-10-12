package com.ahold.alwaysdeliver.monbodb.config.converters;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;

/**
 * @author tomasz.gieszczyk@ah.nl
 *
 */
public class InegerToLocalTimeConverter implements Converter<Integer, LocalTime> {

	@Override
	public LocalTime convert(Integer s) {
		int hours = (s > 0) ? s / 60 : 0;
		int minutes = (s > 0) ? s % 60 : 0;
		return LocalTime.of(hours, minutes);
	}

}
