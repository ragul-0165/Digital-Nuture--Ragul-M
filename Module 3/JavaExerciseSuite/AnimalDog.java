/**
 * Exercise 17: Animal and Dog Classes - Inheritance Example
 * Demonstrates inheritance, method overriding, and polymorphism.
 * Shows parent-child class relationships and method behavior.
 */

// Parent class - Animal
class Animal {
    // Protected attributes (accessible by subclasses)
    protected String name;
    protected String species;
    protected int age;
    protected double weight;
    
    // Constructor
    public Animal(String name, String species, int age, double weight) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.weight = weight;
    }
    
    // Default constructor
    public Animal() {
        this("Unknown", "Unknown", 0, 0.0);
    }
    
    // Getter methods
    public String getName() { return name; }
    public String getSpecies() { return species; }
    public int getAge() { return age; }
    public double getWeight() { return weight; }
    
    // Setter methods
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { 
        if (age >= 0) this.age = age; 
    }
    public void setWeight(double weight) { 
        if (weight > 0) this.weight = weight; 
    }
    
    // Methods that can be overridden
    public void makeSound() {
        System.out.println(name + " makes a generic animal sound.");
    }
    
    public void eat() {
        System.out.println(name + " is eating.");
    }
    
    public void sleep() {
        System.out.println(name + " is sleeping.");
    }
    
    public void move() {
        System.out.println(name + " is moving around.");
    }
    
    // Method to display animal information
    public void displayInfo() {
        System.out.println("\n--- Animal Information ---");
        System.out.println("Name: " + name);
        System.out.println("Species: " + species);
        System.out.println("Age: " + age + " years");
        System.out.println("Weight: " + weight + " kg");
        System.out.println("-------------------------");
    }
    
    // Calculate age in human years (basic calculation)
    public int getAgeInHumanYears() {
        return age * 7; // Simple approximation
    }
}

// Child class - Dog (inherits from Animal)
class Dog extends Animal {
    // Additional attributes specific to dogs
    private String breed;
    private String furColor;
    private boolean isTrained;
    
    // Constructor
    public Dog(String name, int age, double weight, String breed, String furColor) {
        super(name, "Canine", age, weight); // Call parent constructor
        this.breed = breed;
        this.furColor = furColor;
        this.isTrained = false;
    }
    
    // Default constructor
    public Dog() {
        super();
        this.breed = "Mixed";
        this.furColor = "Brown";
        this.isTrained = false;
    }
    
    // Getter and setter for dog-specific attributes
    public String getBreed() { return breed; }
    public String getFurColor() { return furColor; }
    public boolean isTrained() { return isTrained; }
    
    public void setBreed(String breed) { this.breed = breed; }
    public void setFurColor(String furColor) { this.furColor = furColor; }
    public void setTrained(boolean trained) { this.isTrained = trained; }
    
    // Override the makeSound method
    @Override
    public void makeSound() {
        System.out.println(name + " barks: Woof! Woof! 🐕");
    }
    
    // Override the move method
    @Override
    public void move() {
        System.out.println(name + " runs and wags its tail happily!");
    }
    
    // Override the eat method
    @Override
    public void eat() {
        System.out.println(name + " is eating dog food with enthusiasm!");
    }
    
    // Dog-specific methods
    public void bark() {
        makeSound(); // Calls the overridden makeSound method
    }
    
    public void wagTail() {
        System.out.println(name + " is wagging its tail! 🐕💖");
    }
    
    public void fetch() {
        if (isTrained) {
            System.out.println(name + " fetches the ball and brings it back!");
        } else {
            System.out.println(name + " chases the ball but doesn't bring it back.");
        }
    }
    
    public void sit() {
        if (isTrained) {
            System.out.println(name + " sits down obediently.");
        } else {
            System.out.println(name + " looks confused and doesn't sit.");
        }
    }
    
    public void train() {
        if (!isTrained) {
            isTrained = true;
            System.out.println(name + " has been trained! Good dog! 🎉");
        } else {
            System.out.println(name + " is already trained.");
        }
    }
    
    // Override displayInfo to include dog-specific information
    @Override
    public void displayInfo() {
        System.out.println("\n--- Dog Information ---");
        System.out.println("Name: " + name);
        System.out.println("Species: " + species);
        System.out.println("Breed: " + breed);
        System.out.println("Age: " + age + " years (" + getAgeInHumanYears() + " in human years)");
        System.out.println("Weight: " + weight + " kg");
        System.out.println("Fur Color: " + furColor);
        System.out.println("Trained: " + (isTrained ? "Yes" : "No"));
        System.out.println("----------------------");
    }
    
    // Override age calculation for dogs (more accurate)
    @Override
    public int getAgeInHumanYears() {
        if (age <= 0) return 0;
        if (age == 1) return 15;
        if (age == 2) return 24;
        return 24 + (age - 2) * 4; // More accurate dog age calculation
    }
}

// Main class to demonstrate inheritance
public class AnimalDog {
    public static void main(String[] args) {
        System.out.println("Animal and Dog Inheritance Demonstration");
        System.out.println("========================================");
        
        // Create Animal objects
        System.out.println("1. Creating Animal objects:");
        Animal animal1 = new Animal("Generic Animal", "Unknown", 5, 10.0);
        Animal animal2 = new Animal("Lion", "Feline", 8, 150.0);
        
        animal1.displayInfo();
        animal1.makeSound();
        animal1.eat();
        animal1.sleep();
        animal1.move();
        
        System.out.println();
        animal2.displayInfo();
        animal2.makeSound();
        
        // Create Dog objects
        System.out.println("\n2. Creating Dog objects:");
        Dog dog1 = new Dog("Buddy", 3, 25.0, "Golden Retriever", "Golden");
        Dog dog2 = new Dog("Max", 5, 30.0, "German Shepherd", "Black and Brown");
        Dog dog3 = new Dog(); // Default constructor
        dog3.setName("Charlie");
        dog3.setAge(2);
        dog3.setWeight(15.0);
        dog3.setBreed("Beagle");
        dog3.setFurColor("Tricolor");
        
        // Display dog information
        dog1.displayInfo();
        dog2.displayInfo();
        dog3.displayInfo();
        
        // Demonstrate method overriding
        System.out.println("\n3. Demonstrating method overriding:");
        System.out.println("Animal makeSound():");
        animal1.makeSound();
        
        System.out.println("\nDog makeSound() (overridden):");
        dog1.makeSound();
        
        System.out.println("\nAnimal move():");
        animal1.move();
        
        System.out.println("\nDog move() (overridden):");
        dog1.move();
        
        // Demonstrate dog-specific behavior
        System.out.println("\n4. Dog-specific behaviors:");
        
        // Before training
        System.out.println("Before training:");
        dog1.sit();
        dog1.fetch();
        
        // Train the dog
        dog1.train();
        
        // After training
        System.out.println("\nAfter training:");
        dog1.sit();
        dog1.fetch();
        dog1.wagTail();
        
        // Demonstrate polymorphism
        System.out.println("\n5. Polymorphism demonstration:");
        Animal[] animals = {
            new Animal("Cat", "Feline", 4, 5.0),
            new Dog("Rex", 6, 28.0, "Labrador", "Yellow"),
            new Dog("Bella", 2, 20.0, "Border Collie", "Black and White")
        };
        
        System.out.println("Calling makeSound() on different animals:");
        for (Animal animal : animals) {
            System.out.print(animal.getName() + " (" + animal.getClass().getSimpleName() + "): ");
            animal.makeSound();
        }
        
        // Demonstrate instanceof operator
        System.out.println("\n6. Type checking with instanceof:");
        for (Animal animal : animals) {
            System.out.println(animal.getName() + " is an Animal: " + (animal instanceof Animal));
            System.out.println(animal.getName() + " is a Dog: " + (animal instanceof Dog));
            
            // Safe casting
            if (animal instanceof Dog) {
                Dog dog = (Dog) animal;
                System.out.println("  Breed: " + dog.getBreed());
                dog.wagTail();
            }
            System.out.println();
        }
        
        // Demonstrate age calculation differences
        System.out.println("7. Age calculation comparison:");
        Animal genericAnimal = new Animal("Old Cat", "Feline", 10, 6.0);
        Dog oldDog = new Dog("Senior", 10, 25.0, "Retriever", "Gray");
        
        System.out.println("Generic animal age calculation:");
        System.out.println(genericAnimal.getName() + " is " + genericAnimal.getAge() + 
                          " years old (" + genericAnimal.getAgeInHumanYears() + " in human years)");
        
        System.out.println("\nDog age calculation (more accurate):");
        System.out.println(oldDog.getName() + " is " + oldDog.getAge() + 
                          " years old (" + oldDog.getAgeInHumanYears() + " in human years)");
    }
}
