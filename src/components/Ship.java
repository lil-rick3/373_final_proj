package components;

import java.awt.image.BufferedImage;

public abstract class Ship {

	protected double xloc;
	protected double yloc;
	protected int width;
	protected int height;
	protected BufferedImage projection;
	
	
	
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
