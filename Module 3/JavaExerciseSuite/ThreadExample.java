/**
 * Exercise 26: Thread Example
 * Demonstrates creating and running threads using different approaches.
 * Shows thread communication, synchronization, and lifecycle management.
 */
public class ThreadExample {
    
    // Shared counter for demonstration
    private static int sharedCounter = 0;
    private static final Object counterLock = new Object();
    
    public static void main(String[] args) {
        System.out.println("Thread Example Demonstration");
        System.out.println("============================");
        System.out.println("Main thread: " + Thread.currentThread().getName());
        System.out.println();
        
        // 1. Basic thread creation and execution
        demonstrateBasicThreads();
        
        // 2. Thread with Runnable interface
        demonstrateRunnableThreads();
        
        // 3. Thread synchronization
        demonstrateSynchronization();
        
        // 4. Thread communication
        demonstrateThreadCommunication();
        
        // 5. Thread priorities and daemon threads
        demonstrateThreadProperties();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("All thread demonstrations completed!");
        System.out.println("Main thread: " + Thread.currentThread().getName() + " finished");
    }
    
    /**
     * Demonstrates basic thread creation by extending Thread class
     */
    public static void demonstrateBasicThreads() {
        System.out.println("1. Basic Thread Creation (Extending Thread class)");
        System.out.println("=".repeat(50));
        
        // Create threads by extending Thread class
        MessageThread thread1 = new MessageThread("Thread-1", "Hello from Thread-1!", 3);
        MessageThread thread2 = new MessageThread("Thread-2", "Greetings from Thread-2!", 3);
        
        // Start threads
        thread1.start();
        thread2.start();
        
        // Wait for threads to complete
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted: " + e.getMessage());
        }
        
        System.out.println("Basic thread demonstration completed.\n");
    }
    
    /**
     * Demonstrates thread creation using Runnable interface
     */
    public static void demonstrateRunnableThreads() {
        System.out.println("2. Thread Creation with Runnable Interface");
        System.out.println("=".repeat(50));
        
        // Create threads using Runnable interface
        CounterTask task1 = new CounterTask("Counter-A", 1, 5);
        CounterTask task2 = new CounterTask("Counter-B", 10, 15);
        
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        
        // Set thread names
        thread1.setName("CounterThread-1");
        thread2.setName("CounterThread-2");
        
        // Start threads
        thread1.start();
        thread2.start();
        
        // Wait for completion
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted: " + e.getMessage());
        }
        
        System.out.println("Runnable thread demonstration completed.\n");
    }
    
    /**
     * Demonstrates thread synchronization
     */
    public static void demonstrateSynchronization() {
        System.out.println("3. Thread Synchronization");
        System.out.println("=".repeat(50));
        
        System.out.println("Testing WITHOUT synchronization:");
        testUnsynchronizedAccess();
        
        System.out.println("\nTesting WITH synchronization:");
        testSynchronizedAccess();
        
        System.out.println("Synchronization demonstration completed.\n");
    }
    
    /**
     * Test unsynchronized access to shared resource
     */
    public static void testUnsynchronizedAccess() {
        sharedCounter = 0;
        
        // Create threads that increment counter without synchronization
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            final int threadId = i + 1;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    sharedCounter++; // Unsynchronized access
                }
                System.out.println("  Thread-" + threadId + " completed 1000 increments");
            });
        }
        
        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }
        
        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }
        
        System.out.println("  Expected: 5000, Actual: " + sharedCounter + 
                         " (Race condition " + (sharedCounter == 5000 ? "not observed" : "observed") + ")");
    }
    
    /**
     * Test synchronized access to shared resource
     */
    public static void testSynchronizedAccess() {
        sharedCounter = 0;
        
        // Create threads that increment counter with synchronization
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            final int threadId = i + 1;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    synchronized (counterLock) {
                        sharedCounter++; // Synchronized access
                    }
                }
                System.out.println("  Thread-" + threadId + " completed 1000 synchronized increments");
            });
        }
        
        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }
        
        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }
        
        System.out.println("  Expected: 5000, Actual: " + sharedCounter + " (Synchronized correctly)");
    }
    
    /**
     * Demonstrates thread communication using wait/notify
     */
    public static void demonstrateThreadCommunication() {
        System.out.println("4. Thread Communication (Producer-Consumer)");
        System.out.println("=".repeat(50));
        
        SharedBuffer buffer = new SharedBuffer();
        
        // Create producer and consumer threads
        Thread producer = new Thread(new Producer(buffer), "Producer");
        Thread consumer = new Thread(new Consumer(buffer), "Consumer");
        
        // Start threads
        producer.start();
        consumer.start();
        
        // Wait for completion
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted: " + e.getMessage());
        }
        
        System.out.println("Thread communication demonstration completed.\n");
    }
    
    /**
     * Demonstrates thread priorities and daemon threads
     */
    public static void demonstrateThreadProperties() {
        System.out.println("5. Thread Properties (Priority and Daemon)");
        System.out.println("=".repeat(50));
        
        // Demonstrate thread priorities
        Thread highPriorityThread = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("  High Priority Thread: " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "HighPriorityThread");
        
        Thread lowPriorityThread = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("  Low Priority Thread: " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "LowPriorityThread");
        
        // Set priorities
        highPriorityThread.setPriority(Thread.MAX_PRIORITY);
        lowPriorityThread.setPriority(Thread.MIN_PRIORITY);
        
        System.out.println("Starting threads with different priorities:");
        highPriorityThread.start();
        lowPriorityThread.start();
        
        // Demonstrate daemon thread
        Thread daemonThread = new Thread(() -> {
            int count = 0;
            while (true) {
                System.out.println("  Daemon thread running... " + (++count));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }, "DaemonThread");
        
        daemonThread.setDaemon(true); // Set as daemon thread
        System.out.println("Starting daemon thread (will stop when main thread ends):");
        daemonThread.start();
        
        // Wait for non-daemon threads
        try {
            highPriorityThread.join();
            lowPriorityThread.join();
            Thread.sleep(2000); // Let daemon thread run for a bit
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted: " + e.getMessage());
        }
        
        System.out.println("Thread properties demonstration completed.");
        System.out.println("(Daemon thread will stop automatically when main thread ends)");
    }
}

/**
 * Thread class that extends Thread
 */
class MessageThread extends Thread {
    private String threadName;
    private String message;
    private int repeatCount;
    
    public MessageThread(String threadName, String message, int repeatCount) {
        this.threadName = threadName;
        this.message = message;
        this.repeatCount = repeatCount;
    }
    
    @Override
    public void run() {
        for (int i = 1; i <= repeatCount; i++) {
            System.out.println("  " + threadName + " (iteration " + i + "): " + message);
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                System.out.println("  " + threadName + " interrupted: " + e.getMessage());
                Thread.currentThread().interrupt(); // Restore interrupt status
                break;
            }
        }
        System.out.println("  " + threadName + " completed!");
    }
}

/**
 * Runnable task for counting
 */
class CounterTask implements Runnable {
    private String taskName;
    private int startValue;
    private int endValue;
    
    public CounterTask(String taskName, int startValue, int endValue) {
        this.taskName = taskName;
        this.startValue = startValue;
        this.endValue = endValue;
    }
    
    @Override
    public void run() {
        String currentThread = Thread.currentThread().getName();
        System.out.println("  " + taskName + " started on " + currentThread);
        
        for (int i = startValue; i <= endValue; i++) {
            System.out.println("  " + taskName + " (" + currentThread + "): " + i);
            try {
                Thread.sleep(500); // Sleep for 0.5 seconds
            } catch (InterruptedException e) {
                System.out.println("  " + taskName + " interrupted: " + e.getMessage());
                Thread.currentThread().interrupt();
                break;
            }
        }
        
        System.out.println("  " + taskName + " completed on " + currentThread);
    }
}

/**
 * Shared buffer for producer-consumer communication
 */
class SharedBuffer {
    private String data;
    private boolean hasData = false;
    
    public synchronized void put(String data) throws InterruptedException {
        while (hasData) {
            wait(); // Wait until buffer is empty
        }
        
        this.data = data;
        this.hasData = true;
        System.out.println("  Producer put: " + data);
        notifyAll(); // Notify waiting consumers
    }
    
    public synchronized String get() throws InterruptedException {
        while (!hasData) {
            wait(); // Wait until buffer has data
        }
        
        String result = this.data;
        this.hasData = false;
        System.out.println("  Consumer got: " + result);
        notifyAll(); // Notify waiting producers
        return result;
    }
}

/**
 * Producer thread for producer-consumer pattern
 */
class Producer implements Runnable {
    private SharedBuffer buffer;
    
    public Producer(SharedBuffer buffer) {
        this.buffer = buffer;
    }
    
    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                String data = "Data-" + i;
                buffer.put(data);
                Thread.sleep(1000); // Simulate production time
            }
            System.out.println("  Producer finished producing data");
        } catch (InterruptedException e) {
            System.out.println("  Producer interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}

/**
 * Consumer thread for producer-consumer pattern
 */
class Consumer implements Runnable {
    private SharedBuffer buffer;
    
    public Consumer(SharedBuffer buffer) {
        this.buffer = buffer;
    }
    
    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                String data = buffer.get();
                Thread.sleep(1500); // Simulate consumption time
            }
            System.out.println("  Consumer finished consuming data");
        } catch (InterruptedException e) {
            System.out.println("  Consumer interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
