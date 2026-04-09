package br.com.case_ebanx.domain;

import java.math.BigDecimal;

import lombok.Data;

public class Account {

    private String id;
    private BigDecimal balance;

    public Account(String id) {
        this.id = id;
        this.balance = BigDecimal.ZERO;
    }

    public String getId() { return id; }
    public BigDecimal getBalance() { return balance; }

    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }
}
