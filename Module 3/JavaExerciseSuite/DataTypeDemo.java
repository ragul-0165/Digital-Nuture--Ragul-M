/**
 * Exercise 6: Data Type Demonstration
 * Declares variables of all primitive data types and displays their values.
 * Demonstrates Java primitive data types and their ranges.
 */
public class DataTypeDemo {
    public static void main(String[] args) {
        // Declare and initialize variables of all primitive data types
        
        // Integer types
        byte byteVar = 127;                    // 8-bit signed integer
        short shortVar = 32767;                // 16-bit signed integer
        int intVar = 2147483647;               // 32-bit signed integer
        long longVar = 9223372036854775807L;   // 64-bit signed integer
        
        // Floating-point types
        float floatVar = 3.14159f;             // 32-bit floating point
        double doubleVar = 3.141592653589793;  // 64-bit floating point
        
        // Character type
        char charVar = 'A';                    // 16-bit Unicode character
        
        // Boolean type
        boolean booleanVar = true;             // true or false
        
        // Display all primitive data types
        System.out.println("Java Primitive Data Types Demonstration");
        System.out.println("==========================================");
        
        System.out.println("Integer Types:");
        System.out.println("byte: " + byteVar + " (Range: -128 to 127)");
        System.out.println("short: " + shortVar + " (Range: -32,768 to 32,767)");
        System.out.println("int: " + intVar + " (Range: -2^31 to 2^31-1)");
        System.out.println("long: " + longVar + " (Range: -2^63 to 2^63-1)");
        
        System.out.println("\nFloating-Point Types:");
        System.out.println("float: " + floatVar + " (32-bit IEEE 754)");
        System.out.println("double: " + doubleVar + " (64-bit IEEE 754)");
        
        System.out.println("\nOther Types:");
        System.out.println("char: " + charVar + " (16-bit Unicode)");
        System.out.println("boolean: " + booleanVar + " (true or false)");
        
        // Demonstrate size of each data type
        System.out.println("\nSize in bytes:");
        System.out.println("byte: " + Byte.BYTES + " bytes");
        System.out.println("short: " + Short.BYTES + " bytes");
        System.out.println("int: " + Integer.BYTES + " bytes");
        System.out.println("long: " + Long.BYTES + " bytes");
        System.out.println("float: " + Float.BYTES + " bytes");
        System.out.println("double: " + Double.BYTES + " bytes");
        System.out.println("char: " + Character.BYTES + " bytes");
        System.out.println("boolean: Implementation dependent (typically 1 byte)");
    }
}
