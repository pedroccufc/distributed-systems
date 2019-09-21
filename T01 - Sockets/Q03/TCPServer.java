import java.net.*;
import java.io.*;

public class TCPServer {
    
	public static void main (String[] args) {
        
        try{
			int serverPort = 7896;
			ServerSocket listenSocket = new ServerSocket(serverPort);
            Manager mg = new Manager();
                        
			while(true) {
				Socket clientSocket = listenSocket.accept();
                if(!mg.verifica(clientSocket)) {
					mg.clients.add(clientSocket);
					mg.sinal.add('#');
					mg.n1.add(0.0);
					mg.n2.add(0.0);
				}
                int ind = mg.clients.indexOf(clientSocket);
				Connection c = new Connection(clientSocket, mg, ind);
			}
		} catch(IOException e) {System.out.println("Listen socket: "+e.getMessage());}
	}
}

class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	int indice;
	Manager manager;
	
	public Connection (Socket aClientSocket, Manager mg, int ind) {
		try {
			clientSocket = aClientSocket;
			in = new DataInputStream( clientSocket.getInputStream());
			out =new DataOutputStream( clientSocket.getOutputStream());
			manager = mg; 
			indice = ind;
			this.start();
		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}
	public void run(){
		try {			                 
            String data = in.readUTF();	                  
            System.out.println("Expressão recebida: " + data);
            if(data.equals("historico")){
                Calculadora calc = new Calculadora();
                String result = calc.Calcula(manager.n1.get(indice-1), manager.n2.get(indice-1), manager.sinal.get(indice-1));
                out.writeUTF(Double.toString(manager.n1.get(indice-1))+Character.toString(manager.sinal.get(indice-1))+Double.toString(manager.n2.get(indice-1))+" = "+result);
                //o que é passado para out é: n1 'sinal' n2 = result
            } else {   
                char sinal ='#';
                int pos = 0;
                for (int i = 0; i < data.length(); i++) {
                    if(data.charAt(i) == '+' || data.charAt(i) == '-' || data.charAt(i) == '*' || data.charAt(i) == '/'){
                        sinal = data.charAt(i);
                        pos = i;
                        break;
                    }
                }
                manager.n1.add(indice, Double.parseDouble(data.substring(0, pos)));
                manager.n2.add(indice, Double.parseDouble(data.substring(pos+1, data.length())));
                manager.sinal.add(indice, sinal);
                    
                Calculadora calc = new Calculadora();
                String result = calc.Calcula(manager.n1.get(indice), manager.n2.get(indice), manager.sinal.get(indice));
                out.writeUTF(result);
            }      
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());
		} finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
	}
}