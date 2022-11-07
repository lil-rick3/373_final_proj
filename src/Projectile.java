
public class Projectile {
	boolean isUp;
	int speed;
	int xLoc;
	int yLoc;
	
	
	public Projectile(boolean isUp, int speed,int xLoc, int yLoc ) {
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
	public int getxLoc() {
		return xLoc;
	}
	public int getyLoc() {
		return yLoc;
	}
	
	
	
	
	
}
