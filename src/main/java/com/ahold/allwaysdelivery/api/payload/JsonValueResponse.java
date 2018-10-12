package com.ahold.allwaysdelivery.api.payload;

/**
 * @author Tomasz Gieszczyk
 *
 */
public class JsonValueResponse<T> {
	private T value;

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public static <T> JsonValueResponse<T> build(T value) {
		JsonValueResponse<T> result = new JsonValueResponse<>();
		result.setValue(value);
		return result;
	}
}
