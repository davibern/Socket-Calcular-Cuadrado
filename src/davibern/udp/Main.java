package davibern.udp;

/**
 * Clase que ejecuta Servidor y Cliente
 * 
 * @author davibern
 * @version 1.0
 */
public class Main {
    
    /**
     * Método que lanza el programa, sin argumentos
     * @param args sin argumentos
     */
    public static void main (String args[]) {
        // Se crean objetos tipo cliente y servidor
        Client client = new Client(57);
        Server server = new Server();
        
        // Se crean dos hilos de ejecución, uno para el servidor y otro para el cliente
        Thread s = new Thread(server);
        Thread c = new Thread(client);
        
        // Se ejecutan sus métodos run a través de start()
        s.start();
        c.start();
    }
    
}
