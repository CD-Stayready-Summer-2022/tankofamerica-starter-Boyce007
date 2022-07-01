package com.codedifferently.tankofamerica.domain.account.controllers.models;

import com.codedifferently.tankofamerica.domain.user.models.User;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    private String accountName;

    private Double balance;

    @ManyToOne()
    private User owner;

    public Account() {
    }

    public Account(String accountName, Double balance, User owner) {
        this.owner = owner;
        this.accountName = accountName;
        this.balance = balance;
    }

    public Account(String accountName, Double balance) {
        this.accountName = accountName;
        this.balance = balance;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public UUID getId() {
        return id;
    }

    public String getAccountName() {
        return accountName;
    }


    public Double getBalance() {
        return balance;
    }

    public String toString() {
        return String.format("%d %s %.2f %s ", id, accountName, balance,owner.toString());
    }
}
