import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Exercise 37: Bytecode Inspector
 * Demonstrates compiling Java code and inspecting bytecode using javap.
 * Shows compilation process, bytecode analysis, and JVM internals.
 */
public class BytecodeInspector {
    
    private static final String TEMP_DIR = "temp_bytecode";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Java Bytecode Inspector");
        System.out.println("======================");
        System.out.println("Compile and inspect Java bytecode using javap");
        System.out.println();
        
        // Create temporary directory
        createTempDirectory();
        
        boolean continueProgram = true;
        
        while (continueProgram) {
            displayMenu();
            System.out.print("Enter your choice (1-8): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
            
            switch (choice) {
                case 1:
                    inspectSimpleClass();
                    break;
                case 2:
                    inspectMethodBytecode();
                    break;
                case 3:
                    inspectConstructorBytecode();
                    break;
                case 4:
                    inspectInheritanceBytecode();
                    break;
                case 5:
                    inspectGenericsBytecode();
                    break;
                case 6:
                    inspectLambdaBytecode();
                    break;
                case 7:
                    createCustomClassAndInspect(scanner);
                    break;
                case 8:
                    continueProgram = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice! Please try again.");
            }
            
            if (continueProgram) {
                System.out.println();
            }
        }
        
        // Cleanup
        cleanupTempDirectory();
        scanner.close();
        System.out.println("Bytecode inspection completed!");
    }
    
    /**
     * Display the main menu
     */
    public static void displayMenu() {
        System.out.println("=== Bytecode Inspector Options ===");
        System.out.println("1. Inspect Simple Class");
        System.out.println("2. Inspect Method Bytecode");
        System.out.println("3. Inspect Constructor Bytecode");
        System.out.println("4. Inspect Inheritance Bytecode");
        System.out.println("5. Inspect Generics Bytecode");
        System.out.println("6. Inspect Lambda Bytecode");
        System.out.println("7. Create Custom Class and Inspect");
        System.out.println("8. Exit");
        System.out.println("==================================");
    }
    
    /**
     * Creates temporary directory for compiled classes
     */
    public static void createTempDirectory() {
        try {
            Path tempPath = Paths.get(TEMP_DIR);
            if (!Files.exists(tempPath)) {
                Files.createDirectory(tempPath);
                System.out.println("✅ Created temporary directory: " + TEMP_DIR);
            }
        } catch (IOException e) {
            System.out.println("❌ Error creating temporary directory: " + e.getMessage());
        }
    }
    
    /**
     * Cleans up temporary directory
     */
    public static void cleanupTempDirectory() {
        try {
            Path tempPath = Paths.get(TEMP_DIR);
            if (Files.exists(tempPath)) {
                Files.walk(tempPath)
                     .sorted(Comparator.reverseOrder())
                     .map(Path::toFile)
                     .forEach(File::delete);
                System.out.println("✅ Cleaned up temporary directory");
            }
        } catch (IOException e) {
            System.out.println("⚠️  Warning: Could not clean up temporary directory: " + e.getMessage());
        }
    }
    
    /**
     * Inspects a simple class
     */
    public static void inspectSimpleClass() {
        System.out.println("1. Inspecting Simple Class");
        System.out.println("=".repeat(40));
        
        String className = "SimpleClass";
        String javaCode = """
            public class SimpleClass {
                private int value;
                private String name;
                
                public SimpleClass() {
                    this.value = 0;
                    this.name = "default";
                }
                
                public int getValue() {
                    return value;
                }
                
                public void setValue(int value) {
                    this.value = value;
                }
                
                public String getName() {
                    return name;
                }
                
                public void setName(String name) {
                    this.name = name;
                }
                
                public String toString() {
                    return "SimpleClass{value=" + value + ", name='" + name + "'}";
                }
            }
            """;
        
        compileAndInspect(className, javaCode, "Simple class with fields and methods");
    }
    
    /**
     * Inspects method bytecode
     */
    public static void inspectMethodBytecode() {
        System.out.println("2. Inspecting Method Bytecode");
        System.out.println("=".repeat(40));
        
        String className = "MathOperations";
        String javaCode = """
            public class MathOperations {
                
                public static int add(int a, int b) {
                    return a + b;
                }
                
                public static int multiply(int a, int b) {
                    return a * b;
                }
                
                public static boolean isEven(int number) {
                    return number % 2 == 0;
                }
                
                public static int factorial(int n) {
                    if (n <= 1) {
                        return 1;
                    }
                    return n * factorial(n - 1);
                }
                
                public static int fibonacci(int n) {
                    if (n <= 1) return n;
                    return fibonacci(n - 1) + fibonacci(n - 2);
                }
            }
            """;
        
        compileAndInspect(className, javaCode, "Mathematical operations with recursion");
    }
    
    /**
     * Inspects constructor bytecode
     */
    public static void inspectConstructorBytecode() {
        System.out.println("3. Inspecting Constructor Bytecode");
        System.out.println("=".repeat(40));
        
        String className = "Person";
        String javaCode = """
            public class Person {
                private String name;
                private int age;
                private static int personCount = 0;
                
                public Person() {
                    this("Unknown", 0);
                }
                
                public Person(String name) {
                    this(name, 0);
                }
                
                public Person(String name, int age) {
                    this.name = name;
                    this.age = age;
                    personCount++;
                }
                
                public String getName() {
                    return name;
                }
                
                public int getAge() {
                    return age;
                }
                
                public static int getPersonCount() {
                    return personCount;
                }
                
                static {
                    System.out.println("Person class loaded");
                }
            }
            """;
        
        compileAndInspect(className, javaCode, "Constructor overloading and static initialization");
    }
    
    /**
     * Inspects inheritance bytecode
     */
    public static void inspectInheritanceBytecode() {
        System.out.println("4. Inspecting Inheritance Bytecode");
        System.out.println("=".repeat(40));
        
        // First compile the parent class
        String parentClassName = "Animal";
        String parentCode = """
            public class Animal {
                protected String name;
                
                public Animal(String name) {
                    this.name = name;
                }
                
                public void makeSound() {
                    System.out.println(name + " makes a sound");
                }
                
                public void move() {
                    System.out.println(name + " is moving");
                }
            }
            """;
        
        String childClassName = "Dog";
        String childCode = """
            public class Dog extends Animal {
                private String breed;
                
                public Dog(String name, String breed) {
                    super(name);
                    this.breed = breed;
                }
                
                @Override
                public void makeSound() {
                    System.out.println(name + " barks: Woof!");
                }
                
                public void wagTail() {
                    System.out.println(name + " is wagging tail");
                }
                
                public String getBreed() {
                    return breed;
                }
            }
            """;
        
        // Compile both classes
        compileClass(parentClassName, parentCode);
        compileAndInspect(childClassName, childCode, "Inheritance with method overriding");
    }
    
    /**
     * Inspects generics bytecode
     */
    public static void inspectGenericsBytecode() {
        System.out.println("5. Inspecting Generics Bytecode");
        System.out.println("=".repeat(40));
        
        String className = "GenericBox";
        String javaCode = """
            import java.util.*;
            
            public class GenericBox<T> {
                private T content;
                private List<T> items;
                
                public GenericBox() {
                    this.items = new ArrayList<>();
                }
                
                public GenericBox(T content) {
                    this();
                    this.content = content;
                }
                
                public T getContent() {
                    return content;
                }
                
                public void setContent(T content) {
                    this.content = content;
                }
                
                public void addItem(T item) {
                    items.add(item);
                }
                
                public List<T> getItems() {
                    return new ArrayList<>(items);
                }
                
                public <U> void processItem(U item, java.util.function.Consumer<U> processor) {
                    processor.accept(item);
                }
                
                public static <V> GenericBox<V> create(V content) {
                    return new GenericBox<>(content);
                }
            }
            """;
        
        compileAndInspect(className, javaCode, "Generic types and type erasure");
    }
    
    /**
     * Inspects lambda bytecode
     */
    public static void inspectLambdaBytecode() {
        System.out.println("6. Inspecting Lambda Bytecode");
        System.out.println("=".repeat(40));
        
        String className = "LambdaExample";
        String javaCode = """
            import java.util.*;
            import java.util.function.*;
            import java.util.stream.Collectors;
            
            public class LambdaExample {
                
                public static void demonstrateLambdas() {
                    List<String> words = Arrays.asList("hello", "world", "java", "lambda");
                    
                    // Simple lambda
                    words.forEach(word -> System.out.println(word));
                    
                    // Method reference
                    words.forEach(System.out::println);
                    
                    // Complex lambda
                    List<String> uppercase = words.stream()
                        .filter(word -> word.length() > 4)
                        .map(String::toUpperCase)
                        .collect(Collectors.toList());
                }
                
                public static void functionalInterfaces() {
                    Predicate<Integer> isEven = n -> n % 2 == 0;
                    Function<String, Integer> stringLength = String::length;
                    Consumer<String> printer = System.out::println;
                    Supplier<String> supplier = () -> "Hello Lambda";
                    
                    // Usage
                    boolean result = isEven.test(42);
                    int length = stringLength.apply("test");
                    printer.accept("Lambda");
                    String message = supplier.get();
                }
                
                public static int processNumbers(List<Integer> numbers) {
                    return numbers.stream()
                        .filter(n -> n > 0)
                        .mapToInt(Integer::intValue)
                        .sum();
                }
            }
            """;
        
        compileAndInspect(className, javaCode, "Lambda expressions and method references");
    }
    
    /**
     * Allows user to create custom class and inspect it
     */
    public static void createCustomClassAndInspect(Scanner scanner) {
        System.out.println("7. Create Custom Class and Inspect");
        System.out.println("=".repeat(40));
        
        System.out.print("Enter class name: ");
        String className = scanner.nextLine().trim();
        
        if (className.isEmpty() || !isValidClassName(className)) {
            System.out.println("❌ Invalid class name!");
            return;
        }
        
        System.out.println("Enter Java code (type 'END' on a new line to finish):");
        StringBuilder codeBuilder = new StringBuilder();
        
        while (true) {
            String line = scanner.nextLine();
            if ("END".equals(line)) {
                break;
            }
            codeBuilder.append(line).append("\n");
        }
        
        String javaCode = codeBuilder.toString();
        
        if (javaCode.trim().isEmpty()) {
            System.out.println("❌ No code entered!");
            return;
        }
        
        compileAndInspect(className, javaCode, "User-defined custom class");
    }
    
    /**
     * Validates class name
     */
    public static boolean isValidClassName(String className) {
        return className.matches("[A-Za-z_$][A-Za-z0-9_$]*");
    }
    
    /**
     * Compiles Java code and inspects bytecode
     */
    public static void compileAndInspect(String className, String javaCode, String description) {
        System.out.println("Description: " + description);
        System.out.println();
        
        // Show the Java source code
        System.out.println("Java Source Code:");
        System.out.println("-".repeat(50));
        System.out.println(javaCode);
        System.out.println("-".repeat(50));
        System.out.println();
        
        // Compile the class
        if (compileClass(className, javaCode)) {
            // Inspect the bytecode
            inspectBytecode(className);
        }
    }
    
    /**
     * Compiles Java source code to bytecode
     */
    public static boolean compileClass(String className, String javaCode) {
        try {
            // Write Java source file
            String sourceFileName = className + ".java";
            Path sourcePath = Paths.get(TEMP_DIR, sourceFileName);
            Files.write(sourcePath, javaCode.getBytes());
            
            System.out.println("📝 Created source file: " + sourceFileName);
            
            // Compile using javac
            ProcessBuilder compilerBuilder = new ProcessBuilder(
                "javac", 
                "-d", TEMP_DIR,
                "-cp", TEMP_DIR,
                sourcePath.toString()
            );
            
            compilerBuilder.redirectErrorStream(true);
            Process compilerProcess = compilerBuilder.start();
            
            // Capture compiler output
            StringBuilder compilerOutput = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(compilerProcess.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    compilerOutput.append(line).append("\n");
                }
            }
            
            boolean success = compilerProcess.waitFor(10, TimeUnit.SECONDS) && 
                             compilerProcess.exitValue() == 0;
            
            if (success) {
                System.out.println("✅ Compilation successful!");
                
                // Show compiled class file info
                Path classPath = Paths.get(TEMP_DIR, className + ".class");
                if (Files.exists(classPath)) {
                    long size = Files.size(classPath);
                    System.out.println("📦 Generated bytecode file: " + className + ".class (" + size + " bytes)");
                }
            } else {
                System.out.println("❌ Compilation failed!");
                if (!compilerOutput.toString().trim().isEmpty()) {
                    System.out.println("Compiler output:");
                    System.out.println(compilerOutput.toString());
                }
            }
            
            return success;
            
        } catch (Exception e) {
            System.out.println("❌ Error during compilation: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Inspects bytecode using javap
     */
    public static void inspectBytecode(String className) {
        System.out.println("🔍 Inspecting Bytecode with javap:");
        System.out.println("=".repeat(50));
        
        try {
            // Basic class info
            runJavap(className, new String[]{"-cp", TEMP_DIR, className}, "Basic Class Information");
            
            // Verbose output
            runJavap(className, new String[]{"-cp", TEMP_DIR, "-v", className}, "Verbose Information (Constants, Fields, Methods)");
            
            // Disassembled code
            runJavap(className, new String[]{"-cp", TEMP_DIR, "-c", className}, "Disassembled Bytecode Instructions");
            
            // Private members
            runJavap(className, new String[]{"-cp", TEMP_DIR, "-p", className}, "All Members (including private)");
            
        } catch (Exception e) {
            System.out.println("❌ Error during bytecode inspection: " + e.getMessage());
        }
    }
    
    /**
     * Runs javap with specified options
     */
    public static void runJavap(String className, String[] command, String description) {
        try {
            System.out.println("\n" + description + ":");
            System.out.println("-".repeat(description.length() + 1));
            
            ProcessBuilder javapBuilder = new ProcessBuilder(command);
            javapBuilder.redirectErrorStream(true);
            Process javapProcess = javapBuilder.start();
            
            // Capture and display javap output
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(javapProcess.getInputStream()))) {
                String line;
                int lineCount = 0;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    lineCount++;
                    
                    // Limit output for very verbose sections
                    if (description.contains("Verbose") && lineCount > 50) {
                        System.out.println("... (output truncated for readability)");
                        break;
                    }
                }
            }
            
            boolean success = javapProcess.waitFor(5, TimeUnit.SECONDS) && 
                             javapProcess.exitValue() == 0;
            
            if (!success) {
                System.out.println("⚠️  javap command may have failed or timed out");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error running javap: " + e.getMessage());
        }
    }
    
    /**
     * Analyzes and explains bytecode instructions
     */
    public static void analyzeBytecodeInstructions() {
        System.out.println("\n📚 Common Bytecode Instructions Explained:");
        System.out.println("=".repeat(45));
        
        Map<String, String> instructions = new LinkedHashMap<>();
        instructions.put("aload_0", "Load 'this' reference onto stack");
        instructions.put("iload_1", "Load int from local variable 1 onto stack");
        instructions.put("aload_1", "Load reference from local variable 1 onto stack");
        instructions.put("iconst_0", "Push int constant 0 onto stack");
        instructions.put("bipush", "Push byte value onto stack as int");
        instructions.put("sipush", "Push short value onto stack as int");
        instructions.put("ldc", "Load constant from constant pool");
        instructions.put("istore_1", "Store int from stack to local variable 1");
        instructions.put("astore_1", "Store reference from stack to local variable 1");
        instructions.put("getfield", "Get field value from object");
        instructions.put("putfield", "Set field value in object");
        instructions.put("getstatic", "Get static field value");
        instructions.put("putstatic", "Set static field value");
        instructions.put("invokevirtual", "Invoke instance method (virtual dispatch)");
        instructions.put("invokestatic", "Invoke static method");
        instructions.put("invokespecial", "Invoke constructor or private method");
        instructions.put("invokeinterface", "Invoke interface method");
        instructions.put("invokedynamic", "Invoke dynamic method (lambdas, etc.)");
        instructions.put("iadd", "Add two ints from stack");
        instructions.put("isub", "Subtract two ints from stack");
        instructions.put("imul", "Multiply two ints from stack");
        instructions.put("idiv", "Divide two ints from stack");
        instructions.put("irem", "Remainder of two ints from stack");
        instructions.put("ireturn", "Return int value");
        instructions.put("areturn", "Return reference value");
        instructions.put("return", "Return void");
        instructions.put("new", "Create new object instance");
        instructions.put("dup", "Duplicate top stack value");
        instructions.put("pop", "Remove top stack value");
        instructions.put("checkcast", "Check if object is of given type");
        instructions.put("instanceof", "Check if object is instance of type");
        instructions.put("ifeq", "Branch if int equals zero");
        instructions.put("ifne", "Branch if int not equals zero");
        instructions.put("if_icmpeq", "Branch if two ints are equal");
        instructions.put("goto", "Unconditional branch");
        
        instructions.forEach((instruction, description) -> 
            System.out.printf("%-15s : %s%n", instruction, description));
        
        System.out.println("\n💡 Key Concepts:");
        System.out.println("• JVM is stack-based (most operations use operand stack)");
        System.out.println("• Local variables are numbered (0 = 'this' for instance methods)");
        System.out.println("• Constant pool stores literals and symbolic references");
        System.out.println("• Method dispatch varies by invocation type");
        System.out.println("• Type information is preserved in bytecode");
    }
}
