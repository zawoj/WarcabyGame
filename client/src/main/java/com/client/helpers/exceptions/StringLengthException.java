package com.client.helpers.exceptions;

public class StringLengthException extends Exception {
    public StringLengthException() {
    }

    public StringLengthException(String message) {
        super(message);
    }
}