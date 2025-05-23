import java.util.Scanner;

/**
 * Exercise 15: Palindrome Checker
 * Checks if a string is a palindrome (reads the same forwards and backwards).
 * Demonstrates string manipulation, case sensitivity, and different checking approaches.
 */
public class PalindromeChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Palindrome Checker");
        System.out.println("==================");
        System.out.println("A palindrome reads the same forwards and backwards.");
        System.out.println("Examples: 'racecar', 'A man a plan a canal Panama'");
        System.out.println();
        
        // Get string from user
        System.out.print("Enter a string to check: ");
        String originalString = scanner.nextLine();
        
        // Validate input
        if (originalString.isEmpty()) {
            System.out.println("Error: Please enter a non-empty string!");
            scanner.close();
            return;
        }
        
        System.out.println("\nOriginal string: \"" + originalString + "\"");
        
        // Check palindrome with different approaches
        
        // 1. Simple case-sensitive check
        boolean isSimplePalindrome = checkSimplePalindrome(originalString);
        
        // 2. Case-insensitive check
        boolean isCaseInsensitivePalindrome = checkCaseInsensitivePalindrome(originalString);
        
        // 3. Ignore spaces and punctuation
        boolean isAdvancedPalindrome = checkAdvancedPalindrome(originalString);
        
        // Display results
        System.out.println("\nPalindrome Check Results:");
        System.out.println("=========================");
        System.out.println("1. Simple check (case-sensitive): " + 
                         (isSimplePalindrome ? "✓ YES" : "✗ NO"));
        System.out.println("2. Case-insensitive check: " + 
                         (isCaseInsensitivePalindrome ? "✓ YES" : "✗ NO"));
        System.out.println("3. Advanced check (ignore spaces/punctuation): " + 
                         (isAdvancedPalindrome ? "✓ YES" : "✗ NO"));
        
        // Show the processed string for advanced check
        String cleanedString = cleanString(originalString);
        System.out.println("\nProcessed string for advanced check: \"" + cleanedString + "\"");
        
        // Show character comparison for the advanced check
        if (originalString.length() <= 30) {
            showCharacterComparison(cleanedString);
        }
        
        // Demonstrate with some famous palindrome examples
        System.out.println("\nFamous Palindrome Examples:");
        System.out.println("===========================");
        String[] examples = {
            "racecar",
            "A man a plan a canal Panama",
            "Was it a car or a cat I saw?",
            "Madam",
            "No 'x' in Nixon",
            "Mr. Owl ate my metal worm"
        };
        
        for (String example : examples) {
            boolean result = checkAdvancedPalindrome(example);
            System.out.println("\"" + example + "\" → " + (result ? "✓ Palindrome" : "✗ Not palindrome"));
        }
        
        scanner.close();
    }
    
    /**
     * Simple palindrome check - case sensitive, includes all characters
     * @param str The string to check
     * @return true if palindrome, false otherwise
     */
    public static boolean checkSimplePalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    /**
     * Case-insensitive palindrome check
     * @param str The string to check
     * @return true if palindrome, false otherwise
     */
    public static boolean checkCaseInsensitivePalindrome(String str) {
        String lowerCase = str.toLowerCase();
        return checkSimplePalindrome(lowerCase);
    }
    
    /**
     * Advanced palindrome check - ignores case, spaces, and punctuation
     * @param str The string to check
     * @return true if palindrome, false otherwise
     */
    public static boolean checkAdvancedPalindrome(String str) {
        String cleaned = cleanString(str);
        return checkSimplePalindrome(cleaned);
    }
    
    /**
     * Cleans a string by removing non-alphanumeric characters and converting to lowercase
     * @param str The string to clean
     * @return The cleaned string
     */
    public static String cleanString(String str) {
        StringBuilder cleaned = new StringBuilder();
        
        for (char c : str.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                cleaned.append(Character.toLowerCase(c));
            }
        }
        
        return cleaned.toString();
    }
    
    /**
     * Shows character-by-character comparison for palindrome checking
     * @param str The string to analyze
     */
    public static void showCharacterComparison(String str) {
        if (str.length() == 0) return;
        
        System.out.println("\nCharacter Comparison:");
        System.out.println("====================");
        System.out.println("Comparing characters from both ends:");
        
        int left = 0;
        int right = str.length() - 1;
        
        while (left <= right) {
            if (left == right) {
                System.out.printf("Middle character: '%c' at position %d%n", str.charAt(left), left);
            } else {
                char leftChar = str.charAt(left);
                char rightChar = str.charAt(right);
                boolean match = leftChar == rightChar;
                System.out.printf("Position %d ('%c') vs Position %d ('%c') → %s%n", 
                                left, leftChar, right, rightChar, 
                                match ? "✓ Match" : "✗ No match");
            }
            left++;
            right--;
        }
    }
}
