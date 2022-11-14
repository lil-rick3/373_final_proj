package game_engine;

import java.util.LinkedList;

import components.ships.EnemyShip;

/***
 * 
 * @author Jaret Rickel
 *
 *this class will build the ships and movement pattern, before it gets destroyed
 *might be unnecessary
 *
 */
public class Round {

	MovementPattern demMoves;
	
	public Round (String input, Space_Game theGame, MovementPattern moves){
		LinkedList<EnemyShip> listEnemies = theGame.getEnemyShips();
		demMoves = moves;
		
		for(int i = 0; i < 3; i ++) {
			for(int j = 0; j < 10; j++) {
				listEnemies.add(new EnemyShip(demMoves, (10 * i) + j, "src/graphicImages/enemyship1.png"));
			}
		}
		
		
		
	
		
		
		
	}
	
	
	
	
}
