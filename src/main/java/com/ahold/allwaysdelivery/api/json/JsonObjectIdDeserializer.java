package com.ahold.allwaysdelivery.api.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.bson.types.ObjectId;

import java.io.IOException;

public class JsonObjectIdDeserializer extends JsonDeserializer<ObjectId> {

	@Override
	public ObjectId deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		String value = parser.getValueAsString();
		try {
			return value != null ? new ObjectId(value) : null;
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

}
