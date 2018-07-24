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
        
        Scanner scan = new Scanner(new FileReader("trace1.txt")).useDelimiter("\n");
        MemoriaPrincipal mP = new MemoriaPrincipal();   //2^16
        MemoriaVirtual mV = new MemoriaVirtual();
        MMU mmu = new MMU(mV, mP);
        
        while (scan.hasNext()){
            String linha = scan.next().replaceAll(":","");
            String[] instrucao = linha.split(" ");
            
            System.out.println(instrucao[0]);
            System.out.println(instrucao[1]);
            System.out.println(instrucao[2]);
            mmu.mapear(instrucao[0], instrucao[1], instrucao[2]);
            
        }
    }
}
