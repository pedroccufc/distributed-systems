import java.net.*;
import java.io.*;
import java.util.Scanner;
public class TCPServer {
	public static void main (String args[]) {
		try{
			int serverPort = 7896; // the server port
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while(true) {
				Socket clientSocket1 = listenSocket.accept();
				Socket clientSocket2 = listenSocket.accept();
				Connection c = new Connection(clientSocket1, clientSocket2);
			}
		} catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
	}
}
class Connection extends Thread {

	// Cliente 01
	DataInputStream in1;
	DataOutputStream out1;
	Socket clientSocket1;

	// Cliente 02
	DataInputStream in2;
	DataOutputStream out2;
	Socket clientSocket2;
	
	public Connection (Socket aClientSocket, Socket bClientSocket) {
		try {
			clientSocket1 = aClientSocket;
			in1 = new DataInputStream(clientSocket1.getInputStream());
			out1 = new DataOutputStream(clientSocket1.getOutputStream());

			clientSocket2 = bClientSocket;
			in2 = new DataInputStream(clientSocket2.getInputStream());
			out2 = new DataOutputStream(clientSocket2.getOutputStream());

			String msn1 = " ";
			String msn2 = " ";

			Scanner fluxo = new Scanner(System.in);
			String data = " ";

			while (true) {
				msn1 = in1.readUTF();
				if (!msn1.equals(null)) {
					out2.writeUTF(msn1);
				}

				msn2 = in2.readUTF();
				if (!msn2.equals(null)) {
					out1.writeUTF(msn2);
				}
			}

		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}
}
