package Tests;
import java.util.LinkedList;

import components.ships.*;
import components.powerup.*;
import game_engine.MovementPattern;
import game_engine.Space_Game;
import graphics.Space_Gui;



public class PowerupTest {

    public static void main(String[] args) {
        Space_Gui aGui = new Space_Gui();
        Space_Game aGame = new Space_Game(aGui);
        aGame.testFlag = true;
        //test 1: enemy drops a power up
    
        //a movement pattern
        MovementPattern m = new MovementPattern();
    
    
        //create enemies
        EnemyShip e1 = new EnemyShip(m, 1, "src/graphicImages/enemyship1.png");
        EnemyShip e2 = new EnemyShip(m, 2, "src/graphicImages/enemyship1.png");
        EnemyShip e3 = new EnemyShip(m, 3, "src/graphicImages/enemyship1.png");
        EnemyShip e4 = new EnemyShip(m, 4, "src/graphicImages/enemyship1.png");

        //add enemies to game
        LinkedList<EnemyShip> listEnemies = aGame.getEnemyShips();
        listEnemies.add(e1);
        listEnemies.add(e2);
        listEnemies.add(e3);
        listEnemies.add(e4);
    
        //mark enemies to be destroyed
        e1.setToBeDestroyed(true);
        e2.setToBeDestroyed(true);
        e3.setToBeDestroyed(true);
        e4.setToBeDestroyed(true);

        aGame.runGame();

        LinkedList<Powerup> listPowerups = aGame.getPowerups();
        System.out.println("Powerups generated: ");
        for (Powerup aPowerup: listPowerups) {
            System.out.println(aPowerup.getClass().getName());
        }
    
        }

}