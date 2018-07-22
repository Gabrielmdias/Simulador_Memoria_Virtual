package memoriavirtual;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class MemoriaVirtual {

    public static void main(String[] args) throws FileNotFoundException {
      
        List<String> end_instrucao = new ArrayList<>();
        List<String> tipo_operacao = new ArrayList<>();
        List<String> end_dado = new ArrayList<>();
        
        
        Scanner scanner = new Scanner(new FileReader("trace1.txt")).useDelimiter(" ");
        
        while (scanner.hasNext()){
            end_instrucao.add(scanner.next().replaceAll(":",""));
        }

        System.out.println(end_instrucao);

    }
    
}
