import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        String host = "netology.homework";
        int port = 8765;
        try (Socket client = new Socket(host, port)
             ;Scanner scanner = new Scanner(System.in);
             PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
             BufferedReader reader =
                     new BufferedReader(new InputStreamReader(client.getInputStream()))){

            for(int i = 0; i < 5; i++){
                System.out.println(reader.readLine());
                writer.println(scanner.nextLine());
                System.out.println(reader.readLine());
            }

        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
