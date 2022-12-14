package components.powerup;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import components.Entity;
import components.ships.PlayerShip;
import components.ships.Ship;

/**
 * Gives a bonus effect to player. Is potentially dropped by enemies upon death
 */
public abstract class Powerup extends Entity{

    protected double speed;

    public Powerup(double xloc, double yloc, String imagePath) {

        super(imagePath);
        this.xloc = xloc;
        this.yloc = yloc;
        this.speed = 0.6;
    }


    protected void collisionAction(Entity crashedInto) {
		if(crashedInto instanceof PlayerShip){
			toBeDestroyed = true;
		}
    }


    /**
    *Move the powerup
    */
    public void move() {
		this.yloc += speed;
	}

    
    /** 
     * @return double
     */
    public double getyLoc() {
        return this.yloc;
    }
    
    /** 
     * @return double
     */
    public double getxLoc() {
        return this.xloc;
    }
    
    /** 
     * @return double
     */
    public double getSpeed() {
        return this.speed;
    }

    
   


}
