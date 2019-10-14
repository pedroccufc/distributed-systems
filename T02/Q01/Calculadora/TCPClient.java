import java.net.*;
import java.io.*;
import java.util.logging.*;

public class TCPClient {
	private String msn;
	private String reply;
	DataInputStream in;
	DataOutputStream out;
	Socket s = null;
	
	public void sendRequest(String request) throws IOException {
		this.msn = request;
		out.writeUTF(msn);
	}

	public String getResponse() throws IOException {
		reply = in.readUTF();
		return reply;
	}
	
	public void close(){
		try{
			s.close();
		}catch(IOException ex){
			Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void start() throws UnknownHostException, IOException {
		InetAddress idnet = InetAddress.getByName("localhost");
		s = new Socket("localhost", 7896, idnet, 1280);
		in = new DataInputStream(s.getInputStream());
		out = new DataOutputStream(s.getOutputStream());
	}
}