package davibern.tcp;

// Librerías
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Cliente que envía un número para que el servidor calcule su raíz cuadrada
 * 
 * @author davibern
 * @version 1.1
 */
public class Client {
    
    // Atributos
    private final String HOST = "localhost";
    private final int PORT = 5555;
    private int square;
    
    /**
     * Constructor de clase. Se inicializará con un número de tipo entero
     * 
     * @param square Valor del que se calculará su raíz cuadrada
     */
    public Client(int square) {
        
        this.square = square;
        
        try {
            /*
                - Se crea el socket del cliente con el host y puerto especificado
                - Se abre un stream de datos de tipo entrada
                - Se abre un stream de datos de tiop salida
            */
            Socket client = new Socket(this.HOST, this.PORT);
            InputStream input = client.getInputStream();
            DataInputStream read = new DataInputStream(input);
            System.out.println(read.readUTF());
            
            /*
                Se inicializa los streams de entrada y salida
                Se recupera del servidor el valor de la raíz cuadrada
                Se muestra por pantalla
                Se cierra el socket (cliente)
            */
            OutputStream output = client.getOutputStream();
            DataOutputStream write = new DataOutputStream(output);
            write.writeUTF("" + this.square);
            System.out.println(read.readUTF());
            client.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
    }
    
    /**
     * Método que ejecuta Client
     * @param args Sin argumentos
     */
    public static void main (String[] args) {
        new Client(16);
    }
    
}
