package com.ahold.alwaysdeliver.monbodb.config.converters;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

/**
 * @author tomasz.gieszczyk@ah.nl
 *
 */
public class LongToLocalDateConverter implements Converter<Long, LocalDate> {

	@Override
	public LocalDate convert(Long s) {
		return LocalDate.ofEpochDay(s);
	}

}
