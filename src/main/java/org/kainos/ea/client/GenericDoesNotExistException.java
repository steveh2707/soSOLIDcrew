package org.kainos.ea.client;

public class GenericDoesNotExistException extends Throwable {
    public GenericDoesNotExistException(String validationMessage) {
        super(validationMessage);
    }
}
