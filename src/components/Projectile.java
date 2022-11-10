package components;


public class Projectile {
	boolean isUp;
	double speed;
	double xLoc;
	double yLoc;
	
	
	public Projectile(boolean isUp, double speed,double xLoc, double yLoc ) {
		this.isUp = isUp;
		this.speed = speed;
		this.xLoc = xLoc;
		this.yLoc = yLoc;
	}
	
	public void move() {
		if(isUp) {
			yLoc -= speed;
		}
		else {
			yLoc += speed;
		}
	}
	public double getxLoc() {
		return xLoc;
	}
	public double getyLoc() {
		return yLoc;
	}
	
	
	
	
	
}