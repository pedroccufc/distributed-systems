import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPServer {

    public static ServerSocket sv;      //criando socket do servidor
    public static Socket clientSocket1;  //socket do cliente 1
    public static Socket clientSocket2; //socket do cliente 2


    //cria os canais de entrada e saida dos clientes 1 e 2
    public static DataInputStream in1;
    public static DataOutputStream out1;

    public static DataInputStream in2;
    public static DataOutputStream out2;

    
    //inicializa a Thread de leitura dos clientes
    public void inicializaRead1(){
        Read1 r1 = new Read1();
        r1.start();
    }
    
    public void inicializaRead2(){
        Read2 r2 = new Read2();
        r2.start();
    }

    public static void main (String[] args) {
        try{
            sv = new ServerSocket(7896);
            clientSocket1 = sv.accept();
            
            System.out.println("Cliente 01: " + clientSocket1.getPort());
            
            clientSocket2 = sv.accept();
            System.out.println("Cliente 02: " + clientSocket2.getPort());
            
            //inicializa os canais de entrada e saida
            in1 = new DataInputStream( clientSocket1.getInputStream());
            out1 = new DataOutputStream( clientSocket1.getOutputStream());
            
            in2 = new DataInputStream( clientSocket2.getInputStream());
            out2 = new DataOutputStream( clientSocket2.getOutputStream());
          
            //cria um objeto do server e inicializa as Threads de leitura
            TCPServer server = new TCPServer();
            server.inicializaRead1();
            server.inicializaRead2();
            
            while(true){
                Thread.sleep(1);
            }
            
        } catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());} catch (InterruptedException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public class Read1 extends Thread{      
        public void run(){
            System.out.println("lendo canal 1");
            while(true){
                try {
                    //espera ler de 1 para escrever no canal de 2
                    out2.writeUTF(in1.readUTF());
                } catch (IOException ex) {
                    Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public class Read2 extends Thread{
        public void run(){
            System.out.println("lendo canal 2");
            while(true){
                try {
                    //espera ler de 2 para escrever no canal de 1
                    out1.writeUTF(in2.readUTF());
                } catch (IOException ex) {
                    Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}