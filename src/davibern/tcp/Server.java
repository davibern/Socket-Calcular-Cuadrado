package davibern.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    private final int PORT = 5555;
    private double square;
    
    public Server() {
        
        try {
            ServerSocket server = new ServerSocket(this.PORT);
            System.out.println("Levantado servidor en el puerto " + this.PORT);
            
            Socket client = server.accept();
            System.out.println("Conectado el cliente");
            
            OutputStream output = client.getOutputStream();
            DataOutputStream write = new DataOutputStream(output);
            write.writeUTF("Mensaje enviado desde el servidor");
            
            InputStream input = client.getInputStream();
            DataInputStream read = new DataInputStream(input);
            this.square = Double.parseDouble(read.readUTF());
            
            write.flush();
            
            System.out.println("El cuadrado de " + this.square + " es " + this.getSquare());
            write.writeUTF("El cuadrado de " + this.square + " es " + this.getSquare());
            client.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
    }
    
    private double getSquare() {
        double result;
        result = Math.sqrt(this.square);
        return result;
    }
    
    public static void main (String[] args) {
        new Server();
    }
    
}
