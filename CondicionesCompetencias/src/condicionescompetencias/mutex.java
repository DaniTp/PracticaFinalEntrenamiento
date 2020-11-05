
package condicionescompetencias;


public class mutex {
    private boolean pase=false;
    
    mutex(){
        
    }
    public  void lock(){
            try{
                wait();
            }catch(Exception e){ e.printStackTrace(); }
    }
    
    public  void unlock(){
        notify();
    }
}
