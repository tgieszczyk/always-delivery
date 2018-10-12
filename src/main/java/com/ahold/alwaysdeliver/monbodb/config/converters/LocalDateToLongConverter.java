package com.ahold.alwaysdeliver.monbodb.config.converters;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

/**
 * @author tomasz.gieszczyk@ah.nl
 *
 */
public class LocalDateToLongConverter implements Converter<LocalDate, Long> {

	@Override
	public Long convert(LocalDate s) {
		return s.toEpochDay();
	}
}
