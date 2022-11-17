package components.projectile;

import components.Entity;
import components.ships.Ship;

//class for the projectiles that ships can shoot
public class Projectile extends Entity{
	//TODO: use velocity vector instead
	boolean isUp; //is projectile moving up
	boolean isDiag; //is projectile moving diagonal
	boolean isLeft; //is projectile coming from the left side of the ship
	double speed;
	private int damage;
	
	public Projectile(boolean isUp, boolean isDiag, boolean isLeft, double speed, double xLoc, double yLoc , String imagePath) {
		super(imagePath);
		this.isUp = isUp;
		this.isDiag = isDiag;
		this.isLeft = isLeft;
		this.speed = speed;
		this.xloc = xLoc;
		this.yloc = yLoc;
		damage = 1;
	}
	
	public void move() {
		if(isUp) {
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
		}
	}

	@Override
	//delete the projectile
	protected void collisionAction(Entity crashedInto) {
		if(crashedInto instanceof Ship){
			toBeDestroyed = true;
		}
		
	}

	public int getDamage(){
		return damage;
	}
	
	
	
	
	
}
