package components;

import javax.swing.JPanel;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/***
 * this is the explosion that occurs when an enemy ship gets
 * destroyed, or a projectile hits the friendly ship
 */
public class Explosion extends Entity{
    
    protected static BufferedImage image;
    int duration;
    public Explosion(double xLoc, double yLoc) {
        
        super(image);
        duration = 20;
        
        //TODO Auto-generated constructor stub

        this.xloc = xLoc;
        this.yloc = yLoc;
        
        

    }
    public static void setImageForAll() throws IOException{
        image = ImageIO.read(new File("src/graphicImages/explosion.png"));
    }

   
    @Override
    protected void collisionAction(Entity crashedInto) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        duration --;
        if(duration <= 0){
            toBeDestroyed = true;
        }	
        
    }




    
}