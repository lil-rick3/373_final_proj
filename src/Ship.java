
public abstract class Ship {

	protected double xloc;
	protected double yloc;
	
	
	
	public abstract void move();
	public abstract Projectile shoot();
	
	public double getYloc() {
		return yloc;
	}
	public double getXloc() {
		return xloc;
	}
}
