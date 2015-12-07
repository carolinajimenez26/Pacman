
package clases;
import java.util.Observable;
import static clases.vista.pacman;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class movimientoPacman extends Observable implements Runnable{//observamos al pacman
    
    static int velocidad;//cuanto tiempo espera
    private final vista juego;
    tablero t;
    public int teclaActual = 39;
    private EstadoPacman estadoPacman;
    String arriba;
    String abajo;
    String derecha;
    String izquierda;
    boolean comenzar = true;
    
    public movimientoPacman(vista juego, tablero t) throws IOException{
        this.juego = juego;
        this.velocidad = 200;
        this.t = t;
        izquierda = "/imagenes/carro_left.png";
        derecha = "/imagenes/carro.png";
        abajo = "/imagenes/carro_down.png";
        arriba = "/imagenes/carro_up.png";
        //estadoPacman = new EstadoNormal(juego);//empieza montado en el carro
    }
   
    //@Override
     public void setEstado(EstadoPacman estadoPacman) {
         
         this.estadoPacman = estadoPacman;//actualiza su estado
         setChanged();
         notifyObservers();//les notificamos que cambió su estado
        
    }
    
    public void Comenzar(boolean b){
        comenzar = b;
    }
     
    @Override
    public void run(){
        
        try {
            //que se mueva mientras que no se tope con un muro
            
            while(comenzar){
                int Y=((juego.pacman.getLocation().x)/40);
                int X=((juego.pacman.getLocation().y)/40);
                Thread.sleep(getVelocidad());

                //IZQUIERDA
                if(teclaActual==37){
                    
                     if(t.matriz[X][Y-1]==0){
                        
                        juego.pacman.setImage(izquierda);
                        juego.pacman.setBounds((Y-1)*40, X*40, 40, 40);    
                        t.matriz[X][Y] = 0;
                        t.matriz[X][Y-1] = 9;//carro
                        Thread.sleep(getVelocidad());
                    }

                }
                //ARRIBA
                if(teclaActual==38 ){
                    
                     if(t.matriz[X-1][Y]==0){

                       juego.pacman.setImage(arriba);
                       juego.pacman.setBounds(Y*40, (X-1)*40, 40, 40); 
                       t.matriz[X][Y] = 0;
                       t.matriz[X-1][Y] = 9;
                       Thread.sleep(getVelocidad());

                       
                    }

                }
                
                //DERECHA
                if(teclaActual==39){
                                   
                    if(t.matriz[X][Y+1]==0){

                        juego.pacman.setImage(derecha);
                        juego.pacman.setBounds((Y+1)*40, X*40, 40, 40);
                        t.matriz[X][Y] = 0;
                        t.matriz[X][Y+1] = 9;
                        Thread.sleep(getVelocidad());
                    }

                }
                
                //ABAJO
                if(teclaActual==40){
                    
                     if(t.matriz[X+1][Y]==0){

                        juego.pacman.setImage(abajo);
                        juego.pacman.setBounds(Y*40, (X+1)*40, 40, 40);    
                        t.matriz[X][Y] = 0;
                        t.matriz[X+1][Y] = 9;
                        Thread.sleep(getVelocidad());
                    }

                }
                
                if(actualiza() != 32){//para que no se deje de mover si dispararon
                    
                    teclaActual=actualiza();
                }
                
           }
        } catch (InterruptedException ex) {
           System.out.println("Ocurrio un error " + ex);
           
        }
        
    }
    
     public int getVelocidad() {
        return velocidad;
    }
     
     
    private int actualiza(){
        
        return juego.getTeclaActiva();
    }
    
}
