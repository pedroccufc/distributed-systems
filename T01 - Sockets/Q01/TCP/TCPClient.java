import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPClient {
	public static void main (String[] args) {
		// Para ler do teclado
		Scanner fluxo = new Scanner(System.in);
		Socket s = null;
		try{
			// Porta na qual o servidor vai escutar
			int serverPort = 7896;
			s = new Socket("localhost", serverPort);

			// para entrada e saida
			DataInputStream in = new DataInputStream( s.getInputStream());
			DataOutputStream out =new DataOutputStream( s.getOutputStream());
            
            // lendo a mensagem e enviando para o servidor
			out.writeUTF(fluxo.nextLine());

			// mostrando a resposta que o servidor retornou
			System.out.println("Received: "+ in.readUTF());

		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
     }
}