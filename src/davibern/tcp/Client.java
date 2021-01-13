package davibern.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    
    private final String HOST = "localhost";
    private final int PORT = 5555;
    private int square;
    
    public Client(int square) {
        
        this.square = square;
        
        try {
            Socket client = new Socket(this.HOST, this.PORT);
            InputStream input = client.getInputStream();
            DataInputStream read = new DataInputStream(input);
            System.out.println(read.readUTF());
            
            OutputStream output = client.getOutputStream();
            DataOutputStream write = new DataOutputStream(output);
            write.writeUTF("" + this.square);
            System.out.println(read.readUTF());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
    }
    
    public static void main (String[] args) {
        new Client(16);
    }
    
}
