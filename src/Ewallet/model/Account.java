package Ewallet.model;

public class Account {
    private  String username;
    private  String password;
    private  boolean active;
    private double balance;
    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.active = true;
    }

    public Account(double balance, String username, String password) {
        this.balance = balance;
        this.username = username;
        this.password = password;
        this.active = true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
