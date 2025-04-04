package Ewallet.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private String type; // deposit, withdraw, transfer
    private double amount;
    private Date date;
    private String sourceUser;
    private String targetUser; // Only for transfer transactions

    public Transaction(String type, double amount,String sourceUser, String targetUser) {
        this.type = type;
        this.amount = amount;
        this.date = new Date();
        this.sourceUser = sourceUser;
        this.targetUser = targetUser;
    }
    public String getTransactionHistory() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        return "Transaction{" +
                "Type='" + type + '\'' +
                ", Amount=" + amount +
                ", Date=" +  dateFormat.format(date) +
                ", SourceUser='" + sourceUser + '\'' +
                (targetUser != null ? ", TargetUser='" + targetUser + '\'' : "") +
                '}';
    }
}
