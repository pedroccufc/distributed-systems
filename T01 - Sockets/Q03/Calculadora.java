public class Calculadora {
    public String Calcula(double n1, double n2, char s){
        double res = 0;
        String resultado;
        switch (s) {
            case '+':
                res = n1+n2;
                resultado = Double.toString(res);
                break;
            case '-':
                res = n1-n2;
                resultado = Double.toString(res);
                break;  
            case '*':
                res = n1*n2;
                resultado = Double.toString(res);
                break; 
            case '/':
                if(n2!=0){
                    res = n1/n2;
                    resultado = Double.toString(res);
                }
                else{
                    resultado = "Impossível dividir por 0";
                }
                break;
            default:
                resultado = "Operação inválida";
            }
        return resultado;
    }
}
