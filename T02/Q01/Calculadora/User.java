import java.io.*;
import java.util.*;

public class User {
	public static void main(String[] args) throws IOException {
		Scanner fluxo = new Scanner(System.in);
		String sinal = "", reply, send;
		boolean j = true;
		double n1 = 0, n2 = 0;

		while(j == true){
			System.out.println("Digite o primeiro operando: ");
			n1 = fluxo.nextDouble();

			System.out.println("Digite o segundo operando: ");
			n2 = fluxo.nextDouble();

			System.out.println("Digite uma operador: (+, -, *, /)");
			sinal = fluxo.next();

			if(!sinal.equals("+") && !sinal.equals("-") && !sinal.equals("*") && !sinal.equals("/")){
				System.out.println("Digite uma operação válida");
			}

			TCPClient client = new TCPClient();
			client.start();

			if(sinal.equals("exit")){
				send = "exit";
				client.sendRequest(send);
				client.close();
				System.exit(0);
			}else{
				send = Double.toString(n1) + ";" + Double.toString(n2) + ";" + sinal;
				client.sendRequest(send);
				reply = client.getResponse();
				System.out.println("="+reply);
				System.exit(0);
			}
		}
	}
}