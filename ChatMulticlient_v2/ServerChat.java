import java.net.*;
import java.io.*;

public class ServerChat {

    private ServerSocket serverSocket;
    private ChatManager chatManager;

    public ServerChat() {
        chatManager = new ChatManager();
    }

    public void startServer() {

        try {
            serverSocket = new ServerSocket(5000);

            System.out.println("Server in ascolto...");

            while (true) {

                Socket socket = serverSocket.accept();

                System.out.println("Nuovo client collegato");

                ClientHandler client =
                    new ClientHandler(socket, chatManager);

                client.start();
            }

        } catch (IOException e) {
            System.out.println("Errore server: " + e.getMessage());
        }
    }
}
