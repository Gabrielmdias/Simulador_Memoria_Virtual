package memoriavirtual;


public class Endereco {    
    String hexadecimal;
    int    decimal;

    public Endereco(String hexadecimal) {
        this.hexadecimal = hexadecimal;
        decimal = Integer.parseInt(hexadecimal, 10);
    }
    
    public Endereco(int decimal) {        
        hexadecimal = Integer.toHexString(decimal);
        this.decimal = decimal;
    }

    public String getHexadecimal() {
        return hexadecimal;
    }

    public int getDecimal() {
        return decimal;
    }    
    
}
