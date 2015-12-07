
package clases;

import java.io.IOException;

public abstract class movimiento extends Thread{
    
    int velocidad;//cuanto tiempo espera
    final vista juego;
    boolean comenzar=true;
    tablero t;
    boolean estadoNormal=true;
    int contador=0;
    
    public movimiento(vista juego, int velocidad, tablero t) throws IOException{
        this.t = t;
        this.juego = juego;
        this.velocidad = velocidad;
    }
    
    @Override
     public abstract void run();
        
  
    
     public int getVelocidad() {
        return velocidad;
    }
     
     
     public void Comenzar(boolean comenzar){
         
         this.comenzar=comenzar;
     }
     
     public boolean comprobar(){
         
        return juego.EsNormal();
     }
     
     public boolean comprobar(int X, int Y){
         
         if(juego.EsNormal()){
             
             return t.matriz[X][Y] != 9;
         }
         
         else return t.matriz[X][Y] == 9;
     }
     
     
}
