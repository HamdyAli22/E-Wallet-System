package Ewallet.service;

import Ewallet.model.Account;

public interface AccountService {

    boolean  createAccount(Account account);
    boolean login(Account account);
    boolean deposit(Account account, double amount);
    boolean withdraw(Account account, double amount);
    double checkBalance(Account account);
    boolean transfer(Account sourceAccount, Account targetAccount, double amount);
    void showAccountDetails(Account account);
}
