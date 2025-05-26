import java.util.*;

// Record declarations for pattern matching examples
record Point2D(double x, double y) {}
record CircleShape(Point2D center, double radius) {}
record RectangleShape(Point2D topLeft, Point2D bottomRight) {}
record Person(String name, int age) {}
record CompanyData(String name, List<String> departments) {}
record AddressData(String street, String city, String state, String zip) {}
record EmployeeData(String name, int age, AddressData address, double salary) {}
record DepartmentData(String name, List<EmployeeData> employees) {}

/**
 * Exercise 30: Pattern Matching with Switch Demo
 * Demonstrates pattern matching with switch expressions (Java 17+).
 * Shows type patterns, guard conditions, and modern switch features.
 */
public class PatternSwitchDemo {
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Pattern Matching with Switch Demonstration");
            System.out.println("==========================================");
            System.out.println("Java 17+ Pattern Matching Features");
            System.out.println();
            
            boolean continueProgram = true;
            
            while (continueProgram) {
                displayMenu();
                System.out.print("Enter your choice (1-7, 0 to exit): ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
                
                switch (choice) {
                    case 0:
                        continueProgram = false;
                        break;
                    case 1:
                        demonstrateBasicPatternMatching();
                        break;
                    case 2:
                        demonstrateTypePatterns();
                        break;
                    case 3:
                        demonstrateGuardedPatterns();
                        break;
                    case 4:
                        demonstrateRecordPatterns();
                        break;
                    case 5:
                        demonstrateNestedPatterns();
                        break;
                    case 6:
                        demonstrateExpressionEvaluation();
                        break;
                    case 7:
                        demonstratePerformanceComparison();
                        break;
                    default:
                        System.out.println("❌ Invalid choice! Please try again.");
                }
                
                if (continueProgram) {
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }
            }
            
            System.out.println("Thank you for exploring Pattern Matching!");
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Display the main menu
     */
    public static void displayMenu() {
        System.out.println("=== Pattern Matching Demonstrations ===");
        System.out.println("1. Basic Pattern Matching");
        System.out.println("2. Type Patterns");
        System.out.println("3. Guarded Patterns");
        System.out.println("4. Record Patterns");
        System.out.println("5. Nested Patterns");
        System.out.println("6. Interactive Pattern Demo");
        System.out.println("7. Real-World Examples");
        System.out.println("8. Performance Comparison");
        System.out.println("9. Exit");
        System.out.println("=======================================");
    }
    
    /**
     * Demonstrates basic pattern matching with switch
     */
    public static void demonstrateBasicPatternMatching() {
        System.out.println("1. Basic Pattern Matching");
        System.out.println("=".repeat(35));
        
        // Traditional switch vs pattern matching
        Object[] testObjects = {
            "Hello World",
            42,
            3.14,
            true,
            'A',
            new ArrayList<>(Arrays.asList(1, 2, 3)),
            null
        };
        
        System.out.println("Testing various objects with pattern matching:");
        System.out.println();
        
        for (Object obj : testObjects) {
            System.out.println("Object: " + obj + " (" + (obj != null ? obj.getClass().getSimpleName() : "null") + ")");
            
            // Old way (without pattern matching)
            String oldWayResult = classifyObjectOldWay(obj);
            System.out.println("  Old way result: " + oldWayResult);
            
            // New way (with pattern matching)
            String newWayResult = classifyObjectWithPatterns(obj);
            System.out.println("  Pattern matching result: " + newWayResult);
            
            System.out.println();
        }
    }
    
    /**
     * Old way of type checking and casting
     */
    public static String classifyObjectOldWay(Object obj) {
        if (obj == null) {
            return "null value";
        } else if (obj instanceof String) {
            String s = (String) obj;
            return "String with length " + s.length();
        } else if (obj instanceof Integer) {
            Integer i = (Integer) obj;
            return "Integer: " + (i % 2 == 0 ? "even" : "odd");
        } else if (obj instanceof Double) {
            Double d = (Double) obj;
            return "Double: " + (d > 0 ? "positive" : d < 0 ? "negative" : "zero");
        } else if (obj instanceof Boolean) {
            Boolean b = (Boolean) obj;
            return "Boolean: " + (b ? "true" : "false");
        } else if (obj instanceof List) {
            List<?> list = (List<?>) obj;
            return "List with " + list.size() + " elements";
        } else {
            return "Unknown type: " + obj.getClass().getSimpleName();
        }
    }
    
    /**
     * New way using pattern matching with switch
     */
    public static String classifyObjectWithPatterns(Object obj) {
        return switch (obj) {
            case null -> "null value";
            case String s -> "String with length " + s.length();
            case Integer i -> "Integer: " + (i % 2 == 0 ? "even" : "odd");
            case Double d -> "Double: " + (d > 0 ? "positive" : d < 0 ? "negative" : "zero");
            case Boolean b -> "Boolean: " + (b ? "true" : "false");
            case List<?> list -> "List with " + list.size() + " elements";
            default -> "Unknown type: " + obj.getClass().getSimpleName();
        };
    }
    
    /**
     * Demonstrates type patterns in detail
     */
    public static void demonstrateTypePatterns() {
        System.out.println("2. Type Patterns");
        System.out.println("=".repeat(35));
        
        Object[] objects = {
            "Pattern Matching",
            123,
            45.67,
            Arrays.asList("a", "b", "c"),
            new HashMap<>(Map.of("key", "value")),
            new StringBuilder("builder"),
            new int[]{1, 2, 3, 4, 5}
        };
        
        System.out.println("Demonstrating type patterns with automatic casting:");
        System.out.println();
        
        for (Object obj : objects) {
            String analysis = analyzeObject(obj);
            System.out.println("Object: " + obj);
            System.out.println("Analysis: " + analysis);
            System.out.println();
        }
    }
    
    /**
     * Analyzes objects using type patterns
     */
    public static String analyzeObject(Object obj) {
        return switch (obj) {
            case String s when s.length() > 10 -> 
                "Long string (length: " + s.length() + "): '" + s.substring(0, 10) + "...'";
            case String s -> 
                "Short string (length: " + s.length() + "): '" + s + "'";
            case Integer i when i > 100 -> 
                "Large integer: " + i;
            case Integer i when i < 0 -> 
                "Negative integer: " + i;
            case Integer i -> 
                "Small positive integer: " + i;
            case Double d when d.isNaN() -> 
                "NaN double value";
            case Double d when d.isInfinite() -> 
                "Infinite double value";
            case Double d -> 
                String.format("Double value: %.2f", d);
            case List<?> list when list.isEmpty() -> 
                "Empty list";
            case List<?> list when list.size() == 1 -> 
                "Singleton list: [" + list.get(0) + "]";
            case List<?> list -> 
                "List with " + list.size() + " elements: " + list;
            case Map<?, ?> map when map.isEmpty() -> 
                "Empty map";
            case Map<?, ?> map -> 
                "Map with " + map.size() + " entries: " + map;
            case StringBuilder sb -> 
                "StringBuilder with content: '" + sb.toString() + "'";
            case int[] array -> 
                "Integer array with " + array.length + " elements: " + Arrays.toString(array);
            case null -> 
                "Null reference";
            default -> 
                "Unknown type: " + obj.getClass().getSimpleName();
        };
    }
    
    /**
     * Demonstrates guarded patterns (when clauses)
     */
    public static void demonstrateGuardedPatterns() {
        System.out.println("3. Guarded Patterns");
        System.out.println("=".repeat(35));
        
        System.out.println("Demonstrating guarded patterns with when clauses:");
        System.out.println();
        
        // Test various numbers
        int[] testNumbers = {-10, -1, 0, 1, 5, 10, 15, 25, 50, 100, 1000};
        
        for (int num : testNumbers) {
            String classification = classifyNumber(num);
            System.out.printf("Number %4d: %s%n", num, classification);
        }
        
        System.out.println();
        
        // Test various strings
        String[] testStrings = {"", "a", "ab", "abc", "hello", "Hello World!", "UPPERCASE", "lowercase"};
        
        System.out.println("String classifications:");
        for (String str : testStrings) {
            String classification = classifyString(str);
            System.out.printf("String %-15s: %s%n", "'" + str + "'", classification);
        }
    }
    
    /**
     * Classifies numbers using guarded patterns
     */
    public static String classifyNumber(Object obj) {
        return switch (obj) {
            case Integer i when i < 0 -> "Negative number";
            case Integer i when i == 0 -> "Zero";
            case Integer i when i == 1 -> "One (unit)";
            case Integer i when i > 1 && i <= 10 -> "Small positive (2-10)";
            case Integer i when i > 10 && i <= 100 -> "Medium positive (11-100)";
            case Integer i when i > 100 -> "Large positive (>100)";
            case Double d when d.isNaN() -> "Not a Number";
            case Double d when d.isInfinite() -> "Infinite";
            case Double d when d < 0 -> "Negative decimal";
            case Double d when d == 0.0 -> "Zero decimal";
            case Double _ -> "Positive decimal";
            default -> "Not a number";
        };
    }
    
    /**
     * Classifies strings using guarded patterns
     */
    /**
     * Classifies strings using guarded patterns
     */
    public static String classifyString(Object obj) {
        return switch (obj) {
            case String s when s.isEmpty() -> "Empty string";
            case String s when s.length() == 1 -> "Single character: '" + s + "'";
            case String s when s.length() <= 3 -> "Short string (2-3 chars): '" + s + "'";
            case String s when s.equals(s.toUpperCase()) -> "All uppercase: '" + s + "'";
            case String s when s.equals(s.toLowerCase()) -> "All lowercase: '" + s + "'";
            case String s when s.matches("\\d+") -> "Numeric string: '" + s + "'";
            case String s when s.contains(" ") -> "Multi-word string: '" + s + "'";
            case String s -> "Regular string: '" + s + "'";
            default -> "Not a string";
        };
    }
    
    /**
     * Demonstrates record patterns (Java 19+ preview, Java 21 final)
     */
    public static void demonstrateRecordPatterns() {
        System.out.println("4. Record Patterns");
        System.out.println("=".repeat(35));
        
        // Create some record instances using the Person record defined at the top
        Person alice = new Person("Alice", 30);
        Person bob = new Person("Bob", 25);
        Person charlie = new Person("Charlie", 35);
        
        // Pattern matching with records
        System.out.println(describePerson(alice));
        System.out.println(describePerson(bob));
        System.out.println(describePerson(charlie));
        
        // Nested record patterns using the records defined at the top
        AddressData addr1 = new AddressData("123 Main St", "Springfield", "IL", "12345");
        AddressData addr2 = new AddressData("456 Oak Ave", "Chicago", "IL", "60601");
        
        EmployeeData emp1 = new EmployeeData("Alice", 30, addr1, 75000);
        EmployeeData emp2 = new EmployeeData("Bob", 25, addr2, 65000);
        
        // Describe employees using pattern matching
        System.out.println("Employee details:");
        for (EmployeeData emp : List.of(emp1, emp2)) {
            String details = switch (emp) {
                case EmployeeData(var name, var age, AddressData _, var salary) when age < 30 -> 
                    String.format("%s (Young employee): $%,.2f", name, salary);
                case EmployeeData(var name, var age, AddressData _, var salary) -> 
                    String.format("%s (%d years): $%,.2f", name, age, salary);
            };
            System.out.println("- " + details);
        }
    }
    
    /**
     * Analyzes objects using type patterns
     */
    public static String describePerson(Object obj) {
        return switch (obj) {
            case Person p when p.age() < 18 -> 
                p.name() + " is a minor";
            case Person p when p.age() < 65 -> 
                p.name() + " is an adult";
            case Person p -> 
                p.name() + " is a senior";
            default -> "Not a person";
        };
    
    }
    
    /**
     * Demonstrates nested patterns with company and employee data
     */
    public static void demonstrateNestedPatterns() {
        System.out.println("5. Nested Patterns");
        System.out.println("================");
        
        // Create test data
        AddressData addr1 = new AddressData("123 Main St", "Springfield", "IL", "62701");
        AddressData addr2 = new AddressData("456 Oak Ave", "Chicago", "IL", "60601");
        
        EmployeeData emp1 = new EmployeeData("Alice", 30, addr1, 75000);
        EmployeeData emp2 = new EmployeeData("Bob", 25, addr2, 65000);
        
        List<EmployeeData> engineeringTeam = new ArrayList<>();
        engineeringTeam.add(emp1);
        engineeringTeam.add(emp2);
        
        DepartmentData engineering = new DepartmentData("Engineering", engineeringTeam);
        DepartmentData sales = new DepartmentData("Sales", List.of(emp1));
        
        CompanyData company = new CompanyData("TechCorp", List.of("Engineering", "Sales"));
        
        // Test individual components
        Object[] testObjects = {addr1, emp1, engineering, company};
        
        System.out.println("Analyzing individual components:");
        for (Object obj : testObjects) {
            String componentAnalysis = analyzeComponent(obj);
            System.out.println("Component: " + componentAnalysis);
        }
        
        // Using the sales variable to fix the unused warning
        System.out.println("\nSales department has " + sales.employees().size() + " employees");
    }
    
    /**
     * Analyzes company using nested patterns
     */
    public static String analyzeCompany(Object obj) {
        return switch (obj) {
            case CompanyData(var name, var departments) when departments.isEmpty() -> 
                "Empty company: " + name;
            case CompanyData(var name, var departments) when departments.size() == 1 -> 
                "Small company '" + name + "' with 1 department";
            case CompanyData(var name, var departments) -> 
                "Company '" + name + "' with " + departments.size() + " departments";
            default -> "Not a company";
        };
    }
    
    /**
     * Analyzes individual components of a company
     */
    public static String analyzeComponent(Object obj) {
        return switch (obj) {
            case AddressData a -> 
                String.format("Address: %s, %s, %s %s", a.street(), a.city(), a.state(), a.zip());
            case EmployeeData e ->
                String.format("Employee: %s, age %d, salary $%,.2f", 
                    e.name(), e.age(), e.salary());
            case DepartmentData d ->
                String.format("Department: %s with %d employees", 
                    d.name(), d.employees().size());
            case CompanyData c ->
                String.format("Company: %s with %d departments", 
                    c.name(), c.departments().size());
            default -> "Unknown component: " + obj.getClass().getSimpleName();
        };
    }
    
    /**
     * Demonstrates expression evaluation
     */
    public static void demonstrateExpressionEvaluation() {
        System.out.println("6. Expression Evaluation");
        System.out.println("======================");
        
        // Create test expressions
        Expr[] expressions = {
            new Num(42),
            new AddExpr(new Num(10), new Num(5)),
            new MultExpr(new Num(3), new Num(7)),
            new SubExpr(new Num(20), new Num(8)),
            new AddExpr(new MultExpr(new Num(2), new Num(3)), new Num(4))
        };
        
        for (Expr expr : expressions) {
            double result = evaluateExpression(expr);
            String exprString = expressionToString(expr);
            System.out.println("Expression: " + exprString + " = " + result);
        }
    }
    
    /**
     * Evaluates mathematical expressions using pattern matching
     */
    /**
     * Evaluates mathematical expressions using pattern matching
     */
    public static double evaluateExpression(Expr expr) {
        if (expr instanceof Num n) {
            return n.value();
        } else if (expr instanceof AddExpr a) {
            return evaluateExpression(a.left()) + evaluateExpression(a.right());
        } else if (expr instanceof MultExpr m) {
            return evaluateExpression(m.left()) * evaluateExpression(m.right());
        } else if (expr instanceof SubExpr s) {
            return evaluateExpression(s.left()) - evaluateExpression(s.right());
        }
        throw new IllegalArgumentException("Unknown expression type: " + 
            (expr != null ? expr.getClass().getName() : "null"));
    }
    
    /**
     * Converts expressions to string representation
     */
    public static String expressionToString(Expr expr) {
        if (expr instanceof Num n) {
            return String.valueOf(n.value());
        } else if (expr instanceof AddExpr a) {
            return "(" + expressionToString(a.left()) + " + " + expressionToString(a.right()) + ")";
        } else if (expr instanceof MultExpr m) {
            return "(" + expressionToString(m.left()) + " * " + expressionToString(m.right()) + ")";
        } else if (expr instanceof SubExpr s) {
            return "(" + expressionToString(s.left()) + " - " + expressionToString(s.right()) + ")";
        }
        return "Unknown: " + (expr != null ? expr.getClass().getSimpleName() : "null");
    }
    
    /**
     * Demonstrates performance comparison
     */
    public static void demonstratePerformanceComparison() {
        System.out.println("8. Performance Comparison");
        System.out.println("=".repeat(35));
        
        // Create test data
        Object[] testData = new Object[1_000_000];
        Random random = new Random();
        
        for (int i = 0; i < testData.length; i++) {
            int type = random.nextInt(4);
            // Removed unused variable 's' to fix lint warning
            testData[i] = switch (type) {
                case 0 -> "String" + i;
                case 1 -> random.nextInt(1000);
                case 2 -> random.nextDouble() * 100;
                case 3 -> random.nextBoolean();
                default -> throw new IllegalStateException("Unexpected value: " + type);
            };
        }
        
        System.out.println("Performance test with " + testData.length + " objects:");
        System.out.println();
        
        // Test traditional instanceof approach
        long startTime = System.nanoTime();
        int traditionalCount = 0;
        for (Object obj : testData) {
            if (isStringOldWay(obj)) {
                traditionalCount++;
            }
        }
        long traditionalTime = System.nanoTime() - startTime;
        
        // Test pattern matching approach
        startTime = System.nanoTime();
        int patternCount = 0;
        for (Object obj : testData) {
            if (isStringWithPattern(obj)) {
                patternCount++;
            }
        }
        long patternTime = System.nanoTime() - startTime;
        
        System.out.println("Traditional instanceof:");
        System.out.println("  Time: " + traditionalTime / 1_000_000 + " ms");
        System.out.println("  Strings found: " + traditionalCount);
        
        System.out.println("\nPattern matching:");
        System.out.println("  Time: " + patternTime / 1_000_000 + " ms");
        System.out.println("  Strings found: " + patternCount);
        
        double speedup = (double) traditionalTime / patternTime;
        System.out.printf("\nRelative performance: %.2fx %s%n", 
                Math.abs(speedup), 
                speedup > 1 ? "(pattern matching faster)" : "(traditional faster)");
        
        System.out.println("\nNote: Performance may vary based on JVM optimization.");
        System.out.println("Pattern matching is generally comparable or slightly better.");
    }
    
    /**
     * Traditional instanceof check
     */
    public static boolean isStringOldWay(Object obj) {
        return obj instanceof String;
    }
    
    /**
     * Pattern matching check
     */
    public static boolean isStringWithPattern(Object obj) {
        return switch (obj) {
            case String _ -> true;
            default -> false;
        };
    }
    
    // Define record types at class level for nested patterns demo
    record HTTPResponse(int statusCode, String body) {}
    
    // Expression types for evaluation demo
    interface Expr {}
    record Num(double value) implements Expr {}
    record AddExpr(Expr left, Expr right) implements Expr {}
    record MultExpr(Expr left, Expr right) implements Expr {}
    record SubExpr(Expr left, Expr right) implements Expr {}
}