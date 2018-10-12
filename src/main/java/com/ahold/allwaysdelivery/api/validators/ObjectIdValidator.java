package com.ahold.allwaysdelivery.api.validators;

import com.ahold.allwaysdelivery.api.exceptions.ValidationException;
import com.ahold.allwaysdelivery.api.payload.ErrorCode;
import com.ahold.allwaysdelivery.api.payload.JsonError;
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
