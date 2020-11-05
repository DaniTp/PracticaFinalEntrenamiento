
package condicionescompetencias;

import static java.lang.System.exit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
public class Hilo extends Thread{
    private JTextArea area;
    private RCompartido rc;
    private boolean pause=true;
    private int inic;
    private boolean block=false;
    private VCerradura vcerr;
    private int elegido=100;
    private mutex m;
    private int algoritmo=0;
    private Lock mutex;
    private Bandera[] b = new Bandera[4];
    private int turno;
    int id;
    private Ban2[] b2 = new Ban2[4];
    private Ban2[] c = new Ban2[4];
    
    
    
    Hilo(JTextArea area, RCompartido rc,VCerradura vcerr, Bandera[] b, int id, int turno, Ban2[] b2,Ban2[] c){
        this.area=area;
        this.rc=rc;
        this.vcerr=vcerr;
        mutex = new ReentrantLock();
        this.b=b;
        this.id=id;
        this.turno=turno;
        this.b2=b2;
        this.c=c;
        m = new mutex();
    }
    
    public void setAlgoritmo(int n){
        algoritmo=n;
    }

    public void run(){
       
  
      switch(algoritmo){
          case 0:   //Variable cerradura          
     int iter=0;
        while(true && (algoritmo==0)){
           try{
             String aux="En espera...";
              if(!vcerr.isVcerradura()){
                   vcerr.cierra();

              rc.setRc(this.getName());
              area.append(rc.getRc()+" Eats"+"\n");
             if(isBloquear()){
                  stop();
              }
              block=false;
                vcerr.abre();} else area.append(aux+"\n");

              inic= (int)(Math.random()*190);
              Thread.sleep((int)(Math.random()*150));

            }catch(Exception e){
                e.printStackTrace();
            }
            iter++;
            block=false;
        }
         
                
          case 1://Condiciones competencias
                while(true && (algoritmo==1)){
                    try {
              rc.setRc(this.getName());
              area.append(rc.getRc()+" Eats"+"\n");
                    
                        sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                block=false;
                }
                
          case 2: //Desactivacion de interrupciones
              while(true && (algoritmo==2)){
           try{
             String aux="En espera...";
              if(rc.isEntra()){
              rc.bloque();
              rc.setRc(this.getName());
              area.append(rc.getRc()+" Eats"+"\n");
              
              if(isBloquear()){
                  stop();
              }
              block= false;
                 rc.desbloquea();
              }else area.append(aux+"\n");   
              Thread.sleep((int)(Math.random()*150));

            }catch(Exception e){
               e.printStackTrace();
            }
        }
              
          case 3: //mutex
               while(true && (algoritmo==3)){
           try{
             String aux="En espera...";
              if(mutex.tryLock()){
                  mutex.lock();
              rc.setRc(this.getName());
              area.append(rc.getRc()+" Eats"+"\n");
              
              if(isBloquear()){
                  stop();
              }
              block=false;
              mutex.unlock();
           
              }else area.append(aux+"\n");   
              Thread.sleep((int)(Math.random()*150));

            }catch(Exception e){
                e.printStackTrace();
            }
        }
               
          case 4: //dekker
         while(true && (algoritmo==4)){
          try{
//               b[id].setB(true);
              String aux="En espera...";
               while(b[1].isB()){
                   if(turno==1){
                       b[id].setB(false);
                       while(turno==1){area.append(aux+"\n"); }
                       b[id].setB(true);
                   }
               }
             
              rc.setRc(this.getName());
              area.append(rc.getRc()+" Eats"+"\n");
              turno=1;
               if(isBloquear()){
                  stop();
              }
               block=false;
                   sleep(20);
                   
                      
              Thread.sleep((int)(Math.random()*150));                                             
            }catch(Exception e){
                e.printStackTrace();
            }
         }

        
         
          case 5: //mutex artesanal
              while(true && (algoritmo==5)){
                  try{
                String aux="En espera..."  ;
                m.lock();
                rc.setRc(this.getName());
                area.append(rc.getRc()+": Eats"+"\n");
                if(isBloquear()){
                    stop();
                }
                m.lock();
                
       
              Thread.sleep((int)(Math.random()*150));
          } catch (InterruptedException ex) {
              Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
          }
              }
              
          case 6: //dijkstra
              while(true && (algoritmo==6)){
                try{
                String aux="En espera..."  ;
                  int j;
                  for(int i=0;i<b2.length;i++){
                      b2[i].setB(false);
                  
                      if(rc.getk() != i){
                          for(int i2=0;i2<c.length;i2++){
                              c[i2].setB(true);
                              if(b[0].isB()){
                                  rc.setk(i);
                              }
                              else{
                                  for(int i3=0;i3<c.length;i3++){
                                      c[i3].setB(false);
                                      
                                      for(j=0;j<4;j++){
                                          if( j != i && c[j].getB()) break;
                                               
                                      }
                                       rc.setRc(this.getName());
                                       area.append(rc.getRc()+" Eats"+"\n"); 
                                       if(isBloquear()){
                                           stop();
                                       }
                                       block= false;
                                  }
                              }
                          }
                      }
                  }
                                Thread.sleep((int)(Math.random()*150));                                             
            }catch(Exception e){
                e.printStackTrace();
            }
              }
          default: break;
               
}   
      
    }
    
    public boolean isBloquear(){
        return block;
    }
    
    public void setBlock(boolean b){
        block=b;
    }
    
    public void Elegido(int n){
        elegido=n;
    }

}
