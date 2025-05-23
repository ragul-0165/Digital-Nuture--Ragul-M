import java.util.*;
import java.util.stream.*;
import java.util.function.*;

/**
 * Exercise 28: Even Number Stream Filter
 * Demonstrates filtering even numbers using Java Stream API.
 * Shows various stream operations, collectors, and functional programming concepts.
 */
public class EvenStreamFilter {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Even Number Stream Filter Demonstration");
        System.out.println("======================================");
        System.out.println("Using Java Stream API to filter and process even numbers");
        System.out.println();
        
        boolean continueProgram = true;
        
        while (continueProgram) {
            displayMenu();
            System.out.print("Enter your choice (1-10): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
            
            switch (choice) {
                case 1:
                    demonstrateBasicEvenFiltering();
                    break;
                case 2:
                    filterUserInputNumbers(scanner);
                    break;
                case 3:
                    demonstrateAdvancedStreamOperations();
                    break;
                case 4:
                    demonstrateCollectors();
                    break;
                case 5:
                    demonstrateParallelStreams();
                    break;
                case 6:
                    demonstrateStreamComposition();
                    break;
                case 7:
                    demonstratePerformanceComparison();
                    break;
                case 8:
                    demonstrateCustomPredicates();
                    break;
                case 9:
                    demonstrateRealWorldScenarios();
                    break;
                case 10:
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
        System.out.println("Thank you for using the Stream Filter Demo!");
    }
    
    /**
     * Display the main menu
     */
    public static void displayMenu() {
        System.out.println("=== Stream Filter Operations ===");
        System.out.println("1. Basic Even Number Filtering");
        System.out.println("2. Filter User Input Numbers");
        System.out.println("3. Advanced Stream Operations");
        System.out.println("4. Stream Collectors Demo");
        System.out.println("5. Parallel Streams");
        System.out.println("6. Stream Composition");
        System.out.println("7. Performance Comparison");
        System.out.println("8. Custom Predicates");
        System.out.println("9. Real-World Scenarios");
        System.out.println("10. Exit");
        System.out.println("================================");
    }
    
    /**
     * Demonstrates basic even number filtering
     */
    public static void demonstrateBasicEvenFiltering() {
        System.out.println("1. Basic Even Number Filtering");
        System.out.println("=".repeat(40));
        
        // Create sample data
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        
        System.out.println("Original numbers: " + numbers);
        
        // Basic even filtering
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        
        System.out.println("Even numbers: " + evenNumbers);
        
        // Count even numbers
        long evenCount = numbers.stream()
                .filter(n -> n % 2 == 0)
                .count();
        
        System.out.println("Count of even numbers: " + evenCount);
        
        // Sum of even numbers
        int evenSum = numbers.stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(Integer::intValue)
                .sum();
        
        System.out.println("Sum of even numbers: " + evenSum);
        
        // Average of even numbers
        OptionalDouble evenAverage = numbers.stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(Integer::intValue)
                .average();
        
        if (evenAverage.isPresent()) {
            System.out.printf("Average of even numbers: %.2f%n", evenAverage.getAsDouble());
        }
        
        // Find first even number
        Optional<Integer> firstEven = numbers.stream()
                .filter(n -> n % 2 == 0)
                .findFirst();
        
        firstEven.ifPresent(n -> System.out.println("First even number: " + n));
        
        // Check if any/all numbers are even
        boolean anyEven = numbers.stream().anyMatch(n -> n % 2 == 0);
        boolean allEven = numbers.stream().allMatch(n -> n % 2 == 0);
        boolean noneOdd = numbers.stream().noneMatch(n -> n % 2 == 1);
        
        System.out.println("Any even numbers: " + anyEven);
        System.out.println("All even numbers: " + allEven);
        System.out.println("No odd numbers: " + noneOdd);
    }
    
    /**
     * Filter user input numbers
     */
    public static void filterUserInputNumbers(Scanner scanner) {
        System.out.println("2. Filter User Input Numbers");
        System.out.println("=".repeat(40));
        
        System.out.print("Enter numbers separated by spaces: ");
        String input = scanner.nextLine();
        
        try {
            List<Integer> userNumbers = Arrays.stream(input.split("\\s+"))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            
            System.out.println("Your numbers: " + userNumbers);
            
            if (userNumbers.isEmpty()) {
                System.out.println("No valid numbers entered.");
                return;
            }
            
            // Filter even numbers
            List<Integer> evenNumbers = userNumbers.stream()
                    .filter(n -> n % 2 == 0)
                    .collect(Collectors.toList());
            
            // Filter odd numbers
            List<Integer> oddNumbers = userNumbers.stream()
                    .filter(n -> n % 2 == 1)
                    .collect(Collectors.toList());
            
            System.out.println("Even numbers: " + evenNumbers + " (Count: " + evenNumbers.size() + ")");
            System.out.println("Odd numbers: " + oddNumbers + " (Count: " + oddNumbers.size() + ")");
            
            // Additional operations
            if (!evenNumbers.isEmpty()) {
                int evenSum = evenNumbers.stream().mapToInt(Integer::intValue).sum();
                int evenMax = evenNumbers.stream().mapToInt(Integer::intValue).max().orElse(0);
                int evenMin = evenNumbers.stream().mapToInt(Integer::intValue).min().orElse(0);
                
                System.out.println("Even numbers statistics:");
                System.out.println("  Sum: " + evenSum);
                System.out.println("  Max: " + evenMax);
                System.out.println("  Min: " + evenMin);
            }
            
            // Show squared even numbers
            List<Integer> squaredEvens = userNumbers.stream()
                    .filter(n -> n % 2 == 0)
                    .map(n -> n * n)
                    .collect(Collectors.toList());
            
            if (!squaredEvens.isEmpty()) {
                System.out.println("Squared even numbers: " + squaredEvens);
            }
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Please enter valid integers only.");
        }
    }
    
    /**
     * Demonstrates advanced stream operations
     */
    public static void demonstrateAdvancedStreamOperations() {
        System.out.println("3. Advanced Stream Operations");
        System.out.println("=".repeat(40));
        
        List<Integer> numbers = IntStream.rangeClosed(1, 20)
                .boxed()
                .collect(Collectors.toList());
        
        System.out.println("Working with numbers 1-20: " + numbers);
        
        // Complex filtering and transformation
        List<String> processedEvens = numbers.stream()
                .filter(n -> n % 2 == 0)                    // Filter even numbers
                .filter(n -> n > 5)                         // Filter greater than 5
                .map(n -> n * 2)                            // Double each number
                .map(n -> "Number: " + n)                   // Convert to string
                .collect(Collectors.toList());
        
        System.out.println("Processed evens (>5, doubled, formatted): " + processedEvens);
        
        // Using distinct and sorted
        List<Integer> duplicatesWithEvens = Arrays.asList(2, 4, 2, 6, 8, 4, 10, 6, 8, 12);
        List<Integer> distinctSortedEvens = duplicatesWithEvens.stream()
                .filter(n -> n % 2 == 0)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        
        System.out.println("Original with duplicates: " + duplicatesWithEvens);
        System.out.println("Distinct sorted evens: " + distinctSortedEvens);
        
        // Limit and skip
        List<Integer> limitedEvens = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0)
                .skip(5)                    // Skip first 5 even numbers
                .limit(10)                  // Take next 10
                .boxed()
                .collect(Collectors.toList());
        
        System.out.println("Even numbers (skip 5, take 10): " + limitedEvens);
        
        // FlatMap example with nested lists
        List<List<Integer>> nestedNumbers = Arrays.asList(
                Arrays.asList(1, 2, 3, 4),
                Arrays.asList(5, 6, 7, 8),
                Arrays.asList(9, 10, 11, 12)
        );
        
        List<Integer> flattenedEvens = nestedNumbers.stream()
                .flatMap(List::stream)
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        
        System.out.println("Nested lists: " + nestedNumbers);
        System.out.println("Flattened evens: " + flattenedEvens);
        
        // Peek for debugging
        System.out.println("\nUsing peek() for debugging:");
        List<Integer> debuggedEvens = numbers.stream()
                .filter(n -> n % 2 == 0)
                .peek(n -> System.out.print("Even: " + n + " "))
                .map(n -> n * n)
                .peek(n -> System.out.print("Squared: " + n + " | "))
                .collect(Collectors.toList());
        
        System.out.println("\nFinal result: " + debuggedEvens);
    }
    
    /**
     * Demonstrates various collectors
     */
    public static void demonstrateCollectors() {
        System.out.println("4. Stream Collectors Demo");
        System.out.println("=".repeat(40));
        
        List<Integer> numbers = IntStream.rangeClosed(1, 20)
                .boxed()
                .collect(Collectors.toList());
        
        // Collect to different collections
        Set<Integer> evenSet = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toSet());
        
        System.out.println("Even numbers as Set: " + evenSet);
        
        // Collect to specific list type
        LinkedList<Integer> evenLinkedList = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toCollection(LinkedList::new));
        
        System.out.println("Even numbers as LinkedList: " + evenLinkedList);
        
        // Joining strings
        String evenNumbersString = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
        
        System.out.println("Even numbers joined: " + evenNumbersString);
        
        // Grouping by even/odd
        Map<Boolean, List<Integer>> groupedByEvenOdd = numbers.stream()
                .collect(Collectors.groupingBy(n -> n % 2 == 0));
        
        System.out.println("Grouped by even/odd: " + groupedByEvenOdd);
        
        // Partitioning (special case of grouping for boolean)
        Map<Boolean, List<Integer>> partitioned = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        
        System.out.println("Partitioned by even/odd: " + partitioned);
        
        // Counting
        Map<Boolean, Long> countByEvenOdd = numbers.stream()
                .collect(Collectors.groupingBy(n -> n % 2 == 0, Collectors.counting()));
        
        System.out.println("Count by even/odd: " + countByEvenOdd);
        
        // Summary statistics
        IntSummaryStatistics evenStats = numbers.stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(Integer::intValue)
                .summaryStatistics();
        
        System.out.println("Even numbers statistics:");
        System.out.println("  Count: " + evenStats.getCount());
        System.out.println("  Sum: " + evenStats.getSum());
        System.out.println("  Average: " + evenStats.getAverage());
        System.out.println("  Min: " + evenStats.getMin());
        System.out.println("  Max: " + evenStats.getMax());
        
        // Custom collector
        String customCollected = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(String::valueOf)
                .collect(Collectors.reducing("", (a, b) -> a.isEmpty() ? b : a + " + " + b));
        
        System.out.println("Custom collected (sum format): " + customCollected);
    }
    
    /**
     * Demonstrates parallel streams
     */
    public static void demonstrateParallelStreams() {
        System.out.println("5. Parallel Streams");
        System.out.println("=".repeat(40));
        
        // Create large dataset
        List<Integer> largeNumbers = IntStream.rangeClosed(1, 1_000_000)
                .boxed()
                .collect(Collectors.toList());
        
        System.out.println("Working with " + largeNumbers.size() + " numbers");
        
        // Sequential processing
        long startTime = System.nanoTime();
        List<Integer> sequentialEvens = largeNumbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .collect(Collectors.toList());
        long sequentialTime = System.nanoTime() - startTime;
        
        System.out.println("Sequential processing:");
        System.out.println("  Even numbers found: " + sequentialEvens.size());
        System.out.println("  Time taken: " + sequentialTime / 1_000_000 + " ms");
        
        // Parallel processing
        startTime = System.nanoTime();
        List<Integer> parallelEvens = largeNumbers.parallelStream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .collect(Collectors.toList());
        long parallelTime = System.nanoTime() - startTime;
        
        System.out.println("\nParallel processing:");
        System.out.println("  Even numbers found: " + parallelEvens.size());
        System.out.println("  Time taken: " + parallelTime / 1_000_000 + " ms");
        
        double speedup = (double) sequentialTime / parallelTime;
        System.out.printf("  Speedup: %.2fx%n", speedup);
        
        // Verify results are the same
        boolean resultsEqual = sequentialEvens.size() == parallelEvens.size();
        System.out.println("  Results match: " + resultsEqual);
        
        // Show available processors
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("  Available processors: " + processors);
        
        // Example where parallel might not be beneficial
        List<Integer> smallNumbers = IntStream.rangeClosed(1, 100)
                .boxed()
                .collect(Collectors.toList());
        
        startTime = System.nanoTime();
        long smallSequentialCount = smallNumbers.stream()
                .filter(n -> n % 2 == 0)
                .count();
        long smallSequentialTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        long smallParallelCount = smallNumbers.parallelStream()
                .filter(n -> n % 2 == 0)
                .count();
        long smallParallelTime = System.nanoTime() - startTime;
        
        System.out.println("\nSmall dataset (100 numbers):");
        System.out.println("  Sequential time: " + smallSequentialTime / 1000 + " μs");
        System.out.println("  Parallel time: " + smallParallelTime / 1000 + " μs");
        System.out.println("  Note: Parallel overhead may not be worth it for small datasets");
    }
    
    /**
     * Demonstrates stream composition
     */
    public static void demonstrateStreamComposition() {
        System.out.println("6. Stream Composition");
        System.out.println("=".repeat(40));
        
        List<Integer> numbers = IntStream.rangeClosed(1, 30)
                .boxed()
                .collect(Collectors.toList());
        
        // Compose multiple operations
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isGreaterThan10 = n -> n > 10;
        Predicate<Integer> isLessThan25 = n -> n < 25;
        
        Function<Integer, Integer> square = n -> n * n;
        Function<Integer, String> format = n -> String.format("(%d)", n);
        
        List<String> composedResult = numbers.stream()
                .filter(isEven.and(isGreaterThan10).and(isLessThan25))
                .map(square.andThen(format))
                .collect(Collectors.toList());
        
        System.out.println("Original numbers: " + numbers);
        System.out.println("Composed filter (even AND >10 AND <25), then squared and formatted:");
        System.out.println("Result: " + composedResult);
        
        // Stream of streams flattening
        Stream<Stream<Integer>> streamOfStreams = Stream.of(
                Stream.of(1, 2, 3, 4),
                Stream.of(5, 6, 7, 8),
                Stream.of(9, 10, 11, 12)
        );
        
        List<Integer> flattenedAndFiltered = streamOfStreams
                .flatMap(Function.identity())
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        
        System.out.println("\nFlattened streams and filtered evens: " + flattenedAndFiltered);
        
        // Optional chaining with streams
        Optional<Integer> maxEvenSquare = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .max(Integer::compareTo);
        
        maxEvenSquare.ifPresentOrElse(
                value -> System.out.println("Max even square: " + value),
                () -> System.out.println("No even numbers found")
        );
    }
    
    /**
     * Demonstrates performance comparison
     */
    public static void demonstratePerformanceComparison() {
        System.out.println("7. Performance Comparison");
        System.out.println("=".repeat(40));
        
        List<Integer> numbers = IntStream.rangeClosed(1, 10_000_000)
                .boxed()
                .collect(Collectors.toList());
        
        System.out.println("Performance test with " + numbers.size() + " numbers");
        
        // Traditional for loop
        long startTime = System.nanoTime();
        List<Integer> traditionalEvens = new ArrayList<>();
        for (Integer number : numbers) {
            if (number % 2 == 0) {
                traditionalEvens.add(number);
            }
        }
        long traditionalTime = System.nanoTime() - startTime;
        
        // Stream API
        startTime = System.nanoTime();
        List<Integer> streamEvens = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        long streamTime = System.nanoTime() - startTime;
        
        // Parallel Stream
        startTime = System.nanoTime();
        List<Integer> parallelStreamEvens = numbers.parallelStream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        long parallelStreamTime = System.nanoTime() - startTime;
        
        System.out.println("Results:");
        System.out.println("  Traditional loop: " + traditionalTime / 1_000_000 + " ms (" + traditionalEvens.size() + " evens)");
        System.out.println("  Stream API: " + streamTime / 1_000_000 + " ms (" + streamEvens.size() + " evens)");
        System.out.println("  Parallel Stream: " + parallelStreamTime / 1_000_000 + " ms (" + parallelStreamEvens.size() + " evens)");
        
        System.out.println("\nRelative Performance:");
        System.out.printf("  Stream vs Traditional: %.2fx%n", (double) streamTime / traditionalTime);
        System.out.printf("  Parallel vs Traditional: %.2fx%n", (double) parallelStreamTime / traditionalTime);
        System.out.printf("  Parallel vs Stream: %.2fx%n", (double) parallelStreamTime / streamTime);
    }
    
    /**
     * Demonstrates custom predicates
     */
    public static void demonstrateCustomPredicates() {
        System.out.println("8. Custom Predicates");
        System.out.println("=".repeat(40));
        
        List<Integer> numbers = IntStream.rangeClosed(1, 50)
                .boxed()
                .collect(Collectors.toList());
        
        // Define custom predicates
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isDivisibleBy3 = n -> n % 3 == 0;
        Predicate<Integer> isDivisibleBy5 = n -> n % 5 == 0;
        Predicate<Integer> isPrime = EvenStreamFilter::isPrime;
        Predicate<Integer> isPerfectSquare = n -> {
            int sqrt = (int) Math.sqrt(n);
            return sqrt * sqrt == n;
        };
        
        System.out.println("Numbers 1-50: " + numbers);
        
        // Even numbers
        List<Integer> evens = numbers.stream()
                .filter(isEven)
                .collect(Collectors.toList());
        System.out.println("Even numbers: " + evens);
        
        // Numbers divisible by both 2 and 3 (i.e., by 6)
        List<Integer> divisibleBy6 = numbers.stream()
                .filter(isEven.and(isDivisibleBy3))
                .collect(Collectors.toList());
        System.out.println("Divisible by 6: " + divisibleBy6);
        
        // Numbers divisible by 2 OR 5
        List<Integer> divisibleBy2Or5 = numbers.stream()
                .filter(isEven.or(isDivisibleBy5))
                .collect(Collectors.toList());
        System.out.println("Divisible by 2 OR 5: " + divisibleBy2Or5);
        
        // Odd numbers (negation of even)
        List<Integer> odds = numbers.stream()
                .filter(isEven.negate())
                .collect(Collectors.toList());
        System.out.println("Odd numbers: " + odds);
        
        // Even prime numbers (should be just 2)
        List<Integer> evenPrimes = numbers.stream()
                .filter(isEven.and(isPrime))
                .collect(Collectors.toList());
        System.out.println("Even prime numbers: " + evenPrimes);
        
        // Perfect squares that are even
        List<Integer> evenPerfectSquares = numbers.stream()
                .filter(isEven.and(isPerfectSquare))
                .collect(Collectors.toList());
        System.out.println("Even perfect squares: " + evenPerfectSquares);
        
        // Complex predicate composition
        Predicate<Integer> complexPredicate = isEven
                .and(n -> n > 10)
                .and(n -> n < 40)
                .and(isDivisibleBy3.negate());
        
        List<Integer> complexResult = numbers.stream()
                .filter(complexPredicate)
                .collect(Collectors.toList());
        System.out.println("Even, >10, <40, NOT divisible by 3: " + complexResult);
    }
    
    /**
     * Demonstrates real-world scenarios
     */
    public static void demonstrateRealWorldScenarios() {
        System.out.println("9. Real-World Scenarios");
        System.out.println("=".repeat(40));
        
        // Scenario 1: Processing sales data
        List<Sale> sales = Arrays.asList(
                new Sale(1, 150.0, "2024-01-15"),
                new Sale(2, 200.0, "2024-01-16"),
                new Sale(3, 175.0, "2024-01-17"),
                new Sale(4, 250.0, "2024-01-18"),
                new Sale(5, 180.0, "2024-01-19"),
                new Sale(6, 300.0, "2024-01-20")
        );
        
        System.out.println("Sales Data Processing:");
        System.out.println("Original sales: " + sales);
        
        // Filter sales with even IDs and amount > 170
        List<Sale> filteredSales = sales.stream()
                .filter(sale -> sale.getId() % 2 == 0)
                .filter(sale -> sale.getAmount() > 170.0)
                .collect(Collectors.toList());
        
        System.out.println("Even ID sales with amount > 170: " + filteredSales);
        
        // Calculate total revenue from even ID sales
        double totalRevenue = sales.stream()
                .filter(sale -> sale.getId() % 2 == 0)
                .mapToDouble(Sale::getAmount)
                .sum();
        
        System.out.printf("Total revenue from even ID sales: $%.2f%n", totalRevenue);
        
        // Scenario 2: Student grade processing
        List<Student> students = Arrays.asList(
                new Student(101, "Alice", 85),
                new Student(102, "Bob", 92),
                new Student(103, "Charlie", 78),
                new Student(104, "Diana", 96),
                new Student(105, "Eve", 88),
                new Student(106, "Frank", 74)
        );
        
        System.out.println("\nStudent Grade Processing:");
        System.out.println("Original students: " + students);
        
        // Students with even IDs who passed (grade >= 80)
        List<Student> passingEvenIdStudents = students.stream()
                .filter(student -> student.getId() % 2 == 0)
                .filter(student -> student.getGrade() >= 80)
                .collect(Collectors.toList());
        
        System.out.println("Passing students with even IDs: " + passingEvenIdStudents);
        
        // Average grade of even ID students
        OptionalDouble averageGrade = students.stream()
                .filter(student -> student.getId() % 2 == 0)
                .mapToInt(Student::getGrade)
                .average();
        
        averageGrade.ifPresent(avg -> 
                System.out.printf("Average grade of even ID students: %.2f%n", avg));
        
        // Scenario 3: Inventory processing
        System.out.println("\nInventory Processing:");
        List<Integer> productIds = Arrays.asList(101, 102, 103, 104, 105, 106, 107, 108, 109, 110);
        List<Integer> quantities = Arrays.asList(15, 22, 8, 45, 12, 30, 5, 18, 25, 40);
        
        // Process even product IDs with sufficient stock (>= 20)
        List<String> restockRecommendations = IntStream.range(0, productIds.size())
                .filter(i -> productIds.get(i) % 2 == 0)
                .filter(i -> quantities.get(i) < 20)
                .mapToObj(i -> "Product " + productIds.get(i) + " needs restock (current: " + quantities.get(i) + ")")
                .collect(Collectors.toList());
        
        System.out.println("Restock recommendations for even product IDs:");
        restockRecommendations.forEach(System.out::println);
    }
    
    /**
     * Helper method to check if a number is prime
     */
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}

/**
 * Sale class for real-world scenario demonstration
 */
class Sale {
    private int id;
    private double amount;
    private String date;
    
    public Sale(int id, double amount, String date) {
        this.id = id;
        this.amount = amount;
        this.date = date;
    }
    
    public int getId() { return id; }
    public double getAmount() { return amount; }
    public String getDate() { return date; }
    
    @Override
    public String toString() {
        return String.format("Sale{id=%d, amount=%.2f, date='%s'}", id, amount, date);
    }
}

/**
 * Student class for real-world scenario demonstration
 */
class Student {
    private int id;
    private String name;
    private int grade;
    
    public Student(int id, String name, int grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
    
    public int getId() { return id; }
    public String getName() { return name; }
    public int getGrade() { return grade; }
    
    @Override
    public String toString() {
        return String.format("Student{id=%d, name='%s', grade=%d}", id, name, grade);
    }
}
