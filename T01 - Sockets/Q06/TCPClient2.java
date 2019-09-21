import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPClient2 {
    
    public static DataInputStream in;
    
    public static Read2 r2;
    public void inicializaRead2(){
        r2 = new Read2();
        r2.start();
    }
    
	public static void main (String[] args) {
                
        Scanner fluxo = new Scanner(System.in);
		Socket s = null;

		try{
			int serverPort = 7896;
            InetAddress idnet = InetAddress.getByName("localhost");
			s = new Socket(idnet,serverPort,idnet,1288);

			in = new DataInputStream( s.getInputStream());
			DataOutputStream out =new DataOutputStream( s.getOutputStream());
                        
            TCPClient2 c2 = new TCPClient2();
            c2.inicializaRead2();
                        
            while(true){
                out.writeUTF(fluxo.nextLine());      	
            } 
		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
		}
    }

    public class Read2 extends Thread{
        public void run(){
            while(true){
                try {
                    System.out.println("Cliente 01: "+ in.readUTF());
                } catch (IOException ex) {
                    Logger.getLogger(TCPClient2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }    
}