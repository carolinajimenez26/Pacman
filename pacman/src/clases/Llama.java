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
            
            if(control.getTeclaLlama() == 32 && getEstadoNormal()){//Sólo puede disparar si está en estado normal
                
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
        control.v.llamita.setImage("/imagenes/llama_left.png");
                    
            for(int i = 1; i <= 3; i++){
                if(control.m.getElemento(p.getFirst(), p.getSecond()) != 1){//if(t.matriz[Y/40][(X-(40*i))/40] != 1)
                    if(control.m.getElemento(p.getFirst() - i, p.getSecond()) == 2){
                        control.v.llamita.setBounds(X-(40*i), Y, 40, 40);
                        setX(X-i);//la llamita debe saber donde se encuentra
                        control.v.llamita.setVisible(true);
                        control.v.eliminar(X-i, Y);//eliminar(Y/40, (X-(40*i))/40);
                        control.m.QuitaElemento(X-i, Y);
                        Thread.sleep(100);
                    }else{
                        control.v.llamita.setBounds(X-(40*i), Y, 40, 40);
                        control.v.llamita.setVisible(true);
                        Thread.sleep(100);
                    }
                }else{
                    break;
                }
                
            try {
                Thread.sleep(getVelocidad());
            } catch (InterruptedException ex) {
                Logger.getLogger(Llama.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public void moveRight() {
        if(actualiza() == 39){//derecha
                            juego.llamita.setImage("/imagenes/llama.png");
                            for(int i = 1; i <= 3; i++){
                                if(t.matriz[Y/40][(X+(40*i))/40] != 1){
                                    if(t.matriz[Y/40][(X+(40*i))/40]== 2){//si hay un fantasma
                                        juego.llamita.setBounds(X+(40*i), Y, 40, 40);
                                        juego.llamita.setVisible(true);
                                        juego.eliminar(Y/40,(X+(40*i))/40);
                                        Thread.sleep(100);
                                    }else{
                                        juego.llamita.setBounds(X+(40*i), Y, 40, 40);
                                        juego.llamita.setVisible(true);
                                        Thread.sleep(100);
                                    }
                                    
                                }
                                else break;
                            }
                            
                            Thread.sleep(getVelocidad());
                        }
                        
    }

    @Override
    public void moveDown() {
        if(actualiza()==40){//abajo
                            juego.llamita.setImage("/imagenes/llama_down.png");
                            for(int i = 1; i <= 3; i++){
                                if(t.matriz[(Y+(40*i))/40][X/40] != 1){
                                    if(t.matriz[(Y+(40*i))/40][X/40]== 2){//si hay un fantasma
                                        juego.llamita.setBounds(X, Y+(40*i), 40, 40);
                                        juego.llamita.setVisible(true);
                                        juego.eliminar((Y+(40*i))/40,X/40);
                                        Thread.sleep(100);
                                    }else{
                                        juego.llamita.setBounds(X, Y+(40*i), 40, 40);
                                        juego.llamita.setVisible(true);
                                        Thread.sleep(100);
                                    }
                                }
                                else break;
                            }
                            
                            Thread.sleep(getVelocidad());
                        }
    }

    @Override
    public void moveUp() {
        if(actualiza() == 38){//arriba
                            juego.llamita.setImage("/imagenes/llama_up.png");
                            for(int i = 1; i <= 3; i++){
                                if(t.matriz[(Y-(40*i))/40][X/40] != 1){
                                    if(t.matriz[(Y-(40*i))/40][X/40]== 2){//si hay un fantasma
                                        juego.llamita.setBounds(X, Y-(40*i), 40, 40);
                                        juego.llamita.setVisible(true);
                                        juego.eliminar((Y-(40*i))/40,X/40);
                                        Thread.sleep(100);
                                    }else{
                                        juego.llamita.setBounds(X, Y-(40*i), 40, 40);
                                        juego.llamita.setVisible(true);
                                        Thread.sleep(100);
                                    }
                                    
                                }
                                
                                else break;
                            }
                            
                            Thread.sleep(getVelocidad());
                        }
                        
    }
    
    


}
