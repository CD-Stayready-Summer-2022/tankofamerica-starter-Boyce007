package com.codedifferently.tankofamerica.domain.exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message) {
        super(message);
    }

}
