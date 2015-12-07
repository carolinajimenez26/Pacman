
package clases;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public abstract class movimiento extends Thread implements Observer{
    boolean estadoNormal;
    int X,Y;//coordenadas donde se encuentra el objeto EN LA MATRIZ
    boolean comenzar;
    int velocidad;//cuanto tiempo espera
    Control control;
    
    public movimiento(int velocidad, Control control) throws IOException{
        this.velocidad = velocidad;
        estadoNormal = true; //inicia en estado normal
        this.control = control;
    }
    
    public boolean getEstadoNormal(){
        return estadoNormal;
    }
    
    public void setEstadoNormal(boolean b){
        estadoNormal = b;
    }
    
    public void setX(int X){
        this.X = X;
    }
    
    public void setY(int Y){
        this.Y = Y;
    }
    
    public void setVelocidad(int velocidad){
        this.velocidad = velocidad;
    }
    
    public int getVelocidad(){
        return velocidad;
    }
    
    public void Comenzar(boolean b){
        comenzar = b;
    }
    
    public int getX(){
        return X;
    }
    
    public int getY(){
        return Y;
    }
    
    @Override
    public void update(Observable o, Object o1) {//Este se ejecuta cada que se les notifique un cambio
        //a los observadores
        //intercambia el estado anterior
       if(getEstadoNormal()) setEstadoNormal(false);
       else setEstadoNormal(true);
    }
    
    @Override
    public abstract void run();
        
     //cada objeto se mueve de forma diferente
    public abstract void moveLeft();
    public abstract void moveRight();
    public abstract void moveDown();
    public abstract void moveUp();
     
}
