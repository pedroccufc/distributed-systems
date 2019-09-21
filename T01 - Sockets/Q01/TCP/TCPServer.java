import java.net.*;
import java.io.*;
public class TCPServer {
	public static void main (String[] args) {
		try{
			// porta na qual o servidor está escutando
			int serverPort = 7896;
			ServerSocket listenSocket = new ServerSocket(serverPort);

			// accept cria uma conexão com o servidor
			while(true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
			}
		} catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
	}
}

// Criando uma nova Thread para executar o processo
class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	public Connection (Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;
			in = new DataInputStream( clientSocket.getInputStream());
			out = new DataOutputStream( clientSocket.getOutputStream());

			// método start() inicializa a Thread
			this.start();
		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}
	public void run(){

		try {
			// retornando ao cliente a mensagem recebida
			String data = in.readUTF();
			System.out.println("Mensagem recebida: " + data);
			out.writeUTF(data);

		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());
		} finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
	}
}