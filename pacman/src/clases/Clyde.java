

package clases;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Clyde  extends movimiento{

    public Clyde(int velocidad, Control control) throws IOException {
        super(velocidad, control);
    }


    @Override
    public void run() {
        while(comenzar){
            try {
                move();
            } catch (InterruptedException ex) {
                Logger.getLogger(Clyde.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void move() throws InterruptedException{//El fantasma se mueve dependiendo de el estado en el que el pacman se encuentre
        if(estadoNormal){
            moveRight();
            moveUp();
            moveLeft();
            moveDown();
        }else{
            if(Alerta(1)) arribaV();
            if(Alerta(2)) abajoV();
            if(Alerta(3)) derechaV();
            if(Alerta(4)) izquierdaV();
            else{
                moveLeft();
            }
        }
                
    }

    @Override
    public void moveLeft() {
        int i = 1;
        int aux = Y;

        if(control.m.getElemento(X, aux-1) == 0 && !Alerta(4)){
            control.v.amarillito.setBounds((Y-i)*40,X*40, 40, 40); 
            if(control.m.getElemento(X, aux-1) == 3){
                try {
                    control.EstadoVulnerable();//cambia a estado vulnerable
                } catch (InterruptedException ex) {
                    Logger.getLogger(Clyde.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            control.m.QuitaElemento(X, aux);
            control.m.AgregaElemento(X, Y-i, 2);
            setY(Y-i);
            aux--;i++;
            try {
                Thread.sleep(getVelocidad());
            } catch (InterruptedException ex) {
                Logger.getLogger(Clyde.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void moveRight() {
      
        int i = 1;
        int aux = Y;

        if(control.m.getElemento(X, aux+1) == 0 && !Alerta(3)){
            control.v.amarillito.setBounds((Y+i)*40,X*40, 40, 40); 
            if(control.m.getElemento(X, aux+1) == 3){
                try {
                    control.EstadoVulnerable();//cambia a estado vulnerable
                } catch (InterruptedException ex) {
                    Logger.getLogger(Clyde.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            control.m.QuitaElemento(X, aux);
            control.m.AgregaElemento(X, Y+i, 2);
            setY(Y+i);
            aux++;i++;
            try {
                Thread.sleep(getVelocidad());
            } catch (InterruptedException ex) {
                Logger.getLogger(Clyde.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void moveDown() {
        int i = 1;
        int aux = X;

        if(control.m.getElemento(aux+1, Y) == 0 && !Alerta(2)){
            control.v.amarillito.setBounds(Y*40,(X+i)*40, 40, 40); 
            if(control.m.getElemento(aux+1, Y) == 3){
                try {
                    control.EstadoVulnerable();//cambia a estado vulnerable
                } catch (InterruptedException ex) {
                    Logger.getLogger(Clyde.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            control.m.QuitaElemento(aux, Y);
            control.m.AgregaElemento(X+i, Y, 2);
            setX(X+i);
            aux++;i++;
            try {
                Thread.sleep(getVelocidad());
            } catch (InterruptedException ex) {
                Logger.getLogger(Clyde.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void moveUp() {
        
        int i = 1;
        int aux = X;

        if(control.m.getElemento(aux-1, Y) == 0 && !Alerta(1)){
            control.v.amarillito.setBounds(Y*40,(X-i)*40, 40, 40); 
            if(control.m.getElemento(aux-1, Y) == 3){
                try {
                    control.EstadoVulnerable();//cambia a estado vulnerable
                } catch (InterruptedException ex) {
                    Logger.getLogger(Clyde.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            control.m.QuitaElemento(aux, Y);
            control.m.AgregaElemento(X-i, Y, 2);
            setX(X-i);
            aux--;i++;
            try {
                Thread.sleep(getVelocidad());
            } catch (InterruptedException ex) {
                Logger.getLogger(Clyde.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void izquierdaV() throws InterruptedException{

        if(control.m.getElemento(X, Y-1) == 0){
            control.v.amarillito.setBounds((Y-1)*40,X*40, 40, 40);
            control.m.QuitaElemento(X, Y);
            control.m.AgregaElemento(X, Y-1, 2);
            setY(Y-1);
            try {
                Thread.sleep(getVelocidad());
            } catch (InterruptedException ex) {
                Logger.getLogger(Clyde.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }

    
     private void derechaV() throws InterruptedException{
        
        if(control.m.getElemento(X, Y+1) == 0){
            control.v.amarillito.setBounds((Y+1)*40,X*40, 40, 40);
            control.m.QuitaElemento(X, Y);
            control.m.AgregaElemento(X, Y+1, 2);
            setY(Y+1);
            try {
                Thread.sleep(getVelocidad());
            } catch (InterruptedException ex) {
                Logger.getLogger(Clyde.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }

    
    private void abajoV() throws InterruptedException{
        
        if(control.m.getElemento(X+1, Y) == 0){
            control.v.amarillito.setBounds(Y*40,(X+1)*40, 40, 40);
            control.m.QuitaElemento(X, Y);
            control.m.AgregaElemento(X+1, Y, 2);
            setX(X-1);
            try {
                Thread.sleep(getVelocidad());
            } catch (InterruptedException ex) {
                Logger.getLogger(Clyde.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    
    
    private void arribaV() throws InterruptedException{
        
        if(control.m.getElemento(X-1, Y) == 0){
            control.v.amarillito.setBounds(Y*40,(X-1)*40, 40, 40);
            control.m.QuitaElemento(X, Y);
            control.m.AgregaElemento(X-1, Y, 2);
            setX(X-1);
            try {
                Thread.sleep(getVelocidad());
            } catch (InterruptedException ex) {
                Logger.getLogger(Clyde.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    
}