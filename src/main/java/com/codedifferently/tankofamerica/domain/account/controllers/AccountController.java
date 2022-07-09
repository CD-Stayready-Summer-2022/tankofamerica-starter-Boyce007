package com.codedifferently.tankofamerica.domain.account.controllers;

import com.codedifferently.tankofamerica.domain.exceptions.AccountNotFoundException;
import com.codedifferently.tankofamerica.domain.exceptions.InvalidDepositException;
import com.codedifferently.tankofamerica.domain.exceptions.UserNotFoundException;
import com.codedifferently.tankofamerica.domain.account.models.Account;
import com.codedifferently.tankofamerica.domain.account.services.AccountService;
import com.codedifferently.tankofamerica.domain.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.NoTransactionException;

import java.util.UUID;

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

    @ShellMethod("Get all Accounts from user")
    public String getAllAccounts(@ShellOption({"-U","--User"})Long id){
        try {
            String accounts = accountService.getAllFromUser(id);
            return accounts;
        } catch (UserNotFoundException e) {
            return "No user with id " + id + "Exist";
        }
    }

    @ShellMethod("delete account")
    public String deleteAccount(@ShellOption({"-I","--Account"})UUID id) {
        try {
            Account account = accountService.getById(id);
            accountService.delete(id);
            return " You have successfully deleted account " + id;
        } catch (AccountNotFoundException e) {
            return "Account with id " + id +" not found ";
        }
    }
    @ShellMethod("deposit money")
    public String depositMoney(@ShellOption({"-I","Account"})UUID id,
                               @ShellOption({"-M","Amount to add"}) Double money) {
        try {
            Account account = accountService.getById(id);
            accountService.deposit(money,id);
            return String.format("You have deposited $%.2f into your account",money);
        } catch (AccountNotFoundException e) {
            return "Account with id " + id +" not found ";
        } catch (InvalidDepositException e) {
            return "amount " + money + " cannot be deposited into account";
        }

    }
    @ShellMethod("withdrawal money ")
    public String withdrawal(@ShellOption({"-I","Account id"})UUID id,
                             @ShellOption({"-A","Amount"}) Double amount) {
        try {
            accountService.deposit(amount,id);
            return String.format("You withdrew $%.2f from your account",amount);
        } catch (AccountNotFoundException e) {
           return  "Account with id " + id +" not found ";
        } catch (InvalidDepositException e) {
           return " withdrawal not available Account Overdrawn ";
        }

    }

    @ShellMethod("View balance")
    public String viewBalance(@ShellOption({"-I","Account"})UUID id) {
        try {
            Account account = accountService.getById(id);
            return "Your account balance is " + account.getBalance();
        } catch (AccountNotFoundException e) {
            return "Account with id " + id +" not found ";
        }

    }

    @ShellMethod("get all transactions")
    public String getAllTransactions(@ShellOption({"-I","Account id"}) UUID id ) {
        try {
            String transactions = accountService.getAllTransactionsFromAccount(id);
            return transactions;
        } catch (AccountNotFoundException e) {
            return "Account with id " + id +" not found ";
        } catch (NoTransactionException e) {
            return "Account has made no transactions";
        }
    }

    @ShellMethod("get latest transaction")
    public String getLatestTransaction(@ShellOption({"-I","Account id"}) UUID id ) {
        try {
            String transaction = accountService.getLatestTransactions(id);
            return transaction;
        } catch (AccountNotFoundException e) {
            return "Account with id " + id +" not found ";
        } catch (NoTransactionException e) {
            return "Account has made no transactions";
        }
    }


}
