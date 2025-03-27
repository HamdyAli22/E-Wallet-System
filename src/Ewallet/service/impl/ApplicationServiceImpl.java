package Ewallet.service.impl;

import Ewallet.model.Account;
import Ewallet.service.AccountService;
import Ewallet.service.ApplicationService;
import Ewallet.service.TransactionService;

import java.util.Scanner;

public class ApplicationServiceImpl implements ApplicationService {
    private  Scanner scanner = new Scanner(System.in);
    private AccountServiceImpl    accountService = new AccountServiceImpl();
    private ValidationServiceImpl validationService = new ValidationServiceImpl();
    @Override
    public void start() {
        System.out.println("................> Hi Sir I hobe to be good <................");
        int counter = 0;
        while (true) {
            System.out.println("Please Enter Your Choice");
            System.out.println("1-Login    2-Create New Account    3-Exit");
            int choice = 0;

            while (true) {
                try{
                    choice = Integer.parseInt(scanner.nextLine());
                }catch (Exception e){
                    System.out.println("Please Enter Valid Number.");
                    scanner = new Scanner(System.in);
                    continue;
                }
                break;
            }

            boolean exit = false;
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    System.out.println("Have a nice day");
                    exit = true;
                    break;

                default:
                    counter++;
                    if (counter != 4){
                        System.out.println("Invalid Choice, Try Again");
                    }

            }

            if (exit) {
                break;
            }
            if(counter == 4) {
                System.out.println("Please try later");
                break;

            }
        }

    }
    private void login(){
        Account account = extractAccount();

        if (account == null) {
            return;
        }

        boolean success = accountService.login(account);
        if (success) {
            System.out.println("Login Successful!");
            userDashboard(account);
        } else{
            System.out.println("Invalid Username or Password. Try again.");
        }
    }
    private void userDashboard(Account account){
        int counter = 0;
        System.out.println("Welcome, " + account.getUsername());
        while(true){
            System.out.println("1-Deposit    2-Withdraw    3-Check Balance    4-Transfer    5-Show Account Details    6-Logout");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    deposit(account.getUsername());
                    break;
                case 2:
                    withdraw(account.getUsername());
                    break;
                    case 3:
                        checkBalance(account.getUsername());
                        break;
                        case 4:
                            transfer(account.getUsername());
                            break;
                            case 5:
                                accountService.showAccountDetails(account.getUsername());
                                break;
                case 6:
                    System.out.println("Logged out successfully!");
                    return;
                default:
                    counter++;
                    if (counter != 4){
                        System.out.println("Invalid Choice, Try Again");
                    }


            }
            if(counter == 4) {
                System.out.println("Please try later");
                break;

            }
        }

    }
    private void createAccount(){

        Account account = extractAccount();
        if (account == null) {
            return;
        }
        boolean accountCreated = accountService.createAccount(account);
        if(accountCreated){
            System.out.println("Account created successfully");
        } else{
            System.out.println("Account already exists");
        }

    }

    private void deposit(String username){

        System.out.println("Please Enter amount to deposit");
        double amount = Double.parseDouble(scanner.nextLine());

        if (accountService.deposit(username, amount)) {
            System.out.println("Deposit Successful. Current Balance:" + accountService.checkBalance(username));
        } else {
            System.out.println("Account Not Found.");
        }

    }

    private void withdraw(String username){
        System.out.println("Please Enter amount to withdraw");
        double amount = Double.parseDouble(scanner.nextLine());
        if (accountService.withdraw(username, amount)) {
            System.out.println("Withdrawal Successful. Current Balance:" + accountService.checkBalance(username));
        }else{
            System.out.println("Insufficient Balance.");
        }
    }
    private void checkBalance(String username) {
        double balance = accountService.checkBalance(username);
        if (balance >= 0) {
            System.out.println("Your current balance is: $" + balance);
        } else {
            System.out.println("Account not found.");
        }
    }

    private void transfer(String sourceUserame) {


        System.out.println("Enter the username of the recipient:");
        String targetUsername = scanner.nextLine();
       double amount = 0;
        if(accountService.findAccountByUsername(targetUsername) != null) {
            System.out.println("Enter the amount to transfer:");
            amount = Double.parseDouble(scanner.nextLine());
        }else{
            System.out.println("Transfer failed: Target account not found.");
            return;
        }
        boolean success = accountService.transfer(sourceUserame, targetUsername, amount);
        if (success) {
            System.out.println("Current Balance: " + accountService.checkBalance(sourceUserame));
        }
    }

    private Account extractAccount(){
        String username;
        System.out.println("Please Enter username");
        while (true){
            username = scanner.nextLine();

            if (!validationService.validateUsername(username)) {
                continue;
            }
            break;
        }
        String password;
        System.out.println("Please Enter password");
        while (true){
            password = scanner.nextLine();

            if (!validationService.validatePassword(password)) {
                continue;
            }
            break;
        }

        return new Account(username, password);

    }
}
