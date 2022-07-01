package com.codedifferently.tankofamerica.domain.account.repos;

import com.codedifferently.tankofamerica.domain.account.controllers.models.Account;
import com.codedifferently.tankofamerica.domain.user.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepo extends CrudRepository<Account,Long> {
    List<Account> findByOwner(User owner);}
