import java.io.*;
import java.net.*;
import java.util.logging.*;

public class TCPClient1 {
    private String messege;
    private String response;

    DataInputStream in;
    DataOutputStream out;
    
    Socket s = null;    
    
    public void sendRequest(String request) throws IOException{
        messege = request;
        out.writeUTF(messege);      	
    }
    
    public void close(){
        try {
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(TCPClient1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getResponse() throws IOException{
        response = in.readUTF();
        return response;
    }
    
    public void start() throws UnknownHostException, IOException{
        int serverPort = 7896;
        InetAddress idnet = InetAddress.getByName("localhost");

        s = new Socket(idnet, serverPort, idnet, 1289);   
        in = new DataInputStream( s.getInputStream());
        out = new DataOutputStream( s.getOutputStream());
     }
}
