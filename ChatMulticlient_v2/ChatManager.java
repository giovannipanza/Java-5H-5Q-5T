import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatManager {

    private List<ClientHandler> clients;

    public ChatManager() {
        clients = new CopyOnWriteArrayList<>();
    }

    public void addClient(ClientHandler client) {
        clients.add(client);
        System.out.println("Client connessi: " + clients.size());
    }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
        System.out.println("Client connessi: " + clients.size());
    }

    public void broadcast(String messaggio) {

        for (ClientHandler client : clients) {
            client.sendMessage(messaggio);
        }
    }
}
