import java.util.*;
import java.util.function.Function;

/**
 * Exercise 29: Java Records Demonstration
 * Demonstrates the use of records (Java 14+) to define Person(name, age).
 * Shows record features, benefits, and various use cases.
 */

// Basic Person record
public record PersonRecord(String name, int age) {
    
    // Compact constructor for validation
    public PersonRecord {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        if (age > 150) {
            throw new IllegalArgumentException("Age cannot be greater than 150");
        }
        // Normalize the name
        name = name.trim();
    }
    
    // Additional constructor
    public PersonRecord(String name) {
        this(name, 0);
    }
    
    // Instance methods
    public String getAgeCategory() {
        if (age < 13) return "Child";
        if (age < 20) return "Teenager";
        if (age < 60) return "Adult";
        return "Senior";
    }
    
    public boolean isAdult() {
        return age >= 18;
    }
    
    public PersonRecord withAge(int newAge) {
        return new PersonRecord(name, newAge);
    }
    
    public PersonRecord withName(String newName) {
        return new PersonRecord(newName, age);
    }
    
    // Static factory methods
    public static PersonRecord child(String name, int age) {
        if (age >= 13) {
            throw new IllegalArgumentException("Child must be under 13 years old");
        }
        return new PersonRecord(name, age);
    }
    
    public static PersonRecord adult(String name, int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Adult must be 18 or older");
        }
        return new PersonRecord(name, age);
    }
    
    // Main demonstration method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Java Records Demonstration (PersonRecord)");
        System.out.println("=========================================");
        System.out.println("Demonstrating Java 14+ Records feature");
        System.out.println();
        
        boolean continueProgram = true;
        
        while (continueProgram) {
            displayMenu();
            System.out.print("Enter your choice (1-10): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
            
            switch (choice) {
                case 1:
                    demonstrateBasicRecordFeatures();
                    break;
                case 2:
                    createPersonInteractively(scanner);
                    break;
                case 3:
                    demonstrateRecordMethods();
                    break;
                case 4:
                    demonstrateRecordValidation();
                    break;
                case 5:
                    demonstrateRecordCollections();
                    break;
                case 6:
                    demonstrateRecordComparison();
                    break;
                case 7:
                    demonstrateRecordSerialization();
                    break;
                case 8:
                    demonstrateAdvancedRecordFeatures();
                    break;
                case 9:
                    compareRecordVsClass();
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
        System.out.println("Thank you for exploring Java Records!");
    }
    
    /**
     * Display the main menu
     */
    public static void displayMenu() {
        System.out.println("=== Java Records Demonstrations ===");
        System.out.println("1. Basic Record Features");
        System.out.println("2. Create Person Interactively");
        System.out.println("3. Record Methods Demo");
        System.out.println("4. Record Validation");
        System.out.println("5. Records in Collections");
        System.out.println("6. Record Comparison");
        System.out.println("7. Record Serialization");
        System.out.println("8. Advanced Record Features");
        System.out.println("9. Record vs Class Comparison");
        System.out.println("10. Exit");
        System.out.println("===================================");
    }
    
    /**
     * Demonstrates basic record features
     */
    public static void demonstrateBasicRecordFeatures() {
        System.out.println("1. Basic Record Features");
        System.out.println("=".repeat(30));
        
        // Creating records
        PersonRecord person1 = new PersonRecord("Alice", 25);
        PersonRecord person2 = new PersonRecord("Bob", 30);
        
        System.out.println("Created records:");
        System.out.println("person1: " + person1);
        System.out.println("person2: " + person2);
        
        // Accessing components (automatic getter methods)
        System.out.println("\nAccessing components:");
        System.out.println("person1.name(): " + person1.name());
        System.out.println("person1.age(): " + person1.age());
        System.out.println("person2.name(): " + person2.name());
        System.out.println("person2.age(): " + person2.age());
        
        // toString() is automatically generated
        System.out.println("\nAutomatic toString():");
        System.out.println("person1.toString(): " + person1.toString());
        
        // equals() and hashCode() are automatically generated
        PersonRecord person1Copy = new PersonRecord("Alice", 25);
        System.out.println("\nAutomatic equals() and hashCode():");
        System.out.println("person1.equals(person1Copy): " + person1.equals(person1Copy));
        System.out.println("person1.hashCode(): " + person1.hashCode());
        System.out.println("person1Copy.hashCode(): " + person1Copy.hashCode());
        System.out.println("HashCodes equal: " + (person1.hashCode() == person1Copy.hashCode()));
        
        // Records are immutable
        System.out.println("\nRecord immutability:");
        System.out.println("Records are immutable - all fields are final");
        System.out.println("Cannot modify person1.name or person1.age after creation");
        
        // Using alternative constructor
        PersonRecord child = new PersonRecord("Charlie");
        System.out.println("Using single-parameter constructor: " + child);
    }
    
    /**
     * Create person interactively
     */
    public static void createPersonInteractively(Scanner scanner) {
        System.out.println("2. Create Person Interactively");
        System.out.println("=".repeat(30));
        
        try {
            System.out.print("Enter person's name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter person's age: ");
            int age = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
            
            PersonRecord person = new PersonRecord(name, age);
            
            System.out.println("✅ Person created successfully!");
            System.out.println("Person: " + person);
            System.out.println("Age category: " + person.getAgeCategory());
            System.out.println("Is adult: " + person.isAdult());
            
            // Demonstrate immutable updates
            System.out.println("\nDemonstrating immutable updates:");
            PersonRecord olderPerson = person.withAge(person.age() + 1);
            PersonRecord renamedPerson = person.withName("New " + person.name());
            
            System.out.println("Original: " + person);
            System.out.println("With age + 1: " + olderPerson);
            System.out.println("With modified name: " + renamedPerson);
            
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Validation Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Input Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates record methods
     */
    public static void demonstrateRecordMethods() {
        System.out.println("3. Record Methods Demo");
        System.out.println("=".repeat(30));
        
        PersonRecord[] people = {
                new PersonRecord("Alice", 8),
                new PersonRecord("Bob", 16),
                new PersonRecord("Charlie", 25),
                new PersonRecord("Diana", 45),
                new PersonRecord("Eve", 67)
        };
        
        System.out.println("People collection:");
        for (PersonRecord person : people) {
            System.out.printf("%-10s (age %2d) - %s, Adult: %s%n", 
                    person.name(), person.age(), 
                    person.getAgeCategory(), person.isAdult());
        }
        
        // Using static factory methods
        System.out.println("\nUsing static factory methods:");
        try {
            PersonRecord child = PersonRecord.child("Tommy", 10);
            PersonRecord adult = PersonRecord.adult("Sarah", 30);
            
            System.out.println("Child: " + child);
            System.out.println("Adult: " + adult);
            
            // This will throw an exception
            PersonRecord invalidChild = PersonRecord.child("Invalid", 15);
            
        } catch (IllegalArgumentException e) {
            System.out.println("Expected validation error: " + e.getMessage());
        }
        
        // Demonstrating method chaining with immutable updates
        System.out.println("\nMethod chaining with immutable updates:");
        PersonRecord original = new PersonRecord("John", 20);
        PersonRecord modified = original
                .withAge(25)
                .withName("John Smith");
        
        System.out.println("Original: " + original);
        System.out.println("Modified: " + modified);
    }
    
    /**
     * Demonstrates record validation
     */
    public static void demonstrateRecordValidation() {
        System.out.println("4. Record Validation");
        System.out.println("=".repeat(30));
        
        System.out.println("Testing various validation scenarios:");
        
        // Valid cases
        String[] validNames = {"Alice", " Bob ", "Charlie Smith"};
        int[] validAges = {0, 25, 100, 150};
        
        System.out.println("\nValid cases:");
        for (String name : validNames) {
            for (int age : validAges) {
                try {
                    PersonRecord person = new PersonRecord(name, age);
                    System.out.println("✅ " + person + " (name trimmed if needed)");
                } catch (Exception e) {
                    System.out.println("❌ Unexpected error: " + e.getMessage());
                }
            }
        }
        
        // Invalid cases
        System.out.println("\nInvalid cases:");
        
        // Invalid names
        String[] invalidNames = {null, "", "   "};
        for (String name : invalidNames) {
            try {
                PersonRecord person = new PersonRecord(name, 25);
                System.out.println("❌ Should have failed: " + person);
            } catch (IllegalArgumentException e) {
                System.out.println("✅ Correctly rejected name '" + name + "': " + e.getMessage());
            }
        }
        
        // Invalid ages
        int[] invalidAges = {-1, -10, 151, 200};
        for (int age : invalidAges) {
            try {
                PersonRecord person = new PersonRecord("Test", age);
                System.out.println("❌ Should have failed: " + person);
            } catch (IllegalArgumentException e) {
                System.out.println("✅ Correctly rejected age " + age + ": " + e.getMessage());
            }
        }
    }
    
    /**
     * Demonstrates records in collections
     */
    public static void demonstrateRecordCollections() {
        System.out.println("5. Records in Collections");
        System.out.println("=".repeat(30));
        
        List<PersonRecord> people = Arrays.asList(
                new PersonRecord("Alice", 25),
                new PersonRecord("Bob", 30),
                new PersonRecord("Charlie", 35),
                new PersonRecord("Diana", 28),
                new PersonRecord("Eve", 42)
        );
        
        System.out.println("Original list:");
        people.forEach(System.out::println);
        
        // Sorting by age
        System.out.println("\nSorted by age:");
        people.stream()
                .sorted(Comparator.comparing(PersonRecord::age))
                .forEach(System.out::println);
        
        // Sorting by name
        System.out.println("\nSorted by name:");
        people.stream()
                .sorted(Comparator.comparing(PersonRecord::name))
                .forEach(System.out::println);
        
        // Filtering adults
        System.out.println("\nAdults only:");
        people.stream()
                .filter(PersonRecord::isAdult)
                .forEach(System.out::println);
        
        // Grouping by age category
        System.out.println("\nGrouped by age category:");
        Map<String, List<PersonRecord>> groupedByCategory = people.stream()
                .collect(Collectors.groupingBy(PersonRecord::getAgeCategory));
        
        groupedByCategory.forEach((category, personList) -> {
            System.out.println(category + ": " + personList);
        });
        
        // Using as map keys (thanks to automatic hashCode/equals)
        System.out.println("\nUsing records as map keys:");
        Map<PersonRecord, String> personRoles = new HashMap<>();
        personRoles.put(new PersonRecord("Alice", 25), "Developer");
        personRoles.put(new PersonRecord("Bob", 30), "Manager");
        personRoles.put(new PersonRecord("Charlie", 35), "Architect");
        
        // Lookup works because of proper equals/hashCode
        PersonRecord lookupKey = new PersonRecord("Alice", 25);
        String role = personRoles.get(lookupKey);
        System.out.println("Role for " + lookupKey + ": " + role);
        
        // Set operations (thanks to proper equals/hashCode)
        Set<PersonRecord> uniquePeople = new HashSet<>(people);
        uniquePeople.add(new PersonRecord("Alice", 25)); // Duplicate, won't be added
        System.out.println("\nUnique people in set: " + uniquePeople.size());
    }
    
    /**
     * Demonstrates record comparison
     */
    public static void demonstrateRecordComparison() {
        System.out.println("6. Record Comparison");
        System.out.println("=".repeat(30));
        
        PersonRecord person1 = new PersonRecord("Alice", 25);
        PersonRecord person2 = new PersonRecord("Alice", 25);
        PersonRecord person3 = new PersonRecord("Bob", 25);
        PersonRecord person4 = new PersonRecord("Alice", 30);
        
        System.out.println("Comparison tests:");
        System.out.println("person1: " + person1);
        System.out.println("person2: " + person2);
        System.out.println("person3: " + person3);
        System.out.println("person4: " + person4);
        System.out.println();
        
        // Equality tests
        System.out.println("Equality tests:");
        System.out.println("person1.equals(person2): " + person1.equals(person2) + " (same name and age)");
        System.out.println("person1.equals(person3): " + person1.equals(person3) + " (different name)");
        System.out.println("person1.equals(person4): " + person1.equals(person4) + " (different age)");
        System.out.println("person1 == person2: " + (person1 == person2) + " (different objects)");
        
        // HashCode tests
        System.out.println("\nHashCode tests:");
        System.out.println("person1.hashCode(): " + person1.hashCode());
        System.out.println("person2.hashCode(): " + person2.hashCode());
        System.out.println("person3.hashCode(): " + person3.hashCode());
        System.out.println("Equal objects have same hashCode: " + (person1.hashCode() == person2.hashCode()));
        
        // Structural equality
        System.out.println("\nStructural equality demonstration:");
        PersonRecord reconstructed = new PersonRecord(person1.name(), person1.age());
        System.out.println("Reconstructed: " + reconstructed);
        System.out.println("person1.equals(reconstructed): " + person1.equals(reconstructed));
        
        // Custom comparison logic
        Comparator<PersonRecord> ageComparator = Comparator.comparing(PersonRecord::age);
        Comparator<PersonRecord> nameComparator = Comparator.comparing(PersonRecord::name);
        Comparator<PersonRecord> combinedComparator = nameComparator.thenComparing(ageComparator);
        
        List<PersonRecord> people = Arrays.asList(person1, person3, person4, person2);
        System.out.println("\nSorting with custom comparators:");
        System.out.println("Original order: " + people);
        
        List<PersonRecord> sortedByAge = people.stream()
                .sorted(ageComparator)
                .collect(Collectors.toList());
        System.out.println("Sorted by age: " + sortedByAge);
        
        List<PersonRecord> sortedByName = people.stream()
                .sorted(nameComparator)
                .collect(Collectors.toList());
        System.out.println("Sorted by name: " + sortedByName);
    }
    
    /**
     * Demonstrates record serialization
     */
    public static void demonstrateRecordSerialization() {
        System.out.println("7. Record Serialization");
        System.out.println("=".repeat(30));
        
        PersonRecord person = new PersonRecord("Alice", 25);
        System.out.println("Original person: " + person);
        
        // JSON-like string representation (manual)
        String jsonLike = String.format("{\"name\":\"%s\",\"age\":%d}", person.name(), person.age());
        System.out.println("JSON-like representation: " + jsonLike);
        
        // Demonstrating record components via reflection
        System.out.println("\nRecord components via reflection:");
        Class<PersonRecord> recordClass = PersonRecord.class;
        System.out.println("Is record: " + recordClass.isRecord());
        
        var recordComponents = recordClass.getRecordComponents();
        System.out.println("Record components:");
        for (var component : recordComponents) {
            System.out.printf("  - %s %s%n", component.getType().getSimpleName(), component.getName());
        }
        
        // Simulating deserialization
        System.out.println("\nSimulating deserialization:");
        Map<String, Object> data = Map.of("name", "Bob", "age", 30);
        PersonRecord deserialized = new PersonRecord(
                (String) data.get("name"),
                (Integer) data.get("age")
        );
        System.out.println("Deserialized person: " + deserialized);
        
        // Record canonical constructor can be used for deserialization
        System.out.println("Records provide canonical constructor suitable for deserialization frameworks");
    }
    
    /**
     * Demonstrates advanced record features
     */
    public static void demonstrateAdvancedRecordFeatures() {
        System.out.println("8. Advanced Record Features");
        System.out.println("=".repeat(30));
        
        // Nested records
        record Address(String street, String city, String zipCode) {}
        record PersonWithAddress(String name, int age, Address address) {
            public String getFullAddress() {
                return address.street() + ", " + address.city() + " " + address.zipCode();
            }
        }
        
        Address address = new Address("123 Main St", "Springfield", "12345");
        PersonWithAddress personWithAddr = new PersonWithAddress("John", 30, address);
        
        System.out.println("Nested records:");
        System.out.println("Person with address: " + personWithAddr);
        System.out.println("Full address: " + personWithAddr.getFullAddress());
        
        // Record implementing interface
        interface Identifiable {
            String getId();
        }
        
        record Employee(String name, int age, String employeeId) implements Identifiable {
            @Override
            public String getId() {
                return employeeId;
            }
        }
        
        Employee emp = new Employee("Jane", 28, "EMP001");
        System.out.println("\nRecord implementing interface:");
        System.out.println("Employee: " + emp);
        System.out.println("ID: " + emp.getId());
        
        // Generic records
        record Pair<T, U>(T first, U second) {
            public boolean hasSameTypes() {
                return first.getClass().equals(second.getClass());
            }
        }
        
        Pair<String, Integer> nameAge = new Pair<>("Alice", 25);
        Pair<Integer, Integer> coordinates = new Pair<>(10, 20);
        
        System.out.println("\nGeneric records:");
        System.out.println("Name-Age pair: " + nameAge);
        System.out.println("Coordinates: " + coordinates);
        System.out.println("Name-Age has same types: " + nameAge.hasSameTypes());
        System.out.println("Coordinates has same types: " + coordinates.hasSameTypes());
        
        // Record with validation and transformation
        record NormalizedPerson(String name, int age) {
            public NormalizedPerson {
                // Compact constructor with validation and normalization
                Objects.requireNonNull(name, "Name cannot be null");
                name = name.trim().toLowerCase();
                if (name.isEmpty()) {
                    throw new IllegalArgumentException("Name cannot be empty");
                }
                if (age < 0 || age > 150) {
                    throw new IllegalArgumentException("Age must be between 0 and 150");
                }
            }
            
            public String getDisplayName() {
                return Arrays.stream(name.split("\\s+"))
                        .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                        .collect(Collectors.joining(" "));
            }
        }
        
        NormalizedPerson normalized = new NormalizedPerson("  ALICE SMITH  ", 25);
        System.out.println("\nNormalized person:");
        System.out.println("Stored name: '" + normalized.name() + "'");
        System.out.println("Display name: '" + normalized.getDisplayName() + "'");
    }
    
    /**
     * Compares records vs traditional classes
     */
    public static void compareRecordVsClass() {
        System.out.println("9. Record vs Class Comparison");
        System.out.println("=".repeat(30));
        
        // Traditional class (for comparison)
        class PersonClass {
            private final String name;
            private final int age;
            
            public PersonClass(String name, int age) {
                this.name = name;
                this.age = age;
            }
            
            public String getName() { return name; }
            public int getAge() { return age; }
            
            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                PersonClass that = (PersonClass) obj;
                return age == that.age && Objects.equals(name, that.name);
            }
            
            @Override
            public int hashCode() {
                return Objects.hash(name, age);
            }
            
            @Override
            public String toString() {
                return "PersonClass{name='" + name + "', age=" + age + '}';
            }
        }
        
        PersonClass personClass = new PersonClass("Alice", 25);
        PersonRecord personRecord = new PersonRecord("Alice", 25);
        
        System.out.println("Comparison of equivalent Person implementations:");
        System.out.println();
        
        System.out.println("Traditional class:");
        System.out.println("  Creation: new PersonClass(\"Alice\", 25)");
        System.out.println("  Access: personClass.getName(), personClass.getAge()");
        System.out.println("  toString(): " + personClass.toString());
        System.out.println("  Lines of code: ~25 lines");
        System.out.println();
        
        System.out.println("Record:");
        System.out.println("  Creation: new PersonRecord(\"Alice\", 25)");
        System.out.println("  Access: personRecord.name(), personRecord.age()");
        System.out.println("  toString(): " + personRecord.toString());
        System.out.println("  Lines of code: 1 line (plus optional methods)");
        System.out.println();
        
        System.out.println("Benefits of Records:");
        System.out.println("  ✅ Concise syntax");
        System.out.println("  ✅ Automatic equals(), hashCode(), toString()");
        System.out.println("  ✅ Immutable by default");
        System.out.println("  ✅ Clear intent (data carrier)");
        System.out.println("  ✅ No boilerplate code");
        System.out.println("  ✅ Pattern matching support (Java 17+)");
        System.out.println();
        
        System.out.println("When to use Records:");
        System.out.println("  ✅ Simple data carriers");
        System.out.println("  ✅ DTOs (Data Transfer Objects)");
        System.out.println("  ✅ Value objects");
        System.out.println("  ✅ Configuration objects");
        System.out.println("  ✅ Return types for multiple values");
        System.out.println();
        
        System.out.println("When NOT to use Records:");
        System.out.println("  ❌ Need mutable state");
        System.out.println("  ❌ Need inheritance (records are final)");
        System.out.println("  ❌ Complex business logic");
        System.out.println("  ❌ Need to extend other classes");
        
        // Performance comparison (simple)
        System.out.println("\nSimple performance comparison:");
        long start = System.nanoTime();
        for (int i = 0; i < 100_000; i++) {
            PersonClass pc = new PersonClass("Test", i);
            pc.hashCode();
        }
        long classTime = System.nanoTime() - start;
        
        start = System.nanoTime();
        for (int i = 0; i < 100_000; i++) {
            PersonRecord pr = new PersonRecord("Test", i);
            pr.hashCode();
        }
        long recordTime = System.nanoTime() - start;
        
        System.out.printf("Class time: %d ms%n", classTime / 1_000_000);
        System.out.printf("Record time: %d ms%n", recordTime / 1_000_000);
        System.out.println("Performance is typically similar or slightly better for records");
    }
}
