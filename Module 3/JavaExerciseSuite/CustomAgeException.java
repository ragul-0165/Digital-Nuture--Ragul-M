import java.util.Scanner;

/**
 * Exercise 20: Custom Exception Handling
 * Demonstrates creating custom exceptions and advanced exception handling.
 * Creates InvalidAgeException for age validation scenarios.
 */

// Custom exception class
class InvalidAgeException extends Exception {
    private int invalidAge;
    
    // Constructor with message only
    public InvalidAgeException(String message) {
        super(message);
    }
    
    // Constructor with message and age
    public InvalidAgeException(String message, int age) {
        super(message);
        this.invalidAge = age;
    }
    
    // Constructor with message, age, and cause
    public InvalidAgeException(String message, int age, Throwable cause) {
        super(message, cause);
        this.invalidAge = age;
    }
    
    // Getter for invalid age
    public int getInvalidAge() {
        return invalidAge;
    }
    
    // Override toString for better error reporting
    @Override
    public String toString() {
        return "InvalidAgeException: " + getMessage() + 
               (invalidAge != 0 ? " (Age provided: " + invalidAge + ")" : "");
    }
}

// Custom exception for negative age
class NegativeAgeException extends InvalidAgeException {
    public NegativeAgeException(int age) {
        super("Age cannot be negative", age);
    }
}

// Custom exception for unrealistic age
class UnrealisticAgeException extends InvalidAgeException {
    public UnrealisticAgeException(int age) {
        super("Age seems unrealistic for a human", age);
    }
}

// Person class with age validation
class Person {
    private String name;
    private int age;
    private String email;
    
    // Constructor with age validation
    public Person(String name, int age, String email) throws InvalidAgeException {
        this.name = name;
        this.email = email;
        setAge(age); // Use setter for validation
    }
    
    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
    
    // Setter with validation
    public void setAge(int age) throws InvalidAgeException {
        validateAge(age);
        this.age = age;
    }
    
    // Age validation method
    private void validateAge(int age) throws InvalidAgeException {
        if (age < 0) {
            throw new NegativeAgeException(age);
        }
        
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or older for registration", age);
        }
        
        if (age > 150) {
            throw new UnrealisticAgeException(age);
        }
    }
    
    // Method to check if person can vote
    public boolean canVote() throws InvalidAgeException {
        if (age < 0) {
            throw new InvalidAgeException("Cannot determine voting eligibility for negative age", age);
        }
        return age >= 18;
    }
    
    // Method to check if person can retire
    public boolean canRetire() throws InvalidAgeException {
        if (age < 0) {
            throw new InvalidAgeException("Cannot determine retirement eligibility for negative age", age);
        }
        return age >= 65;
    }
    
    // Method to get age category
    public String getAgeCategory() throws InvalidAgeException {
        if (age < 0) {
            throw new InvalidAgeException("Cannot categorize negative age", age);
        }
        
        if (age < 13) return "Child";
        if (age < 20) return "Teenager";
        if (age < 60) return "Adult";
        return "Senior";
    }
    
    @Override
    public String toString() {
        return String.format("Person{name='%s', age=%d, email='%s'}", name, age, email);
    }
}

// Age verification service class
class AgeVerificationService {
    
    // Verify age for different services
    public static void verifyAgeForService(int age, String service) throws InvalidAgeException {
        switch (service.toLowerCase()) {
            case "voting":
                if (age < 18) {
                    throw new InvalidAgeException("Must be 18 or older to vote", age);
                }
                break;
            case "drinking":
                if (age < 21) {
                    throw new InvalidAgeException("Must be 21 or older to drink alcohol", age);
                }
                break;
            case "driving":
                if (age < 16) {
                    throw new InvalidAgeException("Must be 16 or older to drive", age);
                }
                break;
            case "senior_discount":
                if (age < 65) {
                    throw new InvalidAgeException("Must be 65 or older for senior discount", age);
                }
                break;
            default:
                throw new InvalidAgeException("Unknown service: " + service, age);
        }
    }
    
    // Batch age verification
    public static void verifyAges(int[] ages) throws InvalidAgeException {
        for (int i = 0; i < ages.length; i++) {
            if (ages[i] < 0) {
                throw new InvalidAgeException("Negative age found at index " + i, ages[i]);
            }
            if (ages[i] > 200) {
                throw new InvalidAgeException("Unrealistic age found at index " + i, ages[i]);
            }
        }
    }
}

// Main class
public class CustomAgeException {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Custom Age Exception Demonstration");
        System.out.println("==================================");
        
        // Interactive age validation
        boolean continueInput = true;
        while (continueInput) {
            try {
                System.out.print("\nEnter your name: ");
                String name = scanner.nextLine();
                
                System.out.print("Enter your age: ");
                int age = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
                
                System.out.print("Enter your email: ");
                String email = scanner.nextLine();
                
                // Create person with validation
                Person person = new Person(name, age, email);
                
                System.out.println("\n✅ Person created successfully!");
                System.out.println(person);
                
                // Test additional validations
                testPersonCapabilities(person);
                
            } catch (InvalidAgeException e) {
                System.out.println("\n❌ Age Validation Error:");
                System.out.println("   " + e.getMessage());
                if (e.getInvalidAge() != 0) {
                    System.out.println("   Invalid age provided: " + e.getInvalidAge());
                }
                
                // Provide suggestions based on the type of exception
                provideSuggestions(e);
                
            } catch (Exception e) {
                System.out.println("\n❌ Unexpected Error: " + e.getMessage());
                scanner.nextLine(); // Clear buffer
            }
            
            System.out.print("\nDo you want to try again? (y/n): ");
            String response = scanner.nextLine().toLowerCase();
            continueInput = response.equals("y") || response.equals("yes");
        }
        
        // Demonstrate various exception scenarios
        demonstrateExceptionScenarios();
        
        // Test service age verification
        testServiceAgeVerification();
        
        // Test batch age verification
        testBatchAgeVerification();
        
        scanner.close();
        System.out.println("\nThank you for using the Age Validation System!");
    }
    
    /**
     * Test person's capabilities and handle exceptions
     */
    public static void testPersonCapabilities(Person person) {
        try {
            System.out.println("\n--- Capability Assessment ---");
            System.out.println("Age Category: " + person.getAgeCategory());
            System.out.println("Can Vote: " + (person.canVote() ? "Yes" : "No"));
            System.out.println("Can Retire: " + (person.canRetire() ? "Yes" : "No"));
            
        } catch (InvalidAgeException e) {
            System.out.println("❌ Capability Assessment Error: " + e.getMessage());
        }
    }
    
    /**
     * Provide suggestions based on exception type
     */
    public static void provideSuggestions(InvalidAgeException e) {
        if (e instanceof NegativeAgeException) {
            System.out.println("💡 Suggestion: Age must be a positive number.");
        } else if (e instanceof UnrealisticAgeException) {
            System.out.println("💡 Suggestion: Please check if the age is correct. Maximum realistic age is 150.");
        } else if (e.getInvalidAge() > 0 && e.getInvalidAge() < 18) {
            System.out.println("💡 Suggestion: You must be 18 or older to register.");
            int yearsToWait = 18 - e.getInvalidAge();
            System.out.println("   You need to wait " + yearsToWait + " more year(s).");
        }
    }
    
    /**
     * Demonstrate various exception scenarios
     */
    public static void demonstrateExceptionScenarios() {
        System.out.println("\n=== Exception Scenarios Demonstration ===");
        
        // Test cases with different invalid ages
        int[] testAges = {-5, 10, 17, 25, 151, 200};
        String[] testNames = {"Negative Age", "Child", "Minor", "Adult", "Too Old", "Unrealistic"};
        
        for (int i = 0; i < testAges.length; i++) {
            System.out.println("\n" + (i + 1) + ". Testing " + testNames[i] + " (Age: " + testAges[i] + "):");
            
            try {
                Person testPerson = new Person("Test User " + (i + 1), testAges[i], "test@email.com");
                System.out.println("   ✅ Person created: " + testPerson);
                
            } catch (NegativeAgeException e) {
                System.out.println("   ❌ NegativeAgeException: " + e.getMessage());
                
            } catch (UnrealisticAgeException e) {
                System.out.println("   ❌ UnrealisticAgeException: " + e.getMessage());
                
            } catch (InvalidAgeException e) {
                System.out.println("   ❌ InvalidAgeException: " + e.getMessage());
            }
        }
    }
    
    /**
     * Test service age verification
     */
    public static void testServiceAgeVerification() {
        System.out.println("\n=== Service Age Verification ===");
        
        String[] services = {"voting", "drinking", "driving", "senior_discount"};
        int[] testAges = {15, 18, 20, 25, 65, 70};
        
        for (String service : services) {
            System.out.println("\nTesting " + service + " eligibility:");
            
            for (int age : testAges) {
                try {
                    AgeVerificationService.verifyAgeForService(age, service);
                    System.out.println("   Age " + age + ": ✅ Eligible for " + service);
                    
                } catch (InvalidAgeException e) {
                    System.out.println("   Age " + age + ": ❌ " + e.getMessage());
                }
            }
        }
    }
    
    /**
     * Test batch age verification
     */
    public static void testBatchAgeVerification() {
        System.out.println("\n=== Batch Age Verification ===");
        
        // Test valid ages
        int[] validAges = {18, 25, 35, 45, 65, 80};
        System.out.println("Testing valid ages: " + java.util.Arrays.toString(validAges));
        
        try {
            AgeVerificationService.verifyAges(validAges);
            System.out.println("✅ All ages are valid!");
            
        } catch (InvalidAgeException e) {
            System.out.println("❌ Batch validation failed: " + e.getMessage());
        }
        
        // Test invalid ages
        int[] invalidAges = {18, 25, -5, 45, 65}; // Contains negative age
        System.out.println("\nTesting invalid ages: " + java.util.Arrays.toString(invalidAges));
        
        try {
            AgeVerificationService.verifyAges(invalidAges);
            System.out.println("✅ All ages are valid!");
            
        } catch (InvalidAgeException e) {
            System.out.println("❌ Batch validation failed: " + e.getMessage());
        }
        
        // Test unrealistic ages
        int[] unrealisticAges = {18, 25, 35, 250, 65}; // Contains unrealistic age
        System.out.println("\nTesting unrealistic ages: " + java.util.Arrays.toString(unrealisticAges));
        
        try {
            AgeVerificationService.verifyAges(unrealisticAges);
            System.out.println("✅ All ages are valid!");
            
        } catch (InvalidAgeException e) {
            System.out.println("❌ Batch validation failed: " + e.getMessage());
        }
    }
}
