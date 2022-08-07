import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8765;
        try (ServerSocket socket = new ServerSocket(port)){
            System.out.println("Server started");
            while (true) {
                try (Socket client = socket.accept();
                     PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
                     BufferedReader reader =
                             new BufferedReader(new InputStreamReader(client.getInputStream()))){

                    System.out.printf("New connection, port: %d\n", client.getPort());
                    writer.println("Hello, what's your name?");
                    String name = reader.readLine();
                    writer.println("Hello, " + name);
                    askClient("Do you like listening a rock?", "YEEEAAAA, ROOOCK!!!"
                    , "It's sad", writer, reader);
                    askClient("Are you kid?", "Go do homework", "Go work",
                            writer, reader);
                    askClient("Do you like pizza?", "I like too", "WHYYY?!",
                            writer, reader);

                    writer.println("Bye bye, " + name);
                } catch (IOException ex){
                    System.out.println(ex.getMessage());
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void askClient(String ask, String trueResult, String falseResult,
                                 PrintWriter writer, BufferedReader reader){
        try {
            writer.println(ask + " (Yes/No)");
            String result = reader.readLine();
            switch (result) {
                case "Yes":
                    writer.println(trueResult);
                    break;
                case "No":
                    writer.println(falseResult);
                    break;
                default:
                    writer.println("I don't understand you");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
