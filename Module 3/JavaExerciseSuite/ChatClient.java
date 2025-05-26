import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private boolean running;

    public void startConnection() throws IOException {
        System.out.println("Connecting to server...");
        socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        System.out.println("Connected to server at " + SERVER_ADDRESS + ":" + SERVER_PORT);
        
        // Set up I/O streams
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        running = true;
        
        // Start a thread to read messages from server
        Thread readThread = new Thread(this::readMessages);
        readThread.start();
        
        // Main thread will handle sending messages
        sendMessages();
        
        // Clean up when done
        stop();
    }
    
    private void readMessages() {
        try {
            String message;
            while (running && (message = in.readLine()) != null) {
                System.out.println("Server: " + message);
                if ("bye".equalsIgnoreCase(message.trim())) {
                    System.out.println("Server has ended the chat.");
                    running = false;
                    break;
                }
            }
        } catch (IOException e) {
            if (running) {
                System.err.println("Error reading from server: " + e.getMessage());
            }
        } finally {
            running = false;
        }
    }
    
    private void sendMessages() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Type your messages (type 'bye' to exit):");
            
            while (running) {
                String message = scanner.nextLine();
                out.println(message);
                
                if ("bye".equalsIgnoreCase(message.trim())) {
                    running = false;
                    System.out.println("Ending chat session...");
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Error sending message: " + e.getMessage());
        }
    }
    
    public void stop() {
        running = false;
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            System.err.println("Error closing resources: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ChatClient client = new ChatClient();
        try {
            client.startConnection();
        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}
