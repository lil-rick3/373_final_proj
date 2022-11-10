package components.ships;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import components.projectile.Projectile;

public abstract class Ship {

	protected double xloc;
	protected double yloc;
	protected int width;
	protected int height;
	protected BufferedImage projection;
	
	
	public Ship(String imagePath) {
		try {
			projection = ImageIO.read(new File(imagePath));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public abstract void move();
	public abstract Projectile shoot();
	
	public double getYloc() {
		return yloc;
	}
	public double getXloc() {
		return xloc;
	}
	public BufferedImage getImage() {
		return projection;
	}
}
