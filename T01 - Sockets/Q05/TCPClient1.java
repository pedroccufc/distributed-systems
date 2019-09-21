import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPClient1 {
    
    public static DataInputStream in;
    
    public static Read1 r1;
    public void inicializaRead1(){
        r1 = new Read1();
        r1.start();
    }
    
    public static void main (String[] args) {
        Scanner fluxo = new Scanner(System.in);
        Socket s = null;

        try{
            int serverPort = 7896;
            InetAddress idnet = InetAddress.getByName("localhost");
            s = new Socket(idnet,serverPort,idnet,1287);    
            
            in = new DataInputStream( s.getInputStream());
            DataOutputStream out = new DataOutputStream( s.getOutputStream());

            TCPClient1 c1 = new TCPClient1();
            c1.inicializaRead1();
                        
            while(true){
                out.writeUTF(fluxo.nextLine());      
            } 
        }catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
        }catch (EOFException e){System.out.println("EOF:"+e.getMessage());
        }catch (IOException e){System.out.println("readline:"+e.getMessage());
        }
        }
        
    public class Read1 extends Thread{
        public void run(){
            while(true){
                try {
                    System.out.println("Cliente 02: " + in.readUTF());
                } catch (IOException ex) {
                    Logger.getLogger(TCPClient1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }    
        
}
