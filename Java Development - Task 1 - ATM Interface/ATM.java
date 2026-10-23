package com.oasis.atm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM {

    private Bank bank;
    private Scanner sc;
    private List<Transaction> transactions;

    public ATM() {
        bank = new Bank();
        sc = new Scanner(System.in);
        transactions = new ArrayList<>();
    }

    public void start() {
        System.out.println("===== ATM INTERFACE =====");
        System.out.print("Enter User ID: ");
        String userId = sc.next();

        Account account = bank.findAccount(userId);

        if (account == null) {
            System.out.println("Account not found!");
            return;
        }

        int attempts = 0;
        boolean loggedIn = false;

        while (attempts < 3) {
            System.out.print("Enter PIN: ");
            String enteredPin = sc.next();

            if (account.checkPin(enteredPin)) {
                loggedIn = true;
                break;
            }

            attempts++;
            System.out.println("Incorrect PIN!");
        }

        if (!loggedIn) {
            System.out.println("Too many attempts. Access denied.");
            return;
        }

        System.out.println("Welcome, " + account.getName() + "!");

        int choice = 0;

        while (choice != 5) {
            System.out.println("\n1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid menu number.");
                sc.next();
                continue;
            }

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Balance: ₹" + account.getBalance());
                    break;

                case 2:
                    deposit(account);
                    break;

                case 3:
                    withdraw(account);
                    break;

                case 4:
                    showHistory();
                    break;

                case 5:
                    System.out.println("Thank you for using the ATM!");
                    break;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private void deposit(Account account) {
        System.out.print("Enter deposit amount: ₹");

        if (!sc.hasNextDouble()) {
            System.out.println("Invalid amount!");
            sc.next();
            return;
        }

        double amount = sc.nextDouble();

        if (account.deposit(amount)) {
            transactions.add(new Transaction(
                    "Deposit", amount, account.getName()));
            System.out.println("Deposit successful!");
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    private void withdraw(Account account) {
        System.out.print("Enter withdrawal amount: ₹");

        if (!sc.hasNextDouble()) {
            System.out.println("Invalid amount!");
            sc.next();
            return;
        }

        double amount = sc.nextDouble();

        if (account.withdraw(amount)) {
            transactions.add(new Transaction(
                    "Withdrawal", amount, account.getName()));
            System.out.println("Withdrawal successful!");
        } else {
            System.out.println("Invalid amount or insufficient balance!");
        }
    }

    private void showHistory() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }

        for (Transaction transaction : transactions) {
            System.out.println(transaction.getDetails());
        }
    }
}
