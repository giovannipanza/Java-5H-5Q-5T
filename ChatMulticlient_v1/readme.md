SCHEMA
                     +-------------------------+
                     |       MainServer        |
                     |                         |
                     |    Avvia il server      |
                     +-------------------------+
                                  |
                                  | crea
                                  v
             +-----------------------------------------+
             |               ServerChat                |
             |                                         |
             |  Accetta le connessioni                 |
             |  e gestisce i client collegati          |
             +-----------------------------------------+
                                  |
                                  | crea un Thread
                                  | per ogni client
                                  v
             +-----------------------------------------+
             |                                         |
             v                   v                     v
    +----------------+  +----------------+  +----------------+
    | ClientHandler 1|  | ClientHandler 2|  | ClientHandler 3|
    |                |  |                |  |                |
    |     Thread     |  |     Thread     |  |     Thread     |
    +----------------+  +----------------+  +----------------+
             |                   |                     |
             v                   v                     v
         Client 1             Client 2              Client 3


CLASSI
MainServer -> Contiene il main() e avvia il server
ServerChat -> Apre il ServerSocket, accetta i client e conserva l'elenco dei client connessi
ClientHandler extends Thread -> Gestisce la comunicazione con un singolo client e inoltra i messaggi al server


METODI 
+ startServer(): void — attende le connessioni, crea e avvia i ClientHandler.
+ addClient(ClientHandler c): void — aggiunge un client alla lista.
+ removeClient(ClientHandler c): void — rimuove un client disconnesso.
+ broadcast(String msg): void — invia un messaggio a tutti i client collegati.

FLUSSO DI ESECUZIONE
1. Avvio del server
   MainServer crea ServerChat e chiama startServer().
2. Attesa di una connessione 
   ServerChat rimane bloccato su accept() fino a quando un client si collega.
3. Creazione del Thread
   Per il nuovo client viene creato un ClientHandler, aggiunto alla lista e avviato con start().
4. Ricezione di un messaggio
   Il ClientHandler legge il messaggio tramite readLine() e richiama server.broadcast(messaggio).
5. Distribuzione del messaggio
   ServerChat scorre la lista dei client e richiama sendMessage() su ciascun ClientHandler.
6. Disconnessione
   Quando readLine() restituisce null o si verifica un errore di comunicazione, il client viene rimosso dalla lista e il socket viene chiuso.

VANTAGGI DI QUESTA SCELTA
- Incapsulamento: la lista dei client rimane gestita da ServerChat.
- Separazione delle responsabilità: ClientHandler riceve i messaggi, ServerChat li distribuisce.
- Collaborazione tra oggetti: un oggetto richiama i metodi di un altro attraverso un riferimento.

Un oggetto non deve necessariamente conoscere tutti gli oggetti con cui deve interagire. È sufficiente che conosca l'oggetto responsabile del servizio di cui ha bisogno.
