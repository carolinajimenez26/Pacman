package clases;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Llama extends movimiento{

    public Llama(int velocidad, Control control) throws IOException {
        super(velocidad, control);
    }

    @Override
    public void run() {
        while(comenzar){
            System.err.println();//sin esto no funciona XD
            if(control.getTeclaLlama() == 32 && getEstadoNormal()){//Sólo puede disparar si está en estado normal
                System.err.println("Llama run()");
                System.out.println("TeclaLlama(): "+ control.getTeclaLlama());
                System.out.println("estadoNormal(): " + getEstadoNormal());
                
                if(control.getTeclaActiva() == 37){ //Izquierda
                    moveLeft();
                }
                if(control.getTeclaActiva() == 38){ //Arriba
                    moveUp();
                }
                if(control.getTeclaActiva() == 39){ //Derecha
                    moveRight();
                }
                if(control.getTeclaActiva() == 40){ //Abajo
                    moveDown();
                }
                
                control.setTeclaLlama(0);
                control.v.llamita.setVisible(false);
            }
        }
    }

    @Override
    public void moveLeft() {
        Pair p = control.m.getCarro();
        int X = control.v.pacman.getLocation().x;
        int Y = control.v.pacman.getLocation().y;
        control.v.llamita.setImage("/imagenes/llama_left.png");
                    
            for(int i = 1; i <= 3; i++){
                if(control.m.getElemento(p.getFirst(), p.getSecond() - i) != 1){//if(t.matriz[Y/40][(X-(40*i))/40] != 1)
                    if(control.m.getElemento(p.getFirst() - i, p.getSecond()) == 2){
                        control.v.llamita.setBounds(X-(40*i), Y, 40, 40);
                        control.v.llamita.setVisible(true);
                        control.v.eliminar(Y/40, (X-(40*i))/40);//quita el fantasma del tablero de juego
                        control.m.QuitaElemento(p.getFirst() - i, p.getSecond());//quita el elemento de la matriz
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Llama.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        control.v.llamita.setBounds(X-(40*i), Y, 40, 40);
                        control.v.llamita.setVisible(true);
                        //No nos interesa poner la llama en la matriz
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Llama.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }else break;
            
            }
            try {
                Thread.sleep(getVelocidad());
            } catch (InterruptedException ex) {
                Logger.getLogger(Llama.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public void moveRight() {
        
        int X = control.v.pacman.getLocation().x;
        int Y = control.v.pacman.getLocation().y;
                    
        Pair p = control.m.getCarro();
        control.v.llamita.setImage("/imagenes/llama.png");
        
            for(int i = 1; i <= 3; i++){
                if(control.m.getElemento(p.getFirst(), p.getSecond() + i) != 1){
                    if(control.m.getElemento(p.getFirst(), p.getSecond() + i) == 2){
                        control.v.llamita.setBounds(X+(40*i), Y, 40, 40);
                        control.v.llamita.setVisible(true);
                        control.v.eliminar(Y/40, (X+(40*i))/40);//quita el fantasma del tablero de juego
                        control.m.QuitaElemento(p.getFirst(), p.getSecond() + i);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Llama.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        control.v.llamita.setBounds(X+(40*i), Y, 40, 40);
                        control.v.llamita.setVisible(true);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Llama.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }else break;
            }
            try {
                Thread.sleep(getVelocidad());
            } catch (InterruptedException ex) {
                Logger.getLogger(Llama.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
                
    @Override
    public void moveDown() {
        Pair p = control.m.getCarro();
        int X = control.v.pacman.getLocation().x;
        int Y = control.v.pacman.getLocation().y;
        control.v.llamita.setImage("/imagenes/llama_down.png");
            
            for(int i = 1; i <= 3; i++){
                if(control.m.getElemento(p.getFirst() + i, p.getSecond()) != 1){
                    if(control.m.getElemento(p.getFirst() + i, p.getSecond()) == 2){
                        control.v.llamita.setBounds(X, Y+(40*i), 40, 40);
                        control.v.llamita.setVisible(true);
                        control.v.eliminar((Y+(40*i))/40,X/40);//quita el fantasma del tablero de juego
                        control.m.QuitaElemento(p.getFirst() + i, p.getSecond());
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Llama.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        control.v.llamita.setBounds(X, Y+(40*i), 40, 40);
                        control.v.llamita.setVisible(true);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Llama.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }else break;
            }
        try {
            Thread.sleep(getVelocidad());
        } catch (InterruptedException ex) {
            Logger.getLogger(Llama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void moveUp() {
        Pair p = control.m.getCarro();
        int X = control.v.pacman.getLocation().x;
        int Y = control.v.pacman.getLocation().y;
        control.v.llamita.setImage("/imagenes/llama_up.png");
            
            for(int i = 1; i <= 3; i++){
                if(control.m.getElemento(p.getFirst()- i, p.getSecond()) != 1){
                    if(control.m.getElemento(p.getFirst() + i, p.getSecond()) == 2){
                        control.v.llamita.setBounds(X, Y-(40*i), 40, 40);
                        control.v.llamita.setVisible(true);
                        control.v.eliminar((Y-(40*i))/40,X/40);//quita el fantasma del tablero de juego
                        control.m.QuitaElemento(p.getFirst() + i, p.getSecond());
                        try { 
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Llama.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        control.v.llamita.setBounds(X, Y-(40*i), 40, 40);
                         control.v.llamita.setVisible(true);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Llama.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }else break;
            }
        try {
            Thread.sleep(getVelocidad());
        } catch (InterruptedException ex) {
            Logger.getLogger(Llama.class.getName()).log(Level.SEVERE, null, ex);
        }             
    }
    
}
