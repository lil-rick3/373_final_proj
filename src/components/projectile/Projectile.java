package components.projectile;

import components.Entity;
import components.ships.Ship;

/**
 * class for the projectiles that ships can shoot
 */
public class Projectile extends Entity{
	//TODO: use velocity vector instead
	double xVel;
	double yVel;
	
	double speed;
	private int damage;
	/**
	 * 
	 * @param xVel unit vector in x
	 * @param yVel unit vector in y
	 * @param xLoc
	 * @param yLoc
	 * @param imagePath
	 * 
	 */
	public Projectile(double xVel, double yVel, double xLoc, double yLoc , int damage, String imagePath) {
		
		super(imagePath);
		this.xVel = xVel;
		this.yVel = yVel;
		
		
		this.xloc = xLoc;
		this.yloc = yLoc;
		this.damage = damage;
	}
	
	public void move() {
		/*if(isUp) {
			if(isDiag) {
				if(isLeft) {
					this.yloc -= speed;
					this.xloc -= (speed/4);
				}
				else {
					this.yloc -= speed;
					this.xloc += (speed/4);
				}
			}
			else {
			this.yloc -= speed;
			}
		}
		else {
			if(isDiag) {
				if(isLeft) {
					this.yloc += speed;
					this.xloc -= speed;
				}
				else {
					this.yloc += speed;
					this.xloc += speed;
				}
			}
			else {
				this.yloc += speed;
			}
		}*/

		this.yloc += yVel;
		this.xloc += xVel;
	}

	
	/** 
	 * @param crashedInto
	 * delete the projectile
	 */
	@Override
	protected void collisionAction(Entity crashedInto) {
		if(crashedInto instanceof Ship){
			toBeDestroyed = true;
		}
		
	}

	public int getDamage(){
		return damage;
	}
	
	
	
	
	
}
