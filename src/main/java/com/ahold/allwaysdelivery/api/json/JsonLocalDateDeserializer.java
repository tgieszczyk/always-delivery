package com.ahold.allwaysdelivery.api.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author tomasz.gieszczyk@ah.nl
 *
 */
public class JsonLocalDateDeserializer extends JsonDeserializer<LocalDate> {

	private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

	@Override
	public LocalDate deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		String value = parser.getValueAsString();
		return deserialize(value);
	}

	public static LocalDate deserialize(String value) throws IOException, JsonProcessingException {
		if (!StringUtils.isEmpty(value)) {
			return LocalDate.parse(value, dateTimeFormatter);
		}
		return null;
	}
}
