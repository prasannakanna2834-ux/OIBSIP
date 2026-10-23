
package com.oasis.atm;

public class Account {

    private String userId;
    private String pin;
    private String name;
    private double balance;

    // Constructor
    public Account(String userId, String pin,
                   String name, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.name = name;
        this.balance = balance;
    }

    // Get user ID
    public String getUserId() {
        return userId;
    }

    // Get account holder name
    public String getName() {
        return name;
    }

    // Get current balance
    public double getBalance() {
        return balance;
    }

    // Verify PIN
    public boolean checkPin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    // Deposit money
    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }

        balance += amount;
        return true;
    }

    // Withdraw money
    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;
        return true;
    }
}
