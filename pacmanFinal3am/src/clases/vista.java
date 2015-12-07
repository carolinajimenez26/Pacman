
package clases;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.*;

public class vista extends JFrame {
    
   public static Imagen pacman;  
   public static Imagen PERDISTE;
   public static Imagen GANASTE;
   public static Imagen cereza;  
   public static Imagen amarillito; 
   public static Imagen rojito; 
   public static Imagen azulito; 
   public static Imagen rosadito;
   public static Imagen llamita= new Imagen(); 
   public Imagen imagenPrincipal= new Imagen();

   private int teclaActiva = 39;//para que el carro comience moviéndose
   int teclaLlama=0;
   movimientoPacman mv_pacman;
   private Clyde mv_clyde;
   private Blinky mv_blinky;
   private Inky mv_inky;
   private Pinky mv_pinky;
   Llama mv_llamita;
   boolean empezar = false;
   tablero t = new tablero();
   private int contador=0;
   
   boolean estadoNormal = true;//así comienza por defecto
    
    public vista() throws IOException{
        
        KeyListener listener = new MyKeyListener(this);
        addKeyListener(listener);
        setFocusable(true);
        
        inicializa();
        
    }
    
    public void inicializa() throws IOException{
        
        setTitle("PACMAN");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        this.setResizable(false);
        this.setBackground(java.awt.Color.white);


        //cargamos las imagenes de los obstaculos del tablero
        imagenPrincipal.setImage("/imagenes/fondocompleto.png");
        
        pacman=new Imagen();
        pacman.setImage("/imagenes/carro.png");
        cereza = new Imagen();
        cereza.setImage("/imagenes/cereza.png");
        amarillito = new Imagen();
        amarillito.setImage("/imagenes/2.png");
        rosadito = new Imagen();
        rosadito.setImage("/imagenes/4.png");
        rojito = new Imagen();
        rojito.setImage("/imagenes/3.png");
        azulito = new Imagen();
        azulito.setImage("/imagenes/5.png");
        llamita.setImage("/imagenes/llama.png");
        PERDISTE=new Imagen();
        PERDISTE.setImage("/imagenes/perdiste.png");
        GANASTE= new Imagen();
        GANASTE.setImage("/imagenes/ganaste.png");
        
        imagenPrincipal.setLayout(null); 
        
        imagenPrincipal.add(pacman);
        imagenPrincipal.add(cereza);
        imagenPrincipal.add(amarillito);
        imagenPrincipal.add(rojito);
        imagenPrincipal.add(azulito);
        imagenPrincipal.add(rosadito);
        imagenPrincipal.add(llamita);
        imagenPrincipal.add(PERDISTE);
        imagenPrincipal.add(GANASTE);
        
        
        pacman.setBounds(1*40, 7*40, 40, 40); // x --> 1, y --> 7
        cereza.setBounds(27*40, 1*40, 40, 40);
        amarillito.setBounds( 24*40,1*40, 40, 40);// x --> 7, y --> 14
        azulito.setBounds( 15*40,7*40, 40, 40);
        rojito.setBounds( 13*40,6*40, 40, 40);
        rosadito.setBounds( 16*40,6*40, 40, 40);
        llamita.setBounds(80, 80, 40, 40);
        PERDISTE.setBounds(8*40, 6*40, 600, 300);
        GANASTE.setBounds(9*40, 6*40, 383, 228);
        
        llamita.setVisible(false);
        PERDISTE.setVisible(false);
        GANASTE.setVisible(false);
        //agregamos el panel principal a la ventana
        this.setContentPane(imagenPrincipal);
        
        JButton reinicio= new JButton("REINICIAR");
        
        add(reinicio);
        
        reinicio.setBounds(12*40, 16*40, 160, 30);
       
        this.setVisible(true);

        Comenzar();
        
        
}
    
    public void EstadoVulnerable() throws InterruptedException{
        
       mv_pacman.setEstado(new EstadoVulnerable(this));
       
    }
    
    public void EstadoNormal() throws InterruptedException{
       mv_pacman.setEstado(new EstadoNormal(this)); 
    }
    
    public boolean EsNormal(){
        
        return estadoNormal==true;
    }
    
    
    public void Comenzar() throws IOException{
        
        mv_pacman = new movimientoPacman(this,t);
        mv_clyde = new Clyde(this,t);
        mv_pinky = new Pinky(this,t);
        mv_inky = new Inky(this,t);
        mv_blinky = new Blinky(this,t);
        mv_llamita= new Llama(this,t);
        
        mv_pacman.addObserver(mv_clyde);//los fantasmas van a estar observando al pacman
        mv_pacman.addObserver(mv_blinky);
        mv_pacman.addObserver(mv_inky);
        mv_pacman.addObserver(mv_pinky);
       // mv_pacman.mostrar();//muestra el estado actual del Pacman
        
        mv_clyde.start();
        mv_pinky.start();
        mv_blinky.start();
        mv_inky.start();
        mv_llamita.start();
        mv_pacman.run();

        empezar=true;
        
    }
    
    public void Terminar(){
        
        mv_pacman.Comenzar(false);
        mv_llamita.Comenzar(false);
        mv_pinky.Comenzar(false);
        mv_blinky.Comenzar(false);
        mv_inky.Comenzar(false);
        mv_clyde.Comenzar(false);
        amarillito.setVisible(false);
        rojito.setVisible(false);
        azulito.setVisible(false);
        rosadito.setVisible(false);
        pacman.setVisible(false);
        
    }
    
    public void eliminarPacman(int X, int Y){
        
         if(pacman.getLocation().x==Y*40){
            
            if(pacman.getLocation().y==X*40){//perdiste
                
                pacman.setVisible(false);
                t.matriz[X][Y]=0;
                Terminar();
                PERDISTE.setVisible(true);
            }
                
        }
    }
    
    public void eliminar(int X, int Y){
        
         if(pacman.getLocation().x==Y*40){
            
            if(pacman.getLocation().y==X*40){//perdiste
                
                pacman.setVisible(false);
                t.matriz[X][Y]=0;
                Terminar();
                PERDISTE.setVisible(true);
            }
                
        }
        
        if(amarillito.getLocation().x==Y*40){
            
            if(amarillito.getLocation().y==X*40){
                
                amarillito.setVisible(false);
                t.matriz[X][Y]=0;
                mv_clyde.Comenzar(false);
                contador++;
                if(contador==4){
                    
                Terminar();
                GANASTE.setVisible(true);
                
               
                        
                }
            }
        }
        
        if(rosadito.getLocation().x==Y*40){
            
            if(rosadito.getLocation().y==X*40){
                
                rosadito.setVisible(false);
                t.matriz[X][Y]=0;
                mv_pinky.Comenzar(false);
                contador++;
                if(contador==4){
                    
                Terminar();
                
                GANASTE.setVisible(true);
                
                }
                        
            }
        }
        
        if(azulito.getLocation().x==Y*40){
            
            if(azulito.getLocation().y==X*40){
                
                
                azulito.setVisible(false);
                t.matriz[X][Y]=0;
                mv_inky.Comenzar(false);
                contador++;
                if(contador==4){
                    
                Terminar();
                GANASTE.setVisible(true);
                }
                        
            }
        }
        
        if(rojito.getLocation().x==Y*40){
                        
            if(rojito.getLocation().y==X*40){
                
                rojito.setVisible(false);
                t.matriz[X][Y]=0;
                mv_blinky.Comenzar(false);
                contador++;
                if(contador==4){
                    
                Terminar();
                GANASTE.setVisible(true);
                }
                        
            }
        }
        
       
    }
    
  
 
    

public static void main(String[] args) throws IOException {
     
        vista vis=new vista();

}


    
    public int getTeclaActiva(){
        
        return teclaActiva;
    }
    
     public int getTeclallama(){
        
        return teclaLlama;
    }
    
    
    public void setTeclaActiva(int tecla){
        
        teclaActiva=tecla;
    }
    
    public void setTeclallama(int tecla){
        
        teclaLlama=tecla;
    }
    
    
    public class MyKeyListener implements KeyListener {
        
        vista v;

        public MyKeyListener( vista v) {
            
            this.v=v;
        }
        
        
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
			 
            if(e.getExtendedKeyCode() == 37){

                v.setTeclaActiva(37);
            }

            if(e.getExtendedKeyCode() == 38){

                v.setTeclaActiva(38);
            }

            if(e.getExtendedKeyCode() == 39){

                v.setTeclaActiva(39);
            }

            if(e.getExtendedKeyCode() == 40){

                v.setTeclaActiva(40);
            }
            if(e.getExtendedKeyCode() == 32){

                v.setTeclallama(32);
            }
        
        }
        

     @Override
	public void keyReleased(KeyEvent e) {                
                
		}
	}

}
    
    