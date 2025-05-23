import java.util.Scanner;
import java.util.Random;

/**
 * Exercise 10: Number Guessing Game
 * Interactive game where user guesses a random number between 1-100.
 * Demonstrates loops, conditionals, random number generation, and user interaction.
 */
public class GuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("====================================");
        System.out.println("I'm thinking of a number between 1 and 100.");
        System.out.println("Can you guess what it is?");
        
        // Generate random number between 1 and 100
        int secretNumber = random.nextInt(100) + 1;
        int attempts = 0;
        int maxAttempts = 7;  // Limit number of attempts
        boolean hasGuessedCorrectly = false;
        
        System.out.println("You have " + maxAttempts + " attempts to guess the number.");
        System.out.println();
        
        // Game loop
        while (attempts < maxAttempts && !hasGuessedCorrectly) {
            attempts++;
            System.out.print("Attempt " + attempts + "/" + maxAttempts + " - Enter your guess: ");
            
            // Validate input
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number!");
                scanner.next(); // Clear invalid input
                attempts--; // Don't count invalid input as an attempt
                continue;
            }
            
            int userGuess = scanner.nextInt();
            
            // Validate range
            if (userGuess < 1 || userGuess > 100) {
                System.out.println("Please enter a number between 1 and 100!");
                attempts--; // Don't count invalid input as an attempt
                continue;
            }
            
            // Check guess and provide feedback
            if (userGuess == secretNumber) {
                hasGuessedCorrectly = true;
                System.out.println("🎉 Congratulations! You guessed it!");
                System.out.println("The number was indeed " + secretNumber);
                System.out.println("You found it in " + attempts + " attempts!");
                
                // Performance feedback
                if (attempts == 1) {
                    System.out.println("Amazing! You got it on the first try!");
                } else if (attempts <= 3) {
                    System.out.println("Excellent guessing skills!");
                } else if (attempts <= 5) {
                    System.out.println("Good job!");
                } else {
                    System.out.println("You made it just in time!");
                }
                
            } else if (userGuess < secretNumber) {
                System.out.println("Too low! Try a higher number.");
                
                // Additional hints
                if (secretNumber - userGuess > 20) {
                    System.out.println("Hint: You're quite far from the target.");
                } else if (secretNumber - userGuess > 10) {
                    System.out.println("Hint: You're getting warmer, but still low.");
                } else {
                    System.out.println("Hint: You're very close! Just a bit higher.");
                }
                
            } else {
                System.out.println("Too high! Try a lower number.");
                
                // Additional hints
                if (userGuess - secretNumber > 20) {
                    System.out.println("Hint: You're quite far from the target.");
                } else if (userGuess - secretNumber > 10) {
                    System.out.println("Hint: You're getting warmer, but still high.");
                } else {
                    System.out.println("Hint: You're very close! Just a bit lower.");
                }
            }
            
            // Show remaining attempts
            if (!hasGuessedCorrectly && attempts < maxAttempts) {
                System.out.println("Attempts remaining: " + (maxAttempts - attempts));
                System.out.println();
            }
        }
        
        // Game over message
        if (!hasGuessedCorrectly) {
            System.out.println("\n😞 Game Over! You've used all your attempts.");
            System.out.println("The secret number was: " + secretNumber);
            System.out.println("Better luck next time!");
        }
        
        // Ask if user wants to play again
        System.out.print("\nWould you like to play again? (y/n): ");
        scanner.nextLine(); // Clear the buffer
        String playAgain = scanner.nextLine().toLowerCase();
        
        if (playAgain.equals("y") || playAgain.equals("yes")) {
            System.out.println("Starting a new game...\n");
            main(args); // Restart the game
        } else {
            System.out.println("Thanks for playing! Goodbye!");
        }
        
        scanner.close();
    }
}
