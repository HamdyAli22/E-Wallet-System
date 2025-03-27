package Ewallet.service.impl;

import Ewallet.model.Account;
import Ewallet.model.WalletSystem;
import Ewallet.service.AccountService;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    private WalletSystem walletSystem =  WalletSystem.getInstance();

    @Override
    public boolean createAccount(Account account) {
        if(findAccountByUsername(account.getUsername()) != null){
            return false;
        }
        walletSystem.getAccounts().add(account);
        return true;
    }

    @Override
    public boolean login(Account account) {
        Account existingAccount = findAccountByUsername(account.getUsername());
        if (existingAccount != null && existingAccount.getPassword().equals(account.getPassword())) {
            return true; // Login successful
        }
        return false;
    }

    @Override
    public boolean deposit(String username, double amount) {
        Account account = findAccountByUsername(username);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            return true;
        }
        return false;
    }

    public boolean withdraw(String username, double amount) {
        Account account = findAccountByUsername(username);
        if (account != null) {
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    @Override
    public double checkBalance(String username) {
        Account acc = findAccountByUsername(username);
        if (acc != null) {
            return acc.getBalance();
        }
        return 0;
    }

    @Override
    public boolean transfer(String sourceUsername, String targetUsername, double amount) {

        Account sourceAccount = findAccountByUsername(sourceUsername);
        Account targetAccount = findAccountByUsername(targetUsername);

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
        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        targetAccount.setBalance(targetAccount.getBalance() + amount);

        System.out.println("Transfer successful: " + amount + " transferred to " + targetUsername);
        return true;
    }

    @Override
    public void showAccountDetails(String username) {
        Account account = findAccountByUsername(username);
        if(account != null){
            System.out.println("===== Account Details =====");
            System.out.println("Username: " + account.getUsername());
            System.out.println("Password: " + account.getPassword());
            System.out.println("Balance: $" + account.getBalance());
        }
    }

    public Account findAccountByUsername(String username) {
        List<Account> accounts = walletSystem.getAccounts();
        for (Account acc : accounts) {
            if (acc.getUsername().equalsIgnoreCase(username)) { // Case-insensitive match
                return acc;
            }
        }
        return null; // Account not found
    }
}
