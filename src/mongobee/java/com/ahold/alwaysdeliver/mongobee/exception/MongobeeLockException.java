package com.ahold.alwaysdeliver.mongobee.exception;

/**
 * Error while can not obtain process lock
 */
public class MongobeeLockException extends MongobeeException {
  public MongobeeLockException(String message) {
    super(message);
  }
}
