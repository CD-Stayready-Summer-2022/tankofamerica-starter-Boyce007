package com.codedifferently.tankofamerica.domain.transcation.repos;

import com.codedifferently.tankofamerica.domain.account.models.Account;
import com.codedifferently.tankofamerica.domain.transcation.models.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepo extends CrudRepository<Transaction,Long> {
        Optional<Transaction> findById(Long Id);
        List<Transaction> findByAccount(Account account);
}
