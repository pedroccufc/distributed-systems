import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class TCPClient1 {
    
    public static DataInputStream in1;
    
    public static Read1 read1;
    public void inicializaRead1(){
        read1 = new Read1();
        read1.start();
    }
    
    
	public static void main (String[] args) {
		// arguments supply message and hostname
                
        Scanner ler = new Scanner(System.in);
                
		Socket s = null;
		try{
			int serverPort = 7896;
            s = new Socket("localhost", serverPort);    
			
            in1 = new DataInputStream(s.getInputStream());
			DataOutputStream out1 = new DataOutputStream( s.getOutputStream());
                        
            TCPClient1 cliente1 = new TCPClient1();
            cliente1.inicializaRead1();
                        
            while(true){
                out1.writeUTF(ler.nextLine());      
            } 
		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());}
    }
        
    public class Read1 extends Thread{
        public void run(){
            while(true){
                try {
                    System.out.println("Cliente 02: " + in1.readUTF());
                } catch (IOException ex) {
                    Logger.getLogger(TCPClient1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                        
        }
    }    
        
}
