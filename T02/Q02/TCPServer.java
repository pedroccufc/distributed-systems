import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private String message;
    private String response;

    DataInputStream in;
    DataOutputStream out;
            
    public static void main (String[] args) {
	try{
            ServerSocket listenSocket = new ServerSocket(7896);
            Socket clientSocket = listenSocket.accept();

            TCPServer server = new TCPServer();

            server.in = new DataInputStream( clientSocket.getInputStream());
            server.out = new DataOutputStream( clientSocket.getOutputStream());
            System.out.println("cliente: "+clientSocket.getPort());

            String msn_ = server.getRequest();
            if(msn_.equals("")){
                System.exit(0);
            }

            Despachante d = new Despachante();
            d.invoke(msn_);

            server.sendResponse(d.getResponse());
        } catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}	
    }
    
    public void sendResponse(String response) throws IOException{
         this.out.writeUTF(response);
     }
     
     public String getRequest() throws IOException{
        message = in.readUTF();
        
        if(message.equals("exit")){
            System.exit(0);
        }else{
            Despachante d = new Despachante();
            d.invoke(message);
            sendResponse(d.getResponse());
        }
        return message;
     }
}

