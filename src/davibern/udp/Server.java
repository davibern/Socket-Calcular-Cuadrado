package davibern.udp;

// Librerías
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Servidor: calcula la raíz cuadrada de un valor recibido por el cliente UDP
 * 
 * @author davibern
 * @version 1.1
 */
public class Server implements Runnable {
    
    // Atributos
    private static final int PORT = 9999;
    
    /**
     * Método que envía al cliente la raíz cuadrada de un número dado por el cliente
     */
    public void enviarRaizCuadrada () {
        try {
            // Se crea un array de bytes para recibir la informaciónd el cliente
            byte[] buffer = new byte[1024];
            // Se crea el socket del servidor
            DatagramSocket ds = new DatagramSocket(PORT);
            // Se crea un paquete de datagrama
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            // Se recibe el paquete
            ds.receive(dp);
            // Se pasa su valor a String
            String str = new String(dp.getData(), 0, dp.getLength());
            // Se convierte el valor a entero
            double num = Double.parseDouble(str.trim());
            // Se calcula su raiz cuadrada
            double result = (double) Math.sqrt(num);
            // Se crea otro array de bytes para pasar el calculo
            byte[] b2 = String.valueOf(result).getBytes();
            // Se obtiene el valor del host local
            InetAddress ia = InetAddress.getLocalHost();
            // Se crea otro datagrama para enviar el resutlado
            DatagramPacket dp1 = new DatagramPacket(b2, b2.length, ia, dp.getPort());
            // Se envía el resultado
            ds.send(dp1);
        } catch (SocketException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
    /**
     * Método sobreescrito de la intefaz Runnable
     * Ejecuta el método enviarRaizCuadrada()
     */
    @Override
    public void run () {
        this.enviarRaizCuadrada();
    }
}
