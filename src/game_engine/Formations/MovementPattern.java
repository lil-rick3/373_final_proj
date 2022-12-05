package game_engine.Formations;

import components.ships.EnemyShip;


/***
 * 
 * @author Jaret Rickel
 *
 *The movement pattern is a class used to dictate how ships should move. 
 *Each ship is given a movement pattern which they ca then follow
 */
public abstract class MovementPattern {
	
	protected int yOffSet;
	protected int xOffSet;
	
	protected boolean initial;
	protected int initialOffset;
	public MovementPattern(){
		initial = true;
		
	}
	/***
	 * 
	 * @param aShip the ship that you want to move
	 */
	public abstract void moveShip(EnemyShip aShip);
	/***
	 * increment is used to create a pattern for the ships
	 */
	
	public abstract void increment();
	
	
	
	
	
	
}