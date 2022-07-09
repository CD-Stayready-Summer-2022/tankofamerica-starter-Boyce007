package com.codedifferently.tankofamerica.domain.account.services;

import com.codedifferently.tankofamerica.domain.exceptions.AccountNotFoundException;
import com.codedifferently.tankofamerica.domain.exceptions.InvalidDepositException;
import com.codedifferently.tankofamerica.domain.exceptions.OverdraftException;
import com.codedifferently.tankofamerica.domain.exceptions.UserNotFoundException;
import com.codedifferently.tankofamerica.domain.account.models.Account;
import org.springframework.transaction.NoTransactionException;

import java.util.UUID;


public interface AccountService {
    Account createAccount(Long User, Account account) throws UserNotFoundException;

    Account getById(UUID id) throws AccountNotFoundException;

    String getAllFromUser(Long userId) throws UserNotFoundException;

    Boolean delete(UUID id);

    Account deposit(Double money, UUID id) throws AccountNotFoundException, InvalidDepositException;

    Account withdrawal(Double money, UUID id) throws OverdraftException, AccountNotFoundException;

    String getAllTransactionsFromAccount(UUID uuid) throws AccountNotFoundException, NoTransactionException;

    String getLatestTransactions(UUID uuid) throws AccountNotFoundException, NoTransactionException;
}
