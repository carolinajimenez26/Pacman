
package clases;
import java.util.Observable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class movimientoPacman extends Observable implements Runnable{//observamos al pacman

    int X, Y; //Posición del carro EN LA MATRIZ
    int velocidad = 200;
    boolean comenzar = true;
    private EstadoPacman estadoPacman;
    private Control control;
    public int teclaActual = 39;//Para que empiece moviéndose
    
    public movimientoPacman(Control control){
        this.control = control;//para tener una comunicación directa con el controlador del juego
    }
    
    public void Comenzar(boolean b){
        comenzar = b;
    }
    
    public void setEstado(EstadoPacman estadoPacman) {
         
         this.estadoPacman = estadoPacman;//actualiza su estado
         setChanged();
         notifyObservers();//les notificamos que cambió su estado
        
    }

    public void setX(int X) {
        this.X = X;
    }

    public void setY(int Y) {
        this.Y = Y;
    }
    
    public int getVelocidad(){
        return velocidad;
    }
    
    @Override
    public void run(){
        
        try {
            while(comenzar){
                int Y=((juego.pacman.getLocation().x)/40);
                int X=((juego.pacman.getLocation().y)/40);
                Thread.sleep(getVelocidad());

                //IZQUIERDA
                if(teclaActual == 37){
                    
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
                
                if(control.actualiza() != 32){//para que no se pare si apreta la tecla de la llama
                    
                    teclaActual=actualiza();//el control sabe cuál es la tecla actual en Vista
                }
                
           }
        } catch (InterruptedException ex) {
           System.out.println("Ocurrio un error " + ex);
           
        }
        
    }

}
