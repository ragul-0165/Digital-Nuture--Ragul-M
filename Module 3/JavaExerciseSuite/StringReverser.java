import java.util.Scanner;

/**
 * Exercise 14: String Reverser
 * Reverses a user-entered string using multiple approaches.
 * Demonstrates string manipulation, loops, and different algorithmic approaches.
 */
public class StringReverser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("String Reverser");
        System.out.println("===============");
        
        // Get string from user
        System.out.print("Enter a string to reverse: ");
        String originalString = scanner.nextLine();
        
        // Validate input
        if (originalString.isEmpty()) {
            System.out.println("Error: Please enter a non-empty string!");
            scanner.close();
            return;
        }
        
        System.out.println("\nOriginal string: \"" + originalString + "\"");
        System.out.println("Length: " + originalString.length() + " characters");
        
        // Method 1: Using StringBuilder (most efficient)
        String reversed1 = reverseUsingStringBuilder(originalString);
        
        // Method 2: Using character array
        String reversed2 = reverseUsingCharArray(originalString);
        
        // Method 3: Using recursive approach
        String reversed3 = reverseUsingRecursion(originalString);
        
        // Method 4: Using for loop (building string)
        String reversed4 = reverseUsingLoop(originalString);
        
        // Display results
        System.out.println("\nReversed strings (all methods should produce the same result):");
        System.out.println("=============================================================");
        System.out.println("Method 1 (StringBuilder): \"" + reversed1 + "\"");
        System.out.println("Method 2 (Character Array): \"" + reversed2 + "\"");
        System.out.println("Method 3 (Recursion): \"" + reversed3 + "\"");
        System.out.println("Method 4 (Loop): \"" + reversed4 + "\"");
        
        // Verify all methods produce the same result
        boolean allMethodsMatch = reversed1.equals(reversed2) && 
                                 reversed2.equals(reversed3) && 
                                 reversed3.equals(reversed4);
        
        System.out.println("\nVerification: " + (allMethodsMatch ? "✓ All methods match!" : "✗ Methods don't match!"));
        
        // Show character-by-character breakdown
        System.out.println("\nCharacter-by-character breakdown:");
        System.out.println("Position:  Original → Reversed");
        for (int i = 0; i < originalString.length(); i++) {
            int reversedIndex = originalString.length() - 1 - i;
            System.out.printf("%8d:  '%c'      → '%c'%n", 
                            i, 
                            originalString.charAt(i), 
                            originalString.charAt(reversedIndex));
        }
        
        // Additional string analysis
        System.out.println("\nString Analysis:");
        System.out.println("================");
        System.out.println("Contains spaces: " + (originalString.contains(" ") ? "Yes" : "No"));
        System.out.println("All uppercase: " + (originalString.equals(originalString.toUpperCase()) ? "Yes" : "No"));
        System.out.println("All lowercase: " + (originalString.equals(originalString.toLowerCase()) ? "Yes" : "No"));
        
        // Check if it's a palindrome
        String cleanString = originalString.replaceAll("\\s+", "").toLowerCase();
        String cleanReversed = reversed1.replaceAll("\\s+", "").toLowerCase();
        boolean isPalindrome = cleanString.equals(cleanReversed);
        System.out.println("Is palindrome: " + (isPalindrome ? "Yes" : "No"));
        
        scanner.close();
    }
    
    /**
     * Reverses string using StringBuilder (most efficient approach)
     * @param str The string to reverse
     * @return The reversed string
     */
    public static String reverseUsingStringBuilder(String str) {
        return new StringBuilder(str).reverse().toString();
    }
    
    /**
     * Reverses string using character array
     * @param str The string to reverse
     * @return The reversed string
     */
    public static String reverseUsingCharArray(String str) {
        char[] charArray = str.toCharArray();
        int left = 0;
        int right = charArray.length - 1;
        
        // Swap characters from both ends moving towards center
        while (left < right) {
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
            left++;
            right--;
        }
        
        return new String(charArray);
    }
    
    /**
     * Reverses string using recursion
     * @param str The string to reverse
     * @return The reversed string
     */
    public static String reverseUsingRecursion(String str) {
        // Base case: empty string or single character
        if (str.length() <= 1) {
            return str;
        }
        
        // Recursive case: last character + reverse of remaining string
        return str.charAt(str.length() - 1) + reverseUsingRecursion(str.substring(0, str.length() - 1));
    }
    
    /**
     * Reverses string using a simple loop
     * @param str The string to reverse
     * @return The reversed string
     */
    public static String reverseUsingLoop(String str) {
        String reversed = "";
        
        // Build reversed string by adding characters from end to beginning
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed += str.charAt(i);
        }
        
        return reversed;
    }
}
