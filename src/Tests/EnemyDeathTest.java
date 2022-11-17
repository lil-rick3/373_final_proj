package Tests;

import java.util.LinkedList;

import components.projectile.Projectile;
import components.ships.EnemyShip;
import game_engine.MovementPattern;
import game_engine.Space_Game;
import graphics.Space_Gui;

public class EnemyDeathTest {
    public static void main(String[] args) {
        Space_Gui aGui = new Space_Gui();
        Space_Game aGame = new Space_Game(aGui);
        aGame.testFlag = true;
        //TEST: ENEMY DROPS A POWERUP

        System.out.println(" ");
        System.out.println("TEST 1: ENEMY DIES AFTER LOSING ALL HEALTH");
    
        //a movement pattern
        MovementPattern m = new MovementPattern();
    
    
        //create enemies
        EnemyShip e1 = new EnemyShip(m, 1, "src/graphicImages/enemyship1.png");

        //add enemies to game
        LinkedList<EnemyShip> listEnemies = aGame.getEnemyShips();
        listEnemies.add(e1);
        System.out.println(e1.getHealth());
        e1.setxloc(100);
        e1.setyloc(500);
        System.out.println(e1.getxloc());
        System.out.println(e1.getyloc());

        LinkedList<Projectile> listProjectiles = aGame.getPlayerProjectiles();
        listProjectiles.add(new Projectile(true, false, false, 1, 100, 500, "src/graphicImages/PlayerProjectile.png"));
        aGame.runGame();
        System.out.println(e1.getxloc());
        System.out.println(e1.getyloc());
        System.out.println(e1.getHealth());
        listProjectiles.add(new Projectile(true, false, false, 1, 100, 100, "src/graphicImages/PlayerProjectile.png"));
        aGame.runGame();
        System.out.println(e1.getxloc());
        System.out.println(e1.getyloc());
        System.out.println(e1.getHealth());
        listProjectiles.add(new Projectile(true, false, false, 1, 100, 100, "src/graphicImages/PlayerProjectile.png"));
        aGame.runGame();
        System.out.println(e1.getxloc());
        System.out.println(e1.getyloc());
        System.out.println(e1.getHealth());

    }
}
