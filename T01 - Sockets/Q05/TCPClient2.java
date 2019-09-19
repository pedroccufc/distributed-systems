import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class TCPClient2 {
    
    public static DataInputStream in2;

    public static Read2 read2;
    public void inicializaRead2(){
        read2 = new Read2();
        read2.start();
    }
    
	public static void main (String[] args) {
		// arguments supply message and hostname  
        Scanner ler = new Scanner(System.in);
                
		Socket s = null;
		try{
			int serverPort = 7896;
			s = new Socket("localhost", serverPort);    
			in2 = new DataInputStream( s.getInputStream());
			DataOutputStream out2 =new DataOutputStream( s.getOutputStream());
                        
            TCPClient2 cliente2 = new TCPClient2();
            cliente2.inicializaRead2();
                        
            while(true){
                out2.writeUTF(ler.nextLine());      	
            } 
		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());}
    }

    public class Read2 extends Thread{
        public void run(){
            while(true){
                try {
                    System.out.println("Cliente 01: " + in2.readUTF());
                } catch (IOException ex) {
                    Logger.getLogger(TCPClient2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }    
}