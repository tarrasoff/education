package com.example.education.concurrency.bankAccount;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentBank {
    private HashMap<String, BankAccount> accounts;
    private Lock bankLock;

    public ConcurrentBank() {
        this.accounts = new HashMap<>();
        this.bankLock = new ReentrantLock();
    }

    public BankAccount createAccount(double initialBalance) {
        String accountNumber = generateAccountNumber();
        BankAccount account = new BankAccount(accountNumber, initialBalance);
        bankLock.lock();
        try {
            accounts.put(accountNumber, account);
        } finally {
            bankLock.unlock();
        }
        return account;
    }

    public boolean transfer(BankAccount fromAccount, BankAccount toAccount, double amount) {
        if (fromAccount.withdraw(amount)) {
            toAccount.deposit(amount);
            return true;
        }
        return false;
    }

    public double getTotalBalance() {
        double totalBalance = 0;
        bankLock.lock();
        try {
            for (BankAccount account : accounts.values()) {
                totalBalance += account.getBalance();
            }
        } finally {
            bankLock.unlock();
        }
        return totalBalance;
    }

    private String generateAccountNumber() {
        return "Account" + (accounts.size() + 1);
    }
}