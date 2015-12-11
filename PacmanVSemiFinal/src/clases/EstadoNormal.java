/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EstadoNormal extends EstadoPacman{ 

    public EstadoNormal(Control control) {
        this.control = control;
        SetEstado();
    }

    @Override
    public void SetEstado() {
        try {
            control.mv_llamita= new Llama(50, control);//empieza a disparar
            control.mv_llamita.start();
            //control.v.setImagenNormal();//cambia la imagen del pacman
        } catch (IOException ex) {
            Logger.getLogger(EstadoNormal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}