package org.kainos.ea.client;

public class GenericActionFailedException extends Throwable {
    public GenericActionFailedException(String validationMessage) {
        super(validationMessage);
    }
}
