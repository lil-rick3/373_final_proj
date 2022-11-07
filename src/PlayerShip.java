
public class PlayerShip extends Ship{
	
	
	
	
	boolean upOn;
	boolean downOn;
	boolean rightOn;
	boolean leftOn;
	boolean isShooting;
	boolean shootOnLeft;
	
	
	public PlayerShip() {
		xloc = 20;
		yloc = 20;
		upOn = false;
		downOn = false;
		rightOn = false;
		leftOn = false;
		isShooting = false;
		shootOnLeft = true;
		
	}
	

	public boolean isShooting() {
		return isShooting;
	}


	public void setShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}


	public void move() {
		if(rightOn) {
			xloc++;
		}
		if(leftOn) {
			xloc --;
		}
		if(downOn) {
			yloc ++;
		}
		if(upOn) {
			yloc --;
		}
	}
	
	public Projectile shoot() {
		if(shootOnLeft) {
			shootOnLeft = !shootOnLeft;
			return new Projectile(true, 1, xloc, yloc);
			
		}
		else {
			shootOnLeft = !shootOnLeft;
			return new Projectile(true, 1, xloc + 18, yloc);
		}
		
	}
	


	


	public void setUpOn(boolean upOn) {
		this.upOn = upOn;
	}


	public void setDownOn(boolean downOn) {
		this.downOn = downOn;
	}


	public void setRightOn(boolean rightOn) {
		this.rightOn = rightOn;
	}


	public void setLeftOn(boolean leftOn) {
		this.leftOn = leftOn;
	}


	public void startMotion(char c) {
		if(c == 'd') {
			rightOn = true;
		}
		else if(c == 'a') {
			leftOn = true;
		}
		else if(c == 'w') {
			upOn = true;
		}
		else if(c == 's') {
			downOn = true;
		}
	}

	public void stopMotion(char c) {
		if(c == 'd') {
			rightOn = false;
		}
		else if(c == 'a') {
			leftOn = false;
		}
		else if(c == 'w') {
			upOn = false;
		}
		else if(c == 's') {
			downOn = false;
		}
	}
}
