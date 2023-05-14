package com.sepfort.letters.exceptions;

public class MessageNotFoundException extends RuntimeException{

    private String message;

    public MessageNotFoundException(String message) {
        super(message);
    }

}
