
package condicionescompetencias;

import java.util.ArrayList;


public class RCompartido {
    private String rc;
    private mutex m;
    private ArrayList<Interrupcion> interrupciones;
   private int k=2;
 
  
   public int getk(){
       return k;
   }
   
   public void setk(int k){
       this.k=k;
   }
RCompartido(){
    rc="";
    //m.lock();
//    m.unlock();
interrupciones = new ArrayList<Interrupcion>();
for(int i=0;i<10;i++){
    interrupciones.add(new Interrupcion());
}
}
    public String getRc() {
        String aux="En espera...";
         //       m.lock();
                aux=rc;
          //      m.unlock();
                return aux;
    }

    public void setRc(String rc) {
     //   m.lock();
        this.rc = rc;
       // m.unlock();
        }
    
    public ArrayList<Interrupcion> getInterrupciones(){
        return interrupciones;
    }
    
    public void setInterrupciones(ArrayList<Interrupcion> interrupciones){
        this.interrupciones=interrupciones;
    }
    
    public void bloque(){
        for(Interrupcion inter:interrupciones)
            inter.setInter(false);
    }
            
    public void desbloquea(){
        for(Interrupcion inter: interrupciones)
            inter.setInter(true);
    }
    
    public boolean isEntra(){
        boolean ban = false;
        for(Interrupcion inter: interrupciones)
            if(inter.isInter())
                ban=true;
            else return false;
        return ban;
    }
    
   
    }
    


    


   
         
    
    
    

