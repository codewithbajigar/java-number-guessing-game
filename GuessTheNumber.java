
package projects;

import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    static final int MIN = 1;
    static final int MAX = 100;
    static final int MAX_ATTEMPTS = 7;

    // Overall game statistics
    static int totalGames = 0;
    static int totalWins = 0;
    static int totalLosses = 0;
    static int totalGuesses = 0;
    static int bestAttempts = Integer.MAX_VALUE;

    // Terminal colors
    static final String RESET = "\u001B[0m";
    static final String GREEN = "\u001B[32m";
    static final String RED = "\u001B[31m";
    static final String YELLOW = "\u001B[33m";
    static final String CYAN = "\u001B[36m";
    static final String BLUE = "\u001B[34m";
    static final String BOLD = "\u001B[1m";

    public static void main(String[] args) {

        clearLine();
        printBanner();

        System.out.print(CYAN + "Enter your name: " + RESET);
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            name = "Player";
        }

        System.out.println();
        System.out.println(GREEN + BOLD
                + "Welcome, " + name + "! Let's test your guessing skills."
                + RESET);

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

            System.out.print("\n" + YELLOW
                    + "Would you like to play again? (yes/no): "
                    + RESET);

            String choice = sc.nextLine().trim();

            if (choice.equalsIgnoreCase("yes")
                    || choice.equalsIgnoreCase("y")) {

                System.out.println();
                System.out.println(CYAN
                        + "Starting a new round... Good luck!"
                        + RESET);

            } else {
                playAgain = false;
            }
        }

        showFinalReport(name);

        sc.close();
    }

    // Display game banner
    static void printBanner() {

        System.out.println(CYAN + BOLD);
        System.out.println("==========================================");
        System.out.println("       NUMBER GUESSING CHALLENGE");
        System.out.println("==========================================");
        System.out.println(RESET);
        System.out.println("             THINK & GUESS!");
        System.out.println("------------------------------------------");
    }

    // Play one round
    static boolean playGame() {

        int number = random.nextInt(MAX - MIN + 1) + MIN;
        int attempts = 0;

        System.out.println("\n" + BLUE + BOLD
                + "--------------- NEW ROUND ---------------"
                + RESET);

        while (attempts < MAX_ATTEMPTS) {

            System.out.print("\n" + CYAN
                    + "Attempt " + (attempts + 1)
                    + "/" + MAX_ATTEMPTS
                    + " | Enter your guess: " + RESET);

            String input = sc.nextLine().trim();

            int guess;

            try {
                guess = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(RED
                        + "Invalid input! Enter a whole number."
                        + RESET);
                continue;
            }

            if (guess < MIN || guess > MAX) {
                System.out.println(RED
                        + "Please enter a number between 1 and 100."
                        + RESET);
                continue;
            }

            attempts++;
            totalGuesses++;

            if (guess == number) {

                System.out.println("\n" + GREEN + BOLD+ "******************************************");
                System.out.println("             CONGRATULATIONS!");
                System.out.println("              YOU WON!");
                System.out.println("******************************************"+ RESET);

                System.out.println(GREEN+ "Correct number : " + number);
                System.out.println("Attempts used  : " + attempts+ RESET);

                if (attempts < bestAttempts) {
                    bestAttempts = attempts;
                    System.out.println(YELLOW+ "New personal best! Excellent work!"+ RESET);
                }

                return true;

            } else if (guess > number) {

                System.out.println(YELLOW+ "Too HIGH! Try a lower number."+ RESET);

            } else {

                System.out.println(YELLOW+ "Too LOW! Try a higher number."+ RESET);
            }

            System.out.println("Attempts remaining: "+ (MAX_ATTEMPTS - attempts));
        }

        System.out.println("\n" + RED + BOLD+ "GAME OVER! YOU LOST THIS ROUND."+ RESET);

        System.out.println("The correct number was: " + number);

        return false;
    }

    // Display statistics after every round
    static void showScoreboard() {

        double winRate = (totalWins * 100.0) / totalGames;

        System.out.println("\n" + CYAN + BOLD);
        System.out.println("==========================================");
        System.out.println("             YOUR SCOREBOARD");
        System.out.println("==========================================");
        System.out.println(RESET);

        System.out.printf("Total Games   : %d%n", totalGames);

        System.out.println(GREEN + "Total Wins    : "+ totalWins + RESET);

        System.out.println(RED + "Total Losses  : "+ totalLosses + RESET);

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

        System.out.println("\n" + CYAN + BOLD);
        System.out.println("==========================================");
        System.out.println("              FINAL REPORT");
        System.out.println("==========================================");
        System.out.println(RESET);

        System.out.println("Player        : " + name);
        System.out.println("Games Played  : " + totalGames);

        System.out.println(GREEN + "Games Won     : "
                + totalWins + RESET);

        System.out.println(RED + "Games Lost    : "
                + totalLosses + RESET);

        System.out.printf("Win Rate      : %.2f%%%n", winRate);
        System.out.println("Total Guesses : " + totalGuesses);

        if (bestAttempts == Integer.MAX_VALUE) {
            System.out.println("Best Attempts : No win yet");
        } else {
            System.out.println("Best Attempts : " + bestAttempts);
        }

        System.out.println();

        if (totalWins > totalLosses) {
            System.out.println(GREEN+ "Great job! You won more games than you lost."+ RESET);
        } else if (totalWins == totalLosses) {
            System.out.println(YELLOW+ "Well played! Your wins and losses are equal."+ RESET);
        } else {
            System.out.println(YELLOW+ "Keep practicing. You can improve your score!"+ RESET);
        }

        System.out.println("\n" + CYAN + BOLD
                + "Thank you for playing, " + name + "!");
        System.out.println("See you next time!");
        System.out.println("=========================================="+ RESET);
    }

    static void clearLine() {
        System.out.println();
    }
}
