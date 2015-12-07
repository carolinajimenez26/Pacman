
package clases;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

    
    public class Imagen extends JPanel {
 
       private Image img=null;
       private Icon icon;

       public Imagen() {
                   
       }
       
        @Override
       protected void paintComponent(Graphics g) {
        Graphics2D g2 =(Graphics2D) g;
        
        g2.drawImage(img, 0, 0, img.getWidth(this), img.getHeight(this), null);
  
       }
        
       public void setImage(String fileName){
        setIcon(new ImageIcon(getClass().getResource(fileName))); 
        
        

    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon){
        this.icon=icon;
        if(icon!=null)
            img=((ImageIcon)icon).getImage();
    }
      
       
    } 