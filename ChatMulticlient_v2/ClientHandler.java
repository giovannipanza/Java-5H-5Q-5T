import java.net.*;
import java.io.*;

public class ClientHandler extends Thread {

    private Socket socket;
    private ChatManager chatManager;

    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket, ChatManager chatManager) {
        this.socket = socket;
        this.chatManager = chatManager;
    }

    @Override
    public void run() {

        try {
            in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );

            out = new PrintWriter(
                socket.getOutputStream(), true
            );

            chatManager.addClient(this);

            String messaggio;

            while ((messaggio = in.readLine()) != null) {

                System.out.println("Ricevuto: " + messaggio);

                chatManager.broadcast(messaggio);
            }

        } catch (IOException e) {
            System.out.println("Errore client: " + e.getMessage());

        } finally {

            chatManager.removeClient(this);

            try {
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void sendMessage(String messaggio) {
        if (out != null) {
            out.println(messaggio);
        }
    }
}
