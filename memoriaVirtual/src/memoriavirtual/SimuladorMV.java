package memoriavirtual;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class SimuladorMV {
    
    public static void main(String[] args) throws FileNotFoundException {        
            
        Scanner scanner = new Scanner(System.in);        
        
        int qtdFrames = -1;
        while(!valor(qtdFrames)){
            System.out.println("Escolha a quantidade de frames que a memória principal deve ter: ");
            qtdFrames = scanner.nextInt();
            if(!valor(qtdFrames))
                System.out.println("Quantidade inválida!");
        }

        int tamPag = -1;
        while(!valor(tamPag)){
            System.out.println("Qual deve ser tamanho da página ?");
            tamPag = scanner.nextInt();
            if(!valor(tamPag)) 
                System.out.println("Tamanho inválido!");
        }
                
        int algoritmo = -1;
        while (!valorOpcao(algoritmo)) {
            System.out.println("Escolha o algoritmo de substituicao de pagina:");
            System.out.println("    1 - FIFO");
            System.out.println("    2 - Segunda Chance");
            System.out.println("    3 - Relógio");
            System.out.println();
            System.out.println("    0 - Sair");

            while (!scanner.hasNextInt())
                System.out.println("Opção inválida!");
            
            algoritmo = scanner.nextInt();
            
            if (algoritmo < 3 || algoritmo > -1)
                valorOpcao(algoritmo);
        }
        MMU mmu = new MMU(qtdFrames, tamPag, algoritmo);

        System.out.println("-----------------------------------------------");        
        
        scanner = new Scanner(new FileReader("trace1.txt")).useDelimiter("\n");
        
        while (scanner.hasNext()){
            String linha = scanner.next().replaceAll(":","").replaceAll("0x","").replaceAll("(\\r)", "");
            String[] instrucao = linha.split(" ");
            
            if (instrucao.length > 2)
                mmu.mapearInstrucao(instrucao[0], instrucao[1], instrucao[2]);
        }
        
        System.out.println("Hits  : " + mmu.getHit());
        System.out.println("Misses: " + mmu.getMiss());
        System.out.println("Total : " + (mmu.getHit() + mmu.getMiss()));
    }
    
    public static boolean valor(int valor){
        return (valor > 0);
    }
    
    public static boolean valorOpcao(int valor){
        return (valor > 0 && valor < 4);
    }
}
