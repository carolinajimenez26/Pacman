
package clases;

import java.io.IOException;

public abstract class movimiento extends Thread{
    
    int X,Y;//coordenadas donde se encuentra el objeto EN LA MATRIZ
    boolean comenzar;
    int velocidad;//cuanto tiempo espera

    
    public movimiento(int velocidad) throws IOException{
        this.velocidad = velocidad;
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
    public abstract void run();
        
     //cada objeto se mueve de forma diferente
    public abstract void moveLeft();
    public abstract void moveRight();
    public abstract void moveDown();
    public abstract void moveUp();
     
}
