package components.projectile;

import components.Entity;

public class Projectile extends Entity{
	boolean isUp;
	boolean isDiag;
	boolean isLeft;
	double speed;
	
	
	public Projectile(boolean isUp, boolean isDiag, boolean isLeft, double speed, double xLoc, double yLoc , String imagePath) {
		super(imagePath);
		this.isUp = isUp;
		this.isDiag = isDiag;
		this.isLeft = isLeft;
		this.speed = speed;
		this.xloc = xLoc;
		this.yloc = yLoc;
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
	
	
	
	
	
	
}
