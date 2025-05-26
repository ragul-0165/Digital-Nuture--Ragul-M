import java.util.Scanner;

/**
 * Exercise 9: Grade Calculator
 * Assigns letter grades based on numerical marks using conditional logic.
 * Demonstrates nested if-else statements and grade classification.
 */
public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Student Grade Calculator");
        System.out.println("========================");
        
        // Get student name
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        
        // Get marks from user
        System.out.print("Enter marks (0-100): ");
        double marks = scanner.nextDouble();
        
        // Validate input
        if (marks < 0 || marks > 100) {
            System.out.println("Error: Marks must be between 0 and 100!");
            scanner.close();
            return;
        }
        
        // Determine grade using if-else ladder
        char grade;
        String description;
        
        if (marks >= 90) {
            grade = 'A';
            description = "Excellent";
        } else if (marks >= 80) {
            grade = 'B';
            description = "Very Good";
        } else if (marks >= 70) {
            grade = 'C';
            description = "Good";
        } else if (marks >= 60) {
            grade = 'D';
            description = "Satisfactory";
        } else if (marks >= 50) {
            grade = 'E';
            description = "Pass";
        } else {
            grade = 'F';
            description = "Fail";
        }
        
        // Display results
        System.out.println("\n--- Grade Report ---");
        System.out.println("Student Name: " + studentName);
        System.out.printf("Marks Obtained: %.1f/100%n", marks);
        System.out.println("Letter Grade: " + grade);
        System.out.println("Performance: " + description);
        
        // Additional feedback
        if (grade == 'F') {
            System.out.println("Recommendation: Need improvement. Consider additional study.");
        } else if (grade == 'E' || grade == 'D') {
            System.out.println("Recommendation: Satisfactory performance. Can do better with more effort.");
        } else if (grade == 'C') {
            System.out.println("Recommendation: Good performance. Keep up the good work!");
        } else if (grade == 'B') {
            System.out.println("Recommendation: Very good performance. Excellent work!");
        } else {
            System.out.println("Recommendation: Outstanding performance. Exceptional work!");
        }
        
        // Display grading scale
        System.out.println("\nGrading Scale:");
        System.out.println("A: 90-100 (Excellent)");
        System.out.println("B: 80-89  (Very Good)");
        System.out.println("C: 70-79  (Good)");
        System.out.println("D: 60-69  (Satisfactory)");
        System.out.println("E: 50-59  (Pass)");
        System.out.println("F: 0-49   (Fail)");
        
        scanner.close();
    }
}
