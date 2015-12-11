
package clases;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
* Se debe tener en cuenta:
    1 --> Pared
    9 --> Carro/Pacman
    3 --> Cereza
    2 --> Fantasma
    0 --> Espacio vacío, por donde podría moverse un elemento
*/

public class tablero {

    private Pair p;//para retornar la posicion X, Y
    private int[][] matriz= new int[15][30];//Nuestro tablero de juego tiene estas dimensiones
    /*Imagen tablero: Ancho --> 1201 pixeles
                      Alto  --> 604 pixeles
    
    1201 / 30 = 40.03
    604 / 15 = 40.26
    
    Todas las imágenes las manejamos con una dimensión de 40X40, así que cada recuadro de la imagen
    tendrá estas dimensiones, y este es el valor en el que deberíamos movernos
    */
    
    public tablero() throws IOException{
        llenaMatriz();
    }
    
    public void Agrega(int filas, int columnas, int elemento){
        if(DimensionValida(filas,columnas)) matriz[filas][columnas] = elemento; //Agrega un elemento en la matriz
    }
    
    public void Quita(int filas, int columnas){
        if(DimensionValida(filas,columnas)) matriz[filas][columnas] = 0;//elimina el elemento que había en esa posición
    }
    
    public int getElemento(int filas, int columnas){
        if(DimensionValida(filas,columnas)) return matriz[filas][columnas];
        return -1;//si no
    }
    
    public boolean DimensionValida(int X, int Y){
        return X < 15 && X >= 0 && Y < 30 && Y>= 0;
    }
    
    public Pair getCarro(){//busca donde está el pacman
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 30; j++){
                if(matriz[i][j] == 9) return p = new Pair(i,j);
            }
        }
        return p = new Pair(-1,-1);//si no lo encuentra, retorna error
    }
    
        
    //Si se quiere reiniciar el juego con un botón, se deberá volver a cargar toda esta clase, 
    //porque a medida que se va jugando se va modificando la matriz, así que se deben establecer
    //de nuevo sus valores originales
    public void llenaMatriz() throws FileNotFoundException, IOException{
        String cadena;
        FileReader f = new FileReader("build/classes/tablero2.txt");//Aquí está toda la información del
        //juego inicial
        BufferedReader b = new BufferedReader(f);
        for(int k = 0; k < 15; k++){//fila
            cadena = b.readLine();
                for(int l = 0; l < 30; l++){
                    matriz[k][l] = cadena.charAt(l) - '0';//lo convierte a entero
                }
                System.out.println();
            }
        b.close();
    }
}