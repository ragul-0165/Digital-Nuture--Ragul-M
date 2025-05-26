/**
 * Exercise 8: Operator Precedence Demonstration
 * Evaluates complex expressions and explains operator precedence rules.
 * Demonstrates how Java evaluates expressions with multiple operators.
 */
public class OperatorPrecedence {
    public static void main(String[] args) {
        System.out.println("Operator Precedence Demonstration");
        System.out.println("==================================");
        
        // Complex expression to evaluate
        int a = 10, b = 5, c = 3, d = 2;
        
        System.out.println("Given values:");
        System.out.println("a = " + a + ", b = " + b + ", c = " + c + ", d = " + d);
        System.out.println();
        
        // Expression 1: Arithmetic operators
        int result1 = a + b * c - d;
        System.out.println("Expression 1: a + b * c - d");
        System.out.println("Step-by-step evaluation:");
        System.out.println("1. b * c = " + b + " * " + c + " = " + (b * c) + " (multiplication first)");
        System.out.println("2. a + (b * c) = " + a + " + " + (b * c) + " = " + (a + b * c));
        System.out.println("3. (a + b * c) - d = " + (a + b * c) + " - " + d + " = " + result1);
        System.out.println("Result: " + result1);
        System.out.println();
        
        // Expression 2: With parentheses
        int result2 = (a + b) * c - d;
        System.out.println("Expression 2: (a + b) * c - d");
        System.out.println("Step-by-step evaluation:");
        System.out.println("1. (a + b) = (" + a + " + " + b + ") = " + (a + b) + " (parentheses first)");
        System.out.println("2. (a + b) * c = " + (a + b) + " * " + c + " = " + ((a + b) * c));
        System.out.println("3. ((a + b) * c) - d = " + ((a + b) * c) + " - " + d + " = " + result2);
        System.out.println("Result: " + result2);
        System.out.println();
        
        // Expression 3: Mixed operators with precedence
        boolean result3 = a > b && c < d || a == 10;
        System.out.println("Expression 3: a > b && c < d || a == 10");
        System.out.println("Step-by-step evaluation:");
        System.out.println("1. a > b = " + a + " > " + b + " = " + (a > b) + " (relational operators)");
        System.out.println("2. c < d = " + c + " < " + d + " = " + (c < d));
        System.out.println("3. a == 10 = " + a + " == 10 = " + (a == 10));
        System.out.println("4. (a > b) && (c < d) = " + (a > b) + " && " + (c < d) + " = " + ((a > b) && (c < d)) + " (&& has higher precedence than ||)");
        System.out.println("5. ((a > b) && (c < d)) || (a == 10) = " + ((a > b) && (c < d)) + " || " + (a == 10) + " = " + result3);
        System.out.println("Result: " + result3);
        System.out.println();
        
        // Expression 4: Assignment with arithmetic
        int x = 5;
        int result4 = x += 3 * 2;  // Same as x = x + (3 * 2)
        System.out.println("Expression 4: x += 3 * 2 (where x initially = 5)");
        System.out.println("Step-by-step evaluation:");
        System.out.println("1. 3 * 2 = " + (3 * 2) + " (multiplication first)");
        System.out.println("2. x += (3 * 2) means x = x + " + (3 * 2) + " = 5 + " + (3 * 2) + " = " + result4);
        System.out.println("Result: x = " + result4);
        System.out.println();
        
        // Operator precedence table
        System.out.println("Java Operator Precedence (highest to lowest):");
        System.out.println("1. Postfix operators: expr++, expr--");
        System.out.println("2. Unary operators: ++expr, --expr, +expr, -expr, ~, !");
        System.out.println("3. Multiplicative: *, /, %");
        System.out.println("4. Additive: +, -");
        System.out.println("5. Shift: <<, >>, >>>");
        System.out.println("6. Relational: <, >, <=, >=, instanceof");
        System.out.println("7. Equality: ==, !=");
        System.out.println("8. Bitwise AND: &");
        System.out.println("9. Bitwise XOR: ^");
        System.out.println("10. Bitwise OR: |");
        System.out.println("11. Logical AND: &&");
        System.out.println("12. Logical OR: ||");
        System.out.println("13. Ternary: ? :");
        System.out.println("14. Assignment: =, +=, -=, *=, /=, %=, etc.");
    }
}
