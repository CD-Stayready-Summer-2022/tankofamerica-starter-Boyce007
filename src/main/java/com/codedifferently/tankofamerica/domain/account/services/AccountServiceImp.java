package com.codedifferently.tankofamerica.domain.account.services;

import com.codedifferently.tankofamerica.domain.account.UserNotFoundException;
import com.codedifferently.tankofamerica.domain.account.controllers.models.Account;
import com.codedifferently.tankofamerica.domain.account.repos.AccountRepo;
import com.codedifferently.tankofamerica.domain.user.models.User;
import com.codedifferently.tankofamerica.domain.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImp implements AccountService {
    private AccountRepo accountRepo;
    private UserService userService;

    @Autowired
    public AccountServiceImp(AccountRepo accountRepo){
        this.accountRepo = accountRepo;
    }

    @Override
    public Account createAccount(Long userId, Account account) throws UserNotFoundException {
        User owner = userService.getById(userId);
        account.setOwner(owner);
        return accountRepo.save(account);
    }

    @Override
    public String getById(String id) {
        return null;
    }

    @Override
    public String getAllFromUser(Long userId) throws UserNotFoundException {
        StringBuilder builder = new StringBuilder();
        User owner = userService.getById(userId);
        List<Account> accounts = accountRepo.findByOwner(owner);
        for (Account account: accounts) {
            builder.append(account.toString() + "\n");
        }
        return builder.toString().trim();
    }

    @Override
    public Account update(Account account) {
        return null;
    }

    @Override
    public Boolean delete(String id) {
        return null;
    }
}
