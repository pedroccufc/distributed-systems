import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Manager {
	public static List<Socket> clients = new ArrayList<Socket>();
	public static List<Character> sinal = new ArrayList<Character>();
	public static List<Double> n1 = new ArrayList<Double>();
	public static List<Double> n2 = new ArrayList<Double>();
	
	public static boolean verifica(Socket soc){
		for (Socket socket : clients) {
			if(socket.equals(soc))
				return true;
		}
		return false;
	}

    
}