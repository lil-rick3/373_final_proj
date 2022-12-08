package components.ships;

import java.util.LinkedList;

import components.Entity;
import components.projectile.Projectile;
import components.ships.weapon.Doubleshot;
import components.ships.weapon.Singleshot;
import components.ships.weapon.Sprayshot;
import game_engine.Formations.MovementPattern;

/**
 * this class is for the enemy ships
 */
public class EnemyShip extends Ship {
	
	private MovementPattern howToMove;//this tells how the ships should move during each iteration of the game
	private int id; //every enemyship gets a unique ID
	private int health;
	private int damage;
	private double aggression;
	public EnemyShip(MovementPattern moveInstr, int id, String imagePath, int health, int weapontype, double aggression) {
		super(imagePath);
		projectileFilePath = "src/graphicImages/EnemyProjectile.png";
		isUp = false;
		howToMove = moveInstr;
		this.id = id;
		move();
		this.health = health;
		projVelocity = 1;
		damage = 1;
		weapon = new Singleshot(this, projVelocity, damage);
		this.aggression = aggression;
		chooseWeapon(weapontype);
	}
	
	private void chooseWeapon(int inp){
		
		switch(inp){
			case 0:
				weapon = new Singleshot(this, projVelocity, damage);
			break;
			case 1:
				weapon = new Doubleshot(this, projVelocity, damage);
			break;
			case 2:
				weapon = new Sprayshot(this, projVelocity, damage);
			break;
			default:
				weapon = new Singleshot(this, projVelocity, damage);




		}
	}
	/** 
	 * @return int
	 */
	public int getId() {
		return id;
	}
	
	@Override
	public void move() {
		howToMove.moveShip(this);
	}

	
	/** 
	 * @return LinkedList<Projectile>
	 * randomly determine if enemy should shoot a projectile downwards
	 */
	@Override
	public LinkedList<Projectile> shoot() {
		
		LinkedList<Projectile> projList = new LinkedList<>();
		double ranNum =  Math.random();
		if(ranNum < aggression) {
			projList = this.weapon.shoot(xloc,yloc, projectileFilePath);
		}
		return projList;
	}

	public void checkBoundsForRemoval(){
		//TODO: implement
	}
	
	/** 
	 * @param xloc
	 */
	public void setxloc(double xloc){
		this.xloc = xloc;
		
	}
	
	/** 
	 * @param yloc
	 */
	public void setyloc(double yloc){
		this.yloc = yloc;
		
	}
	
	/** 
	 * @return int
	 */
	public int getHealth() {
		return health;
	}

	
	/** 
	 * @param crashedInto
	 * if enemy collides with player projectile,
	 * lose health
	 */
	@Override
	protected void collisionAction(Entity crashedInto) {
		// TODO Auto-generated method stub
		if(crashedInto instanceof Projectile){

			Projectile hitProjectile = (Projectile) crashedInto;
			health -= hitProjectile.getDamage();
			if(health <= 0){
				toBeDestroyed = true;
			}
		}
	}

	
	/** 
	 * @return String
	 */
	@Override
    public String toString() {
        return "Enemy number" + id;
    }
	
	
}
