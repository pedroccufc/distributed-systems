import java.net.*;
import java.io.*;
import java.util.*;

public class TCPServer {
	private String msn;
	private String reply;
	DataInputStream in;
	DataOutputStream out;

	public String getRequest() throws IOException {
		msn = in.readUTF();
		return msn;
	}

	public void sendResponse(String response) throws IOException {
		out.writeUTF(response);
	}

	public static void main(String[] args){
		try{
			ServerSocket listenSocket = new ServerSocket(7896);
			Socket clientSocket = listenSocket.accept();

			TCPServer server = new TCPServer();
			server.in = new DataInputStream(clientSocket.getInputStream());
			server.out = new DataOutputStream(clientSocket.getOutputStream());

			String msn_ = server.getRequest();
			if(msn_.equals("")){
				System.exit(0);
			}

			String sinal = "";
			String array[] = new String[3];

			array = msn_.split(";");
			sinal = array[2];

			double n1 = Double.parseDouble(array[0]);
			double n2 = Double.parseDouble(array[1]);

			Calculadora calc = new Calculadora();
			switch(sinal){
				case "+":
					msn_ = Double.toString(calc.add(n1, n2));
					break;
				case "-":
					msn_ = Double.toString(calc.sub(n1, n2));
					break;
				case "*":
					msn_ = Double.toString(calc.mult(n1, n2));
					break;
				case "/":
					if(n2 == 0){
						msn_ = "Impossível dividir por 0";
					}else{
						msn_ = Double.toString(calc.div(n1, n2));
					}
					break;
				default:
					throw new AssertionError();
			}
			server.sendResponse(msn_);
		}catch(IOException e){System.out.println("listen socket: " + e.getMessage());}
	}
}