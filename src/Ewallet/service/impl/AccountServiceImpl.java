package Ewallet.service.impl;

import Ewallet.model.Account;
import Ewallet.model.WalletSystem;
import Ewallet.service.AccountService;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    private WalletSystem walletSystem =  WalletSystem.getInstance();

    @Override
    public boolean createAccount(Account account) {
        List<Account> accounts = walletSystem.getAccounts();
        for (Account acc : accounts) {
            if (acc.getUsername().equals(account.getUsername())) {
                return false;
            }
        }
        walletSystem.getAccounts().add(account);
        return true;
    }

    @Override
    public boolean login(Account account) {
        List<Account> accounts = walletSystem.getAccounts();
        for (Account acc : accounts) {
            if (acc.getUsername().equals(account.getUsername()) && acc.getPassword().equals(account.getPassword())) {
                return true; // Login successful
            }
        }
        return false;
    }

    @Override
    public boolean deposit(String username, double amount) {
        List<Account> accounts = walletSystem.getAccounts();
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                account.setBalance(account.getBalance() + amount);
                return true;
            }
        }
        return false;
    }

    public boolean withdraw(String username, double amount) {
        List<Account> accounts = walletSystem.getAccounts();
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                if (account.getBalance() >= amount) {
                    account.setBalance(account.getBalance() - amount);
                    return true;
                }else{
                    return false;
                }

            }
        }
        return false;
    }

    @Override
    public double checkBalance(String username) {
        for (Account acc : walletSystem.getAccounts()) {
            if (acc.getUsername().equals(username)) {
                return acc.getBalance();
            }
        }
        return 0;
    }

    @Override
    public boolean transfer(String sourceUsername, String targetUsername, double amount) {
        List<Account> accounts = walletSystem.getAccounts();
        Account sourceAccount = null, targetAccount = null;
        for (Account acc : accounts) {
            if (acc.getUsername().equals(sourceUsername)) {
                sourceAccount = acc;
            } else if (acc.getUsername().equals(targetUsername)) {
                targetAccount = acc;
            }
        }
        if (sourceAccount == null) {
            System.out.println("Transfer failed: Source account not found.");
            return false;
        }
        if (targetAccount == null) {
            System.out.println("Transfer failed: Target account not found.");
            return false;
        }
        if (sourceAccount.getBalance() < amount) {
            System.out.println("Transfer failed: Insufficient balance.");
            return false;
        }
        return true;
    }
}
