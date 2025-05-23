import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Exercise 23: File Reader Demonstration
 * Demonstrates reading and displaying file contents using FileReader and BufferedReader.
 * Shows different file reading approaches, error handling, and content analysis.
 */
public class FileReaderDemo {
    
    // Default input file name (should be created by FileWriterDemo)
    private static final String INPUT_FILE = "output.txt";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("File Reader Demonstration");
        System.out.println("=========================");
        System.out.println("This program reads and displays file contents");
        System.out.println();
        
        boolean continueReading = true;
        
        while (continueReading) {
            System.out.println("Choose reading operation:");
            System.out.println("1. Read default file (" + INPUT_FILE + ")");
            System.out.println("2. Read specific file");
            System.out.println("3. Read file with line numbers");
            System.out.println("4. Analyze file content");
            System.out.println("5. Read large file efficiently");
            System.out.println("6. Read all files in current directory");
            System.out.println("7. Exit");
            System.out.print("Enter your choice (1-7): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
            
            switch (choice) {
                case 1:
                    readDefaultFile();
                    break;
                case 2:
                    readSpecificFile(scanner);
                    break;
                case 3:
                    readFileWithLineNumbers(scanner);
                    break;
                case 4:
                    analyzeFileContent(scanner);
                    break;
                case 5:
                    readLargeFileEfficiently(scanner);
                    break;
                case 6:
                    readAllFilesInDirectory();
                    break;
                case 7:
                    continueReading = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            
            if (continueReading) {
                System.out.println();
            }
        }
        
        // Demonstrate additional reading scenarios
        demonstrateAdvancedReading();
        
        scanner.close();
        System.out.println("File reading operations completed!");
    }
    
    /**
     * Reads and displays the default file
     */
    public static void readDefaultFile() {
        System.out.println("Reading " + INPUT_FILE + ":");
        System.out.println("=" + "=".repeat(INPUT_FILE.length() + 9));
        
        File file = new File(INPUT_FILE);
        
        if (!file.exists()) {
            System.out.println("❌ File '" + INPUT_FILE + "' does not exist!");
            System.out.println("💡 Tip: Run FileWriterDemo first to create the file.");
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            boolean isEmpty = true;
            
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                isEmpty = false;
            }
            
            if (isEmpty) {
                System.out.println("📄 File is empty.");
            }
            
            System.out.println("\n✅ File read successfully!");
            displayFileInfo(file);
            
        } catch (IOException e) {
            System.out.println("❌ Error reading file: " + e.getMessage());
        }
    }
    
    /**
     * Reads a user-specified file
     */
    public static void readSpecificFile(Scanner scanner) {
        System.out.print("Enter the filename to read: ");
        String filename = scanner.nextLine();
        
        File file = new File(filename);
        
        if (!file.exists()) {
            System.out.println("❌ File '" + filename + "' does not exist!");
            return;
        }
        
        if (!file.isFile()) {
            System.out.println("❌ '" + filename + "' is not a regular file!");
            return;
        }
        
        System.out.println("\nReading " + filename + ":");
        System.out.println("=" + "=".repeat(filename.length() + 9));
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineCount = 0;
            
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                lineCount++;
            }
            
            System.out.println("\n✅ File read successfully!");
            System.out.println("📊 Total lines: " + lineCount);
            displayFileInfo(file);
            
        } catch (IOException e) {
            System.out.println("❌ Error reading file: " + e.getMessage());
        }
    }
    
    /**
     * Reads file with line numbers
     */
    public static void readFileWithLineNumbers(Scanner scanner) {
        System.out.print("Enter the filename to read with line numbers: ");
        String filename = scanner.nextLine();
        
        File file = new File(filename);
        
        if (!file.exists()) {
            System.out.println("❌ File '" + filename + "' does not exist!");
            return;
        }
        
        System.out.println("\nReading " + filename + " with line numbers:");
        System.out.println("=" + "=".repeat(filename.length() + 25));
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 1;
            
            while ((line = reader.readLine()) != null) {
                System.out.printf("%4d: %s%n", lineNumber, line);
                lineNumber++;
            }
            
            System.out.println("\n✅ File read successfully!");
            System.out.println("📊 Total lines: " + (lineNumber - 1));
            displayFileInfo(file);
            
        } catch (IOException e) {
            System.out.println("❌ Error reading file: " + e.getMessage());
        }
    }
    
    /**
     * Analyzes file content and provides statistics
     */
    public static void analyzeFileContent(Scanner scanner) {
        System.out.print("Enter the filename to analyze: ");
        String filename = scanner.nextLine();
        
        File file = new File(filename);
        
        if (!file.exists()) {
            System.out.println("❌ File '" + filename + "' does not exist!");
            return;
        }
        
        System.out.println("\nAnalyzing " + filename + ":");
        System.out.println("=" + "=".repeat(filename.length() + 11));
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineCount = 0;
            int wordCount = 0;
            int charCount = 0;
            int emptyLines = 0;
            int longestLineLength = 0;
            String longestLine = "";
            List<String> lines = new ArrayList<>();
            
            while ((line = reader.readLine()) != null) {
                lines.add(line);
                lineCount++;
                charCount += line.length();
                
                if (line.trim().isEmpty()) {
                    emptyLines++;
                } else {
                    // Count words (split by whitespace)
                    String[] words = line.trim().split("\\s+");
                    if (!line.trim().isEmpty()) {
                        wordCount += words.length;
                    }
                }
                
                // Track longest line
                if (line.length() > longestLineLength) {
                    longestLineLength = line.length();
                    longestLine = line;
                }
            }
            
            // Display analysis results
            System.out.println("\n📊 Content Analysis Results:");
            System.out.println("============================");
            System.out.println("Total lines: " + lineCount);
            System.out.println("Total words: " + wordCount);
            System.out.println("Total characters: " + charCount);
            System.out.println("Empty lines: " + emptyLines);
            System.out.println("Non-empty lines: " + (lineCount - emptyLines));
            
            if (lineCount > 0) {
                System.out.printf("Average words per line: %.2f%n", (double) wordCount / lineCount);
                System.out.printf("Average characters per line: %.2f%n", (double) charCount / lineCount);
            }
            
            System.out.println("Longest line length: " + longestLineLength + " characters");
            if (!longestLine.isEmpty()) {
                System.out.println("Longest line: \"" + 
                    (longestLine.length() > 50 ? longestLine.substring(0, 50) + "..." : longestLine) + "\"");
            }
            
            // Character frequency analysis (first 100 characters)
            if (charCount > 0) {
                analyzeCharacterFrequency(lines);
            }
            
            displayFileInfo(file);
            
        } catch (IOException e) {
            System.out.println("❌ Error analyzing file: " + e.getMessage());
        }
    }
    
    /**
     * Reads large files efficiently with progress indication
     */
    public static void readLargeFileEfficiently(Scanner scanner) {
        System.out.print("Enter the filename to read (large file): ");
        String filename = scanner.nextLine();
        
        File file = new File(filename);
        
        if (!file.exists()) {
            System.out.println("❌ File '" + filename + "' does not exist!");
            return;
        }
        
        System.out.print("Display content? (y/n) [n recommended for large files]: ");
        boolean displayContent = scanner.nextLine().toLowerCase().startsWith("y");
        
        System.out.println("\nReading " + filename + " efficiently:");
        System.out.println("=" + "=".repeat(filename.length() + 21));
        
        long startTime = System.currentTimeMillis();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename), 8192)) { // Larger buffer
            String line;
            int lineCount = 0;
            long bytesRead = 0;
            
            while ((line = reader.readLine()) != null) {
                lineCount++;
                bytesRead += line.length() + 1; // +1 for newline
                
                if (displayContent) {
                    System.out.println(line);
                }
                
                // Show progress for large files
                if (lineCount % 1000 == 0) {
                    System.out.printf("Processed %d lines... (%.2f KB)%n", lineCount, bytesRead / 1024.0);
                }
            }
            
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            
            System.out.println("\n✅ File read successfully!");
            System.out.println("📊 Performance Statistics:");
            System.out.println("   Lines processed: " + lineCount);
            System.out.println("   Bytes read: " + bytesRead);
            System.out.println("   Time taken: " + duration + " ms");
            System.out.printf("   Reading speed: %.2f lines/second%n", 
                            lineCount / Math.max(duration / 1000.0, 0.001));
            
            displayFileInfo(file);
            
        } catch (IOException e) {
            System.out.println("❌ Error reading large file: " + e.getMessage());
        }
    }
    
    /**
     * Reads all text files in the current directory
     */
    public static void readAllFilesInDirectory() {
        System.out.println("Reading all text files in current directory:");
        System.out.println("============================================");
        
        File currentDir = new File(".");
        File[] files = currentDir.listFiles((dir, name) -> 
            name.toLowerCase().endsWith(".txt") || name.toLowerCase().endsWith(".java"));
        
        if (files == null || files.length == 0) {
            System.out.println("📁 No text files found in current directory.");
            return;
        }
        
        System.out.println("Found " + files.length + " text file(s):");
        System.out.println();
        
        for (File file : files) {
            System.out.println("📄 " + file.getName() + ":");
            System.out.println("-".repeat(file.getName().length() + 4));
            
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                int lineCount = 0;
                
                // Read first few lines
                while ((line = reader.readLine()) != null && lineCount < 5) {
                    System.out.println("  " + line);
                    lineCount++;
                }
                
                // Count remaining lines
                int remainingLines = 0;
                while (reader.readLine() != null) {
                    remainingLines++;
                }
                
                if (remainingLines > 0) {
                    System.out.println("  ... (" + remainingLines + " more lines)");
                }
                
                System.out.println("  Total lines: " + (lineCount + remainingLines));
                displayFileInfo(file);
                
            } catch (IOException e) {
                System.out.println("  ❌ Error reading file: " + e.getMessage());
            }
            
            System.out.println();
        }
    }
    
    /**
     * Demonstrates advanced reading scenarios
     */
    public static void demonstrateAdvancedReading() {
        System.out.println("\n=== Advanced Reading Scenarios ===");
        
        // 1. Reading with different encodings
        demonstrateEncodingHandling();
        
        // 2. Reading binary files (basic)
        demonstrateBinaryReading();
        
        // 3. Error recovery
        demonstrateErrorRecovery();
    }
    
    /**
     * Demonstrates character frequency analysis
     */
    public static void analyzeCharacterFrequency(List<String> lines) {
        System.out.println("\n📈 Character Frequency Analysis (letters only):");
        
        int[] frequency = new int[26]; // For A-Z
        int totalLetters = 0;
        
        for (String line : lines) {
            for (char c : line.toLowerCase().toCharArray()) {
                if (c >= 'a' && c <= 'z') {
                    frequency[c - 'a']++;
                    totalLetters++;
                }
            }
        }
        
        if (totalLetters > 0) {
            System.out.println("Most frequent letters:");
            for (int i = 0; i < 5; i++) { // Show top 5
                int maxIndex = 0;
                for (int j = 1; j < 26; j++) {
                    if (frequency[j] > frequency[maxIndex]) {
                        maxIndex = j;
                    }
                }
                
                if (frequency[maxIndex] > 0) {
                    char letter = (char) ('A' + maxIndex);
                    double percentage = (frequency[maxIndex] * 100.0) / totalLetters;
                    System.out.printf("  %c: %d times (%.1f%%)%n", letter, frequency[maxIndex], percentage);
                    frequency[maxIndex] = 0; // Mark as processed
                }
            }
        }
    }
    
    /**
     * Demonstrates encoding handling
     */
    public static void demonstrateEncodingHandling() {
        System.out.println("\n1. Encoding Handling:");
        
        File file = new File(INPUT_FILE);
        if (!file.exists()) {
            System.out.println("   Skipping encoding demo - no test file available");
            return;
        }
        
        try (FileReader reader = new FileReader(file, java.nio.charset.StandardCharsets.UTF_8)) {
            System.out.println("   ✅ Successfully opened file with UTF-8 encoding");
            
            // Read first line to test
            try (BufferedReader buffered = new BufferedReader(reader)) {
                String firstLine = buffered.readLine();
                if (firstLine != null) {
                    System.out.println("   First line: " + firstLine);
                }
            }
            
        } catch (IOException e) {
            System.out.println("   ❌ Encoding error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates basic binary reading
     */
    public static void demonstrateBinaryReading() {
        System.out.println("\n2. Binary Reading (basic):");
        
        File file = new File(INPUT_FILE);
        if (!file.exists()) {
            System.out.println("   Skipping binary demo - no test file available");
            return;
        }
        
        try (java.io.FileInputStream fis = new java.io.FileInputStream(file)) {
            byte[] buffer = new byte[10]; // Read first 10 bytes
            int bytesRead = fis.read(buffer);
            
            System.out.println("   First " + bytesRead + " bytes (as hex):");
            for (int i = 0; i < bytesRead; i++) {
                System.out.printf("   %02X ", buffer[i]);
            }
            System.out.println();
            
        } catch (IOException e) {
            System.out.println("   ❌ Binary reading error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates error recovery techniques
     */
    public static void demonstrateErrorRecovery() {
        System.out.println("\n3. Error Recovery:");
        
        // Try to read a non-existent file
        try (BufferedReader reader = new BufferedReader(new FileReader("nonexistent.txt"))) {
            reader.readLine();
            
        } catch (IOException e) {
            System.out.println("   ✅ Gracefully handled missing file: " + e.getMessage());
            
            // Demonstrate fallback behavior
            System.out.println("   🔄 Attempting fallback to default file...");
            File fallback = new File(INPUT_FILE);
            if (fallback.exists()) {
                System.out.println("   ✅ Fallback file found and can be used");
            } else {
                System.out.println("   ❌ No fallback file available");
            }
        }
    }
    
    /**
     * Helper method to display file information
     */
    public static void displayFileInfo(File file) {
        System.out.println("\n📁 File Information:");
        System.out.println("   Name: " + file.getName());
        System.out.println("   Size: " + file.length() + " bytes (" + formatBytes(file.length()) + ")");
        System.out.println("   Last modified: " + new java.util.Date(file.lastModified()));
        System.out.println("   Path: " + file.getAbsolutePath());
        System.out.println("   Readable: " + file.canRead());
        System.out.println("   Writable: " + file.canWrite());
    }
    
    /**
     * Helper method to format bytes in human-readable format
     */
    public static String formatBytes(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.1f KB", bytes / 1024.0);
        return String.format("%.1f MB", bytes / (1024.0 * 1024.0));
    }
}
