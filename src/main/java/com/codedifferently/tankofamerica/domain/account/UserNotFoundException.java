package com.codedifferently.tankofamerica.domain.account;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message) {
        super(message);
    }

}
