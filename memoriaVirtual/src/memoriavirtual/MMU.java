/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoriavirtual;

import java.util.ArrayList;
import java.util.List;


public class MMU {

    MemoriaVirtual mV;
    MemoriaPrincipal mP;
    int quant_frame = 512;
    int tam_pag = 128;
    int hit = 0;
    int miss = 0;
    List<Integer> tabela = new ArrayList<>(512);
    List<Integer> flag = new ArrayList<>(512);
    int frameAtual = 0;
        

    public MMU(MemoriaVirtual mV, MemoriaPrincipal mP) {
        this.mV = mV;
        this.mP = mP;
    }    
    
    public void tlb(Endereco endereco, String tipo){
        int num_frame = (endereco.getDecimal() / tam_pag) + 1;
        int indice_tabela = tabela.indexOf(num_frame);
        if (indice_tabela != -1){
            hit++;
            flag.set(indice_tabela, 1);
        }
        else{
            miss++;
            tabela.set(frameAtual, indice_tabela);
            frameAtual = (frameAtual + 1) % quant_frame;
            
        }
    }
    
    public void mapear(String instrucao, String op, String dado){
        
    }
    
    public void escrever(Endereco instrucao, Endereco valor){
    }
    
    public void executarInstrucao(Endereco instrucao, char op, Endereco dado){
        if (op == 'R' || op == 'r'){
        }
        else if (op == 'W' || op == 'w'){
        }
    }
    
    public boolean procurar(Endereco dado){
        //mP.getFrame().stream().anyMatch(p -> p.getValor().equals(dado));
        return false;
    }    
}
