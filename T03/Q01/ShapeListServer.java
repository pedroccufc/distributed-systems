import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ShapeListServer {
	public static void main(String args[]){
         System.setSecurityManager(new SecurityManager());
        try{
            int port = 8512;
            ShapeList aShapeList = new ShapeListServant();
            ShapeList stub = (ShapeList) UnicastRemoteObject.exportObject(aShapeList,0);
			Naming.rebind("ShapeList", aShapeList); 
            System.out.println("ShapeList server ready");
        }catch(Exception e) {
            System.out.println("ShapeList server main " + e.getMessage());
        }
    }
}
