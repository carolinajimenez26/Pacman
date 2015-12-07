
package clases;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class tablero {
    
    public int[][] matriz= new int[15][30];
    
    public tablero() throws IOException{
        llenaMatriz();
    }
    
    public void llenaMatriz() throws FileNotFoundException, IOException{
        String cadena;
        FileReader f = new FileReader("build/classes/tablero2.txt");
        BufferedReader b = new BufferedReader(f);
        for(int k = 0; k < 15; k++){//fila
            cadena = b.readLine();
                for(int l = 0; l < 30; l++){
                    matriz[k][l] = cadena.charAt(l) - '0';
                }
                System.out.println();
        }
        b.close();
        
        
    }
    
    
}
