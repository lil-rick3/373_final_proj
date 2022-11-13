package components.powerup;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Powerup {

    protected double xLoc;
	protected double yLoc;
	protected int width;
	protected int height;
    protected BufferedImage projection;

    //TODO: graphics for powerups
    public Powerup(double xloc, double yloc, String imagePath) {

        try {
            projection = ImageIO.read(new File(imagePath));
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.xLoc = 0.0;
        this.yLoc = 0.0;
        this.width = 0;
        this.height = 0;
    }

    public void move() {
		this.yLoc += 0.1;
	}

    public double getyLoc() {
        return this.yLoc;
    }
    public double getxLoc() {
        return this.xLoc;
    }

   


}
