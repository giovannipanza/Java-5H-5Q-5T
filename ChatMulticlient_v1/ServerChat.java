import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerChat {

    private ServerSocket serverSocket;

    private List<ClientHandler> clients =
            new CopyOnWriteArrayList<>();

    public void startServer() {

        try {
            serverSocket = new ServerSocket(5000);

            System.out.println("Server avviato...");

            while (true) {

                Socket socket = serverSocket.accept();

                System.out.println("Nuovo client");

                ClientHandler client =
                        new ClientHandler(socket, this);

                addClient(client);

                client.start();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addClient(ClientHandler client) {
        clients.add(client);
    }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }

    public void broadcast(String msg) {

        for (ClientHandler client : clients) {
            client.sendMessage(msg);
        }
    }
}
