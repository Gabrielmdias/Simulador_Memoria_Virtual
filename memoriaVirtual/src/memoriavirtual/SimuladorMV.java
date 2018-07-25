package memoriavirtual;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class SimuladorMV {
    
    public static void main(String[] args) throws FileNotFoundException {
        int quant_frame = -1, tam_pag = -1;
        Scanner scanner = new Scanner(System.in);
        
        
        while(!valor(quant_frame)){
            System.out.println("Qual a quantidade de frames ?");
            quant_frame = scanner.nextInt();
            if(!valor(quant_frame)) 
                System.out.println("Quantidade invalida!");
        }

        while(!valor(tam_pag)){
            System.out.println("Qual o tamanho da pagina ?");
            tam_pag = scanner.nextInt();
            if(!valor(tam_pag)) 
                System.out.println("Tamanho invalido!");
        }
        
        int opcao = -1;
        
        while (!valorOpcao(opcao)) {
            System.out.println("Escolha o algoritmo de substituicao de pagina:");
            System.out.println("    1 - FIFO");
            System.out.println("    2 - Outro");
            System.out.println("    3 - Sair");

            while (!scanner.hasNextInt())
                System.out.println("Opção Inválida");
            
            opcao = scanner.nextInt();
            
            if (opcao < 3 || opcao > -1)
                valorOpcao(opcao);
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
    
    public static boolean valor(int valor){
        if(valor > 0)
            return true; 
        else
            return false;
    }
    
    public static boolean valorOpcao(int valor){
        if(valor > 0)
            return true; 
        else if(valor < 4)
                return false;
        else
            return false;
    }
}
