/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

public class EstadoNormal extends EstadoPacman{ 


    public EstadoNormal(vista juego) {
        this.juego=juego;
        SetEstado();
    }

    @Override
    public void SetEstado() {
        juego.mv_pacman.izquierda = "/imagenes/carro_left.png";
        juego.mv_pacman.derecha = "/imagenes/carro.png";
        juego.mv_pacman.abajo = "/imagenes/carro_down.png";
        juego.mv_pacman.arriba = "/imagenes/carro_up.png";
        juego.mv_llamita.Comenzar(true);
        juego.mv_llamita.run();
    }
}