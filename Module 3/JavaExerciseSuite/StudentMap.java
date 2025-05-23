import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * Exercise 25: Student Map using HashMap
 * Demonstrates HashMap collection for storing student ID-name pairs.
 * Shows key-value operations, iteration, sorting, and various map manipulations.
 */
public class StudentMap {
    
    // HashMap to store student ID -> Name mappings
    private static HashMap<Integer, String> students = new HashMap<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Student Map Management System (HashMap)");
        System.out.println("======================================");
        System.out.println("Manage student ID-Name pairs using HashMap");
        System.out.println();
        
        boolean continueProgram = true;
        
        while (continueProgram) {
            displayMenu();
            System.out.print("Enter your choice (1-13): ");
            
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
                    updateStudent(scanner);
                    break;
                case 6:
                    displaySortedStudents();
                    break;
                case 7:
                    addMultipleStudents(scanner);
                    break;
                case 8:
                    displayStatistics();
                    break;
                case 9:
                    searchByName(scanner);
                    break;
                case 10:
                    clearAllStudents(scanner);
                    break;
                case 11:
                    demonstrateIterators();
                    break;
                case 12:
                    demonstrateAdvancedOperations();
                    break;
                case 13:
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
        System.out.println("Thank you for using the Student Map Management System!");
    }
    
    /**
     * Display the main menu
     */
    public static void displayMenu() {
        System.out.println("=== Student Map Operations ===");
        System.out.println("1.  Add Student");
        System.out.println("2.  Display All Students");
        System.out.println("3.  Remove Student");
        System.out.println("4.  Search by ID");
        System.out.println("5.  Update Student");
        System.out.println("6.  Display Sorted Students");
        System.out.println("7.  Add Multiple Students");
        System.out.println("8.  Display Statistics");
        System.out.println("9.  Search by Name");
        System.out.println("10. Clear All Students");
        System.out.println("11. Demonstrate Iterators");
        System.out.println("12. Advanced Operations");
        System.out.println("13. Exit");
        System.out.println("==============================");
    }
    
    /**
     * Add a new student to the map
     */
    public static void addStudent(Scanner scanner) {
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        
        // Check if ID already exists
        if (students.containsKey(studentId)) {
            System.out.println("⚠️  Student ID " + studentId + " already exists!");
            System.out.println("Current student: " + students.get(studentId));
            System.out.print("Do you want to update? (y/n): ");
            String response = scanner.nextLine().toLowerCase();
            if (!response.equals("y") && !response.equals("yes")) {
                System.out.println("Operation cancelled.");
                return;
            }
        }
        
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine().trim();
        
        if (studentName.isEmpty()) {
            System.out.println("❌ Student name cannot be empty!");
            return;
        }
        
        String oldName = students.put(studentId, studentName);
        
        if (oldName != null) {
            System.out.println("✅ Student ID " + studentId + " updated from '" + oldName + "' to '" + studentName + "'");
        } else {
            System.out.println("✅ Student added: ID " + studentId + " -> " + studentName);
        }
        
        System.out.println("📊 Total students: " + students.size());
    }
    
    /**
     * Display all students in the map
     */
    public static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("📋 No students in the map.");
            return;
        }
        
        System.out.println("📋 Student Map (" + students.size() + " students):");
        System.out.println("=====================================");
        System.out.printf("%-10s | %-20s%n", "ID", "Name");
        System.out.println("=====================================");
        
        for (Map.Entry<Integer, String> entry : students.entrySet()) {
            System.out.printf("%-10d | %-20s%n", entry.getKey(), entry.getValue());
        }
        
        System.out.println("=====================================");
        System.out.println("Total: " + students.size() + " students");
    }
    
    /**
     * Remove a student from the map
     */
    public static void removeStudent(Scanner scanner) {
        if (students.isEmpty()) {
            System.out.println("📋 No students to remove.");
            return;
        }
        
        System.out.print("Enter student ID to remove: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        
        String removedName = students.remove(studentId);
        
        if (removedName != null) {
            System.out.println("✅ Student removed: ID " + studentId + " (" + removedName + ")");
            System.out.println("📊 Remaining students: " + students.size());
        } else {
            System.out.println("❌ Student with ID " + studentId + " not found!");
            suggestSimilarIds(studentId);
        }
    }
    
    /**
     * Search for a student by ID
     */
    public static void searchStudent(Scanner scanner) {
        if (students.isEmpty()) {
            System.out.println("📋 No students to search.");
            return;
        }
        
        System.out.print("Enter student ID to search: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        
        String studentName = students.get(studentId);
        
        if (studentName != null) {
            System.out.println("✅ Student found:");
            System.out.println("   ID: " + studentId);
            System.out.println("   Name: " + studentName);
        } else {
            System.out.println("❌ Student with ID " + studentId + " not found!");
            suggestSimilarIds(studentId);
        }
    }
    
    /**
     * Update a student's information
     */
    public static void updateStudent(Scanner scanner) {
        if (students.isEmpty()) {
            System.out.println("📋 No students to update.");
            return;
        }
        
        System.out.print("Enter student ID to update: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        
        if (!students.containsKey(studentId)) {
            System.out.println("❌ Student with ID " + studentId + " not found!");
            return;
        }
        
        String currentName = students.get(studentId);
        System.out.println("Current name: " + currentName);
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine().trim();
        
        if (newName.isEmpty()) {
            System.out.println("❌ New name cannot be empty!");
            return;
        }
        
        students.put(studentId, newName);
        System.out.println("✅ Student updated: ID " + studentId + " -> " + newName);
        System.out.println("   (Previous name: " + currentName + ")");
    }
    
    /**
     * Display students sorted by various criteria
     */
    public static void displaySortedStudents() {
        if (students.isEmpty()) {
            System.out.println("📋 No students to sort.");
            return;
        }
        
        System.out.println("Choose sorting option:");
        System.out.println("1. Sort by ID (ascending)");
        System.out.println("2. Sort by ID (descending)");
        System.out.println("3. Sort by Name (alphabetical)");
        System.out.println("4. Sort by Name (reverse alphabetical)");
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter choice (1-4): ");
        int choice = scanner.nextInt();
        
        switch (choice) {
            case 1:
                displaySortedById(true);
                break;
            case 2:
                displaySortedById(false);
                break;
            case 3:
                displaySortedByName(true);
                break;
            case 4:
                displaySortedByName(false);
                break;
            default:
                System.out.println("❌ Invalid choice!");
        }
    }
    
    /**
     * Display students sorted by ID
     */
    public static void displaySortedById(boolean ascending) {
        TreeMap<Integer, String> sortedMap = new TreeMap<>();
        if (!ascending) {
            sortedMap = new TreeMap<>(Collections.reverseOrder());
        }
        sortedMap.putAll(students);
        
        System.out.println("📋 Students sorted by ID (" + (ascending ? "ascending" : "descending") + "):");
        System.out.println("================================================");
        System.out.printf("%-10s | %-20s%n", "ID", "Name");
        System.out.println("================================================");
        
        for (Map.Entry<Integer, String> entry : sortedMap.entrySet()) {
            System.out.printf("%-10d | %-20s%n", entry.getKey(), entry.getValue());
        }
    }
    
    /**
     * Display students sorted by name
     */
    public static void displaySortedByName(boolean ascending) {
        List<Map.Entry<Integer, String>> entryList = new ArrayList<>(students.entrySet());
        
        if (ascending) {
            entryList.sort(Map.Entry.comparingByValue());
        } else {
            entryList.sort(Map.Entry.<Integer, String>comparingByValue().reversed());
        }
        
        System.out.println("📋 Students sorted by Name (" + (ascending ? "A-Z" : "Z-A") + "):");
        System.out.println("===============================================");
        System.out.printf("%-10s | %-20s%n", "ID", "Name");
        System.out.println("===============================================");
        
        for (Map.Entry<Integer, String> entry : entryList) {
            System.out.printf("%-10d | %-20s%n", entry.getKey(), entry.getValue());
        }
    }
    
    /**
     * Add multiple students at once
     */
    public static void addMultipleStudents(Scanner scanner) {
        System.out.println("Add multiple students:");
        System.out.println("Format: ID1:Name1, ID2:Name2, ID3:Name3");
        System.out.print("Enter student data: ");
        String input = scanner.nextLine();
        
        String[] entries = input.split(",");
        int addedCount = 0;
        int updatedCount = 0;
        
        System.out.println("\nProcessing entries...");
        for (String entry : entries) {
            String trimmedEntry = entry.trim();
            if (trimmedEntry.isEmpty()) continue;
            
            String[] parts = trimmedEntry.split(":");
            if (parts.length != 2) {
                System.out.println("  ❌ Invalid format: " + trimmedEntry);
                continue;
            }
            
            try {
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                
                if (name.isEmpty()) {
                    System.out.println("  ❌ Empty name for ID " + id);
                    continue;
                }
                
                String oldName = students.put(id, name);
                if (oldName != null) {
                    System.out.println("  🔄 Updated: ID " + id + " -> " + name + " (was: " + oldName + ")");
                    updatedCount++;
                } else {
                    System.out.println("  ✅ Added: ID " + id + " -> " + name);
                    addedCount++;
                }
                
            } catch (NumberFormatException e) {
                System.out.println("  ❌ Invalid ID format: " + parts[0].trim());
            }
        }
        
        System.out.println("\n📊 Summary:");
        System.out.println("   Students added: " + addedCount);
        System.out.println("   Students updated: " + updatedCount);
        System.out.println("   Total students: " + students.size());
    }
    
    /**
     * Display statistics about the student map
     */
    public static void displayStatistics() {
        System.out.println("📊 Student Map Statistics:");
        System.out.println("==========================");
        
        if (students.isEmpty()) {
            System.out.println("No students in the map.");
            return;
        }
        
        System.out.println("Total students: " + students.size());
        
        // ID statistics
        Set<Integer> ids = students.keySet();
        int minId = Collections.min(ids);
        int maxId = Collections.max(ids);
        double avgId = ids.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        
        System.out.println("\nID Analysis:");
        System.out.println("   Smallest ID: " + minId);
        System.out.println("   Largest ID: " + maxId);
        System.out.printf("   Average ID: %.1f%n", avgId);
        System.out.println("   ID Range: " + (maxId - minId));
        
        // Name statistics
        analyzeNames();
        
        // Distribution analysis
        analyzeIdDistribution();
    }
    
    /**
     * Analyze name statistics
     */
    public static void analyzeNames() {
        if (students.isEmpty()) return;
        
        System.out.println("\nName Analysis:");
        
        int minLength = Integer.MAX_VALUE;
        int maxLength = 0;
        int totalLength = 0;
        String shortest = "";
        String longest = "";
        
        for (String name : students.values()) {
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
        
        System.out.println("   Shortest name: \"" + shortest + "\" (" + minLength + " characters)");
        System.out.println("   Longest name: \"" + longest + "\" (" + maxLength + " characters)");
        System.out.printf("   Average length: %.1f characters%n", averageLength);
        
        // Duplicate names analysis
        analyzeDuplicateNames();
    }
    
    /**
     * Analyze duplicate names
     */
    public static void analyzeDuplicateNames() {
        Map<String, List<Integer>> nameToIds = new HashMap<>();
        
        for (Map.Entry<Integer, String> entry : students.entrySet()) {
            String name = entry.getValue();
            nameToIds.computeIfAbsent(name, k -> new ArrayList<>()).add(entry.getKey());
        }
        
        System.out.println("\nDuplicate Names Analysis:");
        boolean foundDuplicates = false;
        
        for (Map.Entry<String, List<Integer>> entry : nameToIds.entrySet()) {
            if (entry.getValue().size() > 1) {
                foundDuplicates = true;
                System.out.println("   \"" + entry.getKey() + "\" appears " + entry.getValue().size() + 
                                 " times (IDs: " + entry.getValue() + ")");
            }
        }
        
        if (!foundDuplicates) {
            System.out.println("   No duplicate names found.");
        }
    }
    
    /**
     * Analyze ID distribution
     */
    public static void analyzeIdDistribution() {
        System.out.println("\nID Distribution Analysis:");
        
        Set<Integer> ids = students.keySet();
        List<Integer> idList = new ArrayList<>(ids);
        Collections.sort(idList);
        
        // Check for gaps in ID sequence
        List<Integer> gaps = new ArrayList<>();
        for (int i = 1; i < idList.size(); i++) {
            int current = idList.get(i);
            int previous = idList.get(i - 1);
            if (current - previous > 1) {
                for (int j = previous + 1; j < current; j++) {
                    gaps.add(j);
                }
            }
        }
        
        if (gaps.isEmpty()) {
            System.out.println("   No gaps in ID sequence (within range).");
        } else {
            System.out.println("   Gaps in ID sequence: " + gaps.subList(0, Math.min(10, gaps.size())));
            if (gaps.size() > 10) {
                System.out.println("   ... and " + (gaps.size() - 10) + " more gaps");
            }
        }
        
        // ID ranges
        int rangeSize = 100;
        Map<String, Integer> ranges = new HashMap<>();
        for (int id : ids) {
            int rangeStart = (id / rangeSize) * rangeSize;
            String range = rangeStart + "-" + (rangeStart + rangeSize - 1);
            ranges.put(range, ranges.getOrDefault(range, 0) + 1);
        }
        
        System.out.println("   ID distribution by ranges (size " + rangeSize + "):");
        for (Map.Entry<String, Integer> entry : ranges.entrySet()) {
            System.out.println("     " + entry.getKey() + ": " + entry.getValue() + " students");
        }
    }
    
    /**
     * Search students by name (reverse lookup)
     */
    public static void searchByName(Scanner scanner) {
        if (students.isEmpty()) {
            System.out.println("📋 No students to search.");
            return;
        }
        
        System.out.print("Enter student name to search: ");
        String searchName = scanner.nextLine().trim();
        
        List<Integer> matchingIds = new ArrayList<>();
        List<Integer> partialMatchIds = new ArrayList<>();
        
        for (Map.Entry<Integer, String> entry : students.entrySet()) {
            String name = entry.getValue();
            if (name.equalsIgnoreCase(searchName)) {
                matchingIds.add(entry.getKey());
            } else if (name.toLowerCase().contains(searchName.toLowerCase())) {
                partialMatchIds.add(entry.getKey());
            }
        }
        
        if (!matchingIds.isEmpty()) {
            System.out.println("✅ Exact matches found:");
            for (int id : matchingIds) {
                System.out.println("   ID " + id + ": " + students.get(id));
            }
        }
        
        if (!partialMatchIds.isEmpty()) {
            System.out.println("🔍 Partial matches found:");
            for (int id : partialMatchIds) {
                System.out.println("   ID " + id + ": " + students.get(id));
            }
        }
        
        if (matchingIds.isEmpty() && partialMatchIds.isEmpty()) {
            System.out.println("❌ No students found with name containing '" + searchName + "'");
        }
    }
    
    /**
     * Clear all students from the map
     */
    public static void clearAllStudents(Scanner scanner) {
        if (students.isEmpty()) {
            System.out.println("📋 Map is already empty.");
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
        
        // 1. Enhanced for loop with entrySet
        System.out.println("1. Enhanced for loop (entrySet):");
        for (Map.Entry<Integer, String> entry : students.entrySet()) {
            System.out.println("   ID " + entry.getKey() + ": " + entry.getValue());
        }
        
        // 2. KeySet iteration
        System.out.println("\n2. KeySet iteration:");
        for (Integer id : students.keySet()) {
            System.out.println("   ID " + id + ": " + students.get(id));
        }
        
        // 3. Values iteration
        System.out.println("\n3. Values iteration:");
        for (String name : students.values()) {
            System.out.println("   " + name);
        }
        
        // 4. Stream API
        System.out.println("\n4. Stream API:");
        students.entrySet().stream()
                .forEach(entry -> System.out.println("   ID " + entry.getKey() + ": " + entry.getValue()));
        
        // 5. Iterator with explicit iterator
        System.out.println("\n5. Explicit Iterator:");
        var iterator = students.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println("   ID " + entry.getKey() + ": " + entry.getValue());
        }
    }
    
    /**
     * Demonstrate advanced HashMap operations
     */
    public static void demonstrateAdvancedOperations() {
        System.out.println("🚀 Advanced HashMap Operations:");
        System.out.println("===============================");
        
        if (students.isEmpty()) {
            // Add sample data for demonstration
            students.put(101, "Alice");
            students.put(102, "Bob");
            students.put(103, "Charlie");
            students.put(104, "Diana");
            students.put(105, "Alice"); // Duplicate name
            System.out.println("Added sample data for demonstration.");
        }
        
        // 1. Different Map implementations
        demonstrateMapTypes();
        
        // 2. Bulk operations
        demonstrateBulkOperations();
        
        // 3. Conditional operations (Java 8+)
        demonstrateConditionalOperations();
        
        // 4. Performance comparison
        demonstratePerformance();
    }
    
    /**
     * Demonstrate different Map implementations
     */
    public static void demonstrateMapTypes() {
        System.out.println("\n1. Different Map implementations:");
        
        // HashMap (current)
        System.out.println("   HashMap (insertion order may vary): " + students);
        
        // LinkedHashMap (maintains insertion order)
        LinkedHashMap<Integer, String> linkedMap = new LinkedHashMap<>(students);
        System.out.println("   LinkedHashMap (maintains insertion order): " + linkedMap);
        
        // TreeMap (sorted by keys)
        TreeMap<Integer, String> treeMap = new TreeMap<>(students);
        System.out.println("   TreeMap (sorted by keys): " + treeMap);
    }
    
    /**
     * Demonstrate bulk operations
     */
    public static void demonstrateBulkOperations() {
        System.out.println("\n2. Bulk operations:");
        
        // Create test map
        Map<Integer, String> testMap = new HashMap<>();
        testMap.put(201, "John");
        testMap.put(202, "Jane");
        
        System.out.println("   Test map: " + testMap);
        
        // putAll
        Map<Integer, String> backup = new HashMap<>(students);
        students.putAll(testMap);
        System.out.println("   After putAll: " + students.size() + " students");
        
        // Restore
        students.clear();
        students.putAll(backup);
        System.out.println("   Restored original map");
    }
    
    /**
     * Demonstrate conditional operations
     */
    public static void demonstrateConditionalOperations() {
        System.out.println("\n3. Conditional operations (Java 8+):");
        
        // putIfAbsent
        String result = students.putIfAbsent(999, "Test Student");
        System.out.println("   putIfAbsent(999, 'Test Student'): " + (result == null ? "added" : "already exists"));
        
        // getOrDefault
        String name = students.getOrDefault(888, "Unknown Student");
        System.out.println("   getOrDefault(888, 'Unknown Student'): " + name);
        
        // computeIfAbsent
        students.computeIfAbsent(777, k -> "Generated Student " + k);
        System.out.println("   computeIfAbsent(777, ...): " + students.get(777));
        
        // replace
        boolean replaced = students.replace(999, "Test Student", "Updated Test Student");
        System.out.println("   replace(999, ...): " + replaced);
        
        // Clean up test entries
        students.remove(999);
        students.remove(777);
    }
    
    /**
     * Demonstrate performance characteristics
     */
    public static void demonstratePerformance() {
        System.out.println("\n4. Performance demonstration:");
        
        Map<Integer, String> perfTestMap = new HashMap<>();
        
        // Add many entries
        long startTime = System.nanoTime();
        for (int i = 1; i <= 10000; i++) {
            perfTestMap.put(i, "Student " + i);
        }
        long endTime = System.nanoTime();
        
        System.out.println("   Added 10,000 entries in " + (endTime - startTime) / 1_000_000 + " ms");
        
        // Random access test
        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            int randomId = (int) (Math.random() * 10000) + 1;
            perfTestMap.get(randomId);
        }
        endTime = System.nanoTime();
        
        System.out.println("   1,000 random lookups in " + (endTime - startTime) / 1_000_000 + " ms");
        System.out.println("   Average lookup time: " + (endTime - startTime) / 1_000_000.0 + " ms per lookup");
    }
    
    /**
     * Suggest similar IDs for typos
     */
    public static void suggestSimilarIds(int inputId) {
        Set<Integer> ids = students.keySet();
        List<Integer> suggestions = new ArrayList<>();
        
        // Find IDs within range of ±10
        for (int id : ids) {
            if (Math.abs(id - inputId) <= 10) {
                suggestions.add(id);
            }
        }
        
        if (!suggestions.isEmpty()) {
            Collections.sort(suggestions);
            System.out.println("💡 Similar IDs found: " + suggestions);
        }
    }
}
