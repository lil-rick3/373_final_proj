package components.ships;

import java.awt.image.BufferedImage;

import java.util.LinkedList;



import components.Entity;
import components.projectile.Projectile;
import components.ships.weapon.Weapon;

public abstract class Ship extends Entity{

	protected Weapon weapon;
	protected int health;
	protected String projectileFilePath;
	protected boolean isUp;
	protected int projVelocity;

	public Ship(String imagePath) {
		super(imagePath);
		
	}
	
	
	public abstract void move();
	public abstract LinkedList<Projectile> shoot();
	
	
	
	/** 
	 * @param ahealth
	 */
	public void setHealth(int ahealth) {
		this.health = ahealth;
	}
	
	/** 
	 * @return int
	 */
	public int getHealth() {
		return this.health;
	}
	public boolean getIsUp(){
		return isUp;
	}
}
