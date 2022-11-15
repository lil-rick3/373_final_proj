package components.ships;

import java.util.LinkedList;

import components.Entity;
import components.projectile.Projectile;
import game_engine.MovementPattern;

public class EnemyShip extends Ship {

	
	
	MovementPattern howToMove;
	private int id;
	
	public EnemyShip(MovementPattern moveInstr, int id, String imagePath) {
		super(imagePath);
		projectileFilePath = "src/graphicImages/EnemyProjectile.png";
		howToMove = moveInstr;
		this.id = id;
		move();
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public void move() {
		howToMove.moveShip(this);
		
		
	}

	@Override
	public LinkedList<Projectile> shoot() {
		LinkedList<Projectile> list = new LinkedList<>();
		
		int ranNum = (int) (Math.random() * 1000.0);
		if(ranNum < 5) {
			list.add(new Projectile(false, false, false, 1, xloc, yloc, projectileFilePath));
			//System.out.println(xloc + " " + yloc);
		}
		return list;
	}

	public void setxloc(double xloc){
		this.xloc = xloc;
		
	}
	public void setyloc(double yloc){
		this.yloc = yloc;
		
	}
	
}
