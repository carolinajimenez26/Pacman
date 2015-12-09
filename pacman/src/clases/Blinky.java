package clases;

import java.io.IOException;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
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
    
    public void moveRandom() throws InterruptedException{
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
    
    public void move() throws InterruptedException{//El fantasma se mueve dependiendo de el estado en el que el pacman se encuentre
        if(estadoNormal){
            
            int movimiento = Maximiza();
            switch(movimiento){
                case 0 :
                    moveLeft();
                    break;
                case 1 :
                    moveRight();
                    break;
                case 2 :
                    moveDown();
                    break;
                case 3 :
                    moveUp();
                    break;
                default : moveRandom();
            }
        }else{
            int movimiento = Minimiza();
            switch(movimiento){
                case 0 :
                    izquierdaV();
                    break;
                case 1 :
                    derechaV();
                    break;
                case 2 :
                    abajoV();
                    break;
                case 3 :
                    arribaV();
                    break;
                default : moveRandom();
            }
        }
    }
    

    @Override
    public void moveLeft() {
        int i = 1;
        int aux = Y;

        if(CanMoveLeft()) {//&& !Alerta(4)){
            control.v.rojito.setBounds((Y-i)*40,X*40, 40, 40); 
            if(control.m.getElemento(X, aux-1) == 3){
                try {
                    //control.v.cereza.setVisible(false);
                    if(control.m.getElemento(X, aux-1) == 3) control.m.QuitaElemento(X, aux-1);
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

        if(CanMoveRight()) {//&& !Alerta(3)){
            control.v.rojito.setBounds((Y+i)*40,X*40, 40, 40); 
            if(control.m.getElemento(X, aux+1) == 3){
                try {
                    //control.v.cereza.setVisible(false);
                    if(control.m.getElemento(X, aux+1) == 3) control.m.QuitaElemento(X, aux+1);
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

        if(CanMoveDown()){ //&& !Alerta(2)){
            control.v.rojito.setBounds(Y*40,(X+i)*40, 40, 40); 
            if(control.m.getElemento(aux+1, Y) == 3){
                try {
                    //control.v.cereza.setVisible(false);
                    if(control.m.getElemento(aux+1, Y) == 3) control.m.QuitaElemento(aux+1, Y);
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

        if(CanMoveUp()){ //&& !Alerta(1)){
            control.v.rojito.setBounds(Y*40,(X-i)*40, 40, 40); 
            if(control.m.getElemento(aux-1, Y) == 3){
                try {
                    //control.v.cereza.setVisible(false);
                    if(control.m.getElemento(aux-1, Y) == 3) control.m.QuitaElemento(aux-1, Y);
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

        if(CanMoveLeftV()){
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
        
        if(CanMoveRightV()){
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
        
        if(CanMoveDownV()){
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
        
        if(CanMoveUpV()){
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
    
}