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
	int yOffSet;
	int xOffSet;
	boolean movingDown;
	
	public MovementPattern(){
		phaseInCycle = 0;
		yOffSet = 30;
		xOffSet = 30;
		movingDown = true;
		
	}
	/***
	 * 
	 * @param aShip the ship that you want to move
	 */
	public void moveShip(EnemyShip aShip) {
		int id = aShip.getId();
		aShip.setxloc((30 + phaseInCycle/2) + (id%10)*xOffSet);
		aShip.setyloc((30 + phaseInCycle/2) + (id/10)*yOffSet);
		
	}
	/***
	 * increment is used to create a pattern for the ships
	 */
	public void increment() {
		if(movingDown && phaseInCycle <= 160) {
			phaseInCycle++;
		}
		else if (movingDown && phaseInCycle > 160) {
			movingDown = false;
		}
		else if (!movingDown && phaseInCycle >= 0) {
			phaseInCycle--;
		}
		else if (!movingDown && phaseInCycle < 0) {
			movingDown = true;
		}
	}
	
	
	
	
	
	
}