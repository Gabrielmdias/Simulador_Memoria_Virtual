package memoriavirtual;

import java.util.ArrayList;
import java.util.List;


public class MMU {
    List<Long> tabela = new ArrayList<>(512),
                flag = new ArrayList<>(512),
                mV  = new ArrayList<>(),
                mF = new ArrayList<>();
    int quant_frame = 512,
        tam_pag = 128,
        hit = 0,
        miss = 0,
        fault = 0,
        frameAtual = 0,
        numPaginas = 65536 / tam_pag;
        

    public MMU() {
        for (int i = 0; i < 512; i++) {
            //mF.add(i, new Long(-1));
        }
    }    
 
    public void mapearInstrucao(String endInstr, String op, String endDado) {
        Long instrucao = Long.parseLong(endInstr, 16),
                dado = Long.parseLong(endDado, 16);

        ler(instrucao);
        
        if (op.equals("R") || op.equals("r"))                
            ler(dado);
        else if (op.equals("W") || op.equals("w"))
            escrever(dado);
    }
    
    public void ler(Long endereco){
        if (mF.indexOf(endereco) > -1)
            hit++;
        else {
            miss++;
            escrever(endereco);
        }
    }
    
    public void escrever(Long endereco){
        if (mF.size() < 512)
            mF.add(endereco);
        else {
            fault++;
            //replace
        }
    }

    public int getHit() {
        return hit;
    }

    public int getMiss() {
        return miss;
    }

}
