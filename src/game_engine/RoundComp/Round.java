package game_engine;

import java.util.LinkedList;

import components.ships.EnemyShip;
import game_engine.Formations.Freestyle;
import game_engine.Formations.MovementPattern;
import game_engine.Formations.Rotational;
import game_engine.Formations.StandardFormation;

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
	
	public Round (String input, Space_Game theGame){
		LinkedList<EnemyShip> listEnemies = theGame.getEnemyShips();
		demMoves = new Freestyle();
		
		/*for(int i = 0; i < 3; i ++) {
			for(int j = 0; j < 10; j++) {
				listEnemies.add(new EnemyShip(demMoves, (10 * i) + j, "src/graphicImages/enemyship3.png"));
			}
		}	
		*/
		for(int i = 0; i < 30; i++){
			listEnemies.add(new EnemyShip(demMoves, i, "src/graphicImages/enemyship3.png"));
		}
		
	}
	public void increment(){
		demMoves.increment();
	}
	
	
	
	
}
