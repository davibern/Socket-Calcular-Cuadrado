package davibern.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Receipt {
    
    private static final int PORT = 5555;
    
    public static void main (String[] args) {
     
        try {
            DatagramSocket socket = new DatagramSocket(PORT);
            
            byte[] message = new byte[1000];
            
            DatagramPacket packet = new DatagramPacket(message, message.length);
            
            System.out.println("Esperando mensajes...");
            
            while (true) {
                socket.receive(packet);
                String data = new String (packet.getData(), 0, packet.getLength());
                System.out.println("Mensaje recibido: " + data);
            }
            
        } catch (SocketException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
}
