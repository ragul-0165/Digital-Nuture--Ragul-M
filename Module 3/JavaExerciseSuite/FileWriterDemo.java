import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Exercise 22: File Writer Demonstration
 * Demonstrates writing user input to a file using FileWriter and BufferedWriter.
 * Shows file I/O operations, exception handling, and different writing approaches.
 */
public class FileWriterDemo {
    
    // Default output file name
    private static final String OUTPUT_FILE = "output.txt";
    private static final String BACKUP_FILE = "output_backup.txt";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("File Writer Demonstration");
        System.out.println("=========================");
        System.out.println("This program writes user input to '" + OUTPUT_FILE + "'");
        System.out.println();
        
        boolean continueWriting = true;
        
        while (continueWriting) {
            System.out.println("Choose writing mode:");
            System.out.println("1. Write new content (overwrite file)");
            System.out.println("2. Append to existing content");
            System.out.println("3. Write multiple lines");
            System.out.println("4. Write formatted data");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
            
            switch (choice) {
                case 1:
                    writeNewContent(scanner);
                    break;
                case 2:
                    appendContent(scanner);
                    break;
                case 3:
                    writeMultipleLines(scanner);
                    break;
                case 4:
                    writeFormattedData(scanner);
                    break;
                case 5:
                    continueWriting = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            
            if (continueWriting) {
                System.out.println();
            }
        }
        
        // Demonstrate additional file operations
        demonstrateAdvancedFileOperations();
        
        scanner.close();
        System.out.println("File writing operations completed!");
    }
    
    /**
     * Writes new content to file (overwrites existing content)
     */
    public static void writeNewContent(Scanner scanner) {
        System.out.print("Enter content to write to file: ");
        String content = scanner.nextLine();
        
        try (FileWriter writer = new FileWriter(OUTPUT_FILE)) {
            writer.write(content);
            System.out.println("✅ Content successfully written to " + OUTPUT_FILE);
            System.out.println("📁 File size: " + new java.io.File(OUTPUT_FILE).length() + " bytes");
            
        } catch (IOException e) {
            System.out.println("❌ Error writing to file: " + e.getMessage());
        }
    }
    
    /**
     * Appends content to existing file
     */
    public static void appendContent(Scanner scanner) {
        System.out.print("Enter content to append to file: ");
        String content = scanner.nextLine();
        
        try (FileWriter writer = new FileWriter(OUTPUT_FILE, true)) { // true for append mode
            writer.write("\n" + content); // Add newline before appending
            System.out.println("✅ Content successfully appended to " + OUTPUT_FILE);
            System.out.println("📁 File size: " + new java.io.File(OUTPUT_FILE).length() + " bytes");
            
        } catch (IOException e) {
            System.out.println("❌ Error appending to file: " + e.getMessage());
        }
    }
    
    /**
     * Writes multiple lines to file
     */
    public static void writeMultipleLines(Scanner scanner) {
        System.out.println("Enter multiple lines (type 'END' on a new line to finish):");
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {
            String line;
            int lineCount = 0;
            
            while (!(line = scanner.nextLine()).equals("END")) {
                writer.write(line);
                writer.newLine(); // Platform-independent newline
                lineCount++;
            }
            
            System.out.println("✅ " + lineCount + " lines written to " + OUTPUT_FILE);
            System.out.println("📁 File size: " + new java.io.File(OUTPUT_FILE).length() + " bytes");
            
        } catch (IOException e) {
            System.out.println("❌ Error writing multiple lines: " + e.getMessage());
        }
    }
    
    /**
     * Writes formatted data to file
     */
    public static void writeFormattedData(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        
        System.out.print("Enter your city: ");
        String city = scanner.nextLine();
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {
            // Write header
            writer.write("=== User Information ===");
            writer.newLine();
            writer.write("Generated on: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            writer.newLine();
            writer.newLine();
            
            // Write user data
            writer.write("Name: " + name);
            writer.newLine();
            writer.write("Age: " + age);
            writer.newLine();
            writer.write("City: " + city);
            writer.newLine();
            writer.newLine();
            
            // Write calculated information
            writer.write("Birth Year (approx): " + (2024 - age));
            writer.newLine();
            writer.write("Age Category: " + getAgeCategory(age));
            writer.newLine();
            
            // Write footer
            writer.newLine();
            writer.write("========================");
            writer.newLine();
            writer.write("End of user information");
            
            System.out.println("✅ Formatted data written to " + OUTPUT_FILE);
            System.out.println("📁 File size: " + new java.io.File(OUTPUT_FILE).length() + " bytes");
            
        } catch (IOException e) {
            System.out.println("❌ Error writing formatted data: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates advanced file operations
     */
    public static void demonstrateAdvancedFileOperations() {
        System.out.println("\n=== Advanced File Operations ===");
        
        // 1. Check if file exists
        java.io.File file = new java.io.File(OUTPUT_FILE);
        System.out.println("1. File exists: " + file.exists());
        
        if (file.exists()) {
            System.out.println("   File size: " + file.length() + " bytes");
            System.out.println("   Last modified: " + new java.util.Date(file.lastModified()));
            System.out.println("   Can read: " + file.canRead());
            System.out.println("   Can write: " + file.canWrite());
        }
        
        // 2. Create backup
        if (file.exists()) {
            createBackup();
        }
        
        // 3. Write sample data for testing
        writeSampleData();
        
        // 4. Demonstrate error handling
        demonstrateErrorHandling();
    }
    
    /**
     * Creates a backup of the output file
     */
    public static void createBackup() {
        System.out.println("\n2. Creating backup file...");
        
        try (java.io.FileInputStream fis = new java.io.FileInputStream(OUTPUT_FILE);
             java.io.FileOutputStream fos = new java.io.FileOutputStream(BACKUP_FILE)) {
            
            byte[] buffer = new byte[1024];
            int bytesRead;
            
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            
            System.out.println("✅ Backup created: " + BACKUP_FILE);
            
        } catch (IOException e) {
            System.out.println("❌ Error creating backup: " + e.getMessage());
        }
    }
    
    /**
     * Writes sample data to demonstrate different content types
     */
    public static void writeSampleData() {
        System.out.println("\n3. Writing sample data...");
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("sample_data.txt"))) {
            // Write different types of content
            writer.write("Sample Data File");
            writer.newLine();
            writer.write("================");
            writer.newLine();
            writer.newLine();
            
            // Numbers
            writer.write("Numbers: ");
            for (int i = 1; i <= 10; i++) {
                writer.write(i + " ");
            }
            writer.newLine();
            
            // Special characters
            writer.write("Special Characters: !@#$%^&*()_+-=[]{}|;:,.<>?");
            writer.newLine();
            
            // Unicode characters
            writer.write("Unicode: αβγδε ∑∏∫√ ♠♣♥♦");
            writer.newLine();
            
            // Formatted numbers
            writer.write("Formatted Numbers:");
            writer.newLine();
            for (int i = 1; i <= 5; i++) {
                writer.write(String.format("  Item %02d: $%.2f%n", i, i * 12.99));
            }
            
            System.out.println("✅ Sample data written to sample_data.txt");
            
        } catch (IOException e) {
            System.out.println("❌ Error writing sample data: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates error handling scenarios
     */
    public static void demonstrateErrorHandling() {
        System.out.println("\n4. Demonstrating error handling...");
        
        // Try to write to an invalid path
        try (FileWriter writer = new FileWriter("/invalid/path/file.txt")) {
            writer.write("This will fail");
            
        } catch (IOException e) {
            System.out.println("✅ Expected error caught: " + e.getMessage());
        }
        
        // Try to write to a read-only file (simulate)
        java.io.File readOnlyFile = new java.io.File("readonly_test.txt");
        try {
            readOnlyFile.createNewFile();
            readOnlyFile.setReadOnly();
            
            try (FileWriter writer = new FileWriter(readOnlyFile)) {
                writer.write("This might fail on read-only file");
                System.out.println("✅ Write to read-only file succeeded (file system dependent)");
                
            } catch (IOException e) {
                System.out.println("✅ Expected error for read-only file: " + e.getMessage());
            }
            
            // Clean up
            readOnlyFile.setWritable(true);
            readOnlyFile.delete();
            
        } catch (IOException e) {
            System.out.println("Error in read-only test: " + e.getMessage());
        }
    }
    
    /**
     * Helper method to determine age category
     */
    public static String getAgeCategory(int age) {
        if (age < 13) return "Child";
        if (age < 20) return "Teenager";
        if (age < 60) return "Adult";
        return "Senior";
    }
}
