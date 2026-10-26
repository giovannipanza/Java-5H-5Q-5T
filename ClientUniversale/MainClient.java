import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8080);
        System.out.println("Connesso al server!");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Creazione e avvio dei due thread distinti
        Thread tRicezione = new Thread(new TaskRicezione(in));
        Thread tInvio = new Thread(new TaskInvio(out));

        tRicezione.start();
        tInvio.start();
    }
}
