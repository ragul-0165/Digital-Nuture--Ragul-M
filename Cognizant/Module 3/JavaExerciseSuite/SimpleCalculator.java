import java.util.Scanner;

/**
 * Exercise 2: Simple Calculator
 * Performs basic arithmetic operations (+, -, *, /) on two numbers.
 * Demonstrates user input, arithmetic operators, and conditional logic.
 */
public class SimpleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Get first number from user
        System.out.print("Enter the first number: ");
        double num1 = scanner.nextDouble();
        
        // Get second number from user
        System.out.print("Enter the second number: ");
        double num2 = scanner.nextDouble();
        
        // Get operation from user
        System.out.print("Enter operation (+, -, *, /): ");
        char operation = scanner.next().charAt(0);
        
        double result = 0;
        boolean validOperation = true;
        
        // Perform calculation based on operation
        switch (operation) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    System.out.println("Error: Division by zero is not allowed!");
                    validOperation = false;
                }
                break;
            default:
                System.out.println("Error: Invalid operation!");
                validOperation = false;
        }
        
        // Display result if operation was valid
        if (validOperation) {
            System.out.printf("Result: %.2f %c %.2f = %.2f%n", num1, operation, num2, result);
        }
        
        scanner.close();
    }
}
