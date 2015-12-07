
package clases;


import java.util.logging.Level;
import java.util.logging.Logger;

public class EstadoVulnerable extends EstadoPacman{//Este debe comunicarse con el controlador, para que
    //a su vez el controlador modifique lo que éste desea
        
    public EstadoVulnerable(Vista v) {
        this.v = v;
        SetEstado();
    }

    @Override
    public void SetEstado() {
        //Llama a la vista y cambia los atributos del pacman
        //detiene el hilo de la llamita
    }
    
  
}
