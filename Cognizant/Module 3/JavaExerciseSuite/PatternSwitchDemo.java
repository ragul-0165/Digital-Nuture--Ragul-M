import java.util.*;
import java.util.function.*;

/**
 * Exercise 30: Pattern Matching with Switch Demo
 * Demonstrates pattern matching with switch expressions (Java 17+).
 * Shows type patterns, guard conditions, and modern switch features.
 */
public class PatternSwitchDemo {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Pattern Matching with Switch Demonstration");
        System.out.println("==========================================");
        System.out.println("Java 17+ Pattern Matching Features");
        System.out.println();
        
        boolean continueProgram = true;
        
        while (continueProgram) {
            displayMenu();
            System.out.print("Enter your choice (1-9): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
            
            switch (choice) {
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
                    interactivePatternDemo(scanner);
                    break;
                case 7:
                    demonstrateRealWorldExamples();
                    break;
                case 8:
                    demonstratePerformanceComparison();
                    break;
                case 9:
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
        System.out.println("Thank you for exploring Pattern Matching!");
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
            case Double d -> "Positive decimal";
            default -> "Not a number";
        };
    }
    
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
        
        // Define records for demonstration
        record Point(int x, int y) {}
        record Circle(Point center, int radius) {}
        record Rectangle(Point topLeft, Point bottomRight) {}
        record Person(String name, int age) {}
        
        // Create test objects
        Object[] shapes = {
            new Point(0, 0),
            new Point(5, 5),
            new Circle(new Point(0, 0), 5),
            new Circle(new Point(10, 10), 15),
            new Rectangle(new Point(0, 0), new Point(10, 10)),
            new Rectangle(new Point(-5, -5), new Point(5, 5)),
            new Person("Alice", 25),
            "Not a shape"
        };
        
        System.out.println("Analyzing shapes with record patterns:");
        System.out.println();
        
        for (Object shape : shapes) {
            String description = describeShape(shape);
            System.out.println("Shape: " + shape);
            System.out.println("Description: " + description);
            System.out.println();
        }
    }
    
    /**
     * Describes shapes using record patterns
     */
    public static String describeShape(Object shape) {
        return switch (shape) {
            // Simple record patterns
            case Point(var x, var y) when x == 0 && y == 0 -> 
                "Origin point";
            case Point(var x, var y) when x == y -> 
                "Point on diagonal: (" + x + ", " + y + ")";
            case Point(var x, var y) -> 
                "Point at coordinates: (" + x + ", " + y + ")";
            
            // Nested record patterns
            case Circle(Point(var x, var y), var radius) when x == 0 && y == 0 -> 
                "Circle centered at origin with radius " + radius;
            case Circle(Point(var x, var y), var radius) when radius > 10 -> 
                "Large circle (radius " + radius + ") at (" + x + ", " + y + ")";
            case Circle(Point(var x, var y), var radius) -> 
                "Small circle (radius " + radius + ") at (" + x + ", " + y + ")";
            
            // Rectangle patterns
            case Rectangle(Point(var x1, var y1), Point(var x2, var y2)) when x1 == x2 || y1 == y2 -> 
                "Degenerate rectangle (line or point)";
            case Rectangle(Point(var x1, var y1), Point(var x2, var y2)) -> {
                int width = Math.abs(x2 - x1);
                int height = Math.abs(y2 - y1);
                int area = width * height;
                yield "Rectangle: " + width + "x" + height + " (area: " + area + ")";
            }
            
            // Person pattern (different record type)
            case Person(var name, var age) when age < 18 -> 
                "Minor: " + name + " (age " + age + ")";
            case Person(var name, var age) -> 
                "Adult: " + name + " (age " + age + ")";
            
            default -> 
                "Unknown shape type: " + shape.getClass().getSimpleName();
        };
    }
    
    /**
     * Demonstrates nested patterns
     */
    public static void demonstrateNestedPatterns() {
        System.out.println("5. Nested Patterns");
        System.out.println("=".repeat(35));
        
        // Complex nested structures
        record Address(String street, String city, String state, String zip) {}
        record Employee(String name, int age, Address address, double salary) {}
        record Department(String name, List<Employee> employees) {}
        record Company(String name, List<Department> departments) {}
        
        // Create test data
        Address addr1 = new Address("123 Main St", "Springfield", "IL", "62701");
        Address addr2 = new Address("456 Oak Ave", "Chicago", "IL", "60601");
        Address addr3 = new Address("789 Pine Rd", "Peoria", "IL", "61602");
        
        Employee emp1 = new Employee("Alice", 30, addr1, 75000);
        Employee emp2 = new Employee("Bob", 25, addr2, 65000);
        Employee emp3 = new Employee("Charlie", 35, addr3, 85000);
        
        Department engineering = new Department("Engineering", Arrays.asList(emp1, emp3));
        Department sales = new Department("Sales", Arrays.asList(emp2));
        
        Company company = new Company("TechCorp", Arrays.asList(engineering, sales));
        
        System.out.println("Analyzing company structure with nested patterns:");
        System.out.println();
        
        String analysis = analyzeCompany(company);
        System.out.println(analysis);
        
        // Test individual components
        Object[] testObjects = {addr1, emp1, engineering, company};
        
        System.out.println("\nAnalyzing individual components:");
        for (Object obj : testObjects) {
            String componentAnalysis = analyzeComponent(obj);
            System.out.println("Component: " + componentAnalysis);
        }
    }
    
    /**
     * Analyzes company using nested patterns
     */
    public static String analyzeCompany(Object obj) {
        return switch (obj) {
            case Company(var name, var departments) when departments.isEmpty() -> 
                "Empty company: " + name;
            case Company(var name, var departments) when departments.size() == 1 -> 
                "Small company '" + name + "' with 1 department";
            case Company(var name, var departments) -> {
                int totalEmployees = departments.stream()
                    .mapToInt(dept -> dept.employees().size())
                    .sum();
                yield "Company '" + name + "' with " + departments.size() + 
                      " departments and " + totalEmployees + " total employees";
            }
            default -> "Not a company";
        };
    }
    
    /**
     * Analyzes various components using patterns
     */
    public static String analyzeComponent(Object obj) {
        return switch (obj) {
            case Address(var street, var city, var state, var zip) when "IL".equals(state) -> 
                "Illinois address: " + street + ", " + city + " " + zip;
            case Address(var street, var city, var state, var zip) -> 
                "Address: " + street + ", " + city + ", " + state + " " + zip;
            case Employee(var name, var age, Address(var street, var city, var state, var zip), var salary) 
                when salary > 80000 -> 
                "High-paid employee " + name + " (age " + age + ") earning $" + salary + 
                " in " + city + ", " + state;
            case Employee(var name, var age, var address, var salary) -> 
                "Employee " + name + " (age " + age + ") earning $" + salary;
            case Department(var name, var employees) when employees.isEmpty() -> 
                "Empty department: " + name;
            case Department(var name, var employees) -> 
                "Department '" + name + "' with " + employees.size() + " employees";
            default -> 
                "Unknown component: " + obj.getClass().getSimpleName();
        };
    }
    
    /**
     * Interactive pattern matching demo
     */
    public static void interactivePatternDemo(Scanner scanner) {
        System.out.println("6. Interactive Pattern Demo");
        System.out.println("=".repeat(35));
        
        System.out.println("Enter various values to see pattern matching in action!");
        System.out.println("(Type 'quit' to return to main menu)");
        System.out.println();
        
        while (true) {
            System.out.print("Enter a value (string, number, or 'null'): ");
            String input = scanner.nextLine().trim();
            
            if ("quit".equalsIgnoreCase(input)) {
                break;
            }
            
            Object value = parseInput(input);
            String result = analyzeUserInput(value);
            
            System.out.println("Input: " + input);
            System.out.println("Parsed as: " + (value != null ? value.getClass().getSimpleName() : "null"));
            System.out.println("Analysis: " + result);
            System.out.println();
        }
    }
    
    /**
     * Parses user input into appropriate type
     */
    public static Object parseInput(String input) {
        if ("null".equalsIgnoreCase(input)) {
            return null;
        }
        
        // Try to parse as integer
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            // Not an integer
        }
        
        // Try to parse as double
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            // Not a double
        }
        
        // Try to parse as boolean
        if ("true".equalsIgnoreCase(input) || "false".equalsIgnoreCase(input)) {
            return Boolean.parseBoolean(input);
        }
        
        // Return as string
        return input;
    }
    
    /**
     * Analyzes user input using pattern matching
     */
    public static String analyzeUserInput(Object value) {
        return switch (value) {
            case null -> "You entered a null value";
            case String s when s.isEmpty() -> "You entered an empty string";
            case String s when s.length() == 1 -> "Single character: '" + s + "'";
            case String s when s.matches("\\d+") -> "Numeric string: " + s;
            case String s when s.matches("[a-zA-Z]+") -> "Alphabetic string: " + s;
            case String s -> "String with " + s.length() + " characters: '" + s + "'";
            case Integer i when i == 0 -> "Zero integer";
            case Integer i when i > 0 && i <= 10 -> "Small positive integer: " + i;
            case Integer i when i > 10 -> "Large positive integer: " + i;
            case Integer i when i < 0 -> "Negative integer: " + i;
            case Double d when d == 0.0 -> "Zero double";
            case Double d when d > 0 -> "Positive double: " + d;
            case Double d when d < 0 -> "Negative double: " + d;
            case Boolean b -> "Boolean value: " + b;
            default -> "Unexpected type: " + value.getClass().getSimpleName();
        };
    }
    
    /**
     * Demonstrates real-world examples
     */
    public static void demonstrateRealWorldExamples() {
        System.out.println("7. Real-World Examples");
        System.out.println("=".repeat(35));
        
        // JSON-like data processing
        demonstrateJSONProcessing();
        
        // HTTP response handling
        demonstrateHTTPResponseHandling();
        
        // Expression evaluation
        demonstrateExpressionEvaluation();
    }
    
    /**
     * Demonstrates JSON-like data processing
     */
    public static void demonstrateJSONProcessing() {
        System.out.println("JSON-like Data Processing:");
        System.out.println("-".repeat(25));
        
        // Simulate different JSON value types
        Object[] jsonValues = {
            "string value",
            42,
            3.14,
            true,
            null,
            Arrays.asList("array", "of", "strings"),
            Map.of("key1", "value1", "key2", 42)
        };
        
        for (Object value : jsonValues) {
            String jsonString = convertToJSON(value);
            System.out.println("Value: " + value + " -> JSON: " + jsonString);
        }
        System.out.println();
    }
    
    /**
     * Converts values to JSON string representation
     */
    public static String convertToJSON(Object value) {
        return switch (value) {
            case null -> "null";
            case String s -> "\"" + s.replace("\"", "\\\"") + "\"";
            case Integer i -> i.toString();
            case Double d -> d.toString();
            case Boolean b -> b.toString();
            case List<?> list -> "[" + 
                list.stream()
                    .map(PatternSwitchDemo::convertToJSON)
                    .reduce((a, b) -> a + "," + b)
                    .orElse("") + "]";
            case Map<?, ?> map -> "{" +
                map.entrySet().stream()
                    .map(entry -> convertToJSON(entry.getKey()) + ":" + convertToJSON(entry.getValue()))
                    .reduce((a, b) -> a + "," + b)
                    .orElse("") + "}";
            default -> "\"" + value.toString() + "\"";
        };
    }
    
    /**
     * Demonstrates HTTP response handling
     */
    public static void demonstrateHTTPResponseHandling() {
        System.out.println("HTTP Response Handling:");
        System.out.println("-".repeat(22));
        
        // Simulate different HTTP responses
        record HTTPResponse(int statusCode, String body) {}
        
        HTTPResponse[] responses = {
            new HTTPResponse(200, "Success"),
            new HTTPResponse(404, "Not Found"),
            new HTTPResponse(500, "Internal Server Error"),
            new HTTPResponse(301, "Moved Permanently"),
            new HTTPResponse(403, "Forbidden")
        };
        
        for (HTTPResponse response : responses) {
            String handling = handleHTTPResponse(response);
            System.out.println("Response " + response.statusCode() + ": " + handling);
        }
        System.out.println();
    }
    
    /**
     * Handles HTTP responses using pattern matching
     */
    public static String handleHTTPResponse(Object response) {
        return switch (response) {
            case HTTPResponse(int code, var body) when code >= 200 && code < 300 -> 
                "Success: " + body;
            case HTTPResponse(int code, var body) when code >= 300 && code < 400 -> 
                "Redirect: " + body;
            case HTTPResponse(int code, var body) when code >= 400 && code < 500 -> 
                "Client Error: " + body;
            case HTTPResponse(int code, var body) when code >= 500 -> 
                "Server Error: " + body;
            default -> 
                "Unknown response type";
        };
    }
    
    /**
     * Demonstrates expression evaluation
     */
    public static void demonstrateExpressionEvaluation() {
        System.out.println("Expression Evaluation:");
        System.out.println("-".repeat(21));
        
        // Define expression types
        interface Expression {}
        record Number(double value) implements Expression {}
        record Add(Expression left, Expression right) implements Expression {}
        record Multiply(Expression left, Expression right) implements Expression {}
        record Subtract(Expression left, Expression right) implements Expression {}
        
        // Create test expressions
        Expression[] expressions = {
            new Number(42),
            new Add(new Number(10), new Number(5)),
            new Multiply(new Number(3), new Number(7)),
            new Subtract(new Number(20), new Number(8)),
            new Add(new Multiply(new Number(2), new Number(3)), new Number(4))
        };
        
        for (Expression expr : expressions) {
            double result = evaluateExpression(expr);
            String exprString = expressionToString(expr);
            System.out.println("Expression: " + exprString + " = " + result);
        }
    }
    
    /**
     * Evaluates mathematical expressions using pattern matching
     */
    public static double evaluateExpression(Object expr) {
        return switch (expr) {
            case Number(var value) -> value;
            case Add(var left, var right) -> 
                evaluateExpression(left) + evaluateExpression(right);
            case Multiply(var left, var right) -> 
                evaluateExpression(left) * evaluateExpression(right);
            case Subtract(var left, var right) -> 
                evaluateExpression(left) - evaluateExpression(right);
            default -> 
                throw new IllegalArgumentException("Unknown expression type");
        };
    }
    
    /**
     * Converts expressions to string representation
     */
    public static String expressionToString(Object expr) {
        return switch (expr) {
            case Number(var value) -> String.valueOf(value);
            case Add(var left, var right) -> 
                "(" + expressionToString(left) + " + " + expressionToString(right) + ")";
            case Multiply(var left, var right) -> 
                "(" + expressionToString(left) + " * " + expressionToString(right) + ")";
            case Subtract(var left, var right) -> 
                "(" + expressionToString(left) + " - " + expressionToString(right) + ")";
            default -> "Unknown";
        };
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
            case String s -> true;
            default -> false;
        };
    }
    
    // Define record types at class level for nested patterns demo
    record HTTPResponse(int statusCode, String body) {}
    record Number(double value) {}
    record Add(Object left, Object right) {}
    record Multiply(Object left, Object right) {}
    record Subtract(Object left, Object right) {}
}
