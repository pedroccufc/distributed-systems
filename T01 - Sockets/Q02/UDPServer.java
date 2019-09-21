import java.net.*;
import java.io.*;

public class UDPServer{
    public static void main(String[] args){ 
        String resposta = "";
        
    	DatagramSocket aSocket = null;
		try{
	    	aSocket = new DatagramSocket(6789);
			byte[] buffer = new byte[1000];

 			while(true){
 				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
  				aSocket.receive(request);

                if(buffer.length != 0){
                    String exp = new String(buffer);

                    System.out.println("Equação recebida: " + exp);

                    char sinal = '#';
                    int pos = 0;

                    for (int i = 0; i < exp.length(); i++) {
                        if(exp.charAt(i) == '+' || exp.charAt(i) == '-' || exp.charAt(i) == '*' || exp.charAt(i) == '/'){
                            sinal = exp.charAt(i);
                            pos = i;
                            break;
                        }
                    }
                                    
                    String value_1 = exp.substring(0, pos);
                    String value_2 = exp.substring(pos+1, exp.length());
                           
                    double number_1 = Double.parseDouble(value_1);
                    double number_2 = Double.parseDouble(value_2);
                                    
                    double resultado = 0;
                                    
                    switch (sinal) {
                        case '+':
                            resultado = number_1 + number_2;
                            resposta = Double.toString(resultado);
                            break;
                        case '-':
                            resultado = number_1 - number_2;
                            resposta = Double.toString(resultado);
                            break;  
                        case '*':
                            resultado = number_1 * number_2;
                            resposta = Double.toString(resultado);
                            break; 
                        case '/':
                            if(number_2 != 0){
                                resultado = number_1 / number_2;
                                resposta = Double.toString(resultado);
                            } else {
                                resposta = "Impossível dividir por 0";
                            }
                            break;
                        default:
                            resposta = "Operação inválida";
                    }
                }
                System.out.println("Resultado retornado: " + resposta);
    			DatagramPacket reply = new DatagramPacket(resposta.getBytes(), resposta.length(), 
    				request.getAddress(), request.getPort());
    			aSocket.send(reply);
    		}
		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e) {System.out.println("IO: " + e.getMessage());
		}finally {if(aSocket != null) aSocket.close();}
    }
}