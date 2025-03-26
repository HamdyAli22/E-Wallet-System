package Ewallet.model;

import java.util.ArrayList;
import java.util.List;

public class WalletSystem {
    private static WalletSystem instance;

    final private String walletName = "Wallet System";

    private List<Account> accounts = new ArrayList<Account>();

    private WalletSystem() {

    }
    public static WalletSystem getInstance() {
        if (instance == null) {
            instance = new WalletSystem();
        }
        return instance;
    }

    public String getWalletName() {
        return walletName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
