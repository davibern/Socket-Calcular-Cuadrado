package davibern.tcp;

// Librerias
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Servidor que recibe un número para calcular su raíz cuadrada
 * 
 * @author davibern
 * @version 1.1
 */
public class Server {
    
    // Atributos
    private final int PORT = 5555;
    private double square;
    
    /**
     * Constructor sin parámetros
     */
    public Server() {
        
        try {
            // Se inicia el servidor con los parámetros de host y puerto especificados en sus atributos
            ServerSocket server = new ServerSocket(this.PORT);
            System.out.println("Levantado servidor en el puerto " + this.PORT);
            
            // Se inicia conexión con el cliente
            Socket client = server.accept();
            System.out.println("Conectado el cliente");
            
            // Se envia información al cliente
            OutputStream output = client.getOutputStream();
            DataOutputStream write = new DataOutputStream(output);
            write.writeUTF("Mensaje enviado desde el servidor");
            
            // Se obtiene información del servidor, en este caso el valor para calcular
            InputStream input = client.getInputStream();
            DataInputStream read = new DataInputStream(input);
            // Se castea el valor de entero a doble
            this.square = Double.parseDouble(read.readUTF());
            
            // Se limpia el outputstream
            write.flush();
            
            // Se muestra por pantalla y se envía al cliente la raíz cuadrada calculada en getSquare()
            System.out.println("El cuadrado de " + this.square + " es " + this.getSquare());
            write.writeUTF("El cuadrado de " + this.square + " es " + this.getSquare());
            // Se servidor
            server.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
    }
    
    /**
     * Método que calcula la raíz cuadrada
     * @return Devuelve un doble con la raíz cuadrada
     */
    private double getSquare() {
        double result;
        result = Math.sqrt(this.square);
        return result;
    }
    
    /**
     * Método que ejecuta el programa Server
     * @param args Sin argumentos
     */
    public static void main (String[] args) {
        new Server();
    }
    
}
