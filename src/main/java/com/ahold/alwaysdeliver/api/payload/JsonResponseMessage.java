package com.ahold.alwaysdeliver.api.payload;

import java.io.Serializable;
import java.util.Map;

/**
 * @author tgieszczyk@spyro-soft.com
 *
 */
@SuppressWarnings("serial")
public class JsonResponseMessage implements Serializable {
	private String message;
	private Map<String, Object> additionalProperties;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

}
