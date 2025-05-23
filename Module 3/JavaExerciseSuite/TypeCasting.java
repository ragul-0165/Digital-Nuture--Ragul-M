import java.util.Scanner;

/**
 * Exercise 7: Type Casting Demonstration
 * Demonstrates implicit and explicit type casting between different data types.
 * Shows conversion between double and int, and potential data loss.
 */
public class TypeCasting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Type Casting Demonstration");
        System.out.println("===========================");
        
        // Get a double value from user
        System.out.print("Enter a decimal number: ");
        double doubleValue = scanner.nextDouble();
        
        // Explicit casting: double to int (potential data loss)
        int intFromDouble = (int) doubleValue;
        
        System.out.println("\n1. Explicit Casting (double to int):");
        System.out.println("Original double value: " + doubleValue);
        System.out.println("After casting to int: " + intFromDouble);
        System.out.println("Data lost: " + (doubleValue - intFromDouble));
        
        // Get an integer value from user
        System.out.print("\nEnter an integer number: ");
        int intValue = scanner.nextInt();
        
        // Implicit casting: int to double (no data loss)
        double doubleFromInt = intValue;  // Automatic widening conversion
        
        System.out.println("\n2. Implicit Casting (int to double):");
        System.out.println("Original int value: " + intValue);
        System.out.println("After casting to double: " + doubleFromInt);
        
        // Demonstrate other casting scenarios
        System.out.println("\n3. Other Casting Examples:");
        
        // char to int
        char charValue = 'A';
        int asciiValue = (int) charValue;
        System.out.println("Character '" + charValue + "' to ASCII: " + asciiValue);
        
        // int to char
        int numberValue = 65;
        char characterFromInt = (char) numberValue;
        System.out.println("ASCII " + numberValue + " to character: '" + characterFromInt + "'");
        
        // byte to int (implicit)
        byte byteValue = 100;
        int intFromByte = byteValue;
        System.out.println("Byte " + byteValue + " to int: " + intFromByte);
        
        // int to byte (explicit - potential overflow)
        int largeInt = 300;
        byte byteFromInt = (byte) largeInt;
        System.out.println("Large int " + largeInt + " to byte: " + byteFromInt + " (overflow occurred)");
        
        System.out.println("\nNote: Widening conversions (smaller to larger) are automatic.");
        System.out.println("Narrowing conversions (larger to smaller) require explicit casting.");
        
        scanner.close();
    }
}
