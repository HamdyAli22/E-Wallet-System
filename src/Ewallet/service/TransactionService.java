package Ewallet.service;

public interface TransactionService {
    boolean deposit(String username, double amount);
    boolean withdraw(String username, double amount);
    double checkBalance(String username);
}
