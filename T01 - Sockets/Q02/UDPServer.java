import java.net.*;
import java.io.*;

import java.util.Arrays;
import java.lang.String;

public class UDPServer{
    public static void main(String args[]){
    	DatagramSocket aSocket = null;
		try{
	    	aSocket = new DatagramSocket(6789);
					// create socket at agreed port
			byte[] buffer = new byte[1000];
 			while(true){
 				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
  				aSocket.receive(request);
				String data = new String(buffer);

				int pos = 0;
				char op = ' ';
				String op1_ = " ";
				String op2_ = " ";
				double op1 = 0;
				double op2 = 0;
				double operacao = 0;
				String resultado = " ";

				for (int i = 0; i < data.length(); i++) {
					if (data.charAt(i) == '+' || data.charAt(i) == '-' || data.charAt(i) == '*' || data.charAt(i) == '/') {
						op = data.charAt(i);
						pos = i;
					}
					op1_ = data.substring(0, pos);
					op2_ = data.substring(pos+1, data.length());
				}

				op1 = Double.parseDouble(op1_);
				op2 = Double.parseDouble(op2_);

				switch(op){
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
						operacao = op1 / op2;
						resultado = Double.toString(operacao);
						break;
					default:
						break;
				}

    			DatagramPacket reply = new DatagramPacket(resultado.getBytes(), resultado.length(),
    				request.getAddress(), request.getPort());
    			aSocket.send(reply);
    		}
		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e) {System.out.println("IO: " + e.getMessage());
		}finally {if(aSocket != null) aSocket.close();}
    }
}
