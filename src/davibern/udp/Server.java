package davibern.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Sender {
    
    private static final int PORT = 5555;

    public static void main (String args[]) {
        
        try {
            DatagramSocket socket = new DatagramSocket();
            
            InetAddress machine = InetAddress.getByName("localhost");
            
            byte[] message = new byte[1000];
            
            DatagramPacket packet = new DatagramPacket(message, message.length, machine, PORT);
            
            socket.send(packet);
            
            socket.close();
            
        } catch (SocketException e) {
            System.err.println(e.getMessage());
        } catch (UnknownHostException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        

    }
    
}
