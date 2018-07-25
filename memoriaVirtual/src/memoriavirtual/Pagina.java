package memoriavirtual;


public class Pagina {
    boolean b,  //Bit presente/ausente
            p,  //Proteção
            m,  //Modificada
            r,  //Referenciada
            c;  //Cache Desabilitado
    Long pageFrame;

    public Pagina(Long pageFrame) {
        this.pageFrame = pageFrame;
        b = false;
        p = false;
        m = false;
        r = false;
        c = false;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public boolean isP() {
        return p;
    }

    public void setP(boolean p) {
        this.p = p;
    }

    public boolean isM() {
        return m;
    }

    public void setM(boolean m) {
        this.m = m;
    }

    public boolean isR() {
        return r;
    }

    public void setR(boolean r) {
        this.r = r;
    }

    public boolean isC() {
        return c;
    }

    public void setC(boolean c) {
        this.c = c;
    }

    public Long getPageFrame() {
        return pageFrame;
    }

    public void setPageFrame(Long pageFrame) {
        this.pageFrame = pageFrame;
    }
    
    
    
}

