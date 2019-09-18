import java.net.*;
import java.io.*;
import java.util.Scanner;
public class UDPClient{
    public static void main(String args[]){ 
		// args give message contents and destination hostname
		DatagramSocket aSocket = null;
		try {
			Scanner ler = new Scanner(System.in);

			System.out.println("Digite a equação:");
			String str = ler.nextLine();

			aSocket = new DatagramSocket();    
			byte [] m = str.getBytes();
			InetAddress aHost = InetAddress.getByName("localhost");
			int serverPort = 6789;		                                                 
			DatagramPacket request =
			 	new DatagramPacket(m,  str.length(), aHost, serverPort);
			aSocket.send(request);			                        
			byte[] buffer = new byte[1000];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);	
			aSocket.receive(reply);
			System.out.println("Reply: " + new String(reply.getData()));	
		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e){System.out.println("IO: " + e.getMessage());
		}finally {if(aSocket != null) aSocket.close();}
	}		      	
}
