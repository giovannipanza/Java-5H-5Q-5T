MainServer: avvia l'applicazione.
ServerChat: accetta le connessioni e crea i Thread.
ClientHandler: comunica con un singolo client.
ChatManager: conosce tutti i ClientHandler e distribuisce i messaggi.

La differenza rispetto alla soluzione precedente
Nella prima versione (v1) avevamo: server.broadcast(messaggio); Adesso abbiamo: chatManager.broadcast(messaggio);

Non è soltanto un cambiamento di nome: abbiamo distribuito diversamente le responsabilità. In particolare, 
ServerChat non deve più sapere come vengono conservati i client o come vengono inoltrati i messaggi. 
Questi dettagli appartengono a ChatManager.
Questo è un buon esempio di separazione delle responsabilità e incapsulamento.


                         +------------------+
                         |    MainServer    |
                         +------------------+
                                  |
                                  v
                         +------------------+
                         |    ServerChat    |
                         |    porta 5000    |
                         +------------------+
                                  |
                    +-------------+-------------+
                    |             |             |
                    v             v             v
             +------------+ +------------+ +------------+
             |  Handler 1 | |  Handler 2 | |  Handler 3 |
             |  Thread 1  | |  Thread 2  | |  Thread 3  |
             +------------+ +------------+ +------------+
                    |             |             |
                    v             v             v
                Client 1      Client 2      Client 3


                         +------------------+
                         |   ChatManager    |
                         |                  |
                         |  Lista Handler   |
                         |  addClient()     |
                         |  removeClient()  |
                         |  broadcast()     |
                         +------------------+
                                  ^
                                  |
                     utilizzato da tutti
                       i ClientHandler
