
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
    private int contador = 0;
    
    public movimientoPacman(Control control){
        this.control = control;//para tener una comunicación directa con el controlador del juego
    }
    
    public void Comenzar(boolean b){
        comenzar = b;
    }
    
    public void setEstado(EstadoPacman estadoPacman) throws InterruptedException {
         
        this.estadoPacman = estadoPacman;//actualiza su estado
        setChanged();
        notifyObservers();//les notificamos que cambió su estado
        //if(estadoPacman == (new EstadoNormal(control))) control.EstadoNormal();
        //else control.EstadoVulnerable();
    }

    public void setX(int X){//cada que movamos el carro debemos actualizar sus direcciones
        this.X = X;
    }

    public void setY(int Y) {
        this.Y = Y;
    }
    
    public int getX(){
        return X;
    }
    
    public int getY(){
        return Y;
    }
    
    public int getVelocidad(){
        return velocidad;
    }
    
    void startCounting() {
        contador++;
    }
    
    @Override
    public void run(){
        
        try {
            while(comenzar){
                
                Thread.sleep(getVelocidad());
                
                if(!control.getEstadoNormal()) startCounting();//si no está en estado normal, debe ir contando
                
                if(contador == 10){
                    setEstado(new EstadoNormal(control));//volvemos al estado original
                    contador = 0;//volvemos a inicializar
                    control.ChangeEstadoNormal();//cambia de estado MACHETEEEE
                }
                //System.err.println("Contador:"+contador);
                //System.out.println("EstadoNormal: "+ control.getEstadoNormal());
                
                //IZQUIERDA
                if(teclaActual == 37){
                    if(control.m.getElemento(X, Y-1) == 0){
                         control.v.moveLeft();
                         control.v.pacman.setBounds((Y-1)*40, X*40, 40, 40); 
                         control.m.QuitaElemento(X, Y);
                         control.m.AgregaElemento(X, Y-1, 9);
                         setY(Y-1);
                         Thread.sleep(getVelocidad());
                     }   
                }

                
                //ARRIBA
                if(teclaActual == 38 ){
                    if(control.m.getElemento(X-1, Y) == 0){
                         control.v.moveUp();
                         control.v.pacman.setBounds(Y*40, (X-1)*40, 40, 40);
                         control.m.QuitaElemento(X, Y);
                         control.m.AgregaElemento(X-1, Y, 9);
                         setX(X-1);
                         Thread.sleep(getVelocidad());
                    } 
                }
                
                //DERECHA
                if(teclaActual==39){
                    if(control.m.getElemento(X, Y+1) == 0){
                         control.v.moveRight();
                         control.v.pacman.setBounds((Y+1)*40, X*40, 40, 40);
                         control.m.QuitaElemento(X, Y);
                         control.m.AgregaElemento(X, Y+1, 9);
                         setY(Y+1);
                         Pair p = control.m.getCarro();
                         //System.out.println("X: " + getX() + " Y: " + getY());
                         //System.out.println("X: "+p.getFirst()+" Y:"+p.getSecond());//A ver si si se está moviendo
                         //System.err.println("elemento anterior: "+control.m.getElemento((p.getFirst()-1),(p.getSecond()-1)) );
                         Thread.sleep(getVelocidad());
                    } 
                }
                
                //ABAJO
                if(teclaActual==40){
                    if(control.m.getElemento(X+1, Y) == 0){
                         control.v.moveDown();
                         control.v.pacman.setBounds(Y*40, (X+1)*40, 40, 40);   
                         control.m.QuitaElemento(X, Y);
                         control.m.AgregaElemento(X+1, Y, 9);
                         setX(X+1);
                         Thread.sleep(getVelocidad());
                    } 
                }
                
                if(control.getTeclaActiva() != 32){//para que no se pare si apreta la tecla de la llama
                    
                    teclaActual = control.getTeclaActiva();//el control sabe cuál es la tecla actual en Vista
                }
                
           }
        } catch (InterruptedException ex) {
           System.out.println("Ocurrio un error en la clase movimientoPacman" + ex);
        }
        
    }


}
