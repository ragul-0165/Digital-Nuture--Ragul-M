import java.util.Scanner;

/**
 * Exercise 12: Recursive Fibonacci Calculator
 * Calculates the nth Fibonacci number using recursion.
 * Demonstrates recursive algorithms and mathematical sequences.
 */
public class RecursiveFibonacci {
    
    // Counter to track recursive calls (for educational purposes)
    private static int recursiveCalls = 0;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Fibonacci Number Calculator (Recursive)");
        System.out.println("======================================");
        
        // Get position from user
        System.out.print("Enter the position (n) to find the nth Fibonacci number: ");
        int n = scanner.nextInt();
        
        // Validate input
        if (n < 0) {
            System.out.println("Error: Position must be non-negative!");
            scanner.close();
            return;
        }
        
        if (n > 40) {
            System.out.println("Warning: Large numbers will take a very long time due to recursive inefficiency!");
            System.out.print("Do you want to continue? (y/n): ");
            scanner.nextLine(); // Clear buffer
            String response = scanner.nextLine().toLowerCase();
            if (!response.equals("y") && !response.equals("yes")) {
                scanner.close();
                return;
            }
        }
        
        // Reset counter and calculate
        recursiveCalls = 0;
        long startTime = System.currentTimeMillis();
        
        long fibonacciNumber = calculateFibonacci(n);
        
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        
        // Display results
        System.out.println("\nResults:");
        System.out.println("========");
        System.out.println("Position (n): " + n);
        System.out.println("Fibonacci number: " + fibonacciNumber);
        System.out.println("Recursive calls made: " + recursiveCalls);
        System.out.println("Execution time: " + executionTime + " ms");
        
        // Show the sequence up to position n (for small n)
        if (n <= 20) {
            System.out.println("\nFibonacci sequence up to position " + n + ":");
            System.out.print("Position: ");
            for (int i = 0; i <= n; i++) {
                System.out.printf("%4d ", i);
            }
            System.out.println();
            System.out.print("Value:    ");
            for (int i = 0; i <= n; i++) {
                // Reset counter for each calculation
                recursiveCalls = 0;
                long value = calculateFibonacci(i);
                System.out.printf("%4d ", value);
            }
            System.out.println();
        }
        
        // Demonstrate iterative approach for comparison
        if (n <= 40) {
            long iterativeStartTime = System.currentTimeMillis();
            long iterativeResult = calculateFibonacciIterative(n);
            long iterativeEndTime = System.currentTimeMillis();
            
            System.out.println("\nComparison with iterative approach:");
            System.out.println("Iterative result: " + iterativeResult);
            System.out.println("Iterative time: " + (iterativeEndTime - iterativeStartTime) + " ms");
            System.out.println("Recursive is " + (executionTime > 0 ? (executionTime / Math.max(1, iterativeEndTime - iterativeStartTime)) : 1) + "x slower");
        }
        
        // Educational notes
        System.out.println("\nFibonacci Sequence Information:");
        System.out.println("• F(0) = 0, F(1) = 1");
        System.out.println("• F(n) = F(n-1) + F(n-2) for n ≥ 2");
        System.out.println("• Recursive approach has exponential time complexity O(2^n)");
        System.out.println("• Each number is the sum of the two preceding numbers");
        
        scanner.close();
    }
    
    /**
     * Calculates the nth Fibonacci number using recursion
     * @param n The position in the Fibonacci sequence
     * @return The nth Fibonacci number
     */
    public static long calculateFibonacci(int n) {
        recursiveCalls++; // Count recursive calls
        
        // Base cases
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        
        // Recursive case: F(n) = F(n-1) + F(n-2)
        return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
    }
    
    /**
     * Calculates the nth Fibonacci number using iterative approach (for comparison)
     * @param n The position in the Fibonacci sequence
     * @return The nth Fibonacci number
     */
    public static long calculateFibonacciIterative(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        
        long prev2 = 0; // F(0)
        long prev1 = 1; // F(1)
        long current = 0;
        
        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        
        return current;
    }
}
