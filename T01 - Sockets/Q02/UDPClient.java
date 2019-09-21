import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPClient{
    public static void main(String[] args){ 
        Scanner fluxo = new Scanner(System.in);
		DatagramSocket aSocket = null;

		try {
			aSocket = new DatagramSocket();   
            System.out.println("Digite a expressão matemática (EXEMPLO: 4+5): ");

            String exp = fluxo.nextLine();
                        
            if(exp.equals("")){
                System.out.println("Mensagem vazia!");
            } else {
                byte [] m = exp.getBytes();
                InetAddress aHost = InetAddress.getByName("localhost");
                int serverPort = 6789;

                DatagramPacket request = new DatagramPacket(m, exp.length(), aHost, serverPort);
                aSocket.send(request);

                byte[] buffer = new byte[1000];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);	
                aSocket.receive(reply);
                System.out.println("Resultado: " + new String(reply.getData()));
            }

		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e){System.out.println("IO: " + e.getMessage());
		}finally {if(aSocket != null) aSocket.close();}
	}		      	
}