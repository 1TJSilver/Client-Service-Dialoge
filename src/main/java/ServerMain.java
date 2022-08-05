import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8765;
        try (ServerSocket socket = new ServerSocket(port)){
            System.out.println("Server started");
            while (true){
                Socket client = socket.accept();
                PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(client.getInputStream()));
                System.out.printf("New connection, port: %d\n", client.getPort());
                writer.println("Hello, what's your name?");
                String name = reader.readLine();
                writer.println("Hello, " + name);
                writer.println("Do you like listening a rock?(Yes/No)");
                String result = reader.readLine();
                if (result.equals("Yes")){
                    writer.println("YEEEAAAA, ROOOCK!!!");
                } else if (result.equals("No")){
                    writer.println("It's sad");
                } else {
                    System.out.println("I don't understand you :|");
                }
                writer.println("Are you kid?(Yes/No)");
                result = reader.readLine();
                switch (result){
                    case "Yes":
                        writer.println("Go do homework");
                        break;
                    case "No":
                        writer.println("Go work");
                        break;
                    default: System.out.println("I don't understand you :|");
                }
                writer.println("Do you like pizza?(Yes/No)");
                result = reader.readLine();
                switch (result){
                    case "Yes":
                        writer.println("I like too");
                        break;
                    case "No":
                        writer.println("WHYYY?!");
                        break;
                    default: System.out.println("I don't understand you :|");
                }
                writer.println("Bye bye, " + name);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
