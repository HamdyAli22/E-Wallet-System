package Ewallet.service.impl;

import Ewallet.model.Account;
import Ewallet.model.Transaction;
import Ewallet.model.WalletSystem;
import Ewallet.service.AccountService;

import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl implements AccountService {
    private WalletSystem walletSystem =  WalletSystem.getInstance();
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    @Override
    public boolean createAccount(Account account) {
        if(findAccount(account) != null){
            return false;
        }
        walletSystem.getAccounts().add(account);
        return true;
    }

    @Override
    public boolean login(Account account) {
        Account existingAccount = findAccount(account);
        if (existingAccount != null && existingAccount.getPassword().equals(account.getPassword())) {
            return true; // Login successful
        }
        return false;
    }

    @Override
    public boolean deposit(Account account, double amount) {
        Account acc = findAccount(account);
        if (acc != null) {
            acc.setBalance(acc.getBalance() + amount);
            transactions.add(new Transaction("Deposit", amount, account.getUsername(), null));
            return true;
        }
        return false;
    }

    public boolean withdraw(Account account, double amount) {
        Account acc = findAccount(account);
        if (acc != null) {
            if (acc.getBalance() >= amount) {
                acc.setBalance(acc.getBalance() - amount);
                transactions.add(new Transaction("Withdraw", amount, account.getUsername(), null));
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    @Override
    public double checkBalance(Account account) {
        Account acc = findAccount(account);
        if (acc != null) {
            return acc.getBalance();
        }
        return 0;
    }

    @Override
    public boolean transfer(Account sourceAccount, Account targetAccount, double amount) {

        Account sourceAcc = findAccount(sourceAccount);
        Account targetAcc = findAccount(targetAccount);

        if (sourceAcc == null) {
            System.out.println("Transfer failed: Source account not found.");
            return false;
        }
        if (targetAcc == null) {
            System.out.println("Transfer failed: Target account not found.");
            return false;
        }
        if (sourceAcc.getBalance() < amount) {
            System.out.println("Transfer failed: Insufficient balance.");
            return false;
        }
        sourceAcc.setBalance(sourceAcc.getBalance() - amount);
        targetAcc.setBalance(targetAcc.getBalance() + amount);

        System.out.println("Transfer successful: " + amount + " transferred to " + targetAcc.getUsername());
        transactions.add(new Transaction("Transfer", amount, sourceAcc.getUsername(), targetAcc.getUsername()));
        return true;
    }

    @Override
    public void showAccountDetails(Account account) {
        Account acc = findAccount(account);
        if(acc != null){
            System.out.println("===== Account Details =====");
            System.out.println("Username: " + acc.getUsername());
            System.out.println("Password: " + acc.getPassword());
            System.out.println("Balance: $" + acc.getBalance());
            System.out.println("===== Transaction History =====");
            List<Transaction> history = getTransactionHistory();
            if (history.isEmpty()) {
                System.out.println("No transactions found.");
            } else {
                for (Transaction t : history) {
                    System.out.println(t.getTransactionHistory());
                }
            }
        }
    }

    public Account findAccount(Account account) {
        List<Account> accounts = walletSystem.getAccounts();
        for (Account acc : accounts) {
            if (acc.getUsername().equalsIgnoreCase(account.getUsername())) { // Case-insensitive match
                return acc;
            }
        }
        return null; // Account not found
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
    public List<Transaction> getTransactionHistory() {
        return transactions;
    }
}
