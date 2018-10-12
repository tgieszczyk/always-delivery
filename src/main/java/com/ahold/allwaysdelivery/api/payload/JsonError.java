package com.ahold.allwaysdelivery.api.payload;

import com.ahold.allwaysdelivery.utils.GenericBuilder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tgieszczyk@spyro-soft.com
 *
 */
@SuppressWarnings("serial")
public class JsonError implements Serializable {

	private static String UNKNOWN = "UNKNOWN";

	private Map<String, String> errors;

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	public static JsonError errors(Map<String, String> errors) {
		return GenericBuilder.of(JsonError::new).with(JsonError::setErrors, errors).build();
	}

	public static JsonError errors(ErrorCode... codes) {
		Map<String, String> map = new HashMap<>();
		for (ErrorCode code : codes) {
			map.put(code.getCode(), code.getMessage());
		}
		return GenericBuilder.of(JsonError::new).with(JsonError::setErrors, map).build();
	}

	public static JsonError error(String errorCode, String description) {
		HashMap<String, String> map = new HashMap<>();
		map.put(errorCode, description);
		return GenericBuilder.of(JsonError::new).with(JsonError::setErrors, map).build();
	}

	public static JsonError error(ErrorCode error, Object... messageArgs) {
		return error(error.getCode(), String.format(error.getMessage(), messageArgs));
	}

	public static JsonError error(String string) {
		return error(UNKNOWN, string);
	}
}
