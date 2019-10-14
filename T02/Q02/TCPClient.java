import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPClient {
    private String message;
    private String response;

    DataInputStream in;
    DataOutputStream out;
    
    Socket s = null;    
        
     public void sendRequest(String request) throws UnknownHostException, IOException{
        this.message = request;
        this.out.writeUTF(this.message);      	
     }
     public String getResponse() throws IOException{
        this.response = this.in.readUTF();
        return this.response;
     }
     
     public void start() throws UnknownHostException, IOException{
        int serverPort = 7896;
        InetAddress idnet = InetAddress.getByName("localhost");
        this.s = new Socket("localhost", serverPort, idnet, 1227);
        this.in = new DataInputStream( s.getInputStream());
        this.out = new DataOutputStream( s.getOutputStream());

     }
    public void close(){
        try {
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
