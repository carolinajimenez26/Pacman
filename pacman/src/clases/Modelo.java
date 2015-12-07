package clases;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *Esta clase se encargará de tener toda la parte lógica del programa, cada que se haga un movimiento
 * en el tablero, se cambiará internamente la matriz que es la representación de los gráficos
 */
public class Modelo { //¿Debería tener la clase tablero definida internamente en esta clase?
    //Debe ir la inicialización del tablero
    //Métodos que modifiquen el tablero
    
    private tablero T; //Esta clase va a ser la única que pueda acceder a este objeto, y modificarlo
    
    public Modelo() throws IOException{
        T = new tablero();
    }
    
    public void AgregaElemento(int X, int Y, int e){
        T.Agrega(X, Y, e);
    }
    
    public void QuitaElemento(int X, int Y){
        T.Quita(X, Y);
    }
    
    public int getElemento(int X, int Y){
        return T.getElemento(X, Y);
    }
    
}


