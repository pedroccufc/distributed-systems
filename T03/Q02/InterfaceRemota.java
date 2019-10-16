import java.io.File;
import java.rmi.*;
import java.util.ArrayList;

public interface InterfaceRemota extends Remote{
    
    public String getStatus() throws RemoteException;
    public File[] getListLembretes(String name) throws RemoteException;
    public boolean criarLembrete(String name) throws RemoteException;
    public boolean detalharLembrete(String name) throws RemoteException;
    public boolean editarLembrete(String name, String conteudo) throws RemoteException;
    public boolean apagarLembrete(String name) throws RemoteException;
    
}
