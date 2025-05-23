import java.util.*;
import java.util.function.*;

/**
 * Exercise 27: Lambda Expression Sorting
 * Demonstrates lambda expressions for sorting collections.
 * Shows various sorting scenarios using lambda expressions and method references.
 */
public class LambdaSort {
    
    public static void main(String[] args) {
        System.out.println("Lambda Expression Sorting Demonstration");
        System.out.println("=======================================");
        
        // 1. Basic string sorting
        demonstrateBasicStringSorting();
        
        // 2. Custom object sorting
        demonstrateCustomObjectSorting();
        
        // 3. Multiple sorting criteria
        demonstrateMultipleCriteriaSorting();
        
        // 4. Advanced lambda operations
        demonstrateAdvancedLambdaOperations();
        
        // 5. Performance comparison
        demonstratePerformanceComparison();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Lambda sorting demonstrations completed!");
    }
    
    /**
     * Demonstrates basic string sorting with lambda expressions
     */
    public static void demonstrateBasicStringSorting() {
        System.out.println("1. Basic String Sorting with Lambda Expressions");
        System.out.println("=".repeat(50));
        
        List<String> fruits = Arrays.asList("Banana", "Apple", "Orange", "Mango", "Grape", "Kiwi");
        
        System.out.println("Original list: " + fruits);
        
        // 1. Natural order (alphabetical)
        List<String> naturalSort = new ArrayList<>(fruits);
        naturalSort.sort((s1, s2) -> s1.compareTo(s2));
        System.out.println("Alphabetical sort: " + naturalSort);
        
        // 2. Using method reference (equivalent to above)
        List<String> methodRefSort = new ArrayList<>(fruits);
        methodRefSort.sort(String::compareTo);
        System.out.println("Method reference sort: " + methodRefSort);
        
        // 3. Reverse alphabetical order
        List<String> reverseSort = new ArrayList<>(fruits);
        reverseSort.sort((s1, s2) -> s2.compareTo(s1));
        System.out.println("Reverse alphabetical: " + reverseSort);
        
        // 4. Sort by length
        List<String> lengthSort = new ArrayList<>(fruits);
        lengthSort.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));
        System.out.println("Sort by length: " + lengthSort);
        
        // 5. Sort by length using Comparator.comparing
        List<String> lengthSort2 = new ArrayList<>(fruits);
        lengthSort2.sort(Comparator.comparing(String::length));
        System.out.println("Sort by length (Comparator.comparing): " + lengthSort2);
        
        // 6. Case-insensitive sorting
        List<String> mixedCase = Arrays.asList("banana", "Apple", "ORANGE", "mango", "Grape");
        System.out.println("\nMixed case list: " + mixedCase);
        
        List<String> caseInsensitive = new ArrayList<>(mixedCase);
        caseInsensitive.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        System.out.println("Case-insensitive sort: " + caseInsensitive);
        
        // 7. Sort by last character
        List<String> lastCharSort = new ArrayList<>(fruits);
        lastCharSort.sort((s1, s2) -> Character.compare(s1.charAt(s1.length()-1), s2.charAt(s2.length()-1)));
        System.out.println("Sort by last character: " + lastCharSort);
        
        System.out.println();
    }
    
    /**
     * Demonstrates sorting custom objects with lambda expressions
     */
    public static void demonstrateCustomObjectSorting() {
        System.out.println("2. Custom Object Sorting");
        System.out.println("=".repeat(50));
        
        List<Person> people = Arrays.asList(
            new Person("Alice", 30, 75000),
            new Person("Bob", 25, 65000),
            new Person("Charlie", 35, 85000),
            new Person("Diana", 28, 70000),
            new Person("Eve", 32, 80000)
        );
        
        System.out.println("Original list:");
        people.forEach(System.out::println);
        
        // 1. Sort by name
        List<Person> nameSort = new ArrayList<>(people);
        nameSort.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
        System.out.println("\nSorted by name:");
        nameSort.forEach(System.out::println);
        
        // 2. Sort by age (using method reference)
        List<Person> ageSort = new ArrayList<>(people);
        ageSort.sort(Comparator.comparing(Person::getAge));
        System.out.println("\nSorted by age:");
        ageSort.forEach(System.out::println);
        
        // 3. Sort by salary (descending)
        List<Person> salarySort = new ArrayList<>(people);
        salarySort.sort((p1, p2) -> Double.compare(p2.getSalary(), p1.getSalary()));
        System.out.println("\nSorted by salary (descending):");
        salarySort.forEach(System.out::println);
        
        // 4. Sort by salary using Comparator.comparing with reversed
        List<Person> salarySort2 = new ArrayList<>(people);
        salarySort2.sort(Comparator.comparing(Person::getSalary).reversed());
        System.out.println("\nSorted by salary (Comparator.reversed):");
        salarySort2.forEach(System.out::println);
        
        System.out.println();
    }
    
    /**
     * Demonstrates sorting with multiple criteria
     */
    public static void demonstrateMultipleCriteriaSorting() {
        System.out.println("3. Multiple Criteria Sorting");
        System.out.println("=".repeat(50));
        
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "Engineering", 30, 75000),
            new Employee("Bob", "Marketing", 25, 65000),
            new Employee("Charlie", "Engineering", 35, 85000),
            new Employee("Diana", "Marketing", 28, 70000),
            new Employee("Eve", "Engineering", 32, 80000),
            new Employee("Frank", "Sales", 30, 60000),
            new Employee("Grace", "Sales", 30, 62000)
        );
        
        System.out.println("Original employee list:");
        employees.forEach(System.out::println);
        
        // 1. Sort by department, then by age
        List<Employee> deptAgeSort = new ArrayList<>(employees);
        deptAgeSort.sort((e1, e2) -> {
            int deptCompare = e1.getDepartment().compareTo(e2.getDepartment());
            if (deptCompare != 0) {
                return deptCompare;
            }
            return Integer.compare(e1.getAge(), e2.getAge());
        });
        System.out.println("\nSorted by department, then age:");
        deptAgeSort.forEach(System.out::println);
        
        // 2. Using Comparator.thenComparing
        List<Employee> deptAgeSort2 = new ArrayList<>(employees);
        deptAgeSort2.sort(Comparator.comparing(Employee::getDepartment)
                                   .thenComparing(Employee::getAge));
        System.out.println("\nSorted by department, then age (thenComparing):");
        deptAgeSort2.forEach(System.out::println);
        
        // 3. Sort by age, then by salary (descending), then by name
        List<Employee> complexSort = new ArrayList<>(employees);
        complexSort.sort(Comparator.comparing(Employee::getAge)
                                  .thenComparing(Employee::getSalary, Comparator.reverseOrder())
                                  .thenComparing(Employee::getName));
        System.out.println("\nSorted by age, then salary (desc), then name:");
        complexSort.forEach(System.out::println);
        
        // 4. Custom multi-criteria lambda
        List<Employee> customSort = new ArrayList<>(employees);
        customSort.sort((e1, e2) -> {
            // Primary: Department
            int result = e1.getDepartment().compareTo(e2.getDepartment());
            if (result != 0) return result;
            
            // Secondary: Salary (descending)
            result = Double.compare(e2.getSalary(), e1.getSalary());
            if (result != 0) return result;
            
            // Tertiary: Name
            return e1.getName().compareTo(e2.getName());
        });
        System.out.println("\nCustom sort (dept, salary desc, name):");
        customSort.forEach(System.out::println);
        
        System.out.println();
    }
    
    /**
     * Demonstrates advanced lambda operations with sorting
     */
    public static void demonstrateAdvancedLambdaOperations() {
        System.out.println("4. Advanced Lambda Operations");
        System.out.println("=".repeat(50));
        
        List<String> words = Arrays.asList("Lambda", "Stream", "Function", "Predicate", "Consumer", "Supplier");
        
        System.out.println("Original words: " + words);
        
        // 1. Sort and filter in one operation
        List<String> longWordsSorted = words.stream()
                .filter(word -> word.length() > 6)
                .sorted((s1, s2) -> s1.compareTo(s2))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("Long words (>6 chars) sorted: " + longWordsSorted);
        
        // 2. Sort by custom criteria using lambda
        List<String> vowelCountSort = new ArrayList<>(words);
        vowelCountSort.sort((s1, s2) -> {
            int vowels1 = countVowels(s1);
            int vowels2 = countVowels(s2);
            return Integer.compare(vowels1, vowels2);
        });
        System.out.println("Sorted by vowel count: " + vowelCountSort);
        
        // 3. Complex sorting with multiple transformations
        List<String> complexTransform = words.stream()
                .map(String::toLowerCase)
                .sorted(Comparator.comparing(String::length)
                                 .thenComparing(s -> countVowels(s))
                                 .thenComparing(String::compareTo))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("Complex transform sort: " + complexTransform);
        
        // 4. Using custom Comparator with lambda
        Comparator<String> customComparator = (s1, s2) -> {
            // Sort by: consonants count (desc), then length (asc), then alphabetical
            int consonants1 = s1.length() - countVowels(s1);
            int consonants2 = s2.length() - countVowels(s2);
            
            int result = Integer.compare(consonants2, consonants1); // Descending
            if (result != 0) return result;
            
            result = Integer.compare(s1.length(), s2.length()); // Ascending
            if (result != 0) return result;
            
            return s1.compareTo(s2); // Alphabetical
        };
        
        List<String> customSort = new ArrayList<>(words);
        customSort.sort(customComparator);
        System.out.println("Custom comparator sort: " + customSort);
        
        // 5. Functional interface usage
        demonstrateFunctionalInterfaces();
        
        System.out.println();
    }
    
    /**
     * Demonstrates functional interfaces with sorting
     */
    public static void demonstrateFunctionalInterfaces() {
        System.out.println("\nFunctional Interfaces with Sorting:");
        
        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3, 7, 4, 6);
        System.out.println("Original numbers: " + numbers);
        
        // 1. Using Function to transform before sorting
        Function<Integer, String> numberToWord = n -> {
            String[] words = {"zero", "one", "two", "three", "four", "five", 
                            "six", "seven", "eight", "nine"};
            return n < words.length ? words[n] : String.valueOf(n);
        };
        
        List<Integer> wordLengthSort = new ArrayList<>(numbers);
        wordLengthSort.sort(Comparator.comparing(numberToWord.andThen(String::length)));
        System.out.println("Sorted by word length: " + wordLengthSort);
        
        // 2. Using Predicate to filter before sorting
        Predicate<Integer> isEven = n -> n % 2 == 0;
        List<Integer> evenNumbersSorted = numbers.stream()
                .filter(isEven)
                .sorted(Integer::compareTo)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("Even numbers sorted: " + evenNumbersSorted);
        
        // 3. Using Consumer to process after sorting
        Consumer<List<Integer>> printWithIndex = list -> {
            for (int i = 0; i < list.size(); i++) {
                System.out.print("(" + i + ":" + list.get(i) + ") ");
            }
            System.out.println();
        };
        
        List<Integer> descendingSort = new ArrayList<>(numbers);
        descendingSort.sort(Comparator.reverseOrder());
        System.out.print("Descending with indices: ");
        printWithIndex.accept(descendingSort);
    }
    
    /**
     * Demonstrates performance comparison between lambda and traditional sorting
     */
    public static void demonstratePerformanceComparison() {
        System.out.println("5. Performance Comparison");
        System.out.println("=".repeat(50));
        
        // Create large list for performance testing
        List<String> largeList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            largeList.add("String" + random.nextInt(10000));
        }
        
        System.out.println("Performance test with " + largeList.size() + " elements:");
        
        // Test 1: Traditional Comparator
        List<String> list1 = new ArrayList<>(largeList);
        long start1 = System.nanoTime();
        list1.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
        long end1 = System.nanoTime();
        System.out.println("Traditional Comparator: " + (end1 - start1) / 1_000_000 + " ms");
        
        // Test 2: Lambda expression
        List<String> list2 = new ArrayList<>(largeList);
        long start2 = System.nanoTime();
        list2.sort((s1, s2) -> s1.compareTo(s2));
        long end2 = System.nanoTime();
        System.out.println("Lambda expression: " + (end2 - start2) / 1_000_000 + " ms");
        
        // Test 3: Method reference
        List<String> list3 = new ArrayList<>(largeList);
        long start3 = System.nanoTime();
        list3.sort(String::compareTo);
        long end3 = System.nanoTime();
        System.out.println("Method reference: " + (end3 - start3) / 1_000_000 + " ms");
        
        // Test 4: Comparator.naturalOrder()
        List<String> list4 = new ArrayList<>(largeList);
        long start4 = System.nanoTime();
        list4.sort(Comparator.naturalOrder());
        long end4 = System.nanoTime();
        System.out.println("Comparator.naturalOrder(): " + (end4 - start4) / 1_000_000 + " ms");
        
        System.out.println("\nNote: Performance may vary based on JVM optimization and system load.");
        
        // Verify all sorts produced the same result
        boolean allEqual = list1.equals(list2) && list2.equals(list3) && list3.equals(list4);
        System.out.println("All sorting methods produced identical results: " + allEqual);
    }
    
    /**
     * Helper method to count vowels in a string
     */
    public static int countVowels(String str) {
        int count = 0;
        String vowels = "aeiouAEIOU";
        for (char c : str.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }
}

/**
 * Person class for sorting demonstrations
 */
class Person {
    private String name;
    private int age;
    private double salary;
    
    public Person(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
    
    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getSalary() { return salary; }
    
    @Override
    public String toString() {
        return String.format("Person{name='%s', age=%d, salary=%.0f}", name, age, salary);
    }
}

/**
 * Employee class for multiple criteria sorting
 */
class Employee {
    private String name;
    private String department;
    private int age;
    private double salary;
    
    public Employee(String name, String department, int age, double salary) {
        this.name = name;
        this.department = department;
        this.age = age;
        this.salary = salary;
    }
    
    // Getters
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public int getAge() { return age; }
    public double getSalary() { return salary; }
    
    @Override
    public String toString() {
        return String.format("Employee{name='%s', dept='%s', age=%d, salary=%.0f}", 
                           name, department, age, salary);
    }
}
