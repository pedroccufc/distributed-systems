import java.io.*;
import java.net.*;

public class TCPServer {
    private String message;
    private String response;

    DataInputStream in1;
    DataOutputStream out1;
    
    DataInputStream in2;
    DataOutputStream out2;
    
    public void sendResponse1(String response) throws IOException{
        out1.writeUTF(response);
    }
     
    public String getRequest1() throws IOException{
        message = in1.readUTF();
        return message;
    }
    
    public void sendResponse2(String response) throws IOException{
        out2.writeUTF(response);
    }
     
    public String getRequest2() throws IOException{
        message = in2.readUTF();
        return message;
    }

    public static void main (String[] args) {
        try{
            ServerSocket listenSocket = new ServerSocket(7896);

            Socket clientSocket1 = listenSocket.accept();
            System.out.println("Cliente 1: " + clientSocket1.getPort());

            Socket clientSocket2 = listenSocket.accept();
            System.out.println("Cliente 2: " + clientSocket2.getPort());

            TCPServer server = new TCPServer();

            server.in1 = new DataInputStream( clientSocket1.getInputStream());
            server.out1 = new DataOutputStream( clientSocket1.getOutputStream());

            server.in2 = new DataInputStream( clientSocket2.getInputStream());
            server.out2 = new DataOutputStream( clientSocket2.getOutputStream());

            String message;

            while(true){
                message = server.getRequest1();
                server.sendResponse2(message);
                if (message.equals("exit")) {
                    System.exit(0);
                }
                message = server.getRequest2();
                server.sendResponse1(message);
                if (message.equals("exit")) {
                    System.exit(0);
                }
            }

        } catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
    }
}

