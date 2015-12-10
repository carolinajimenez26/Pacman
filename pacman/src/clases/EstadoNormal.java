/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

public class EstadoNormal extends EstadoPacman{ 

    public EstadoNormal(Control control) {
        this.control = control;
        SetEstado();
    }

    @Override
    public void SetEstado() {
        control.mv_llamita.Comenzar(true);//empieza a disparar
        control.mv_llamita.start();
        control.v.setImagenNormal();//cambia la imagen del pacman
    }
}