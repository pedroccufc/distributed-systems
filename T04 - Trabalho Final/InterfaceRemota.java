import org.json.*;
import java.io.File;
import java.rmi.*;
import java.util.ArrayList;

public interface InterfaceRemota extends Remote {
	public File[] listTexts(String directory) throws RemoteException;
	public void createText(JSONObject json) throws RemoteException;
	public void editText(JSONObject json) throws RemoteException;
	public String infoText(JSONObject json) throws RemoteException;
	public void deleteText(JSONObject json) throws RemoteException;
}