import java.io.*;
import java.util.*;

public class User2 {
    public static void main(String[] args) throws IOException {
        
        Scanner fluxo = new Scanner(System.in);
        boolean j = true;
        String messege, response;

        TCPClient2 client = new TCPClient2();
        client.start();

       while(j == true){
          response = client.getResponse();
          if (response == null) {
               
          } else if (response.equals("exit")) {
                client.close();
                System.exit(0);
          } else {
                System.out.println("Cliente 1: "+response);
          }
          messege = fluxo.nextLine();
          client.sendRequest(messege);
          if (messege.equals("exit")) {
              client.close();
              System.exit(0);
          }
       }
    }
}
