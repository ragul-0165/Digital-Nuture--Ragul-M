import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatServer {
    private static final int PORT = 12345;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private boolean running;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started. Waiting for client...");
        
        // Accept a client connection
        clientSocket = serverSocket.accept();
        System.out.println("Client connected: " + clientSocket.getInetAddress());
        
        // Set up I/O streams
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        running = true;
        
        // Start a thread to read messages from client
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
                System.out.println("Client: " + message);
                if ("bye".equalsIgnoreCase(message.trim())) {
                    System.out.println("Client has left the chat.");
                    running = false;
                    break;
                }
            }
        } catch (IOException e) {
            if (running) {
                System.err.println("Error reading from client: " + e.getMessage());
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
            if (clientSocket != null) clientSocket.close();
            if (serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            System.err.println("Error closing resources: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        try {
            server.start(PORT);
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}
