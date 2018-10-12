package com.ahold.allwaysdelivery.api.exceptions;

import com.ahold.allwaysdelivery.api.payload.JsonError;
import org.springframework.http.HttpStatus;

/**
 * @author tgieszczyk@spyro-soft.com
 *
 */
@SuppressWarnings("serial")
public class ValidationException extends RuntimeException {
	private JsonError error;

	public ValidationException(JsonError error) {
		this.error = error;
	}

	public ValidationException(String arg0, JsonError error) {
		super(arg0);
		this.error = error;
	}

	public ValidationException(Throwable cause, JsonError error) {
		super(cause);
		this.error = error;
	}

	public ValidationException(String message, Throwable cause, JsonError error) {
		super(message, cause);
		this.error = error;
	}

	public ValidationException(String message, Throwable cause, boolean enableSuppression,
							   boolean writableStackTrace, JsonError error) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.error = error;
	}

	public HttpStatus getHttpStatus() {
		return HttpStatus.BAD_REQUEST;
	}

	public JsonError getError() {
		return error;
	}

	public void setError(JsonError error) {
		this.error = error;
	}
}
