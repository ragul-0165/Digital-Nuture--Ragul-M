/**
 * Exercise 18: Interface Example - Playable Instrument
 * Demonstrates interfaces, implementation, and polymorphism.
 * Shows how different classes can implement the same interface.
 */

// Interface definition
interface Playable {
    // Abstract methods (implicitly public and abstract)
    void play();
    void stop();
    void tune();
    
    // Default method (Java 8+)
    default void displayInfo() {
        System.out.println("This is a musical instrument that can be played.");
    }
    
    // Static method (Java 8+)
    static void showInterfaceInfo() {
        System.out.println("Playable interface defines methods for musical instruments.");
    }
    
    // Constants (implicitly public, static, and final)
    int MAX_VOLUME = 100;
    int MIN_VOLUME = 0;
    String INTERFACE_VERSION = "1.0";
}

// Additional interface for demonstration
interface Amplifiable {
    void amplify();
    void setVolume(int volume);
    int getVolume();
}

// Abstract base class for instruments
abstract class Instrument {
    protected String name;
    protected String material;
    protected double price;
    protected boolean isInTune;
    
    public Instrument(String name, String material, double price) {
        this.name = name;
        this.material = material;
        this.price = price;
        this.isInTune = false;
    }
    
    // Getters
    public String getName() { return name; }
    public String getMaterial() { return material; }
    public double getPrice() { return price; }
    public boolean isInTune() { return isInTune; }
    
    // Abstract method to be implemented by subclasses
    public abstract String getInstrumentType();
    
    // Common method for all instruments
    public void displayDetails() {
        System.out.println("Instrument: " + name);
        System.out.println("Type: " + getInstrumentType());
        System.out.println("Material: " + material);
        System.out.println("Price: $" + String.format("%.2f", price));
        System.out.println("In Tune: " + (isInTune ? "Yes" : "No"));
    }
}

// Guitar class implementing Playable interface
class Guitar extends Instrument implements Playable, Amplifiable {
    private int numberOfStrings;
    private String guitarType; // acoustic, electric, classical
    private int volume;
    private boolean isAmplified;
    
    public Guitar(String name, String material, double price, int numberOfStrings, String guitarType) {
        super(name, material, price);
        this.numberOfStrings = numberOfStrings;
        this.guitarType = guitarType;
        this.volume = 50; // Default volume
        this.isAmplified = guitarType.equals("electric");
    }
    
    @Override
    public String getInstrumentType() {
        return "String Instrument";
    }
    
    // Implement Playable interface methods
    @Override
    public void play() {
        if (isInTune) {
            System.out.println("🎸 " + name + " is playing beautiful guitar music!");
            if (guitarType.equals("acoustic")) {
                System.out.println("   ♪ Strumming acoustic chords ♪");
            } else if (guitarType.equals("electric")) {
                System.out.println("   ♪ Rocking electric riffs ♪");
            } else {
                System.out.println("   ♪ Playing classical melodies ♪");
            }
        } else {
            System.out.println("⚠️  " + name + " is out of tune! Please tune first.");
        }
    }
    
    @Override
    public void stop() {
        System.out.println("🛑 " + name + " stopped playing. Silence...");
    }
    
    @Override
    public void tune() {
        System.out.println("🔧 Tuning " + name + "...");
        System.out.println("   Adjusting string tension...");
        isInTune = true;
        System.out.println("✅ " + name + " is now in tune!");
    }
    
    // Implement Amplifiable interface methods
    @Override
    public void amplify() {
        if (isAmplified) {
            System.out.println("🔊 " + name + " is now amplified and ready to rock!");
        } else {
            System.out.println("❌ " + name + " cannot be amplified (acoustic guitar).");
        }
    }
    
    @Override
    public void setVolume(int volume) {
        if (volume >= MIN_VOLUME && volume <= MAX_VOLUME) {
            this.volume = volume;
            System.out.println("🔊 " + name + " volume set to " + volume + "%");
        } else {
            System.out.println("❌ Volume must be between " + MIN_VOLUME + " and " + MAX_VOLUME);
        }
    }
    
    @Override
    public int getVolume() {
        return volume;
    }
    
    // Guitar-specific methods
    public void strum() {
        play();
    }
    
    public void pickString(int stringNumber) {
        if (stringNumber >= 1 && stringNumber <= numberOfStrings) {
            System.out.println("🎵 Picking string " + stringNumber + " on " + name);
        } else {
            System.out.println("❌ Invalid string number. " + name + " has " + numberOfStrings + " strings.");
        }
    }
    
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Number of Strings: " + numberOfStrings);
        System.out.println("Guitar Type: " + guitarType);
        System.out.println("Volume: " + volume + "%");
        System.out.println("Can be Amplified: " + (isAmplified ? "Yes" : "No"));
    }
}

// Piano class implementing Playable interface
class Piano extends Instrument implements Playable {
    private int numberOfKeys;
    private String pianoType; // grand, upright, digital
    private boolean hasPedals;
    
    public Piano(String name, String material, double price, int numberOfKeys, String pianoType, boolean hasPedals) {
        super(name, material, price);
        this.numberOfKeys = numberOfKeys;
        this.pianoType = pianoType;
        this.hasPedals = hasPedals;
    }
    
    @Override
    public String getInstrumentType() {
        return "Keyboard Instrument";
    }
    
    // Implement Playable interface methods
    @Override
    public void play() {
        if (isInTune) {
            System.out.println("🎹 " + name + " is playing elegant piano music!");
            if (pianoType.equals("grand")) {
                System.out.println("   ♪ Majestic grand piano melodies ♪");
            } else if (pianoType.equals("upright")) {
                System.out.println("   ♪ Warm upright piano tones ♪");
            } else {
                System.out.println("   ♪ Modern digital piano sounds ♪");
            }
        } else {
            System.out.println("⚠️  " + name + " needs tuning! Some keys sound off.");
        }
    }
    
    @Override
    public void stop() {
        System.out.println("🛑 " + name + " stopped playing. The keys are silent...");
    }
    
    @Override
    public void tune() {
        System.out.println("🔧 Tuning " + name + "...");
        if (pianoType.equals("digital")) {
            System.out.println("   Calibrating digital sound modules...");
        } else {
            System.out.println("   Adjusting string tension for " + numberOfKeys + " keys...");
            System.out.println("   This may take a while...");
        }
        isInTune = true;
        System.out.println("✅ " + name + " is now perfectly tuned!");
    }
    
    // Piano-specific methods
    public void playKey(int keyNumber) {
        if (keyNumber >= 1 && keyNumber <= numberOfKeys) {
            System.out.println("🎵 Playing key " + keyNumber + " on " + name);
        } else {
            System.out.println("❌ Invalid key number. " + name + " has " + numberOfKeys + " keys.");
        }
    }
    
    public void usePedal() {
        if (hasPedals) {
            System.out.println("🦶 Using sustain pedal on " + name + " for richer sound!");
        } else {
            System.out.println("❌ " + name + " doesn't have pedals.");
        }
    }
    
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Number of Keys: " + numberOfKeys);
        System.out.println("Piano Type: " + pianoType);
        System.out.println("Has Pedals: " + (hasPedals ? "Yes" : "No"));
    }
}

// Violin class implementing Playable interface
class Violin extends Instrument implements Playable {
    private boolean hasBow;
    private String violinSize;
    
    public Violin(String name, String material, double price, String violinSize) {
        super(name, material, price);
        this.violinSize = violinSize;
        this.hasBow = true;
    }
    
    @Override
    public String getInstrumentType() {
        return "String Instrument";
    }
    
    @Override
    public void play() {
        if (isInTune) {
            if (hasBow) {
                System.out.println("🎻 " + name + " is playing beautiful violin music!");
                System.out.println("   ♪ Graceful bow movements create melodic sounds ♪");
            } else {
                System.out.println("❌ Cannot play " + name + " without a bow!");
            }
        } else {
            System.out.println("⚠️  " + name + " is out of tune! Please tune first.");
        }
    }
    
    @Override
    public void stop() {
        System.out.println("🛑 " + name + " stopped playing. The bow is at rest...");
    }
    
    @Override
    public void tune() {
        System.out.println("🔧 Tuning " + name + "...");
        System.out.println("   Adjusting the four strings (G, D, A, E)...");
        isInTune = true;
        System.out.println("✅ " + name + " is now in tune!");
    }
    
    public void useBow() {
        if (hasBow) {
            play();
        } else {
            System.out.println("❌ No bow available for " + name);
        }
    }
    
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Violin Size: " + violinSize);
        System.out.println("Has Bow: " + (hasBow ? "Yes" : "No"));
    }
}

// Main class to demonstrate interfaces
public class InstrumentPlayable {
    public static void main(String[] args) {
        System.out.println("Musical Instruments Interface Demonstration");
        System.out.println("==========================================");
        
        // Show interface information
        Playable.showInterfaceInfo();
        System.out.println("Interface Version: " + Playable.INTERFACE_VERSION);
        System.out.println("Volume Range: " + Playable.MIN_VOLUME + " - " + Playable.MAX_VOLUME);
        System.out.println();
        
        // Create instruments
        System.out.println("1. Creating Musical Instruments:");
        Guitar electricGuitar = new Guitar("Fender Stratocaster", "Wood/Electronics", 1200.0, 6, "electric");
        Guitar acousticGuitar = new Guitar("Martin D-28", "Spruce/Rosewood", 2800.0, 6, "acoustic");
        Piano grandPiano = new Piano("Steinway Grand", "Wood/Steel", 85000.0, 88, "grand", true);
        Piano digitalPiano = new Piano("Yamaha P-125", "Plastic/Electronics", 650.0, 88, "digital", false);
        Violin violin = new Violin("Stradivarius Copy", "Maple/Spruce", 1500.0, "4/4");
        
        // Store in array for polymorphism demonstration
        Playable[] instruments = {electricGuitar, acousticGuitar, grandPiano, digitalPiano, violin};
        
        // Display all instruments
        System.out.println("\n2. Instrument Details:");
        for (Playable instrument : instruments) {
            if (instrument instanceof Instrument) {
                ((Instrument) instrument).displayDetails();
                instrument.displayInfo(); // Default method from interface
                System.out.println();
            }
        }
        
        // Demonstrate tuning and playing
        System.out.println("3. Tuning and Playing Instruments:");
        for (Playable instrument : instruments) {
            System.out.println("--- " + ((Instrument) instrument).getName() + " ---");
            instrument.tune();
            instrument.play();
            System.out.println();
        }
        
        // Demonstrate guitar-specific features
        System.out.println("4. Guitar-Specific Features:");
        System.out.println("Electric Guitar:");
        electricGuitar.amplify();
        electricGuitar.setVolume(75);
        electricGuitar.pickString(3);
        electricGuitar.strum();
        
        System.out.println("\nAcoustic Guitar:");
        acousticGuitar.amplify(); // Should show it can't be amplified
        acousticGuitar.pickString(1);
        
        // Demonstrate piano-specific features
        System.out.println("\n5. Piano-Specific Features:");
        System.out.println("Grand Piano:");
        grandPiano.playKey(44); // Middle C
        grandPiano.usePedal();
        
        System.out.println("\nDigital Piano:");
        digitalPiano.playKey(44);
        digitalPiano.usePedal(); // Should show it doesn't have pedals
        
        // Demonstrate violin-specific features
        System.out.println("\n6. Violin-Specific Features:");
        violin.useBow();
        
        // Demonstrate interface polymorphism
        System.out.println("\n7. Polymorphism with Interface:");
        playAllInstruments(instruments);
        
        // Demonstrate multiple interface implementation
        System.out.println("\n8. Multiple Interface Implementation:");
        demonstrateAmplification(electricGuitar);
        
        // Stop all instruments
        System.out.println("\n9. Stopping All Instruments:");
        for (Playable instrument : instruments) {
            instrument.stop();
        }
        
        // Demonstrate instanceof with interfaces
        System.out.println("\n10. Interface Type Checking:");
        for (Playable instrument : instruments) {
            String name = ((Instrument) instrument).getName();
            System.out.println(name + " implements Playable: " + (instrument instanceof Playable));
            System.out.println(name + " implements Amplifiable: " + (instrument instanceof Amplifiable));
            if (instrument instanceof Amplifiable) {
                System.out.println("  Current volume: " + ((Amplifiable) instrument).getVolume() + "%");
            }
            System.out.println();
        }
    }
    
    // Method that accepts any Playable instrument (interface polymorphism)
    public static void playAllInstruments(Playable[] instruments) {
        System.out.println("Playing all instruments in sequence:");
        for (int i = 0; i < instruments.length; i++) {
            System.out.print((i + 1) + ". ");
            instruments[i].play();
        }
    }
    
    // Method to demonstrate Amplifiable interface
    public static void demonstrateAmplification(Amplifiable amp) {
        System.out.println("Demonstrating amplification features:");
        amp.amplify();
        System.out.println("Current volume: " + amp.getVolume());
        amp.setVolume(90);
        amp.setVolume(110); // Should show error
    }
}
