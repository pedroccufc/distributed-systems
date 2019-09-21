import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPServer {

    public static ServerSocket sv;
    public static Socket listenSocket1;
    public static Socket clientSocket1;

    public static Socket listenSocket2;
    public static Socket clientSocket2;


    public static DataInputStream in1;
    public static DataOutputStream out1;

    public static DataInputStream in2;
    public static DataOutputStream out2;

    
    public void inicializaRead1(){
        Read1 r1 = new Read1();
        r1.start();
    }
    
    public void inicializaRead2(){
        Read2 r2 = new Read2();
        r2.start();
    }
    

    public static void main (String[] args) {
        try{
            sv = new ServerSocket(7896);
            listenSocket1 = sv.accept();
            clientSocket1 = listenSocket1;
            System.out.println("Cliente 01: "+clientSocket1.getPort());
            
            listenSocket2 = sv.accept();
            clientSocket2 = listenSocket2;
            System.out.println("Cliente 02: "+clientSocket2.getPort());
                    
            in1 = new DataInputStream( clientSocket1.getInputStream());
            out1 =new DataOutputStream( clientSocket1.getOutputStream());
            
            in2 = new DataInputStream( clientSocket2.getInputStream());
            out2 = new DataOutputStream( clientSocket2.getOutputStream());
          
            
            TCPServer server = new TCPServer();
            server.inicializaRead1();
            server.inicializaRead2();
            
            while(true){
                Thread.sleep(1);
            }
            
        } catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());} catch (InterruptedException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public class Read1 extends Thread{
        public void run(){
            System.out.println("lendo canal 01");
            while(true){
                try {
                    Emoji emoji = new Emoji();
                    out2.writeUTF(emoji.retornaEmoji(in1.readUTF()));
                } catch (IOException ex) {
                    Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public class Read2 extends Thread{
        public void run(){
            System.out.println("lendo canal 02");
            while(true){
                try {
                    Emoji emoji = new Emoji();
                    out1.writeUTF(emoji.retornaEmoji(in2.readUTF()));
                } catch (IOException ex) {
                    Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}