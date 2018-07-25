package memoriavirtual;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MMU {
    List<Pagina>    mV  = new ArrayList<>(),
                    mF = new ArrayList<>();
    int quant_frame = 512,
        tam_pag = 128,
        hit = 0,
        miss = 0,
        fault = 0,
        frameAtual = 0,
        algoritmo = 1,
        ponteiroRelogio = 0,
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
        if (mF.stream().anyMatch(p -> Objects.equals(p.getPageFrame(), endereco)))
            hit++;
        else {
            miss++;
            escrever(endereco);
        }
    }
    
    public void escrever(Long endereco){
        if (mF.size() < 512)
            mF.add(new Pagina(endereco));
        else {
            fault++;
            substituirPagina(endereco);
        }
    }

    public void substituirPagina(Long endereco){
        boolean removed = false;
        
        switch (algoritmo) {            
            case 1: // FIFO
                mV.add(mF.get(0));
                mF.remove(0);
                mF.add(new Pagina(endereco));
            break;    

            case 2: // Segunda Chance
                int p = 0;
                while (!removed && p < mF.size()) {
                    Pagina pagina = mF.get(0);
                    if (pagina.isR()) {
                        mF.remove(pagina);
                        pagina.setR(false);
                        mF.add(pagina);
                    } else {
                        mF.remove(pagina);
                        mF.add(new Pagina(endereco));
                        removed = true;
                    }
                    p++;
                }
                if (!removed) {
                    mV.add(mF.get(0));
                    mF.remove(0);
                    mF.add(new Pagina(endereco));
                }
            break;

            case 3: // Relógio
                while (!removed) {
                    Pagina pagina = mF.get(ponteiroRelogio);
                    if (pagina.isR())
                        pagina.setR(false);
                    else {
                        mF.set(ponteiroRelogio, new Pagina(endereco));
                        removed = true;
                    }                    
                    ponteiroRelogio = (ponteiroRelogio + 1) % mF.size();
                }
            break;   
            
            case 0: // 0 – Exit
            break;

            default: System.out.println("???");
        }
	//writebacks++;
    }
    
    public int getHit() {
        return hit;
    }

    public int getMiss() {
        return miss;
    }

}
