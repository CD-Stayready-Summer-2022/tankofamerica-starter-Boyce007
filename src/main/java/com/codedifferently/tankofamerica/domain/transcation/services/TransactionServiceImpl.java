package com.codedifferently.tankofamerica.domain.transcation.services;

import com.codedifferently.tankofamerica.domain.account.models.Account;
import com.codedifferently.tankofamerica.domain.account.services.AccountService;
import com.codedifferently.tankofamerica.domain.exceptions.AccountNotFoundException;
import com.codedifferently.tankofamerica.domain.exceptions.NoTransactionsAvailableException;
import com.codedifferently.tankofamerica.domain.transcation.models.Transaction;
import com.codedifferently.tankofamerica.domain.transcation.repos.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepo transactionRepo;


    @Autowired
    public TransactionServiceImpl(TransactionRepo transactionRepo ) {
        this.transactionRepo = transactionRepo;;
    }


    @Override
    public Transaction create(Account account, Double amount) {
        Transaction transaction = new Transaction(amount, account);
        return transactionRepo.save(transaction);
    }






}
