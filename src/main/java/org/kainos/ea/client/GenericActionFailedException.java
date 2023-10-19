package org.kainos.ea.client;

public class GenericActionFailedException extends Throwable {
    public GenericActionFailedException(String actionItem) {
        super("Failed to: " + actionItem);
    }
}
