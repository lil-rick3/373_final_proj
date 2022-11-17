package Tests;

import game_engine.Space_Game;
import graphics.Space_Gui;

import static org.junit.Assert.assertEquals;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import components.projectile.Projectile;

public class PlayerTest {
    //test for player shoot
    //test for player movement
    //test for player losing health
     public static void main(String[] args) {
        Space_Gui aGui = new Space_Gui();
        Space_Game aGame = new Space_Game(aGui);
        aGame.testFlag = true;
        
        //TEST1: PLAYER SHOOTS
        System.out.println(" ");
        System.out.println("TEST 1: PLAYER SHOOTS");
        LinkedList<Projectile> listProjectiles = aGame.getPlayerProjectiles();
        System.out.println("Is the list of player projectiles empty?: " + listProjectiles.isEmpty());
        aGame.getPlayerShip().setWillShoot(true);
        assertEquals(true, listProjectiles.isEmpty());

        System.out.println("Player shoots");
        aGame.runGame();

        System.out.println("Is the list of player projectiles empty?: " + listProjectiles.isEmpty());
        assertEquals(false, listProjectiles.isEmpty());

        //TEST2: PLAYER MOVES
        System.out.println(" ");
        System.out.println("TEST 2: PLAYER MOVES");
        System.out.println("Current player position: " + "x = " + aGame.getPlayerShip().getxloc() + ", y = " + aGame.getPlayerShip().getyloc());
        assertEquals(100.0, aGame.getPlayerShip().getxloc(), 0.0001);
        assertEquals(400.0, aGame.getPlayerShip().getyloc(), 0.0001);

        System.out.println("Move down");
        aGame.startMotion('s');
        aGame.runGame();

        System.out.println("Current player position: " + "x = " + aGame.getPlayerShip().getxloc() + ", y = " + aGame.getPlayerShip().getyloc());
        assertEquals(100.0, aGame.getPlayerShip().getxloc(), 0.0001);
        assertEquals(401.0, aGame.getPlayerShip().getyloc(), 0.0001);

        aGame.stopMotion('s');
        aGame.runGame();

        System.out.println("Move right");
        aGame.startMotion('d');
        aGame.runGame();

        System.out.println("Current player position: " + "x = " + aGame.getPlayerShip().getxloc() + ", y = " + aGame.getPlayerShip().getyloc());
        assertEquals(101.0, aGame.getPlayerShip().getxloc(), 0.0001);
        assertEquals(401.0, aGame.getPlayerShip().getyloc(), 0.0001);

        aGame.stopMotion('d');
        aGame.runGame();

        System.out.println("Move left");
        aGame.startMotion('a');
        aGame.runGame();

        System.out.println("Current player position: " + "x = " + aGame.getPlayerShip().getxloc() + ", y = " + aGame.getPlayerShip().getyloc());
        assertEquals(100.0, aGame.getPlayerShip().getxloc(), 0.0001);
        assertEquals(401.0, aGame.getPlayerShip().getyloc(), 0.0001);

        aGame.stopMotion('a');
        aGame.runGame();

        System.out.println("Move up");
        aGame.startMotion('w');
        aGame.runGame();

        System.out.println("Current player position: " + "x = " + aGame.getPlayerShip().getxloc() + ", y = " + aGame.getPlayerShip().getyloc());
        assertEquals(100.0, aGame.getPlayerShip().getxloc(), 0.0001);
        assertEquals(400.0, aGame.getPlayerShip().getyloc(), 0.0001);

        aGame.stopMotion('w');
        aGame.runGame();

        //TEST2: PLAYER TAKES DAMAGE
        System.out.println(" ");
        System.out.println("TEST 3: PLAYER TAKES DAMAGE");

        LinkedList<Projectile> elistProjectiles = aGame.getEnemyProjectiles();
        elistProjectiles.add(new Projectile(false, false, false, 1, 100, 400, "src/graphicImages/EnemyProjectile.png"));

        System.out.println("Current player health: " + aGame.getPlayerShip().getHealth());
        assertEquals(3, aGame.getPlayerShip().getHealth());

        aGame.runGame();

        System.out.println("Player health after collision with enemy projectile: " + aGame.getPlayerShip().getHealth());
        assertEquals(2, aGame.getPlayerShip().getHealth());






        
     }
    
}
