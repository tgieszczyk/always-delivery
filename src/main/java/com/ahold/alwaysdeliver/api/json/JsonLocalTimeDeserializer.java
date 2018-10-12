package com.ahold.alwaysdeliver.api.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author tomasz.gieszczyk@ah.nl
 *
 */
public class JsonLocalTimeDeserializer extends JsonDeserializer<LocalTime> {

	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

	@Override
	public LocalTime deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		String value = parser.getValueAsString();
		if (!StringUtils.isEmpty(value)) {
			return LocalTime.parse(value, dateTimeFormatter);
		}
		return null;
	}

}
