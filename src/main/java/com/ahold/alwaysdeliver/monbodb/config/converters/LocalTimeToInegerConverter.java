package com.ahold.alwaysdeliver.monbodb.config.converters;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;

/**
 * @author tomasz.gieszczyk@ah.nl
 *
 */
public class LocalTimeToInegerConverter implements Converter<LocalTime, Integer> {

	@Override
	public Integer convert(LocalTime s) {
		return s.getHour() * 60 + s.getMinute();
	}

}
