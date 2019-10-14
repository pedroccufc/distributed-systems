public class Despachante {
    private String response;
    
    public void invoke(String message){
        
        Esqueleto esq = new Esqueleto();

        if(message.contains("+")){
            response = esq.add(message);
        }else if(message.contains("-")){
            response = esq.sub(message);
        }else if(message.contains("*")){
            response = esq.mult(message);
        }else if(message.contains("/")){
            response = esq.div(message);
        }
        
    }

    public String getResponse() {
        return response;
    }
        
}
