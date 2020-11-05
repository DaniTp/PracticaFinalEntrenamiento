
package condicionescompetencias;

import static java.lang.Thread.sleep;


public class Bandera {
 
    private boolean b=false;
    Bandera(){
        b=false;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }
         public void espera(){
                        try{
                sleep(100);
            }catch(Exception e){ e.printStackTrace(); }
         }

}
