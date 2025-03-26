package Ewallet.service;

import Ewallet.model.Account;

public interface AccountService {

    boolean  createAccount(Account account);
    boolean login(Account account);
    boolean deposit(String username, double amount);
    boolean withdraw(String username, double amount);
    double checkBalance(String username);
    boolean transfer(String sourceUsername, String targetUsername, double amount);
}
