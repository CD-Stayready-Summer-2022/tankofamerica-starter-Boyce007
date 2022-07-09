package com.codedifferently.tankofamerica.domain.transcation.services;

import com.codedifferently.tankofamerica.domain.account.models.Account;
import com.codedifferently.tankofamerica.domain.exceptions.AccountNotFoundException;
import com.codedifferently.tankofamerica.domain.exceptions.NoTransactionsAvailableException;
import com.codedifferently.tankofamerica.domain.transcation.models.Transaction;

import java.util.UUID;

public interface TransactionService {

    Transaction create(Account account,Double amount);


}
