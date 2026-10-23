package com.oasis.atm;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private List<Account> accounts = new ArrayList<>();

    public Bank() {
        accounts.add(new Account("USER101", "1234", "Prasanna", 5000));
        accounts.add(new Account("USER102", "5678", "Kumar", 8000));
    }

    public Account findAccount(String userId) {
        for (Account account : accounts) {
            if (account.getUserId().equals(userId)) {
                return account;
            }
        }

        return null;
    }
}
