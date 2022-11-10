package game_engine;

import components.ships.EnemyShip;


/***
 * 
 * @author Jaret Rickel
 *
 *The movement pattern is a class used to dictate how ships should move. 
 *Each ship is given a movement pattern which they ca then follow
 */
public class MovementPattern {
	int phaseInCycle;
	
	
	public MovementPattern(){
		phaseInCycle = 0;
	}
	/***
	 * 
	 * @param aShip the ship that you want to move
	 */
	public void moveShip(EnemyShip aShip) {
		aShip.setxloc(30 + phaseInCycle/2);
		aShip.setyloc(30 + phaseInCycle/2);
	}
	/***
	 * increment is used to create a pattern for the ships
	 */
	public void increment() {
		phaseInCycle++;
		if(phaseInCycle > 160) {
			phaseInCycle = 0;
		}
	}
	
	
	
	
	
	
}
