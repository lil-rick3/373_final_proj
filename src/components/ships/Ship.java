package components.ships;

import java.awt.image.BufferedImage;

import java.util.LinkedList;



import components.Entity;
import components.projectile.Projectile;

public abstract class Ship extends Entity{

	
	protected int health;
	String projectileFilePath;
	
	
	public Ship(String imagePath) {
		super(imagePath);
		this.xloc = 0.0;
		this.yloc = 0.0;
		this.width = 0;
		this.height = 0;
		this.health = 3;
	}
	
	
	public abstract void move();
	public abstract LinkedList<Projectile> shoot();
	
	
	
	public void setHealth(int ahealth) {
		this.health = ahealth;
	}
	public int getHealth() {
		return this.health;
	}
}
