package Ewallet.service.impl;

import Ewallet.model.Account;
import Ewallet.model.WalletSystem;
import Ewallet.service.TransactionService;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {
    private WalletSystem walletSystem = WalletSystem.getInstance();
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

    @Override
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
}
