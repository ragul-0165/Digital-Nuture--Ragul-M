import java.util.concurrent.*;

/**
 * Demonstrates the use of ExecutorService with Callable and Future
 */
public class ExecutorCallableDemo {
    
    public static void main(String[] args) {
        System.out.println("=== ExecutorService with Callable Demo ===");
        
        // Create an executor service with a fixed thread pool of 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        try {
            // Submit three Callable tasks to the executor
            System.out.println("Submitting tasks...");
            
            // Task 1: Calculate factorial of 5
            Callable<Long> factorialTask = () -> {
                long result = 1;
                for (int i = 1; i <= 5; i++) {
                    result *= i;
                    Thread.sleep(100); // Simulate work
                }
                return result;
            };
            
            // Task 2: Calculate sum of first 10 natural numbers
            Callable<Integer> sumTask = () -> {
                int sum = 0;
                for (int i = 1; i <= 10; i++) {
                    sum += i;
                    Thread.sleep(80); // Simulate work
                }
                return sum;
            };
            
            // Task 3: Count vowels in a string
            Callable<Integer> vowelCountTask = () -> {
                String text = "Hello, this is a test string for counting vowels";
                int count = 0;
                for (char c : text.toLowerCase().toCharArray()) {
                    if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                        count++;
                    }
                    Thread.sleep(50); // Simulate work
                }
                return count;
            };
            
            // Submit all tasks and get Future objects
            Future<Long> factorialFuture = executor.submit(factorialTask);
            Future<Integer> sumFuture = executor.submit(sumTask);
            Future<Integer> vowelFuture = executor.submit(vowelCountTask);
            
            System.out.println("Tasks submitted. Waiting for results...\n");
            
            // Get results from futures (this will block until results are available)
            try {
                // Get factorial result with a timeout of 2 seconds
                long factorial = factorialFuture.get(2, TimeUnit.SECONDS);
                System.out.println("Factorial of 5: " + factorial);
                
                // Get sum result with a timeout of 2 seconds
                int sum = sumFuture.get(2, TimeUnit.SECONDS);
                System.out.println("Sum of first 10 natural numbers: " + sum);
                
                // Get vowel count result with a timeout of 2 seconds
                int vowelCount = vowelFuture.get(2, TimeUnit.SECONDS);
                System.out.println("Number of vowels in the string: " + vowelCount);
                
                // Demonstrate exception handling with Future
                System.out.println("\nDemonstrating exception handling...");
                Future<?> failingFuture = executor.submit(() -> {
                    throw new RuntimeException("This is a simulated error!");
                });
                
                try {
                    failingFuture.get();
                } catch (ExecutionException e) {
                    System.out.println("Caught exception from task: " + e.getCause().getMessage());
                }
                
            } catch (TimeoutException e) {
                System.err.println("A task timed out: " + e.getMessage());
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Error getting task result: " + e.getMessage());
            }
            
        } finally {
            // Shutdown the executor service
            System.out.println("\nShutting down executor...");
            executor.shutdown();
            
            try {
                // Wait for tasks to complete or timeout after 5 seconds
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    System.out.println("Forcing shutdown...");
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
            
            System.out.println("Executor has been shut down.");
        }
    }
}
