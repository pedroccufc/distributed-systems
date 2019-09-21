import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPServer {
    public static void main (String[] args) {
        try{
           
            ServerSocket sv = new ServerSocket(7896);

            Socket listenSocket = sv.accept();
            Socket clientSocket = listenSocket;
            System.out.println("Cliente 1: "+clientSocket.getPort());

            Socket listenSocket2 = sv.accept();
            Socket clientSocket2 = listenSocket2;
            System.out.println("Cliente 2: "+clientSocket2.getPort());
            
            DataInputStream in1;
            DataOutputStream out1;
    
            DataInputStream in2;
            DataOutputStream out2;
            
            in1 = new DataInputStream( clientSocket.getInputStream());
            out1 =new DataOutputStream( clientSocket.getOutputStream());
            
            in2 = new DataInputStream( clientSocket2.getInputStream());
            out2 =new DataOutputStream( clientSocket2.getOutputStream());
            
            String msn1 = "", msn2 = "";
            
            while(true){
                msn1 = in1.readUTF();
                if(msn1!=null){
                  out2.writeUTF(msn1);
                }
                
                msn2 = in2.readUTF();
                if(msn2!=null){
                  out1.writeUTF(msn2);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        } catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
    }
}