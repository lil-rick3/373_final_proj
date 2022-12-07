package Tests;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import components.projectile.Projectile;
import components.ships.EnemyShip;
import game_engine.Space_Game;
import game_engine.Formations.MovementPattern;
import game_engine.Formations.StandardFormation;
import graphics.Space_Gui;

public class EnemyDeathTest {
    /**test for if enemy dies after losing all health
    */
    public static void main(String[] args) {
        Space_Gui aGui = new Space_Gui();
        Space_Game aGame = new Space_Game(aGui);
        aGame.testFlag = true;
        //TEST: ENEMY DIES AFTER LOSING ALL HEALTH

        System.out.println(" ");
        System.out.println("TEST 1: ENEMY DIES AFTER LOSING ALL HEALTH");
    
        //a movement pattern
        MovementPattern m = new StandardFormation();
    
    
        //create enemies
        EnemyShip e1 = new EnemyShip(m, 1, "src/graphicImages/enemyship1.png",3, 2);

        //add enemies to game
        LinkedList<EnemyShip> listEnemies = aGame.getEnemyShips();
        listEnemies.add(e1);
        e1.setxloc(60);
        e1.setyloc(30);
        
        System.out.println("Beginning enemy health: " + e1.getHealth());

        LinkedList<Projectile> listProjectiles = aGame.getPlayerProjectiles();
        listProjectiles.add(new Projectile(0, -1, 60, -170, 1, "src/graphicImages/PlayerProjectile.png"));

        aGame.runGame();

        System.out.println("After projectile collision, enemy health: " + e1.getHealth());

        listProjectiles.add(new Projectile(0, -1, 60, -170, 1, "src/graphicImages/PlayerProjectile.png"));

        aGame.runGame();

        System.out.println("After 2 projectile collisions, enemy health: " + e1.getHealth());

        listProjectiles.add(new Projectile(0, -1, 60, -170, 1, "src/graphicImages/PlayerProjectile.png"));

        aGame.runGame();

        System.out.println("After 3 projectile collisions, enemy health: " + e1.getHealth());

        assertEquals(true, listEnemies.isEmpty());
        assertEquals(true, listProjectiles.isEmpty());

        System.out.println("Is enemy list empty? :" + listEnemies.isEmpty());
        System.out.println("Is projectile list empty? :" + listProjectiles.isEmpty());


    }
}
