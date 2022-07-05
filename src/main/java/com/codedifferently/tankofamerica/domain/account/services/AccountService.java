package com.codedifferently.tankofamerica.domain.account.services;

import com.codedifferently.tankofamerica.domain.exceptions.AccountNotFoundException;
import com.codedifferently.tankofamerica.domain.exceptions.InvalidDepositException;
import com.codedifferently.tankofamerica.domain.exceptions.UserNotFoundException;
import com.codedifferently.tankofamerica.domain.account.models.Account;

import java.util.UUID;


public interface AccountService {
    Account createAccount(Long User, Account account) throws UserNotFoundException;
    Account getById(UUID id) throws AccountNotFoundException;
    String getAllFromUser(Long userId) throws UserNotFoundException;
    Account update(Account account);
    Boolean delete(UUID id);
    Double deposit(Double money, UUID id) throws AccountNotFoundException, InvalidDepositException;
    Double withdrawal(Double money,UUID id) throws OverdraftException, AccountNotFoundException;
}
