/**
 * Main class demonstrating the use of Java Modules.
 * Uses the Utils class from the same module.
 * 
 * To use with Java Modules, you would need a module-info.java file like this:
 * 
 * module com.example.demo {
 *     // No requires needed since both classes are in the same module
 *     
 *     // Export the package containing Utils if other modules need to use it
 *     exports com.example.demo;
 * }
 */
public class Main {
    public static void main(String[] args) {
        try {
            // Get the name from command line arguments or use a default
            String name = args.length > 0 ? args[0] : "Java Learner";
            
            // Use the Utils class to get a greeting
            String greeting = Utils.greet(name);
            
            // Print the greeting
            System.out.println(greeting);
            
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
