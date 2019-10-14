import java.io.IOException;

public class Proxy {
    
    private String response;
    private String n1, n2, message;
    
    TCPClient client = new TCPClient();
    
    public void start() throws IOException{
        client.start();
    }
    
    public double add(double op1, double op2) throws IOException{
        
        n1 = Double.toString(op1);
        n2 = Double.toString(op2);
        
        message = n1 + ";" + n2 + ";+";
        client.sendRequest(message);
        response = client.getResponse();
        return Double.parseDouble(response);
    }
    public double sub(double op1, double op2) throws IOException{
       
        n1 = Double.toString(op1);
        n2 = Double.toString(op2);
        
        message = n1 + ";" + n2 + ";-";
        client.sendRequest(message);
        response = client.getResponse();
        return Double.parseDouble(response);
    }
    public double mult(double op1, double op2) throws IOException{
        
        n1 = Double.toString(op1);
        n2 = Double.toString(op2);
        
        message = n1 + ";" + n2 + ";*";
        client.sendRequest(message);
        response = client.getResponse();
        return Double.parseDouble(response);
    }
    public double div(double op1, double op2) throws IOException{
        
        n1 = Double.toString(op1);
        n2 = Double.toString(op2);
        
        message = n1 + ";" + n2 + ";/";
        client.sendRequest(message);
        response = client.getResponse();
        return Double.parseDouble(response);
    }
    
    public void close() throws IOException{
        client.sendRequest("close");
        client.close();
    }
}
