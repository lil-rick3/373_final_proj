package components.powerup;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import components.Entity;

public abstract class Powerup extends Entity{

    protected double speed;
    
    public Powerup(double xloc, double yloc, String imagePath) {

        super(imagePath);
        this.xloc = xloc;
        this.yloc = yloc;
        this.speed = 0.3;
    }

    public void move() {
		this.yloc += speed;
	}

    public double getyLoc() {
        return this.yloc;
    }
    public double getxLoc() {
        return this.xloc;
    }
    public double getSpeed() {
        return this.speed;
    }

    
   


}
