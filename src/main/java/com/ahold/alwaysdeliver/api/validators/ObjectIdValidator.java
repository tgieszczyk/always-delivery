package com.ahold.alwaysdeliver.api.validators;

import com.ahold.alwaysdeliver.api.exceptions.ValidationException;
import com.ahold.alwaysdeliver.api.payload.ErrorCode;
import com.ahold.alwaysdeliver.api.payload.JsonError;
import org.bson.types.ObjectId;

/**
 * @author tgieszczyk@spyro-soft.com
 *
 */
public final class ObjectIdValidator {

	private ObjectIdValidator() {

	}

	public static ObjectId getValidatedObjectId(String objectId, ErrorCode errorCode, Object... args) {
		if (!ObjectId.isValid(objectId)) {
			throw new ValidationException(JsonError.error(errorCode, args));
		}

		return new ObjectId(objectId);
	}
}
