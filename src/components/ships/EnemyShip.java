package components.ships;

import components.projectile.Projectile;
import game_engine.MovementPattern;

public class EnemyShip extends Ship {

	
	
	MovementPattern howToMove;
	private int id;
	
	public EnemyShip(MovementPattern moveInstr, int id, String imagePath) {
		super(imagePath);
		howToMove = moveInstr;
		this.id = id;
		move();
	}
	
	public int getId() {
		return id;
	}
	public void setyloc(double yloc) {
		this.yloc = yloc;
	}
	
	public void setxloc(double xloc) {
		this.xloc = xloc;
	}
	@Override
	public void move() {
		howToMove.moveShip(this);
		
	}

	@Override
	public Projectile shoot() {
		
		int ranNum = (int) (Math.random() * 1000.0);
		if(ranNum < 8) {
			return new Projectile(false, 0.5, xloc, xloc);
		}
		else {
			return null;
		}
	}

	
	
}
