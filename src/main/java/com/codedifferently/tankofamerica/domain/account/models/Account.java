package com.codedifferently.tankofamerica.domain.account.models;

import com.codedifferently.tankofamerica.domain.transcation.models.Transaction;
import com.codedifferently.tankofamerica.domain.user.models.User;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    private String name;

    private Double balance;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="account")
    private List<Transaction> transactions;

    @ManyToOne()
    private User owner;

    public Account() {
    }

    public Account(String accountName, Double balance, User owner) {
        this.owner = owner;
        this.name = accountName;
        this.balance = balance;
    }

    public Account(String accountName, User owner) {
        this.name = accountName;
        this.balance = 0.0;
        this.owner = owner;
    }

    public Account(String name, Double balance) {
        this.name = name;
        this.balance = balance;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getName() {
        return name;
    }


    public Double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String toString() {
        return String.format("account %s with a balance of  %.2f owned by %s %s with id %s",
                name, balance,owner.getFirstName(),owner.getLastName(),id);
    }
}
