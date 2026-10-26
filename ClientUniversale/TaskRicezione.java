import java.io.BufferedReader;
import java.io.IOException;

public class TaskRicezione implements Runnable {
    private final BufferedReader in;

    public TaskRicezione(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            String messaggio;
            while ((messaggio = in.readLine()) != null) {
                System.out.println("Server: " + messaggio);
            }
        } catch (IOException e) {
            System.out.println("Connessione terminata.");
        }
    }
}
