import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Exercise 19: Division with Exception Handling
 * Demonstrates try-catch blocks, exception handling, and different types of exceptions.
 * Shows how to handle runtime errors gracefully.
 */
public class DivisionWithException {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Division Calculator with Exception Handling");
        System.out.println("==========================================");
        
        boolean continueCalculation = true;
        
        while (continueCalculation) {
            try {
                // Get dividend
                System.out.print("Enter the dividend (number to be divided): ");
                double dividend = scanner.nextDouble();
                
                // Get divisor
                System.out.print("Enter the divisor (number to divide by): ");
                double divisor = scanner.nextDouble();
                
                // Perform division with exception handling
                double result = performDivision(dividend, divisor);
                
                // Display result
                System.out.printf("Result: %.2f ÷ %.2f = %.2f%n", dividend, divisor, result);
                
                // Additional calculations
                demonstrateIntegerDivision(dividend, divisor);
                
            } catch (ArithmeticException e) {
                System.out.println("❌ Arithmetic Error: " + e.getMessage());
                System.out.println("   Division by zero is not allowed in mathematics!");
                
            } catch (InputMismatchException e) {
                System.out.println("❌ Input Error: Invalid input format!");
                System.out.println("   Please enter numeric values only.");
                scanner.nextLine(); // Clear the invalid input
                
            } catch (Exception e) {
                System.out.println("❌ Unexpected Error: " + e.getMessage());
                System.out.println("   Something went wrong!");
                
            } finally {
                // This block always executes
                System.out.println("📝 Calculation attempt completed.");
            }
            
            // Ask if user wants to continue
            System.out.print("\nDo you want to perform another calculation? (y/n): ");
            scanner.nextLine(); // Clear buffer
            String response = scanner.nextLine().toLowerCase();
            continueCalculation = response.equals("y") || response.equals("yes");
            System.out.println();
        }
        
        // Demonstrate multiple exception scenarios
        demonstrateVariousExceptions();
        
        scanner.close();
        System.out.println("Thank you for using the Division Calculator!");
    }
    
    /**
     * Performs division with explicit exception throwing
     * @param dividend The number to be divided
     * @param divisor The number to divide by
     * @return The result of division
     * @throws ArithmeticException if divisor is zero
     */
    public static double performDivision(double dividend, double divisor) throws ArithmeticException {
        if (divisor == 0) {
            throw new ArithmeticException("Division by zero is not allowed!");
        }
        
        return dividend / divisor;
    }
    
    /**
     * Demonstrates integer division and its special cases
     * @param dividend The dividend
     * @param divisor The divisor
     */
    public static void demonstrateIntegerDivision(double dividend, double divisor) {
        try {
            System.out.println("\n--- Integer Division Analysis ---");
            
            int intDividend = (int) dividend;
            int intDivisor = (int) divisor;
            
            if (intDivisor == 0) {
                throw new ArithmeticException("Integer division by zero!");
            }
            
            int quotient = intDividend / intDivisor;
            int remainder = intDividend % intDivisor;
            
            System.out.printf("Integer division: %d ÷ %d = %d remainder %d%n", 
                            intDividend, intDivisor, quotient, remainder);
            
            // Check for perfect division
            if (remainder == 0) {
                System.out.println("✅ Perfect division (no remainder)");
            } else {
                System.out.printf("📊 Decimal equivalent: %.2f%n", (double) intDividend / intDivisor);
            }
            
        } catch (ArithmeticException e) {
            System.out.println("❌ Integer Division Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates various exception scenarios for educational purposes
     */
    public static void demonstrateVariousExceptions() {
        System.out.println("\n=== Exception Handling Demonstration ===");
        
        // 1. ArithmeticException
        System.out.println("\n1. Demonstrating ArithmeticException:");
        try {
            int result = 10 / 0; // This will cause ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("   Caught ArithmeticException: " + e.getMessage());
        }
        
        // 2. NumberFormatException
        System.out.println("\n2. Demonstrating NumberFormatException:");
        try {
            String invalidNumber = "abc123";
            int number = Integer.parseInt(invalidNumber);
        } catch (NumberFormatException e) {
            System.out.println("   Caught NumberFormatException: " + e.getMessage());
        }
        
        // 3. ArrayIndexOutOfBoundsException
        System.out.println("\n3. Demonstrating ArrayIndexOutOfBoundsException:");
        try {
            int[] array = {1, 2, 3};
            int value = array[5]; // Index out of bounds
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("   Caught ArrayIndexOutOfBoundsException: " + e.getMessage());
        }
        
        // 4. NullPointerException
        System.out.println("\n4. Demonstrating NullPointerException:");
        try {
            String nullString = null;
            int length = nullString.length(); // Will cause NullPointerException
        } catch (NullPointerException e) {
            System.out.println("   Caught NullPointerException: " + e.getMessage());
        }
        
        // 5. Multiple catch blocks
        System.out.println("\n5. Demonstrating multiple catch blocks:");
        demonstrateMultipleCatchBlocks("invalid");
        demonstrateMultipleCatchBlocks("0");
        demonstrateMultipleCatchBlocks("5");
        
        // 6. Nested try-catch
        System.out.println("\n6. Demonstrating nested try-catch:");
        demonstrateNestedTryCatch();
        
        // 7. Exception propagation
        System.out.println("\n7. Demonstrating exception propagation:");
        try {
            methodThatThrowsException();
        } catch (Exception e) {
            System.out.println("   Caught propagated exception: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates multiple catch blocks
     */
    public static void demonstrateMultipleCatchBlocks(String input) {
        try {
            System.out.println("   Processing input: \"" + input + "\"");
            int number = Integer.parseInt(input);
            int result = 100 / number;
            System.out.println("   Result: 100 / " + number + " = " + result);
            
        } catch (NumberFormatException e) {
            System.out.println("   ❌ NumberFormatException: Invalid number format");
            
        } catch (ArithmeticException e) {
            System.out.println("   ❌ ArithmeticException: " + e.getMessage());
            
        } catch (Exception e) {
            System.out.println("   ❌ General Exception: " + e.getMessage());
            
        } finally {
            System.out.println("   ✅ Processing completed for: \"" + input + "\"");
        }
    }
    
    /**
     * Demonstrates nested try-catch blocks
     */
    public static void demonstrateNestedTryCatch() {
        try {
            System.out.println("   Outer try block");
            
            try {
                System.out.println("   Inner try block");
                String[] array = {"10", "0", "abc"};
                
                for (int i = 0; i < array.length; i++) {
                    try {
                        int number = Integer.parseInt(array[i]);
                        int result = 100 / number;
                        System.out.println("     100 / " + number + " = " + result);
                    } catch (ArithmeticException e) {
                        System.out.println("     Inner catch - Division by zero for: " + array[i]);
                    }
                }
                
            } catch (NumberFormatException e) {
                System.out.println("   Middle catch - Number format error");
            }
            
        } catch (Exception e) {
            System.out.println("   Outer catch - General exception");
        } finally {
            System.out.println("   Outer finally - Cleanup completed");
        }
    }
    
    /**
     * Method that throws an exception to demonstrate propagation
     */
    public static void methodThatThrowsException() throws Exception {
        System.out.println("   Method that throws exception called");
        throw new Exception("This is a propagated exception from methodThatThrowsException()");
    }
}
