package memoriavirtual;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class MemoriaVirtual {

    public static void main(String[] args) throws FileNotFoundException {
        List<String> listaArq = new ArrayList<>();
        List<String> end_instrucao = new ArrayList<>();
        List<String> tipo_operacao = new ArrayList<>();
        List<String> end_dado = new ArrayList<>();
        
        
        Scanner scan = new Scanner(new FileReader("trace1.txt")).useDelimiter("\t|\n| ");
        
        while (scan.hasNext()){
            listaArq.add(scan.next().replaceAll(":",""));
        }

        for (int j = 0; j < listaArq.size(); j = j+3) {
            end_instrucao.add(listaArq.get(j));
        }
        for (int j = 1; j < listaArq.size(); j = j+3) {
            tipo_operacao.add(listaArq.get(j));
        }
        for (int j = 2; j < listaArq.size(); j = j+3) {
            end_dado.add(listaArq.get(j));
        }
        
        
        System.out.println(end_instrucao);

    }
    
}
