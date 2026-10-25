import java.net.*;
import java.io.*;

public class ClientHandler extends Thread {

    private Socket socket;
    private ServerChat server;

    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket, ServerChat server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {

        try {
            in = new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream()
                )
            );

            out = new PrintWriter(
                socket.getOutputStream(), true
            );

            String messaggio;

            while ((messaggio = in.readLine()) != null) {

                System.out.println("Ricevuto: " + messaggio);

                server.broadcast(messaggio);
            }

        } catch (IOException e) {
            System.out.println("Client disconnesso");
        } finally {

            server.removeClient(this);

            try {
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void sendMessage(String msg) {

        if (out != null) {
            out.println(msg);
        }
    }
}
