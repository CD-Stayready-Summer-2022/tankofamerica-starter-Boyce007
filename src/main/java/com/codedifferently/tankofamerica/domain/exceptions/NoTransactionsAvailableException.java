package com.codedifferently.tankofamerica.domain.exceptions;

public class NoTransactionsAvailableException extends Exception{
    public NoTransactionsAvailableException(String message) {
        super(message);
    }
}
