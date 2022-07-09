package com.codedifferently.tankofamerica.domain.transcation.models;

import com.codedifferently.tankofamerica.domain.account.models.Account;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Double amount;

    @ManyToOne
    private Account account;

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Transaction(Double amount, Account account) {
        this.amount = amount;
        this.account = account;
    }

    public String toString() {
        return String.format("Transaction %d for $%.2f in account %s",id,amount,account.getId().toString());
   }
}
