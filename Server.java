import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            // Create a server socket listening on port 1234
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server is running... Waiting for client to connect...");

            // Accept the client connection
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            // Streams for communication
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            // Chat loop
            String message;
            while (true) {
                message = input.readLine();
                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Client has disconnected.");
                    break;
                }
                System.out.println("Client: " + message);
                output.println("Server Received: " + message);
            }

            // Close connections
            input.close();
            output.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
