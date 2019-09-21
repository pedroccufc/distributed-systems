import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPClient1 {
	public static void main (String[] args) {
                
        Scanner fluxo = new Scanner(System.in);
		Socket s = null;

		try{
			int serverPort = 7896;
            InetAddress idnet = InetAddress.getByName("localhost");
			s = new Socket(idnet, serverPort, idnet, 1287);

			DataInputStream in = new DataInputStream( s.getInputStream());
			DataOutputStream out =new DataOutputStream( s.getOutputStream());
                        
            String ler;                        
            while(true){
                ler = fluxo.nextLine();
                out.writeUTF(ler);
                String data = in.readUTF();
                System.out.println("Cliente 2: "+ data) ;
            } 
		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
     }
}