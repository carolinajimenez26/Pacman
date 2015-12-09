
package clases;

import java.io.IOException;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.Observable;
import java.util.Observer;

public abstract class movimiento extends Thread implements Observer{
    int X,Y;//coordenadas donde se encuentra el objeto EN LA MATRIZ
    boolean comenzar;
    int velocidad;//cuanto tiempo espera
    Control control;
    boolean estadoNormal;
    
    public movimiento(int velocidad, Control control) throws IOException{
        this.velocidad = velocidad;
        this.control = control;
        comenzar = true;//así inicia
        estadoNormal = true;
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
    
    public boolean getEstadoNormal(){
        return estadoNormal;
    }
    
    public void setEstadoNormal(boolean b){
        estadoNormal = b;
    }
    
    @Override
    public void update(Observable o, Object o1) {//Este se ejecuta cada que se les notifique un cambio
        //a los observadores
        //intercambia el estado anterior
        System.err.println("El pacman cambió de estado");
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
    
    public boolean Alerta(int mv){//retorna si el carro está al menos en un radio de 3 casillas cerca
        
        Pair p = control.m.getCarro();

            if(mv == 1){//arriba
                
                for(int i = 1; i < 3; i++){
                    if(X - i == p.getFirst() && Y == p.getSecond()) return true;
                    else if(Y-i == p.getSecond() && (X - i == p.getFirst() || X + i == p.getFirst())) return true;
                }
                return false;
            }
            
             if(mv == 2){//abajo
                
                for(int i = 1; i < 3; i++){
                
                    if(Y+(40*i) == p.getSecond() && X == p.getFirst()) return true;
                    else if(Y+i == p.getSecond() && (X - i == p.getFirst() || X + i == p.getFirst())) return true;
                }
                
                return false;
            }
             
             
            if(mv == 3){//derecha
                
                for(int i = 1; i < 3; i++){
                
                    if(Y==p.getSecond() && X+(40*i) == p.getFirst()) return true;
                    else{
                        if(X+(40*i) == p.getFirst() && (Y+(40*i) == p.getSecond() || Y+(40*i) == p.getSecond())) return true;
                    }
                }
                
                return false;
            }
            
             if(mv == 4){//izquierda
                
                for(int i = 1; i < 3; i++){
                
                    if(Y == p.getSecond() && X - i == p.getFirst()) return true;
                    else{
                        if(X - i == p.getFirst() && (Y + i == p.getSecond() || Y + i == p.getSecond())) return true;
                    }
                }
                
                return false;
            }
             
            return false;
    }
    
    private double distancia(int x1, int y1, int x2, int y2){
        return sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2));//distancia entre dos puntos
    }
    
    public int Minimiza(){//Minimiza la distancia con el pacman, este es cuando está en estado vulnerable
        double min = 999, tmp = -999;  
        int index = 0;
        Pair p = control.m.getCarro();//Coordenadas del carro, X --> p.getfirst(), Y --> p.getSecond()
        for(int i = 0; i < 4; i++){//Mira todas las posibilidades de movimiento
            if(i == 0){//izquierda
                if(CanMoveLeftV())
                    tmp = distancia(p.getFirst(), p.getSecond(), X, Y-1);
            }
            if(i == 1){//derecha
                if(CanMoveRightV())
                    tmp = distancia(p.getFirst(), p.getSecond(), X, Y+1);
            }
            if(i == 2){//abajo
                if(CanMoveDownV())
                    tmp = distancia(p.getFirst(), p.getSecond(), X+1, Y);
            }
            if(i == 3){//arriba
                if(CanMoveUpV())
                    tmp = distancia(p.getFirst(), p.getSecond(), X-1, Y);
            }
            
            if(tmp < min){
                min = tmp;
                index = i; //guardamos cuál movimiento deberíamos realizar
            }
        }
        return index;
    }
    
    public int Maximiza(){//Maximiza la distancia con el pacman, este es cuando está en estado normal
        double max = -999, tmp = 999;  
        int index = 0;
        Pair p = control.m.getCarro();//Coordenadas del carro, X --> p.getfirst(), Y --> p.getSecond()
        for(int i = 0; i < 4; i++){//Mira todas las posibilidades de movimiento
            if(i == 0){//izquierda
                if(CanMoveLeft())
                    tmp = distancia(p.getFirst(), p.getSecond(), X, Y-1);
            }
            if(i == 1){//derecha
                if(CanMoveRight())
                    tmp = distancia(p.getFirst(), p.getSecond(), X, Y+1);
            }
            if(i == 2){//abajo
                if(CanMoveDown())
                    tmp = distancia(p.getFirst(), p.getSecond(), X+1, Y);
            }
            if(i == 3){//arriba
                if(CanMoveUp())
                    tmp = distancia(p.getFirst(), p.getSecond(), X-1, Y);
            }
            
            if(tmp > max){
                max = tmp;
                index = i; //guardamos cuál movimiento deberíamos realizar
            }
        }
        return index;
    }
    
    public boolean CanMoveLeft(){//Estado normal
        return (control.m.getElemento(X, Y-1) == 0 || control.m.getElemento(X, Y-1) == 3);
    }
    
    public boolean CanMoveRight(){//Estado normal
        return (control.m.getElemento(X, Y+1) == 0 || control.m.getElemento(X, Y+1) == 3);
    }
    
    public boolean CanMoveDown(){//Estado normal
        return (control.m.getElemento(X+1, Y) == 0 || control.m.getElemento(X+1, Y) == 3);
    }
    
    public boolean CanMoveUp(){//Estado normal
        return (control.m.getElemento(X-1, Y) == 0 || control.m.getElemento(X-1, Y) == 3);
    }
    
    public boolean CanMoveLeftV(){//Estado normal
        return control.m.getElemento(X, Y-1) == 0;
    }
    
    public boolean CanMoveRightV(){//Estado normal
        return control.m.getElemento(X, Y+1) == 0;
    }
    
    public boolean CanMoveDownV(){//Estado normal
        return control.m.getElemento(X+1, Y) == 0;
    }
    
    public boolean CanMoveUpV(){//Estado normal
        return control.m.getElemento(X-1, Y) == 0;
    }
     
}
