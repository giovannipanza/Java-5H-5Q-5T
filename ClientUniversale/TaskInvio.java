import java.io.PrintWriter;
import java.util.Scanner;

public class TaskInvio implements Runnable {
    private final PrintWriter out;

    public TaskInvio(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void run() {
        Scanner tastiera = new Scanner(System.in);
        while (true) {
            String messaggio = tastiera.nextLine();
            out.println(messaggio);

            if (messaggio.equalsIgnoreCase("QUIT")) {
                break;
            }
        }
    }
}
