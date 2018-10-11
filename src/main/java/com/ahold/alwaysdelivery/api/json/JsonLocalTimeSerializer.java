package com.ahold.alwaysdelivery.api.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author tgieszczyk@spyro-soft.com
 *
 */
public class JsonLocalTimeSerializer extends JsonSerializer<LocalTime> {

	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

	@Override
	public void serialize(LocalTime value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		jgen.writeString(value != null ? dateTimeFormatter.format(value) : null);
	}

}
