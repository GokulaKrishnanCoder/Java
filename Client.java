import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            // Connect to the server on localhost, port 1234
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Connected to the server.");

            // Streams for communication
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            // Chat loop
            String message;
            while (true) {
                System.out.print("You: ");
                message = userInput.readLine();
                output.println(message);

                // If user types 'exit', close the connection
                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Disconnected from server.");
                    break;
                }

                // Receive response from server
                String response = input.readLine();
                System.out.println(response);
            }

            // Close connections
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
