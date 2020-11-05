
package condicionescompetencias;


public class VCerradura {
    private boolean vcerradura;
    VCerradura(){
        vcerradura=false;
    }

    public boolean isVcerradura() { //Devuelve el estado de la cerradura
        return vcerradura;
    }

    public void setVcerradura(boolean vcerradura) { //Asigna el estado a la cerradura
        this.vcerradura = vcerradura;
    }
    public void cierra(){   //Metodo que cierra la cerradura cuando un proceso entra a su SC
        vcerradura=true;
    }
    public void abre(){     //Metodo que abre la cerradura cuando un proceso sale de su SC
        vcerradura=false;
    }   
}


