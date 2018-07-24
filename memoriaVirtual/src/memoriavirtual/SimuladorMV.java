package memoriavirtual;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class SimuladorMV {
    
    public static void main(String[] args) throws FileNotFoundException {
        
        boolean opcaoValida = false;
        int opcao = -1;
        
        while (!opcaoValida) {
            Scanner scanner = new Scanner(System.in);
            
            while (!scanner.hasNextInt())
                System.out.println("Opção Inválida");
            
            opcao = scanner.nextInt();
            
            if (opcao < 3 || opcao > -1)
                opcaoValida = true;
        }
        
        switch(opcao) {
            case 1: // FIFO
            break;    

            case 2: // OUTRO
            break;

            case 0: // 0 – Exit
            break;

            default: System.out.println("???");
        }
        System.out.println("-----------------------------------------------");        
        
        Scanner scan = new Scanner(new FileReader("trace1.txt")).useDelimiter("\n");
        MMU mmu = new MMU();        
        
        while (scan.hasNext()){
            String linha = scan.next().replaceAll(":","").replaceAll("0x","").replaceAll("(\\r)", "");
            String[] instrucao = linha.split(" ");
            
            mmu.mapearInstrucao(instrucao[0], instrucao[1], instrucao[2]);
        }
        
        System.out.println("Hits  : " + mmu.getHit());
        System.out.println("Misses: " + mmu.getMiss());
        System.out.println("Total : " + (mmu.getHit() + mmu.getMiss()));
    }
}
