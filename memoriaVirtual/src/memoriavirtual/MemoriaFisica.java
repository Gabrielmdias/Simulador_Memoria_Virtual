package memoriavirtual;

import java.util.ArrayList;


public class MemoriaFisica {
    
    ArrayList<Endereco> frame;
    
    public MemoriaFisica(int frames) {
        frame = new ArrayList<>(frame);
    }

    public Endereco getFrame(int i) {
        return frame.get(i);
    }
    
}