import java.io.*;
import java.util.*;

public class User1 {
    public static void main(String[] args) throws IOException {
        
        Scanner fluxo = new Scanner(System.in);
        boolean j = true;
        String messege, response;

        TCPClient1 client = new TCPClient1();
        client.start();

        while(j == true){
            messege = fluxo.nextLine();
            
            client.sendRequest(messege);
            if (messege.equals("exit")) {
               client.close();
               System.exit(0);
            }

            response = client.getResponse();
            if (response == null) {
               
            } else if (response.equals("exit")) {
                client.close();
                System.exit(0);
            } else {
                System.out.println("Cliente 2: "+response);
            }
        }
     
    }
}
