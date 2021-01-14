package davibern.udp;

// Librerías
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Cliente: envía un valor tipo doble para que se calcule su raíz cuadrada en el servidor UDP
 * 
 * @author davibern
 * @version 1.1
 */
public class Client implements Runnable {
    
    // Atributos
    private double square;
    private static final int PORT = 9999;
    
    /**
     * Constructor de clase
     * @param square Valor que se enviará al servidor para calcular su raíz cuadrada
     */
    public Client (double square) {
        this.square = square;
    }
    
    /**
     * Método que envía al servidor el valor para calcular su raíz cuadrada
     */
    public void enviarValorAlServidor () {
        try {
            // Se obtiene su valor en bytes
            byte[] buffer = String.valueOf(this.square).getBytes();
            
            // Se crea el socket del cliente
            DatagramSocket ds = new DatagramSocket();
            // Se obtiene el host local
            InetAddress ia = InetAddress.getLocalHost();
            // Se crea un paquete del datagrama que requiere: el mensaje, su longitud, el host, y el puerto
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length, ia, PORT);
            // Se envia el datagrama
            ds.send(dp);
            
            // Se crea un nuevo array para guardar el cuadrado del numero enviado
            byte[] resultSquare = new byte[1024];
            DatagramPacket dp1 = new DatagramPacket(resultSquare, resultSquare.length);
            // Se recibe el paquete
            ds.receive(dp1);
            // Se guarda como String
            String str = new String(dp1.getData(), 0, dp1.getLength());
            // Se muestra por pantalla
            System.out.println("La raiz cuadrada de " + square + " es " + str);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Método sobreescrito de la intefaz Runnable
     * Ejecuta el método enviarValorAlServidor()
     */
    @Override
    public void run () {
        this.enviarValorAlServidor();
    }
    
}
