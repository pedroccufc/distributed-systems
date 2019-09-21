import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPClient{
    public static void main(String[] args){ 
		DatagramSocket aSocket = null;

		try {
            
			aSocket = new DatagramSocket();   

            // para ler do teclado
            Scanner fluxo = new Scanner(System.in);
            String ler = fluxo.nextLine();

            byte[] m = ler.getBytes();
            InetAddress aHost = InetAddress.getByName("localhost");

            int serverPort = 6789;		                                                 
            DatagramPacket request = new DatagramPacket(m, ler.length(), aHost, serverPort);
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