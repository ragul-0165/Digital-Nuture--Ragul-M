import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpClientDemo {
    private static final String TARGET_URL = "https://api.github.com";
    
    public static void main(String[] args) {
        // Create an HttpClient with a connection timeout
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        
        try {
            // Create an HTTP GET request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(TARGET_URL))
                    .header("Accept", "application/vnd.github.v3+json")
                    .header("User-Agent", "Java 11+ HttpClient Demo")
                    .GET()
                    .build();
            
            System.out.println("Sending request to: " + TARGET_URL);
            
            // Send the request and get the response asynchronously
            httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::statusCode)
                    .thenAccept(statusCode -> 
                        System.out.println("Status Code: " + statusCode))
                    .join();
            
            // Get the full response
            HttpResponse<String> response = httpClient.send(
                request, 
                HttpResponse.BodyHandlers.ofString()
            );
            
            // Print response details
            System.out.println("\n=== Response Headers ===");
            response.headers().map()
                   .forEach((key, values) -> 
                       System.out.printf("%s: %s%n", key, values));
            
            System.out.println("\n=== Response Body (first 500 chars) ===");
            String responseBody = response.body();
            int maxLength = Math.min(500, responseBody.length());
            System.out.println(responseBody.substring(0, maxLength) + 
                (maxLength < responseBody.length() ? "..." : ""));
            
        } catch (Exception e) {
            System.err.println("Error making HTTP request: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
