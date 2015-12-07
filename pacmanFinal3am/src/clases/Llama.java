package clases;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Llama extends movimiento{
    
    int teclaActual = 0;


    public Llama(vista juego, tablero t) throws IOException {
        super(juego, 50, t);
        
    }

    @Override
    public void run() {
        
            try {
                
                while(comenzar){
                    

                    int X=juego.pacman.getLocation().x;
                    int Y=juego.pacman.getLocation().y;

                    System.out.print("");
                 
                    if(juego.getTeclallama() == 32 && comprobar()){
                        
                        if(actualiza() == 37){//izquierda
                            juego.llamita.setImage("/imagenes/llama_left.png");
                            for(int i = 1; i <= 3; i++){
                                if(t.matriz[Y/40][(X-(40*i))/40] != 1){
                                    if(t.matriz[Y/40][(X-(40*i))/40] == 2){//si hay un fantasma
                                        juego.llamita.setBounds(X-(40*i), Y, 40, 40);
                                        juego.llamita.setVisible(true);
                                        juego.eliminar(Y/40, (X-(40*i))/40);
                                        Thread.sleep(100);
                                    }else{
                                        juego.llamita.setBounds(X-(40*i), Y, 40, 40);
                                        juego.llamita.setVisible(true);
                                        Thread.sleep(100);
                                    }
                                    
                                }
                                else break;
                            }
                            
                            Thread.sleep(getVelocidad());
                        }
                        
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
                        
                        
                        juego.setTeclallama(0);
                        juego.llamita.setVisible(false);

                    }
                    
                   

                    }
            
            } catch (InterruptedException ex) {
                System.out.println("ERROR!!");
            }
          
       
    }
    
    
    
    private int actualiza(){
        
        return juego.getTeclaActiva();
    }
    
}
