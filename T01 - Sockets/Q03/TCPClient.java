import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPClient {
	public static void main (String[] args) {
        Scanner fluxo = new Scanner(System.in);
		Socket s = null;

		try{
			int serverPort = 7896;
			s = new Socket("localhost", serverPort);    
			DataInputStream in = new DataInputStream( s.getInputStream());
			DataOutputStream out = new DataOutputStream( s.getOutputStream());
    
            System.out.println("Digite a expressão matemática (EXEMPLO: 4+5): ");
            String exp;

            exp = fluxo.nextLine();
            if(exp.equals("")){
                System.out.println("Mensagem vazia!");
            } else {
                out.writeUTF(exp);
                //s.close();
                String data = in.readUTF();
                if(!exp.equals("historico")){
                    System.out.println("= " + data);
                } else {
                    System.out.println(data);
                }
            }
		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
     }
}