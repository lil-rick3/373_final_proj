package game_engine.RoundComp;

import java.util.LinkedList;

import components.ships.EnemyShip;
import game_engine.Space_Game;
import game_engine.Formations.Freestyle;
import game_engine.Formations.MovementPattern;
import game_engine.Formations.Rotational;
import game_engine.Formations.StandardFormation;

/***
 * 
 * 
 *
 *this class creates a round, which builds the ships and adds the 
 formation that the ships will be in
 *
 */
public class Round {

	MovementPattern demMoves;
	String imageString;
	int numShips;
	int health;
	int weaponType;
	double aggression;
	public Round (RoundData roundInfo){
		//LinkedList<EnemyShip> listEnemies = theGame.getEnemyShips();
		switch(roundInfo.formation){
			case 0:
				demMoves = new StandardFormation();
				numShips = 30;
			break;
			case 1:
				demMoves = new Rotational();
				numShips = 24;
			break;
			case 2:
				demMoves = new Freestyle();
				numShips = 30;
				break;
			default:
				demMoves = new StandardFormation();
				numShips = 30;
		}
		imageString = "src/graphicImages/enemyship";
		imageString += String.valueOf(roundInfo.shiptype);
		imageString += ".png";

		health = roundInfo.health;
		
		weaponType = roundInfo.weapontype;
		this.aggression = roundInfo.aggression;
		/*for(int i = 0; i < 3; i ++) {
			for(int j = 0; j < 10; j++) {
				listEnemies.add(new EnemyShip(demMoves, (10 * i) + j, "src/graphicImages/enemyship3.png"));
			}
		}	
		*/
		/*for(int i = 0; i < 30; i++){
			listEnemies.add(new EnemyShip(demMoves, i, "src/graphicImages/enemyship3.png"));
		}
		*/
	}
	public LinkedList<EnemyShip> startRound(){
		LinkedList<EnemyShip> listEnemies = new LinkedList<EnemyShip>();
		for(int i = 0; i < numShips; i++){
			listEnemies.add(new EnemyShip(demMoves, i, imageString, health, weaponType, aggression));
		}
		return listEnemies;
	}
	public void increment(){
		demMoves.increment();
	}
	
	
	
	
}
