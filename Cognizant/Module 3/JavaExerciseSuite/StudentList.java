import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Iterator;

/**
 * Exercise 24: Student List using ArrayList
 * Demonstrates ArrayList collection for storing and managing student names.
 * Shows CRUD operations, iteration, sorting, and various list manipulations.
 */
public class StudentList {
    
    // ArrayList to store student names
    private static ArrayList<String> students = new ArrayList<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Student List Management System (ArrayList)");
        System.out.println("==========================================");
        System.out.println("Manage a list of student names using ArrayList");
        System.out.println();
        
        boolean continueProgram = true;
        
        while (continueProgram) {
            displayMenu();
            System.out.print("Enter your choice (1-12): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
            
            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 3:
                    removeStudent(scanner);
                    break;
                case 4:
                    searchStudent(scanner);
                    break;
                case 5:
                    sortStudents();
                    break;
                case 6:
                    updateStudent(scanner);
                    break;
                case 7:
                    addMultipleStudents(scanner);
                    break;
                case 8:
                    displayStatistics();
                    break;
                case 9:
                    clearAllStudents(scanner);
                    break;
                case 10:
                    demonstrateIterators();
                    break;
                case 11:
                    demonstrateAdvancedOperations();
                    break;
                case 12:
                    continueProgram = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice! Please try again.");
            }
            
            if (continueProgram) {
                System.out.println();
            }
        }
        
        scanner.close();
        System.out.println("Thank you for using the Student List Management System!");
    }
    
    /**
     * Display the main menu
     */
    public static void displayMenu() {
        System.out.println("=== Student List Operations ===");
        System.out.println("1.  Add Student");
        System.out.println("2.  Display All Students");
        System.out.println("3.  Remove Student");
        System.out.println("4.  Search Student");
        System.out.println("5.  Sort Students");
        System.out.println("6.  Update Student");
        System.out.println("7.  Add Multiple Students");
        System.out.println("8.  Display Statistics");
        System.out.println("9.  Clear All Students");
        System.out.println("10. Demonstrate Iterators");
        System.out.println("11. Advanced Operations");
        System.out.println("12. Exit");
        System.out.println("===============================");
    }
    
    /**
     * Add a new student to the list
     */
    public static void addStudent(Scanner scanner) {
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine().trim();
        
        if (studentName.isEmpty()) {
            System.out.println("❌ Student name cannot be empty!");
            return;
        }
        
        // Check for duplicates
        if (students.contains(studentName)) {
            System.out.print("⚠️  Student '" + studentName + "' already exists. Add anyway? (y/n): ");
            String response = scanner.nextLine().toLowerCase();
            if (!response.equals("y") && !response.equals("yes")) {
                System.out.println("Operation cancelled.");
                return;
            }
        }
        
        students.add(studentName);
        System.out.println("✅ Student '" + studentName + "' added successfully!");
        System.out.println("📊 Total students: " + students.size());
    }
    
    /**
     * Display all students with index numbers
     */
    public static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("📋 No students in the list.");
            return;
        }
        
        System.out.println("📋 Student List (" + students.size() + " students):");
        System.out.println("=====================================");
        
        for (int i = 0; i < students.size(); i++) {
            System.out.printf("%3d. %s%n", (i + 1), students.get(i));
        }
        
        System.out.println("=====================================");
        System.out.println("Total: " + students.size() + " students");
    }
    
    /**
     * Remove a student from the list
     */
    public static void removeStudent(Scanner scanner) {
        if (students.isEmpty()) {
            System.out.println("📋 No students to remove.");
            return;
        }
        
        System.out.println("Choose removal method:");
        System.out.println("1. Remove by name");
        System.out.println("2. Remove by index");
        System.out.print("Enter choice (1-2): ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        
        switch (choice) {
            case 1:
                removeByName(scanner);
                break;
            case 2:
                removeByIndex(scanner);
                break;
            default:
                System.out.println("❌ Invalid choice!");
        }
    }
    
    /**
     * Remove student by name
     */
    public static void removeByName(Scanner scanner) {
        System.out.print("Enter student name to remove: ");
        String studentName = scanner.nextLine().trim();
        
        if (students.remove(studentName)) {
            System.out.println("✅ Student '" + studentName + "' removed successfully!");
            System.out.println("📊 Remaining students: " + students.size());
        } else {
            System.out.println("❌ Student '" + studentName + "' not found!");
            
            // Suggest similar names
            suggestSimilarNames(studentName);
        }
    }
    
    /**
     * Remove student by index
     */
    public static void removeByIndex(Scanner scanner) {
        displayAllStudents();
        System.out.print("Enter student number to remove (1-" + students.size() + "): ");
        
        int index = scanner.nextInt() - 1; // Convert to 0-based index
        scanner.nextLine(); // Clear buffer
        
        if (index >= 0 && index < students.size()) {
            String removedStudent = students.remove(index);
            System.out.println("✅ Student '" + removedStudent + "' removed successfully!");
            System.out.println("📊 Remaining students: " + students.size());
        } else {
            System.out.println("❌ Invalid student number!");
        }
    }
    
    /**
     * Search for a student in the list
     */
    public static void searchStudent(Scanner scanner) {
        if (students.isEmpty()) {
            System.out.println("📋 No students to search.");
            return;
        }
        
        System.out.print("Enter student name to search: ");
        String searchName = scanner.nextLine().trim();
        
        // Exact match
        int exactIndex = students.indexOf(searchName);
        if (exactIndex != -1) {
            System.out.println("✅ Student '" + searchName + "' found at position " + (exactIndex + 1));
        } else {
            System.out.println("❌ Student '" + searchName + "' not found (exact match)");
            
            // Partial match search
            performPartialSearch(searchName);
        }
    }
    
    /**
     * Perform partial search for similar names
     */
    public static void performPartialSearch(String searchName) {
        System.out.println("\n🔍 Searching for partial matches...");
        
        List<String> partialMatches = new ArrayList<>();
        String lowerSearchName = searchName.toLowerCase();
        
        for (int i = 0; i < students.size(); i++) {
            String studentName = students.get(i);
            if (studentName.toLowerCase().contains(lowerSearchName)) {
                partialMatches.add((i + 1) + ". " + studentName);
            }
        }
        
        if (!partialMatches.isEmpty()) {
            System.out.println("📋 Partial matches found:");
            for (String match : partialMatches) {
                System.out.println("   " + match);
            }
        } else {
            System.out.println("❌ No partial matches found.");
        }
    }
    
    /**
     * Sort students alphabetically
     */
    public static void sortStudents() {
        if (students.isEmpty()) {
            System.out.println("📋 No students to sort.");
            return;
        }
        
        System.out.println("Before sorting:");
        displayAllStudents();
        
        // Sort using Collections.sort()
        Collections.sort(students);
        
        System.out.println("\nAfter sorting (alphabetically):");
        displayAllStudents();
        
        System.out.println("✅ Students sorted successfully!");
    }
    
    /**
     * Update a student's name
     */
    public static void updateStudent(Scanner scanner) {
        if (students.isEmpty()) {
            System.out.println("📋 No students to update.");
            return;
        }
        
        displayAllStudents();
        System.out.print("Enter student number to update (1-" + students.size() + "): ");
        
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // Clear buffer
        
        if (index >= 0 && index < students.size()) {
            String currentName = students.get(index);
            System.out.println("Current name: " + currentName);
            System.out.print("Enter new name: ");
            String newName = scanner.nextLine().trim();
            
            if (newName.isEmpty()) {
                System.out.println("❌ New name cannot be empty!");
                return;
            }
            
            students.set(index, newName);
            System.out.println("✅ Student name updated from '" + currentName + "' to '" + newName + "'");
        } else {
            System.out.println("❌ Invalid student number!");
        }
    }
    
    /**
     * Add multiple students at once
     */
    public static void addMultipleStudents(Scanner scanner) {
        System.out.println("Add multiple students (enter names separated by commas):");
        System.out.print("Student names: ");
        String input = scanner.nextLine();
        
        String[] names = input.split(",");
        int addedCount = 0;
        
        System.out.println("\nAdding students...");
        for (String name : names) {
            String trimmedName = name.trim();
            if (!trimmedName.isEmpty()) {
                students.add(trimmedName);
                System.out.println("  ✅ Added: " + trimmedName);
                addedCount++;
            }
        }
        
        System.out.println("\n📊 Summary:");
        System.out.println("   Students added: " + addedCount);
        System.out.println("   Total students: " + students.size());
    }
    
    /**
     * Display statistics about the student list
     */
    public static void displayStatistics() {
        System.out.println("📊 Student List Statistics:");
        System.out.println("===========================");
        
        if (students.isEmpty()) {
            System.out.println("No students in the list.");
            return;
        }
        
        System.out.println("Total students: " + students.size());
        System.out.println("List capacity: " + students.size()); // ArrayList grows dynamically
        
        // Name length statistics
        int minLength = Integer.MAX_VALUE;
        int maxLength = 0;
        int totalLength = 0;
        String shortest = "";
        String longest = "";
        
        for (String name : students) {
            int length = name.length();
            totalLength += length;
            
            if (length < minLength) {
                minLength = length;
                shortest = name;
            }
            if (length > maxLength) {
                maxLength = length;
                longest = name;
            }
        }
        
        double averageLength = (double) totalLength / students.size();
        
        System.out.println("\nName Length Analysis:");
        System.out.println("   Shortest name: \"" + shortest + "\" (" + minLength + " characters)");
        System.out.println("   Longest name: \"" + longest + "\" (" + maxLength + " characters)");
        System.out.printf("   Average length: %.1f characters%n", averageLength);
        
        // Duplicate analysis
        analyzeDuplicates();
        
        // Alphabetical analysis
        analyzeAlphabeticalOrder();
    }
    
    /**
     * Analyze duplicates in the list
     */
    public static void analyzeDuplicates() {
        System.out.println("\nDuplicate Analysis:");
        
        List<String> duplicates = new ArrayList<>();
        List<String> checked = new ArrayList<>();
        
        for (String name : students) {
            if (checked.contains(name)) {
                continue;
            }
            
            int count = Collections.frequency(students, name);
            if (count > 1) {
                duplicates.add(name + " (" + count + " times)");
            }
            checked.add(name);
        }
        
        if (duplicates.isEmpty()) {
            System.out.println("   No duplicates found.");
        } else {
            System.out.println("   Duplicates found:");
            for (String duplicate : duplicates) {
                System.out.println("   - " + duplicate);
            }
        }
    }
    
    /**
     * Analyze alphabetical order
     */
    public static void analyzeAlphabeticalOrder() {
        System.out.println("\nAlphabetical Order Analysis:");
        
        List<String> sortedList = new ArrayList<>(students);
        Collections.sort(sortedList);
        
        boolean isAlreadySorted = students.equals(sortedList);
        System.out.println("   Currently sorted: " + (isAlreadySorted ? "Yes" : "No"));
        
        if (!isAlreadySorted) {
            System.out.println("   First student (alphabetically): " + sortedList.get(0));
            System.out.println("   Last student (alphabetically): " + sortedList.get(sortedList.size() - 1));
        }
    }
    
    /**
     * Clear all students from the list
     */
    public static void clearAllStudents(Scanner scanner) {
        if (students.isEmpty()) {
            System.out.println("📋 List is already empty.");
            return;
        }
        
        System.out.print("⚠️  Are you sure you want to clear all " + students.size() + " students? (y/n): ");
        String response = scanner.nextLine().toLowerCase();
        
        if (response.equals("y") || response.equals("yes")) {
            students.clear();
            System.out.println("✅ All students cleared!");
        } else {
            System.out.println("Operation cancelled.");
        }
    }
    
    /**
     * Demonstrate different iterator types
     */
    public static void demonstrateIterators() {
        if (students.isEmpty()) {
            System.out.println("📋 No students to iterate through.");
            return;
        }
        
        System.out.println("🔄 Iterator Demonstrations:");
        System.out.println("===========================");
        
        // 1. Enhanced for loop
        System.out.println("1. Enhanced for loop:");
        for (String student : students) {
            System.out.println("   " + student);
        }
        
        // 2. Traditional for loop
        System.out.println("\n2. Traditional for loop:");
        for (int i = 0; i < students.size(); i++) {
            System.out.println("   " + (i + 1) + ". " + students.get(i));
        }
        
        // 3. Iterator
        System.out.println("\n3. Iterator:");
        Iterator<String> iterator = students.iterator();
        int count = 1;
        while (iterator.hasNext()) {
            System.out.println("   " + count + ". " + iterator.next());
            count++;
        }
        
        // 4. Stream API (Java 8+)
        System.out.println("\n4. Stream API:");
        students.stream()
                .forEach(student -> System.out.println("   " + student));
    }
    
    /**
     * Demonstrate advanced ArrayList operations
     */
    public static void demonstrateAdvancedOperations() {
        System.out.println("🚀 Advanced ArrayList Operations:");
        System.out.println("=================================");
        
        if (students.isEmpty()) {
            // Add some sample data for demonstration
            students.add("Alice");
            students.add("Bob");
            students.add("Charlie");
            students.add("Diana");
            students.add("Alice"); // Duplicate for testing
            System.out.println("Added sample data for demonstration.");
        }
        
        // 1. Sublist operations
        demonstrateSublist();
        
        // 2. Bulk operations
        demonstrateBulkOperations();
        
        // 3. Custom sorting
        demonstrateCustomSorting();
        
        // 4. Filtering operations
        demonstrateFiltering();
    }
    
    /**
     * Demonstrate sublist operations
     */
    public static void demonstrateSublist() {
        System.out.println("\n1. Sublist operations:");
        if (students.size() >= 3) {
            List<String> sublist = students.subList(0, Math.min(3, students.size()));
            System.out.println("   First 3 students: " + sublist);
        }
        
        if (students.size() > 2) {
            List<String> lastTwo = students.subList(Math.max(0, students.size() - 2), students.size());
            System.out.println("   Last 2 students: " + lastTwo);
        }
    }
    
    /**
     * Demonstrate bulk operations
     */
    public static void demonstrateBulkOperations() {
        System.out.println("\n2. Bulk operations:");
        
        // Create a test list
        List<String> testList = new ArrayList<>();
        testList.add("Alice");
        testList.add("Bob");
        
        System.out.println("   Test list: " + testList);
        System.out.println("   Contains all from test list: " + students.containsAll(testList));
        
        // Retain only elements in test list
        List<String> backup = new ArrayList<>(students);
        students.retainAll(testList);
        System.out.println("   After retainAll: " + students);
        
        // Restore original list
        students.clear();
        students.addAll(backup);
        System.out.println("   Restored original list");
    }
    
    /**
     * Demonstrate custom sorting
     */
    public static void demonstrateCustomSorting() {
        System.out.println("\n3. Custom sorting:");
        
        // Sort by length
        List<String> lengthSorted = new ArrayList<>(students);
        lengthSorted.sort(Comparator.comparing(String::length));
        System.out.println("   Sorted by length: " + lengthSorted);
        
        // Sort by length, then alphabetically
        List<String> complexSorted = new ArrayList<>(students);
        complexSorted.sort(Comparator.comparing(String::length).thenComparing(String::compareToIgnoreCase));
        System.out.println("   Sorted by length, then alphabetically: " + complexSorted);
        
        // Reverse order
        List<String> reverseSorted = new ArrayList<>(students);
        reverseSorted.sort(Collections.reverseOrder());
        System.out.println("   Reverse alphabetical order: " + reverseSorted);
    }
    
    /**
     * Demonstrate filtering operations
     */
    public static void demonstrateFiltering() {
        System.out.println("\n4. Filtering operations:");
        
        // Names starting with specific letter
        char filterLetter = 'A';
        List<String> filteredNames = new ArrayList<>();
        for (String name : students) {
            if (name.toUpperCase().startsWith(String.valueOf(filterLetter))) {
                filteredNames.add(name);
            }
        }
        System.out.println("   Names starting with '" + filterLetter + "': " + filteredNames);
        
        // Names with specific length
        int targetLength = 5;
        List<String> lengthFiltered = new ArrayList<>();
        for (String name : students) {
            if (name.length() == targetLength) {
                lengthFiltered.add(name);
            }
        }
        System.out.println("   Names with " + targetLength + " characters: " + lengthFiltered);
    }
    
    /**
     * Suggest similar names for typos
     */
    public static void suggestSimilarNames(String inputName) {
        System.out.println("\n💡 Did you mean:");
        
        List<String> suggestions = new ArrayList<>();
        String lowerInput = inputName.toLowerCase();
        
        for (String name : students) {
            String lowerName = name.toLowerCase();
            
            // Simple similarity check
            if (lowerName.startsWith(lowerInput.substring(0, Math.min(2, lowerInput.length())))) {
                suggestions.add(name);
            }
        }
        
        if (suggestions.isEmpty()) {
            System.out.println("   No similar names found.");
        } else {
            for (String suggestion : suggestions) {
                System.out.println("   - " + suggestion);
            }
        }
    }
}
