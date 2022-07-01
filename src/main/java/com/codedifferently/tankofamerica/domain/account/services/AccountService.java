package com.codedifferently.tankofamerica.domain.account.services;

import com.codedifferently.tankofamerica.domain.account.UserNotFoundException;
import com.codedifferently.tankofamerica.domain.account.controllers.models.Account;


public interface AccountService {
    Account createAccount(Long User, Account account) throws UserNotFoundException;
    String getById(String id);
    String getAllFromUser(Long userId) throws UserNotFoundException;
    Account update(Account account);
    Boolean delete(String id);
}
