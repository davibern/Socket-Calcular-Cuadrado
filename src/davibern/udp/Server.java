package davibern.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {
    
    public static void main (String args[]) {
        
        try {
            // Se crea un array de bytes para recibir la informaciónd el cliente
            byte[] buffer = new byte[1024];
            // Se crea el socket del servidor
            DatagramSocket ds = new DatagramSocket(9999);
            // Se crea un paquete de datagrama
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            // Se recibe el paquete
            ds.receive(dp);
            // Se pasa su valor a String
            String str = new String(dp.getData(), 0, dp.getLength());
            // Se convierte el valor a entero
            int num = Integer.parseInt(str.trim());
            // Se calcula su raiz cuadrada
            int result = (int) Math.sqrt(num);
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
    
}
