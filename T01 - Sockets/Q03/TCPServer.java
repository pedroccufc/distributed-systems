import java.net.*;
import java.io.*;
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
			this.start();
		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}
	public void run(){
		try {			                 // an echo server

			String data = in.readUTF();	 
			int pos = 0;                 // read a line of data from the stream
			char sinal = ' ';
			double operacao = 0;
			String resultado = " ";

				for(int i=0; i < data.length(); i++){
					if(data.charAt(i) == '+' || data.charAt(i) == '-' || data.charAt(i) == '*' || data.charAt(i) == '/'){
						sinal = data.charAt(i);
						pos = i;
					}
				}

				String op1_ = data.substring(0, pos);
				String op2_ = data.substring(pos+1, data.length());

				double op1 = Double.parseDouble(op1_);
				double op2 = Double.parseDouble(op2_);

				switch(sinal){
					case '+':
						operacao = op1 + op2;
						resultado = Double.toString(operacao);
						break;
					case '-':
						operacao = op1 - op2;
						resultado = Double.toString(operacao);
						break;
					case '*':
						operacao = op1 * op2;
						resultado = Double.toString(operacao);
						break;
					case '/':
						if (op2 == 0) {
							resultado = "Impossível dividir por 0";
						} else {
							operacao = op1 / op2;
							resultado = Double.toString(operacao);
						}
						break;
					default:
						break;
				}

			out.writeUTF(resultado);
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());
		} finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
		

	}
}
