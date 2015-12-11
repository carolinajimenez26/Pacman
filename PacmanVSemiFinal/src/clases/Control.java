
package clases;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *Esta clase se encargará de hacer la comunicación entre la Vista y el Modelo, por ende, 
 * todos los objetos que se utilizan en el juego deben inicializarse en este espacio (los fantasmas, 
 * el pacman, la llama y la cereza). Cada que se haga un llamado a esta clase, ésta decidirá si debe
 * hacer el cambio directamente en la Vista o en el Modelo, o en ambas.
 */
public class Control implements ActionListener{
    //se deben crear todos los objetos acá, menos los tipo imagen
    //se debe tener un objeto de la clase Vista y otro de la clase Modelo
    //Cada que se presione una tecla, Vista hará llamado a esta clase para que cambie los 
    //datos en la matriz lógica y modifique desde aquí las imágenes del JFrame
    JButton reinicio;
    JFrame auxiliar;
    Vista v;
    Modelo m;
    movimientoPacman mv_pacman;
    Clyde mv_clyde;
    Blinky mv_blinky;
    Inky mv_inky;
    Pinky mv_pinky;
    Llama mv_llamita;
    private boolean estadoNormal;
    private int morir = 0;//muere si todos los fantasmas se eliminan
    
    public Control() throws IOException{
        inicializar();
    }
    
    public void inicializar() throws IOException{
        estadoNormal = true;
        m = new Modelo();//inicializa modelo
        v = new Vista(this);//inicializa vista
        
        mv_pacman = new movimientoPacman(this);
        mv_clyde = new Clyde(900,this);
        mv_pinky = new Pinky(200,this);
        mv_inky = new Inky(400,this);
        mv_blinky = new Blinky(300,this);
        mv_llamita= new Llama(50,this);
        
        mv_inky.setX(7);mv_inky.setY(15);
        m.AgregaElemento(7, 15, 2);
        
        mv_pinky.setX(6);mv_pinky.setY(16);
        m.AgregaElemento(6, 16, 2);
        
        mv_blinky.setX(6);mv_blinky.setY(13);
        m.AgregaElemento(6, 13, 2);
        
        mv_clyde.setX(1);mv_clyde.setY(24);
        m.AgregaElemento(1, 24, 2);
        
        mv_pacman.setX(7);mv_pacman.setY(1);
        //cerecita --> columna: 27, fila: 1
        
        //Agregamos los observadores del pacman
        mv_pacman.addObserver(mv_clyde);//los fantasmas van a estar observando al pacman
        mv_pacman.addObserver(mv_blinky);
        mv_pacman.addObserver(mv_inky);
        mv_pacman.addObserver(mv_pinky);
        //mv_pacman.addObserver((Observer) mv_llamita);

        reinicio= new JButton("REINICIAR");
        reinicio.addActionListener(this);
        auxiliar = new JFrame();
        auxiliar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        auxiliar.getContentPane().add(reinicio);
        auxiliar.pack();
        auxiliar.setSize(200, 200);
        
        auxiliar.add(reinicio);
        
        reinicio.setBounds(12*40, 16*40, 160, 30);
        
        
        //Inicializamos los hilos
        mv_clyde.start();
        mv_pinky.start();
        mv_blinky.start();
        mv_inky.start();
        mv_llamita.start();
        mv_pacman.run();
        
        
    }
    
    public boolean getEstadoNormal(){
        return estadoNormal;
    }
    
    public void ChangeEstadoNormal(){
        estadoNormal = !estadoNormal;
    }
    
    public void CuentaMorir(){//empieza a contar
        morir++;
        if(morir == 4) Terminar();
    }
    
    public void Terminar(){//Terminar los hilos de ejecución
        //v.Terminar(); //también dejamos de mostrar todos los elementos
        auxiliar.setVisible(true);
        mv_pacman.Comenzar(false);
        mv_llamita.Comenzar(false);
        mv_pinky.Comenzar(false);
        mv_blinky.Comenzar(false);
        mv_inky.Comenzar(false);
        mv_clyde.Comenzar(false);
        
        if(morir == 4) v.GANASTE.setVisible(true);
        else v.PERDISTE.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == reinicio){
            auxiliar.setVisible(false);
            v.dispose();
            //System.exit(WIDTH);
            try {
                this.inicializar();
            } catch (IOException ex) {
                Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void EstadoVulnerable() throws InterruptedException{
       mv_pacman.setEstado(new EstadoVulnerable(this));
       estadoNormal = false;
    }
    
    public void EstadoNormal() throws InterruptedException{
       mv_pacman.setEstado(new EstadoNormal(this)); 
       estadoNormal = true;
    }
    
    public int getTeclaActiva(){
        return v.getTeclaActiva();
    }
    
    public int getTeclaLlama(){
        return v.getTeclallama();
    }

    void setTeclaLlama(int e) {
        v.setTeclallama(e);
    }
}
