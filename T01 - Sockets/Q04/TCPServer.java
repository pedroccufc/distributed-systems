import java.net.*;
import java.io.*;
import java.util.Scanner;
public class TCPServer {
	public static void main (String args[]) {
		try{
			int serverPort = 7896; // the server port
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while(true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
			}
		} catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
	}
}
class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	public Connection (Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;
			in = new DataInputStream( clientSocket.getInputStream());
			out =new DataOutputStream( clientSocket.getOutputStream());
			String msn = " ";
			Scanner fluxo = new Scanner(System.in);
			String data = " ";

			while(true){
				data = in.readUTF();
				System.out.println("Mensagem Recebida: "+ data);
				System.out.println("Digite sua mensagem:");
				msn = fluxo.nextLine();
				out.writeUTF(msn);
			}

		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}
}
