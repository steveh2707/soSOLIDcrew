package org.kainos.ea.client;

public class GenericValidationException extends Throwable {
    public GenericValidationException(String validationMessage) {
        super(validationMessage);
    }
}
