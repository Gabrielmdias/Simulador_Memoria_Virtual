package memoriavirtual;

import java.util.ArrayList;
import java.util.List;


public class MMU {
    List<Pagina>    mV  = new ArrayList<>(),
                    mF = new ArrayList<>();
    Double maxSize = (Double) Math.pow(2, 32);
    int qtdFrames,
            tamPag,
            tamMF,
            algoritmo,
            hit = 0,
            miss = 0,
            pageFault = 0,
            frameAtual = 0,        
            ponteiroRelogio = 0;        

    public MMU(int qtdFrames, int tamPag, int algoritmo) {
        this.qtdFrames = qtdFrames;
        this.tamPag = tamPag;
        this.tamMF = tamPag * qtdFrames;
        this.algoritmo = algoritmo;
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
    
    public boolean ler(Long endereco){        
        for (int p = 0; p < mF.size(); p++) {
            if (mF.get(p).getPageFrame().equals(endereco)) {
                mF.get(p).setR(true);
                hit++;
                return true;
            }
        }
        miss++;
        escrever(endereco);
        return false;
    }
    
    public void escrever(Long endereco){
        if (mF.size() < 512)
            mF.add(new Pagina(endereco));
        else {
            pageFault++;
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
            
            case 0: // Sair
            break;

            default: System.out.println("???");
        }
	//writebacks++;
    }
    
    public void report(){
        System.out.println("Memória Principal de " + qtdFrames +
                           " frames de tamanho " + tamPag +
                           " totalizando " + tamPag * qtdFrames + " bits");        
        System.out.println("Algoritmo utilizado: ");
        
        System.out.println("Hits  : " + hit);
        System.out.println("Misses: " + miss);
        System.out.println("Total : " + hit + miss);
    }    
}
