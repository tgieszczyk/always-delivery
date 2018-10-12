package com.ahold.allwaysdelivery.api;

import com.ahold.allwaysdelivery.api.exceptions.ValidationException;
import com.ahold.allwaysdelivery.api.payload.JsonError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CommonController {
	protected final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler({ValidationException.class })
    @Order(1)
    public ResponseEntity<Object> handleValidationException(ValidationException ex) {
        try {
            return new ResponseEntity<>(ex.getError(), new HttpHeaders(), ex.getHttpStatus());
        } finally {
            ex.printStackTrace();
        }
    }


    @ExceptionHandler({ org.springframework.validation.BindException.class })
	@Order(2)
	public ResponseEntity<Object> handleValidationException(org.springframework.validation.BindException ex) {
		try {
			Map<String, String> errors = translateBindingResult(ex.getBindingResult());
			return new ResponseEntity<>(JsonError.errors(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		} finally {
			ex.printStackTrace();
		}
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	@Order(3)
	public ResponseEntity<Object> handleValidationsException(MethodArgumentNotValidException ex) {
		try {
			Map<String, String> errors = translateBindingResult(ex.getBindingResult());
			return new ResponseEntity<>(JsonError.errors(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		} finally {
			ex.printStackTrace();
		}
	}

	@ExceptionHandler({ Exception.class })
	@Order(5)
	public ResponseEntity<Object> handleAllOtherExceptions(Exception ex) {
		try {
			JsonError error = JsonError.error(ex.getMessage());
			return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			ex.printStackTrace();
		}
	}

	private Map<String, String> translateBindingResult(BindingResult bindingResult) {
		List<ObjectError> allErrors = bindingResult.getAllErrors();
		Map<String, String> errors = new HashMap<>();
		for (ObjectError objectError : allErrors) {
			errors.put(objectError.getCode(), objectError.getDefaultMessage());
		}
		return errors;
	}
}
