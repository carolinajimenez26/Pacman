
package clases;


import java.util.logging.Level;
import java.util.logging.Logger;

public class EstadoVulnerable extends EstadoPacman{//Este debe comunicarse con el controlador, para que
    //a su vez el controlador modifique lo que éste desea
        
    public EstadoVulnerable(Control control) {
        this.control = control;
        SetEstado();
    }

    @Override
    public void SetEstado() {
        control.mv_llamita.stop();//deja de disparar
        //control.mv_llamita.Comenzar(false);
        control.v.setImagenVulnerable();//cambia la imagen del pacman
    }
    
  
}
