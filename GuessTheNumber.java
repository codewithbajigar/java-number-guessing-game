package projects;

import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    static final int MIN = 1;
    static final int MAX = 100;
    static final int MAX_ATTEMPTS = 7;

    static int totalGames = 0;
    static int totalWins = 0;
    static int totalLosses = 0;
    static int totalGuesses = 0;
    static int bestAttempts = Integer.MAX_VALUE;

    public static void main(String[] args) {

        clearLine();
        printBanner();

        System.out.print("Enter your name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            name = "Player";
        }

        System.out.println();
        System.out.println("Welcome, " + name + "! Let's test your guessing skills.");

        System.out.println("Guess a number from 1 to 100.");
        System.out.println("You have 7 valid attempts in every round.");

        boolean playAgain = true;

        while (playAgain) {

            totalGames++;

            boolean won = playGame();

            if (won) {
                totalWins++;
            } else {
                totalLosses++;
            }

            showScoreboard();

            System.out.print("\nWould you like to play again? (yes/no): ");

            String choice = sc.nextLine().trim();

            if (choice.equalsIgnoreCase("yes")
                    || choice.equalsIgnoreCase("y")) {

                System.out.println();
                System.out.println("Starting a new round... Good luck!");

            } else {
                playAgain = false;
            }
        }

        showFinalReport(name);

        sc.close();
    }

    // Display game banner
    static void printBanner() {

        System.out.println("==========================================");
        System.out.println("       NUMBER GUESSING CHALLENGE");
        System.out.println("==========================================");
        System.out.println("           WELCOME TO CODE_WITH_BAJIGAR ");
        System.out.println("------------------------------------------");
    }

    // Play one round
    static boolean playGame() {

        int number = random.nextInt(MAX - MIN + 1) + MIN;
        int attempts = 0;

        System.out.println("\n--------------- NEW ROUND ---------------");

        while (attempts < MAX_ATTEMPTS) {

            System.out.print("\nAttempt " + (attempts + 1)
                    + "/" + MAX_ATTEMPTS
                    + " | Enter your guess: ");

            String input = sc.nextLine().trim();

            int guess;

            try {
                guess = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Enter a whole number.");
                continue;
            }

            if (guess < MIN || guess > MAX) {
                System.out.println("Please enter a number between 1 and 100.");
                continue;
            }

            attempts++;
            totalGuesses++;

            if (guess == number) {

                System.out.println("\n******************************************");
                System.out.println("             CONGRATULATIONS!");
                System.out.println("              YOU WON!");
                System.out.println("******************************************");

                System.out.println("Correct number : " + number);
                System.out.println("Attempts used  : " + attempts);

                if (attempts < bestAttempts) {
                    bestAttempts = attempts;
                    System.out.println("New personal best! Excellent work!");
                }

                return true;

            } else if (guess > number) {

                System.out.println("Too HIGH! Try a lower number.");

            } else {

                System.out.println("Too LOW! Try a higher number.");
            }

            System.out.println("Attempts remaining: "
                    + (MAX_ATTEMPTS - attempts));
        }

        System.out.println("\nGAME OVER! YOU LOST THIS ROUND.");

        System.out.println("The correct number was: " + number);

        return false;
    }

    // Display statistics after every round
    static void showScoreboard() {

        double winRate = (totalWins * 100.0) / totalGames;

        System.out.println("\n==========================================");
        System.out.println("             YOUR SCOREBOARD");
        System.out.println("==========================================");

        System.out.printf("Total Games   : %d%n", totalGames);
        System.out.println("Total Wins    : " + totalWins);
        System.out.println("Total Losses  : " + totalLosses);

        System.out.printf("Win Rate      : %.2f%%%n", winRate);

        System.out.println("Total Guesses : " + totalGuesses);

        if (bestAttempts == Integer.MAX_VALUE) {
            System.out.println("Best Attempts : No win yet");
        } else {
            System.out.println("Best Attempts : " + bestAttempts);
        }

        System.out.println("------------------------------------------");
    }

    // Display final report
    static void showFinalReport(String name) {

        double winRate = (totalWins * 100.0) / totalGames;

        System.out.println("\n==========================================");
        System.out.println("              FINAL REPORT");
        System.out.println("==========================================");

        System.out.println("Player        : " + name);
        System.out.println("Games Played  : " + totalGames);
        System.out.println("Games Won     : " + totalWins);
        System.out.println("Games Lost    : " + totalLosses);

        System.out.printf("Win Rate      : %.2f%%%n", winRate);

        System.out.println("Total Guesses : " + totalGuesses);

        if (bestAttempts == Integer.MAX_VALUE) {
            System.out.println("Best Attempts : No win yet");
        } else {
            System.out.println("Best Attempts : " + bestAttempts);
        }

        System.out.println();

        if (totalWins > totalLosses) {
            System.out.println("Great job! You won more games than you lost.");
        } else if (totalWins == totalLosses) {
            System.out.println("Well played! Your wins and losses are equal.");
        } else {
            System.out.println("Keep practicing. You can improve your score!");
        }

        System.out.println("\nThank you for playing, " + name + "!");
        System.out.println("See you next time!");
        System.out.println("==========================================");
    }

    static void clearLine() {
        System.out.println();
    }
}
