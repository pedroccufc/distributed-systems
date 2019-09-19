import java.net.*;
import java.io.*;
import java.util.Scanner;
public class TCPClient1 {
	public static void main (String args[]) {
		// arguments supply message and hostname
		Socket s = null;
		try{
			String msn = " ";
			Scanner fluxo = new Scanner(System.in);
			int serverPort = 7896;
			s = new Socket("localhost", serverPort); 
			DataInputStream in = new DataInputStream( s.getInputStream());
			DataOutputStream out =new DataOutputStream( s.getOutputStream());
			String data = " ";

			while(true){
				System.out.print("Cliente 1: ");
				msn = fluxo.nextLine();
				out.writeUTF(msn);
				data = in.readUTF();
				System.out.println("Cliente 2: " + data);
			}
			
			
		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
     }
}
