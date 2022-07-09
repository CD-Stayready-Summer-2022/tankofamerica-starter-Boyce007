package com.codedifferently.tankofamerica.domain.account.services;

import com.codedifferently.tankofamerica.domain.exceptions.AccountNotFoundException;
import com.codedifferently.tankofamerica.domain.exceptions.InvalidDepositException;
import com.codedifferently.tankofamerica.domain.exceptions.OverdraftException;
import com.codedifferently.tankofamerica.domain.exceptions.UserNotFoundException;
import com.codedifferently.tankofamerica.domain.account.models.Account;
import com.codedifferently.tankofamerica.domain.account.repos.AccountRepo;
import com.codedifferently.tankofamerica.domain.transcation.models.Transaction;
import com.codedifferently.tankofamerica.domain.transcation.services.TransactionService;
import com.codedifferently.tankofamerica.domain.user.models.User;
import com.codedifferently.tankofamerica.domain.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.NoTransactionException;

import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImp implements AccountService {
    private AccountRepo accountRepo;
    private UserService userService;
    private TransactionService transactionService;

    @Autowired
    public AccountServiceImp(AccountRepo accountRepo, UserService userService, TransactionService transactionService) {
        this.accountRepo = accountRepo;
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @Override
    public Account createAccount(Long userId, Account account)  {
        User owner = null;
        try {
            owner = userService.getById(userId);
        } catch (UserNotFoundException e) {

        }
        account.setOwner(owner);
        return accountRepo.save(account);
    }

    @Override
    public Account getById(UUID id) throws AccountNotFoundException {
        for (Account account : accountRepo.findAll()) {
            if (account.getId().equals(id)) {
                return account;
            }
        }
        throw new AccountNotFoundException();
    }

    @Override
    public String getAllFromUser(Long userId) throws UserNotFoundException {
        StringBuilder builder = new StringBuilder();
        User owner = userService.getById(userId);
        List<Account> accounts = accountRepo.findByOwner(owner);
        for (Account account : accounts) {
            builder.append(account.toString() + "\n");
        }
        return builder.toString().trim();
    }


    @Override
    public Boolean delete(UUID id) {
        try {
            Account account = getById(id);
            accountRepo.delete(account);
            return true;
        } catch (AccountNotFoundException e) {
            return false;
        }

    }

    @Override
    public Account deposit(Double money, UUID id) throws AccountNotFoundException, InvalidDepositException {
        Account account = getById(id);
        Double newBalance = account.getBalance() + money;
        if (money <= 0) {
            throw new InvalidDepositException();
        }
        account.setBalance(newBalance);
        Transaction transaction = transactionService.create(account, money);
        account.getTransactions().add(transaction);
        return accountRepo.save(account);
    }

    @Override
    public Account withdrawal(Double amount, UUID id) throws OverdraftException, AccountNotFoundException {
        Account account = getById(id);
        Double newBalance = account.getBalance() - amount;
        if (newBalance < 0) {
            throw new OverdraftException();
        }
        account.setBalance(newBalance);
        Transaction transaction = transactionService.create(account,-amount);
        account.getTransactions().add(transaction);
        return accountRepo.save(account);
    }

    public String getAllTransactionsFromAccount(UUID uuid) throws AccountNotFoundException,NoTransactionException {
        StringBuilder builder = new StringBuilder();
        Account account = getById(uuid);
        if (account.getTransactions().size() == 0) {
            throw new NoTransactionException("No transaction available");
        }
        for (Transaction transaction : account.getTransactions()) {
            builder.append(transaction.toString() + "\n");
        }
        return builder.toString().trim();
    }
    public String getLatestTransactions(UUID uuid) throws AccountNotFoundException,NoTransactionException {
        Account account = getById(uuid);
        if (account.getTransactions().size() == 0) {
            throw new NoTransactionException("No transaction available");
        }
        Integer last = account.getTransactions().size()-1;
        Transaction transaction = account.getTransactions().get(last);
        return transaction.toString();
    }


}
