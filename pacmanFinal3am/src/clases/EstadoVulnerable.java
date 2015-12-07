
package clases;

import static clases.vista.cereza;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EstadoVulnerable extends EstadoPacman{
        
    public EstadoVulnerable(vista juego) {
        this.juego=juego;
        SetEstado();
    }

    @Override
    public void SetEstado() {
        
        juego.cereza.setVisible(false);
        juego.t.matriz[(cereza.getLocation().y)/40][(cereza.getLocation().x)/40]=0;
        juego.mv_pacman.izquierda = "/imagenes/pacman2_right.gif";
        juego.mv_pacman.derecha = "/imagenes/pacman2_right.gif";
        juego.mv_pacman.abajo = "/imagenes/pacman2_right.gif";
        juego.mv_pacman.arriba = "/imagenes/pacman2_right.gif";
        juego.mv_llamita.Comenzar(false);

    }
    
  
}
