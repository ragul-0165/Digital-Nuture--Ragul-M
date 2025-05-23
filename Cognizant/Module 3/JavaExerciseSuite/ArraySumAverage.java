import java.util.Scanner;

/**
 * Exercise 13: Array Sum and Average Calculator
 * Calculates sum and average of array elements with additional statistics.
 * Demonstrates array operations, loops, and statistical calculations.
 */
public class ArraySumAverage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Array Sum and Average Calculator");
        System.out.println("===============================");
        
        // Get array size from user
        System.out.print("Enter the number of elements: ");
        int size = scanner.nextInt();
        
        // Validate size
        if (size <= 0) {
            System.out.println("Error: Array size must be positive!");
            scanner.close();
            return;
        }
        
        // Create array
        double[] numbers = new double[size];
        
        // Get array elements from user
        System.out.println("Enter " + size + " numbers:");
        for (int i = 0; i < size; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            numbers[i] = scanner.nextDouble();
        }
        
        // Display the entered array
        System.out.println("\nEntered array:");
        System.out.print("[ ");
        for (int i = 0; i < numbers.length; i++) {
            System.out.printf("%.2f", numbers[i]);
            if (i < numbers.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" ]");
        
        // Calculate sum
        double sum = calculateSum(numbers);
        
        // Calculate average
        double average = sum / numbers.length;
        
        // Find minimum and maximum values
        double min = findMinimum(numbers);
        double max = findMaximum(numbers);
        
        // Count positive, negative, and zero values
        int positiveCount = 0;
        int negativeCount = 0;
        int zeroCount = 0;
        
        for (double num : numbers) {
            if (num > 0) {
                positiveCount++;
            } else if (num < 0) {
                negativeCount++;
            } else {
                zeroCount++;
            }
        }
        
        // Display results
        System.out.println("\nStatistical Analysis:");
        System.out.println("====================");
        System.out.printf("Sum: %.2f%n", sum);
        System.out.printf("Average: %.2f%n", average);
        System.out.printf("Minimum: %.2f%n", min);
        System.out.printf("Maximum: %.2f%n", max);
        System.out.println("Count: " + numbers.length + " elements");
        
        System.out.println("\nValue Distribution:");
        System.out.println("Positive numbers: " + positiveCount);
        System.out.println("Negative numbers: " + negativeCount);
        System.out.println("Zero values: " + zeroCount);
        
        // Calculate and display range
        double range = max - min;
        System.out.printf("Range: %.2f%n", range);
        
        // Show calculation steps
        System.out.println("\nCalculation Details:");
        System.out.print("Sum calculation: ");
        for (int i = 0; i < numbers.length; i++) {
            System.out.printf("%.2f", numbers[i]);
            if (i < numbers.length - 1) {
                System.out.print(" + ");
            }
        }
        System.out.printf(" = %.2f%n", sum);
        
        System.out.printf("Average calculation: %.2f ÷ %d = %.2f%n", sum, numbers.length, average);
        
        scanner.close();
    }
    
    /**
     * Calculates the sum of all elements in the array
     * @param array The array of numbers
     * @return The sum of all elements
     */
    public static double calculateSum(double[] array) {
        double sum = 0;
        for (double num : array) {
            sum += num;
        }
        return sum;
    }
    
    /**
     * Finds the minimum value in the array
     * @param array The array of numbers
     * @return The minimum value
     */
    public static double findMinimum(double[] array) {
        double min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }
    
    /**
     * Finds the maximum value in the array
     * @param array The array of numbers
     * @return The maximum value
     */
    public static double findMaximum(double[] array) {
        double max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
}
