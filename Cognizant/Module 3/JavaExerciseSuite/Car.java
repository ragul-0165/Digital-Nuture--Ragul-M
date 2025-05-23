/**
 * Exercise 16: Car Class - Object-Oriented Programming
 * Demonstrates class creation, attributes, methods, and object instantiation.
 * Shows encapsulation, constructors, and method overloading.
 */
public class Car {
    // Private attributes (encapsulation)
    private String make;
    private String model;
    private int year;
    private String color;
    private double engineSize;
    private int mileage;
    private boolean isRunning;
    
    // Default constructor
    public Car() {
        this.make = "Unknown";
        this.model = "Unknown";
        this.year = 2000;
        this.color = "White";
        this.engineSize = 1.6;
        this.mileage = 0;
        this.isRunning = false;
    }
    
    // Parameterized constructor
    public Car(String make, String model, int year, String color, double engineSize) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.engineSize = engineSize;
        this.mileage = 0;
        this.isRunning = false;
    }
    
    // Copy constructor
    public Car(Car other) {
        this.make = other.make;
        this.model = other.model;
        this.year = other.year;
        this.color = other.color;
        this.engineSize = other.engineSize;
        this.mileage = other.mileage;
        this.isRunning = other.isRunning;
    }
    
    // Getter methods
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public String getColor() { return color; }
    public double getEngineSize() { return engineSize; }
    public int getMileage() { return mileage; }
    public boolean isRunning() { return isRunning; }
    
    // Setter methods
    public void setMake(String make) { this.make = make; }
    public void setModel(String model) { this.model = model; }
    public void setYear(int year) { 
        if (year > 1885 && year <= 2024) { // First car was made in 1885
            this.year = year; 
        }
    }
    public void setColor(String color) { this.color = color; }
    public void setEngineSize(double engineSize) { 
        if (engineSize > 0) {
            this.engineSize = engineSize; 
        }
    }
    
    // Business methods
    public void startEngine() {
        if (!isRunning) {
            isRunning = true;
            System.out.println(make + " " + model + " engine started!");
        } else {
            System.out.println("Engine is already running!");
        }
    }
    
    public void stopEngine() {
        if (isRunning) {
            isRunning = false;
            System.out.println(make + " " + model + " engine stopped!");
        } else {
            System.out.println("Engine is already stopped!");
        }
    }
    
    public void drive(int miles) {
        if (isRunning && miles > 0) {
            mileage += miles;
            System.out.println("Drove " + miles + " miles. Total mileage: " + mileage);
        } else if (!isRunning) {
            System.out.println("Cannot drive. Please start the engine first!");
        } else {
            System.out.println("Invalid distance!");
        }
    }
    
    public void honk() {
        System.out.println("Beep! Beep! 🚗");
    }
    
    // Method overloading - different ways to display details
    public void displayDetails() {
        System.out.println("\n--- Car Details ---");
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Color: " + color);
        System.out.println("Engine Size: " + engineSize + "L");
        System.out.println("Mileage: " + mileage + " miles");
        System.out.println("Status: " + (isRunning ? "Running" : "Stopped"));
        System.out.println("Age: " + (2024 - year) + " years");
        System.out.println("------------------");
    }
    
    public void displayDetails(boolean detailed) {
        if (detailed) {
            displayDetails();
            System.out.println("Additional Information:");
            System.out.println("Full Name: " + year + " " + make + " " + model);
            System.out.println("Fuel Efficiency: " + calculateFuelEfficiency() + " MPG (estimated)");
            System.out.println("Condition: " + getCondition());
        } else {
            System.out.println(year + " " + make + " " + model + " (" + color + ")");
        }
    }
    
    // Utility methods
    private double calculateFuelEfficiency() {
        // Simple estimation based on engine size and year
        double baseMPG = 30.0;
        double enginePenalty = (engineSize - 1.6) * 3; // Larger engines consume more
        double agePenalty = (2024 - year) * 0.2; // Older cars are less efficient
        return Math.max(15.0, baseMPG - enginePenalty - agePenalty);
    }
    
    private String getCondition() {
        if (mileage < 20000) return "Excellent";
        else if (mileage < 50000) return "Very Good";
        else if (mileage < 100000) return "Good";
        else if (mileage < 150000) return "Fair";
        else return "Needs Attention";
    }
    
    // Override toString method
    @Override
    public String toString() {
        return String.format("%d %s %s (Color: %s, Engine: %.1fL, Mileage: %d)", 
                           year, make, model, color, engineSize, mileage);
    }
    
    // Main method to demonstrate the Car class
    public static void main(String[] args) {
        System.out.println("Car Class Demonstration");
        System.out.println("=======================");
        
        // Create cars using different constructors
        System.out.println("1. Creating cars using different constructors:");
        
        // Default constructor
        Car car1 = new Car();
        System.out.println("Car 1 (default): " + car1);
        
        // Parameterized constructor
        Car car2 = new Car("Toyota", "Camry", 2020, "Silver", 2.5);
        System.out.println("Car 2 (parameterized): " + car2);
        
        // Copy constructor
        Car car3 = new Car(car2);
        car3.setColor("Red");
        System.out.println("Car 3 (copy of car2, changed color): " + car3);
        
        // Demonstrate methods
        System.out.println("\n2. Demonstrating car operations:");
        
        car2.displayDetails();
        
        // Try to drive before starting engine
        car2.drive(50);
        
        // Start engine and drive
        car2.startEngine();
        car2.drive(100);
        car2.drive(250);
        car2.honk();
        
        // Display updated details
        car2.displayDetails(true);
        
        // Stop engine
        car2.stopEngine();
        
        // Create more cars for demonstration
        System.out.println("\n3. Creating a car collection:");
        Car[] cars = {
            new Car("Honda", "Civic", 2019, "Blue", 1.8),
            new Car("BMW", "X5", 2021, "Black", 3.0),
            new Car("Ford", "Mustang", 2022, "Red", 5.0),
            new Car("Tesla", "Model 3", 2023, "White", 0.0) // Electric car
        };
        
        for (int i = 0; i < cars.length; i++) {
            System.out.println("Car " + (i + 1) + ": ");
            cars[i].displayDetails(false);
            
            // Simulate some usage
            cars[i].startEngine();
            cars[i].drive((i + 1) * 1000); // Different mileage for each car
            cars[i].stopEngine();
            System.out.println();
        }
        
        // Show final status of all cars
        System.out.println("4. Final status of all cars:");
        for (Car car : cars) {
            car.displayDetails(true);
            System.out.println();
        }
    }
}
