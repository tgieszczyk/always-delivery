package com.ahold.alwaysdeliver.api.payload;

import com.ahold.alwaysdeliver.utils.GenericBuilder;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author tgieszczyk@spyro-soft.com
 *
 */
@SuppressWarnings("serial")
public class JsonResponse implements Serializable {

	private List<JsonResponseMessage> messages;
	private Map<String, Object> result;

	public List<JsonResponseMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<JsonResponseMessage> messages) {
		this.messages = messages;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public static JsonResponse messages(List<JsonResponseMessage> messages) {
		return GenericBuilder.of(JsonResponse::new).with(JsonResponse::setMessages, messages).build();
	}

	public static JsonResponse success(String message) {
		return GenericBuilder.of(JsonResponse::new)
				.with(JsonResponse::setMessages,
						Arrays.asList(GenericBuilder.of(JsonResponseMessage::new)
								.with(JsonResponseMessage::setMessage, message).build()))
				.build();
	}

	public static JsonResponse success(String message, Map<String, Object> result) {
		return GenericBuilder.of(JsonResponse::new).with(JsonResponse::setResult, result)
				.with(JsonResponse::setMessages,
						Arrays.asList(GenericBuilder.of(JsonResponseMessage::new)
								.with(JsonResponseMessage::setMessage, message).build()))
				.build();
	}

}
