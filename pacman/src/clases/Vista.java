
package clases;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *Esta clase se encarga de cargar todos los elementos como paneles, botones, etc 
 * al JFrame
 * El usuario estará interactuando constantemente con la Vista, y cada que se realice un cambio, 
 * esta clase se comunicará con la clase control, que se encargará de hacer los cambios pertinentes
 */
public class Vista extends JFrame{
    //se debe crear el JFrame desde acá y sus componentes básicos
    //Debe tener métodos que permitan la creación de una imagen en cierta parte de la pantalla
    //Así como una que la elimine de aquí
    //En esta clase se crean todas las imagenes que se cargarán en el juego (Las tipo Imagen)
    
    public static Imagen pacman;  
    public static Imagen PERDISTE;
    public static Imagen GANASTE;
    public static Imagen cereza;  
    public static Imagen amarillito; 
    public static Imagen rojito; 
    public static Imagen azulito; 
    public static Imagen rosadito;
    public static Imagen llamita;
    public Imagen imagenPrincipal;
    
    private int teclaActiva = 39;//para que el carro inicie moviéndose
    private int teclaLlama = 0;

    public Vista(){
        
        KeyListener listener = new Vista.MyKeyListener(this);
        addKeyListener(listener);
        setFocusable(true);
        
        imagenPrincipal = new Imagen();
        
        setTitle("PACMAN");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 800);
        this.setResizable(false);
        this.setBackground(java.awt.Color.white);


        //cargamos las imagenes de los obstaculos del tablero
        imagenPrincipal.setImage("/imagenes/fondocompleto.png");
        
        pacman = new Imagen();
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
        llamita = new Imagen();
        llamita.setImage("/imagenes/llama.png");
        PERDISTE = new Imagen();
        PERDISTE.setImage("/imagenes/perdiste.png");
        GANASTE = new Imagen();
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

    }
    
    public void setImagenVulnerable(){//Este es únicamente para el pacman
        pacman.setImage("/imagenes/pacman2_right.gif");//actualiza la imagen del pacman
        cereza.setVisible(false);//quitamos la cereza del tablero de juego
    }
    
    public void setImagenNormal(){//Este es únicamente para el pacman
        pacman.setImage("/imagenes/carro.png");//actualiza la imagen del pacman
    }
   
    public void moveLeft(){//Actualiza la imagen del objeto si es a la izquierda
        pacman.setImage("/imagenes/carro_left.png");
    }
    
    public void moveRight(String o){//Actualiza la imagen del objeto si es a la derecha
        pacman.setImage("/imagenes/carro_right.png");
    }
    
    public void moveDown(String o){//Actualiza la imagen del objeto si es abajo
        pacman.setImage("/imagenes/carro_down.png");
    }
    
    public void moveUp(String o){//Actualiza la imagen del objeto si es arriba
        pacman.setImage("/imagenes/carro_up.png");
    }
    
    public void Terminar(){//Terminar significa poner invisible todos los elementos que habían en el juego
        
        amarillito.setVisible(false);
        rojito.setVisible(false);
        azulito.setVisible(false);
        rosadito.setVisible(false);
        pacman.setVisible(false);
    }
   
    
    public void eliminar(int X, int Y){//Elimina un elemento en estas posiciones
        
        if(pacman.getLocation().x==Y*40 && pacman.getLocation().y==X*40){
                pacman.setVisible(false);
        }
           
        if(amarillito.getLocation().x==Y*40 && amarillito.getLocation().y==X*40){
                amarillito.setVisible(false);
        }
        
        if(rosadito.getLocation().x==Y*40 && rosadito.getLocation().y==X*40){
            rosadito.setVisible(false);    
        }
        
        if(azulito.getLocation().x==Y*40 && azulito.getLocation().y==X*40){
            azulito.setVisible(false);
        }
        
        if(rojito.getLocation().x==Y*40 && rojito.getLocation().y==X*40){
            rojito.setVisible(false);
        }
    }
    
    public int getTeclaActiva(){
        
        return teclaActiva;
    }
    
     public int getTeclallama(){
        
        return teclaLlama;
    }
    
    
    public void setTeclaActiva(int tecla){
        
        teclaActiva = tecla;
    }
    
    public void setTeclallama(int tecla){
        
        teclaLlama = tecla;
    }
    
    public class MyKeyListener implements KeyListener {
        //Cada que se haga una excitación a esta clase, se llama al KeyListener
        Vista v;

        public MyKeyListener(Vista v) {
            
            this.v = v;
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
