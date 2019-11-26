import org.json.*;
import java.net.*;
import java.io.*;
public class TCPServer {
	public static void main (String[] args) {
		try{
			// porta na qual o servidor está escutando
			int serverPort = 12000;
			ServerSocket listenSocket = new ServerSocket(serverPort);
			System.out.println("Servidor ouvindo na porta "+serverPort);

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
	BufferedReader in;
	DataOutputStream out;
	Socket clientSocket;
	public Connection (Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new DataOutputStream(clientSocket.getOutputStream());

			// método start() inicializa a Thread
			this.start();
		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}

	public void run(){

		try {
			InterfaceRemota ir = new Servante();

			// retornando ao cliente a mensagem recebida
			String dados = in.readLine();
			//System.out.println("Mensagem recebida: " + dados);

			JSONObject json = new JSONObject(dados);
			//System.out.println("json: "+json);

			String id = json.getString("id");
			File listFiles[] = null;
			String reply = "textos:\n";

			switch(id){
				case "um":
					listFiles = ir.listTexts("textos/");
					int i = 0;
					for(int j = listFiles.length; i < j; i++){
						File arquivos = listFiles[i];
						reply = reply + arquivos.getName() + "\n";
					}
					break;
				case "dois":
					ir.createText(json);
					reply = "OK";
					break;
				case "tres":
					ir.editText(json);
					reply = "OK";
					break;
				case "quatro":
					reply = ir.infoText(json);
					break;
				case "cinco":
					ir.deleteText(json);
					reply = "OK";
					break;
				case "seis":
					reply = "cliente desconetado!";
					break;
			}

			out.writeUTF(reply);

		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());
		} finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
	}
}