package com.codedifferently.tankofamerica.domain.account.controllers;

import com.codedifferently.tankofamerica.domain.account.UserNotFoundException;
import com.codedifferently.tankofamerica.domain.account.controllers.models.Account;
import com.codedifferently.tankofamerica.domain.account.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @ShellMethod("Create new Account")
    public String createNewAccount (@ShellOption({"-U","--user"}) Long id,
                                    @ShellOption({"-N", "--name"}) String name,
                                    @ShellOption({"-B","--balance"})Double balance){
        try {
            Account account = new Account(name,balance);
            account = accountService.createAccount(id, account);
            return account.toString();
        } catch (UserNotFoundException e) {
            return "The User Id is invalid";
        }
    }
}
