
package clases;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Inky extends movimiento implements Observer{

    public Inky(vista juego, tablero t) throws IOException {
        super(juego, 300, t);
    }


    @Override
    public void update(Observable o, Object o1) {
        estadoNormal=false;     
    }
    
    public void run(){
        try {
            while(comenzar){
                if(estadoNormal){
                    abajo();
                    arriba();
                    izquierda();
                    derecha(); 
                    
                }

                else{

                        //Thread.sleep(getVelocidad());
                        if(Alerta(1)) arribaV();
                        if(Alerta(2)) abajoV();
                        if(Alerta(3)) derechaV();
                        if(Alerta(4)) izquierdaV();
                        else{

                            arriba();
                        }

                }
            }
            
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Clyde.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void izquierdaV() throws InterruptedException{
        
        int Y=((juego.azulito.getLocation().x)/40);
        int X=((juego.azulito.getLocation().y)/40);
        if(t.matriz[X][Y-1]==0&&t.matriz[X][Y-1]!=2){
            juego.azulito.setBounds((Y-1)*40,X*40, 40, 40);
            juego.eliminarPacman(X, Y-1);
            t.matriz[X][Y] = 0;
            t.matriz[X][Y-1] = 2;
            Thread.sleep(getVelocidad());
        }
        
    }
    
    private void derechaV() throws InterruptedException{
        
        int Y=((juego.azulito.getLocation().x)/40);
        int X=((juego.azulito.getLocation().y)/40);
        if(t.matriz[X][Y+1]==0&&t.matriz[X][Y+1]!=2){
            juego.azulito.setBounds((Y+1)*40,X*40, 40, 40);
            juego.eliminarPacman(X, Y+1);
            t.matriz[X][Y] = 0;
            t.matriz[X][Y+1] = 2;
            Thread.sleep(getVelocidad());
        }
        
        
    }
    
    private void abajoV() throws InterruptedException{
        
        int Y=((juego.azulito.getLocation().x)/40);
        int X=((juego.azulito.getLocation().y)/40);
        if(t.matriz[X+1][Y]==0&&t.matriz[X+1][Y]!=2){
            juego.azulito.setBounds(Y*40,(X+1)*40, 40, 40);
            juego.eliminarPacman(X+1, Y);
            t.matriz[X][Y] = 0;
            t.matriz[X+1][Y] = 2;
            Thread.sleep(getVelocidad());
        }
        
        
    }
    
    private void arribaV() throws InterruptedException{
        
        int Y=((juego.azulito.getLocation().x)/40);
        int X=((juego.azulito.getLocation().y)/40);
        if(t.matriz[X-1][Y]==0&&t.matriz[X-1][Y]!=2){
            juego.azulito.setBounds(Y*40,(X-1)*40, 40, 40);
            juego.eliminarPacman(X-1, Y);
            t.matriz[X][Y] = 0;
            t.matriz[X-1][Y] = 2;
            Thread.sleep(getVelocidad());
        }
        
   
    }
    
    private void derecha() throws InterruptedException{
        int i=1;
            int Y=((juego.azulito.getLocation().x)/40);
            int X=((juego.azulito.getLocation().y)/40);
            int aux=Y;
            
            while((t.matriz[X][aux+1] == 0 || t.matriz[X][aux+1] == 3) && !Alerta(3) && t.matriz[X][aux+1] != 2){ //derecha
                
                    juego.azulito.setBounds((Y+i)*40,X*40, 40, 40); 
                    if(t.matriz[X][aux+1]==3){
                        
                        juego.EstadoVulnerable();
                    }
                    t.matriz[X][aux] = 0;
                    t.matriz[X][Y+i] = 2;
                    aux++;
                    i++;
                    Thread.sleep(getVelocidad());
            }
    }
    
    private void izquierda() throws InterruptedException{
        int i=1;
            int Y=((juego.azulito.getLocation().x)/40);
            int X=((juego.azulito.getLocation().y)/40);
            int aux=Y;
            
            while((t.matriz[X][aux-1] == 0 || t.matriz[X][aux-1] == 3) && !Alerta(4) && t.matriz[X][aux-1] != 2){//izquierda
                
                    juego.azulito.setBounds((Y-i)*40,X*40, 40, 40); 
                    if(t.matriz[X][aux-1]==3){
                        
                        juego.EstadoVulnerable();
                    }
                    t.matriz[X][aux] = 0;
                    t.matriz[X][Y-i] = 2;
                    aux--;
                    i++;
                    Thread.sleep(getVelocidad());
            }
    }
    
    private void arriba() throws InterruptedException{
        int Y=((juego.azulito.getLocation().x)/40);
            int X=((juego.azulito.getLocation().y)/40);
            int aux=X, i=1;
            
            while((t.matriz[aux-1][Y] == 0 || t.matriz[aux-1][Y] == 3) && !Alerta(1) && t.matriz[aux-1][Y] != 2){
                
                    juego.azulito.setBounds(Y*40,(X-i)*40, 40, 40); 
                    if(t.matriz[aux-1][Y]==3){
                        
                        juego.EstadoVulnerable();
                    }
                    t.matriz[aux][Y] = 0;
                    t.matriz[X-i][Y] = 2;
                    aux--;
                    i++;
                    Thread.sleep(getVelocidad());
            }
            
    }
    
    private void abajo() throws InterruptedException{
        
            int i=1;
            int Y=((juego.azulito.getLocation().x)/40);
            int X=((juego.azulito.getLocation().y)/40);
            int aux=X;
            
            while((t.matriz[aux+1][Y] == 0 || t.matriz[aux+1][Y] == 3) && !Alerta(2) && t.matriz[aux+1][Y] != 2){
                
                    juego.azulito.setBounds(Y*40,(X+i)*40, 40, 40); 
                    if(t.matriz[aux+1][Y]==3){
                        
                        juego.EstadoVulnerable();
                    }
                    t.matriz[aux][Y] = 0;
                    t.matriz[X+i][Y] = 2;
                    aux++;
                    i++;
                    Thread.sleep(getVelocidad());
            }
    }
    
    public boolean Alerta(int mv){
        
        int X=juego.azulito.getLocation().x;
        int Y=juego.azulito.getLocation().y;
        int Xpacman=juego.pacman.getLocation().x;
        int Ypacman=juego.pacman.getLocation().y;
        
        
            
            if(mv==1){//arriba
                
                for(int i=1; i<3; i++){
                
                    if(Y-(40*i)==Ypacman && X==Xpacman) return true;
                    else{

                        if(Y-(40*i)==Ypacman && (X-(40*i)==Xpacman || X +(40*i)==Xpacman)) return true;
                    }
                }
                
                return false;
            }
            
             if(mv==2){//abajo
                
                for(int i=1; i<3; i++){
                
                    if(Y+(40*i)==Ypacman && X==Xpacman) return true;
                    else{

                        if(Y+(40*i)==Ypacman && (X-(40*i)==Xpacman ||X+(40*i)==Xpacman)) return true;
                    }
                }
                
                return false;
            }
             
             
            if(mv==3){//derecha
                
                for(int i=1; i<3; i++){
                
                    if(Y==Ypacman && X+(40*i)==Xpacman) return true;
                    else{

                        if(X+(40*i)==Xpacman && (Y+(40*i)==Ypacman ||Y+(40*i)==Ypacman)) return true;
                    }
                }
                
                return false;
            }
            
             if(mv==4){//izquierda
                
                for(int i=1; i<3; i++){
                
                    if(Y==Ypacman && X-(40*i)==Xpacman) return true;
                    else{

                        if(X-(40*i)==Xpacman && (Y+(40*i)==Ypacman ||Y+(40*i)==Ypacman)) return true;
                    }
                }
                
                return false;
            }
             
             
            return false;
    }

    
}