public class Esqueleto {
    
    Calculadora calc = new Calculadora();
    double resultado;
    double n1 = 0, n2 = 0;
    String array[] = new String[3];

    public String add(String message){
        array = message.split(";");
        resultado = calc.add(Double.parseDouble(array[0]), Double.parseDouble(array[1]));
        return Double.toString(resultado);
    }
    public String sub(String message){
        array = message.split(";");
        resultado = calc.sub(Double.parseDouble(array[0]), Double.parseDouble(array[1]));
        return Double.toString(resultado);
    }
    public String mult(String message){
        array = message.split(";");
        resultado = calc.mult(Double.parseDouble(array[0]), Double.parseDouble(array[1]));
        return Double.toString(resultado);
    }
    public String div(String message){
        array = message.split(";");
        resultado = calc.div(Double.parseDouble(array[0]), Double.parseDouble(array[1]));
        return Double.toString(resultado);
    }
}
