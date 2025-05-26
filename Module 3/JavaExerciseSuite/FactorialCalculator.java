import java.util.Scanner;

/**
 * Exercise 11: Factorial Calculator
 * Calculates factorial of a number using both iterative and recursive methods.
 * Demonstrates loops, recursion, and mathematical computations.
 */
public class FactorialCalculator {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Factorial Calculator");
        System.out.println("===================");
        
        // Get number from user
        System.out.print("Enter a non-negative integer: ");
        int number = scanner.nextInt();
        
        // Validate input
        if (number < 0) {
            System.out.println("Error: Factorial is not defined for negative numbers!");
            scanner.close();
            return;
        }
        
        if (number > 20) {
            System.out.println("Warning: Large numbers may cause overflow. Proceeding anyway...");
        }
        
        // Calculate factorial using iterative method
        long iterativeResult = calculateFactorialIterative(number);
        
        // Calculate factorial using recursive method
        long recursiveResult = calculateFactorialRecursive(number);
        
        // Display results
        System.out.println("\nResults:");
        System.out.println("========");
        System.out.println("Number: " + number);
        System.out.println("Factorial (Iterative): " + number + "! = " + iterativeResult);
        System.out.println("Factorial (Recursive): " + number + "! = " + recursiveResult);
        
        // Show the calculation steps for small numbers
        if (number <= 10) {
            System.out.println("\nCalculation steps:");
            System.out.print(number + "! = ");
            for (int i = number; i >= 1; i--) {
                System.out.print(i);
                if (i > 1) {
                    System.out.print(" × ");
                }
            }
            System.out.println(" = " + iterativeResult);
        }
        
        // Show some factorial facts
        System.out.println("\nFactorial Facts:");
        System.out.println("• 0! = 1 (by definition)");
        System.out.println("• 1! = 1");
        System.out.println("• n! = n × (n-1) × (n-2) × ... × 2 × 1");
        System.out.println("• Factorial grows very quickly!");
        
        scanner.close();
    }
    
    /**
     * Calculates factorial using iterative approach (loops)
     * @param n The number to calculate factorial for
     * @return The factorial of n
     */
    public static long calculateFactorialIterative(int n) {
        long result = 1;
        
        // Multiply all numbers from 1 to n
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        
        return result;
    }
    
    /**
     * Calculates factorial using recursive approach
     * @param n The number to calculate factorial for
     * @return The factorial of n
     */
    public static long calculateFactorialRecursive(int n) {
        // Base case: 0! = 1 and 1! = 1
        if (n <= 1) {
            return 1;
        }
        
        // Recursive case: n! = n × (n-1)!
        return n * calculateFactorialRecursive(n - 1);
    }
}
