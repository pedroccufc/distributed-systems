import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.rmi.server.UnicastRemoteObject;

public class Servidor {
    public static String nameService = "ServerJM";
    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager());
        try{
            int port = 8512;
            InterfaceRemota ir = new Servente();
            Registry registry = LocateRegistry.createRegistry(port);
            Naming.rebind(nameService, ir);
            System.out.println("Executando Sevidor...\nNome do Serviço:"+nameService);
        }catch(MalformedURLException | RemoteException e){
            System.out.println(e.getMessage());
        }
    }
}