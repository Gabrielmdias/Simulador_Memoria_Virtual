package memoriavirtual;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

// 0x804ae19: W 0x9cb32e0

public class SimuladorMV {
    
    public static void main(String[] args) throws FileNotFoundException {
        List<String> listaArq = new ArrayList<>();
        List<String> endInstrucao = new ArrayList<>();
        List<String> tipoOperacao = new ArrayList<>();
        List<String> endDado = new ArrayList<>();        
        
        Scanner scan = new Scanner(new FileReader("trace1.txt")).useDelimiter("\t|\n| ");
        
        while (scan.hasNext()){
            listaArq.add(scan.next().replaceAll(":",""));
        }

        for (int j = 0; j < listaArq.size(); j = j+3) {
            endInstrucao.add(listaArq.get(j));
        }
        for (int j = 1; j < listaArq.size(); j = j+3) {
            tipoOperacao.add(listaArq.get(j));
        }
        for (int j = 2; j < listaArq.size(); j = j+3) {
            endDado.add(listaArq.get(j));
        }
        
        System.out.println(endInstrucao.get(999));
        
        MemoriaFisica  mFisica = new MemoriaFisica(16);
        MemoriaVirtual mVirtual = new MemoriaVirtual();
        MMU mmu = new MMU(mVirtual, mFisica);
        
        Endereco e0 = new Endereco(endInstrucao.get(0)),                 
                 e1 = new Endereco(endDado.get(0));
        op = tipoOperacao.get(0);
        
    }
}
