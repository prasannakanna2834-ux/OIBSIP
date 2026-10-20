package src;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int round = 0;
        int totalAttempts = 0;
        String playAgain = "yes";

        System.out.println("=== NUMBER GUESSING GAME ===");

        while (playAgain.equalsIgnoreCase("yes")) {

            round++;
            int number = random.nextInt(100) + 1;
            int attempts = 0;
            boolean won = false;

            System.out.println("\nRound " + round);
            System.out.println("Guess a number between 1 and 100.");
            System.out.println("You have 7 attempts.");

            while (attempts < 7) {

                System.out.print("Enter your guess: ");

                if (!sc.hasNextInt()) {
                    System.out.println("Please enter a valid number.");
                    sc.next();
                    continue;
                }

                int guess = sc.nextInt();

                if (guess < 1 || guess > 100) {
                    System.out.println("Enter a number between 1 and 100.");
                    continue;
                }

                attempts++;
                totalAttempts++;

                if (guess == number) {
                    System.out.println("Correct! You won!");
                    System.out.println("Attempts used: " + attempts);
                    won = true;
                    break;
                } else if (guess > number) {
                    System.out.println("Too High!");
                } else {
                    System.out.println("Too Low!");
                }

                System.out.println("Attempts remaining: " + (7 - attempts));
            }

            if (!won) {
                System.out.println("You lost!");
                System.out.println("Correct number: " + number);
            }

            System.out.println("Rounds completed: " + round);
            System.out.println("Total valid guesses: " + totalAttempts);

            System.out.print("Play again? (yes/no): ");
            playAgain = sc.next();
        }

        System.out.println("Thank you for playing!");
        sc.close();
    }
}
