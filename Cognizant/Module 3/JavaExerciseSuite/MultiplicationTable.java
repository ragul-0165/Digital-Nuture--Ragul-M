import java.util.Scanner;

/**
 * Exercise 5: Multiplication Table
 * Prints the multiplication table for a given number.
 * Demonstrates loops and formatted output.
 */
public class MultiplicationTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Get number from user
        System.out.print("Enter a number to generate its multiplication table: ");
        int number = scanner.nextInt();
        
        // Get range for multiplication table
        System.out.print("Enter the range (e.g., 10 for 1-10): ");
        int range = scanner.nextInt();
        
        // Print multiplication table header
        System.out.println("\nMultiplication Table for " + number + ":");
        System.out.println("================================");
        
        // Generate and display multiplication table using loop
        for (int i = 1; i <= range; i++) {
            int result = number * i;
            System.out.printf("%d x %d = %d%n", number, i, result);
        }
        
        scanner.close();
    }
}
