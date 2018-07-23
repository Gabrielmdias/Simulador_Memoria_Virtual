/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoriavirtual;


public class MMU {

    MemoriaVirtual mV;
    MemoriaFisica mF;

    public MMU(MemoriaVirtual mV, MemoriaFisica mF) {
        this.mV = mV;
        this.mF = mF;
    }
    
    
    public void ler(Endereco instrucao, Endereco valor){
    }
    
    public void escrever(Endereco instrucao, Endereco valor){
        if (mV.getPagina().size() < 5)
            mV.getPagina().set(instrucao, new Pagina(valor));
    }
    
    public void executarInstrucao(Endereco instrucao, char op, Endereco dado){
        if (op == 'R'){
            ler(instrucao, dado);
        }
        else if (op == 'W'){
            escrever(instrucao, dado);
        }
    }
    
    public boolean procurar(Endereco dado){
        mV.getPagina().stream().anyMatch(p -> p.getValor().equals(dado));
    }    
    
}
