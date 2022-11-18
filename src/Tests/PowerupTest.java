package Tests;
import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import components.ships.*;
import components.powerup.*;
import game_engine.MovementPattern;
import game_engine.Space_Game;
import graphics.Space_Gui;
import org.junit.Assert.*;
import org.junit.Test;

public class PowerupTest {
    /**test for if enemy drops a powerup after death
    *test for nuke powerup action
    *test for healthup powerup action
    *test for weaponup powerup action
    */

    public static void main(String[] args) {
        Space_Gui aGui = new Space_Gui();
        Space_Game aGame = new Space_Game(aGui);
        aGame.testFlag = true;
        //TEST: ENEMY DROPS A POWERUP

        System.out.println(" ");
        System.out.println("TEST 1: ENEMY DROPS POWERUP AFTER DEATH");
    
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
        System.out.println("Powerups randomly generated: ");
        for (Powerup aPowerup: listPowerups) {
            System.out.println(aPowerup);
            aPowerup.setToBeDestroyed(true);
        }
        aGame.runGame();

        //TEST: PLAYER PICKS UP A NUKE
        System.out.println(" ");
        System.out.println("TEST 2: PLAYER PICKS UP A NUKE POWERUP");

        listPowerups.add(new Nuke(100, 200, "src/graphicImages/Nuke.png"));

        //create enemies
         e1 = new EnemyShip(m, 1,  "src/graphicImages/enemyship1.png");
         e2 = new EnemyShip(m, 2,  "src/graphicImages/enemyship1.png");
         e3 = new EnemyShip(m, 3,  "src/graphicImages/enemyship1.png");
         e4 = new EnemyShip(m, 4,  "src/graphicImages/enemyship1.png");

        //add enemies to game
        listEnemies.add(e1);
        listEnemies.add(e2);
        listEnemies.add(e3);
        listEnemies.add(e4);

        System.out.println("Enemies before nuke:");
        for (EnemyShip e:listEnemies) {
            System.out.println(e);
        }
        System.out.println("Is enemy list empty? :" + listEnemies.isEmpty());

        aGame.runGame();

        System.out.println("Enemies after nuke:");
        for (EnemyShip e:listEnemies) {
            System.out.println(e);
        }
        System.out.println("Is enemy list empty? :" + listEnemies.isEmpty());
        assertEquals(true, listEnemies.isEmpty());

        //TEST: PLAYER PICKS UP A HEALTHUP
        System.out.println(" ");
        System.out.println("TEST 3: PLAYER PICKS UP A HEALTHUP POWERUP");

        System.out.println("Player health: " + aGame.getPlayerShip().getHealth());
        listPowerups.add(new HealthUp(100, 200, "src/graphicImages/HealthUp.png"));
        aGame.runGame();
        System.out.println("After powerup, player health: " + aGame.getPlayerShip().getHealth());

        //TEST: PLAYER PICKS UP A WEAPON UP
        System.out.println(" ");
        System.out.println("TEST 4: PLAYER PICKS UP A WEAPONUP POWERUP");

        System.out.println("Player weapon: " + aGame.getPlayerShip().getWeapon());
        listPowerups.add(new WeaponUp(100, 200, "src/graphicImages/WeaponUp.png"));
        aGame.runGame();
        System.out.println("After 1 upgrade, player weapon: " + aGame.getPlayerShip().getWeapon());
        listPowerups.add(new WeaponUp(100, 200, "src/graphicImages/WeaponUp.png"));
        aGame.runGame();
        System.out.println("After 2 upgrades, player weapon: " + aGame.getPlayerShip().getWeapon());
        }
}