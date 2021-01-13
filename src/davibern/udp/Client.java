package davibern.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Client {
    
    public static void main (String args[]) {
        try {
            // Valor de la que se calculara su raiz cuadrada
            int square = 16;
            // Se obtiene su valor en bytes
            byte[] buffer = String.valueOf(square).getBytes();
            
            // Se crea el socket del cliente
            DatagramSocket ds = new DatagramSocket();
            // Se obtiene el host local
            InetAddress ia = InetAddress.getLocalHost();
            // Se crea un paquete del datagrama que requiere: el mensaje, su longitud, el host, y el puerto
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length, ia, 9999);
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
    
}
