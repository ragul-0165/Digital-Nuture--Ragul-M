import java.util.Scanner;

/**
 * Exercise 3: Even/Odd Checker
 * Checks if a given number is even or odd.
 * Demonstrates modulus operator and conditional statements.
 */
public class EvenOddChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Get number from user
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        // Check if number is even or odd using modulus operator
        if (number % 2 == 0) {
            System.out.println(number + " is an even number.");
        } else {
            System.out.println(number + " is an odd number.");
        }
        
        // Additional information about the concept
        System.out.println("Note: A number is even if it's divisible by 2 (remainder = 0)");
        
        scanner.close();
    }
}
