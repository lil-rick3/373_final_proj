package components.projectile;


public class Projectile {
	boolean isUp;
	boolean isDiag;
	boolean isLeft;
	double speed;
	double xLoc;
	double yLoc;
	
	
	public Projectile(boolean isUp, boolean isDiag, boolean isLeft, double speed, double xLoc, double yLoc ) {
		this.isUp = isUp;
		this.isDiag = isDiag;
		this.isLeft = isLeft;
		this.speed = speed;
		this.xLoc = xLoc;
		this.yLoc = yLoc;
	}
	
	public void move() {
		if(isUp) {
			if(isDiag) {
				if(isLeft) {
					this.yLoc -= speed;
					this.xLoc -= (speed/4);
				}
				else {
					this.yLoc -= speed;
					this.xLoc += (speed/4);
				}
			}
			else {
			this.yLoc -= speed;
			}
		}
		else {
			if(isDiag) {
				if(isLeft) {
					this.yLoc += speed;
					this.xLoc -= speed;
				}
				else {
					this.yLoc += speed;
					this.xLoc += speed;
				}
			}
			else {
				this.yLoc += speed;
			}
		}
	}
	public double getxLoc() {
		return xLoc;
	}
	public double getyLoc() {
		return yLoc;
	}
	
	
	
	
	
}
