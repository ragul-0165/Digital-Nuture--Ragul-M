import java.util.Scanner;

/**
 * Exercise 4: Leap Year Checker
 * Determines if a given year is a leap year.
 * Demonstrates complex conditional logic with multiple conditions.
 */
public class LeapYearChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Get year from user
        System.out.print("Enter a year: ");
        int year = scanner.nextInt();
        
        boolean isLeapYear = false;
        
        // Leap year logic:
        // 1. Divisible by 4 AND
        // 2. If divisible by 100, then must also be divisible by 400
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    isLeapYear = true;
                }
            } else {
                isLeapYear = true;
            }
        }
        
        // Display result
        if (isLeapYear) {
            System.out.println(year + " is a leap year.");
        } else {
            System.out.println(year + " is not a leap year.");
        }
        
        // Explain the rules
        System.out.println("\nLeap Year Rules:");
        System.out.println("1. Year must be divisible by 4");
        System.out.println("2. If divisible by 100, it must also be divisible by 400");
        
        scanner.close();
    }
}
