package clases;

import java.io.IOException;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Blinky extends movimiento{

    public Blinky(int velocidad, Control control) throws IOException {
        super(velocidad, control);
    }

    @Override
    public void run() {
        while(comenzar){
            try {
                move();
            } catch (InterruptedException ex) {
                Logger.getLogger(Blinky.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void move() throws InterruptedException{//El fantasma se mueve dependiendo de el estado en el que el pacman se encuentre
        if(estadoNormal){
            moveUp();
            moveDown();
            moveRight();
            moveLeft();
        }else{
            if(Alerta(1)) arribaV();
                if(Alerta(2)) abajoV();
                if(Alerta(3)) derechaV();
                if(Alerta(4)) izquierdaV();
                else{
                    moveDown();
                }
        }
    }

    @Override
    public void moveLeft() {
        int i = 1;
        int aux = Y;

        if(control.m.getElemento(X, aux-1) == 0 && !Alerta(4)){
            control.v.rojito.setBounds((Y-i)*40,X*40, 40, 40); 
            if(control.m.getElemento(X, aux-1) == 3){
                try {
                    control.EstadoVulnerable();//cambia a estado vulnerable
                } catch (InterruptedException ex) {
                    Logger.getLogger(Blinky.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            control.m.QuitaElemento(X, aux);
            control.m.AgregaElemento(X, Y-i, 2);
            setY(Y-i);
            aux--;i++;
            try {
                Thread.sleep(getVelocidad());
            } catch (InterruptedException ex) {
                Logger.getLogger(Blinky.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void moveRight() {
      
        int i = 1;
        int aux = Y;

        if(control.m.getElemento(X, aux+1) == 0 && !Alerta(3)){
            control.v.rojito.setBounds((Y+i)*40,X*40, 40, 40); 
            if(control.m.getElemento(X, aux+1) == 3){
                try {
                    control.EstadoVulnerable();//cambia a estado vulnerable
                } catch (InterruptedException ex) {
                    Logger.getLogger(Blinky.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            control.m.QuitaElemento(X, aux);
            control.m.AgregaElemento(X, Y+i, 2);
            setY(Y+i);
            aux++;i++;
            try {
                Thread.sleep(getVelocidad());
            } catch (InterruptedException ex) {
                Logger.getLogger(Blinky.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void moveDown() {
        int i = 1;
        int aux = X;

        if(control.m.getElemento(aux+1, Y) == 0 && !Alerta(2)){
            control.v.rojito.setBounds(Y*40,(X+i)*40, 40, 40); 
            if(control.m.getElemento(aux+1, Y) == 3){
                try {
                    control.EstadoVulnerable();//cambia a estado vulnerable
                } catch (InterruptedException ex) {
                    Logger.getLogger(Blinky.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            control.m.QuitaElemento(aux, Y);
            control.m.AgregaElemento(X+i, Y, 2);
            setX(X+i);
            aux++;i++;
            try {
                Thread.sleep(getVelocidad());
            } catch (InterruptedException ex) {
                Logger.getLogger(Blinky.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void moveUp() {
        
        int i = 1;
        int aux = X;

        if(control.m.getElemento(aux-1, Y) == 0 && !Alerta(1)){
            control.v.rojito.setBounds(Y*40,(X-i)*40, 40, 40); 
            if(control.m.getElemento(aux-1, Y) == 3){
                try {
                    control.EstadoVulnerable();//cambia a estado vulnerable
                } catch (InterruptedException ex) {
                    Logger.getLogger(Blinky.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            control.m.QuitaElemento(aux, Y);
            control.m.AgregaElemento(X-i, Y, 2);
            setX(X-i);
            aux--;i++;
            try {
                Thread.sleep(getVelocidad());
            } catch (InterruptedException ex) {
                Logger.getLogger(Blinky.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void izquierdaV() throws InterruptedException{

        if(control.m.getElemento(X, Y-1) == 0){
            control.v.rojito.setBounds((Y-1)*40,X*40, 40, 40);
            control.m.QuitaElemento(X, Y);
            control.m.AgregaElemento(X, Y-1, 2);
            setY(Y-1);
            try {
                Thread.sleep(getVelocidad());
            } catch (InterruptedException ex) {
                Logger.getLogger(Blinky.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }

    
     private void derechaV() throws InterruptedException{
        
        if(control.m.getElemento(X, Y+1) == 0){
            control.v.rojito.setBounds((Y+1)*40,X*40, 40, 40);
            control.m.QuitaElemento(X, Y);
            control.m.AgregaElemento(X, Y+1, 2);
            setY(Y+1);
            try {
                Thread.sleep(getVelocidad());
            } catch (InterruptedException ex) {
                Logger.getLogger(Blinky.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }

    
    private void abajoV() throws InterruptedException{
        
        if(control.m.getElemento(X+1, Y) == 0){
            control.v.rojito.setBounds(Y*40,(X+1)*40, 40, 40);
            control.m.QuitaElemento(X, Y);
            control.m.AgregaElemento(X+1, Y, 2);
            setX(X-1);
            try {
                Thread.sleep(getVelocidad());
            } catch (InterruptedException ex) {
                Logger.getLogger(Blinky.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    
    
    private void arribaV() throws InterruptedException{
        
        if(control.m.getElemento(X-1, Y) == 0){
            control.v.rojito.setBounds(Y*40,(X-1)*40, 40, 40);
            control.m.QuitaElemento(X, Y);
            control.m.AgregaElemento(X-1, Y, 2);
            setX(X-1);
            try {
                Thread.sleep(getVelocidad());
            } catch (InterruptedException ex) {
                Logger.getLogger(Blinky.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    
    public boolean Alerta(int mv){
        
        Pair p = control.m.getCarro();

            if(mv == 1){//arriba
                
                for(int i = 1; i < 3; i++){
                
                    if(Y-(40*i) == p.getSecond() && X == p.getFirst()) return true;
                    else{
                        if(Y-(40*i) == p.getSecond() && (X-(40*i) == p.getFirst() || X + (40*i) == p.getFirst())) return true;
                    }
                }
                
                return false;
            }
            
             if(mv == 2){//abajo
                
                for(int i = 1; i < 3; i++){
                
                    if(Y+(40*i) == p.getSecond() && X == p.getFirst()) return true;
                    else{
                        if(Y+(40*i) == p.getSecond() && (X-(40*i) == p.getFirst() || X+(40*i) == p.getFirst())) return true;
                    }
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
                
                    if(Y==p.getSecond() && X-(40*i) == p.getFirst()) return true;
                    else{
                        if(X-(40*i) == p.getFirst() && (Y+(40*i) == p.getSecond() || Y+(40*i) == p.getSecond())) return true;
                    }
                }
                
                return false;
            }
             
            return false;
    }
    
   
}