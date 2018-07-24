package memoriavirtual;


public class Pagina {
    Endereco endereco;

    public Pagina(Endereco endereco) {
        this.endereco = endereco;
    }    

    public Endereco getValor() {
        return endereco;
    }

    public void setValor(Endereco endereco) {
        this.endereco = endereco;
    }
    
}
