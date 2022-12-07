package Tests;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import Audio.AudioPlayer;
import components.projectile.Projectile;
import game_engine.Space_Game;
import graphics.Game;
import graphics.Space_Gui;

public class ProjectileTest {
    /**test if player projectile moves correctly
    *test if enemy projectile moves correctly
    */
    static AudioPlayer audio;

    public static void main(String[] args) {
        try
	    {
	    	System.out.println("test");
	        audio = new AudioPlayer();
			audio.pause();
	    } 
	    catch (Exception ex) 
	    {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
        }
		
        Game game = new Game();
        Space_Gui aGui = new Space_Gui(game, audio);
        Space_Game aGame = new Space_Game(aGui, game, audio);
        aGame.testFlag = true;
        //TEST1: CHECK IF ENEMY PROJECTILE MOVES 

        System.out.println(" ");
        System.out.println("TEST 1: CHECK FOR ENEMY PROJECTILE MOVEVEMENT");

        LinkedList<Projectile> elistProjectiles = aGame.getEnemyProjectiles();
        Projectile p1 = new Projectile(0, 1, 60, 30, 1, "src/graphicImages/EnemyProjectile.png");
        elistProjectiles.add(p1);

        System.out.println("Initial enemy projectile location: " + "x = " + p1.getxloc() + " y = " + p1.getyloc());
        assertEquals(60.0, p1.getxloc(), 0.0001);
        assertEquals(30.0, p1.getyloc(), 0.0001);

        aGame.runGame();

        System.out.println("After 1 iteration, enemy projectile location: " + "x = " + p1.getxloc() + " y = " + p1.getyloc());
        assertEquals(60.0, p1.getxloc(), 0.0001);
        assertEquals(31.0, p1.getyloc(), 0.0001);

        aGame.runGame();
        aGame.runGame();
        aGame.runGame();
        aGame.runGame();
        aGame.runGame();

        System.out.println("After 5 more iterations, enemy projectile location: " + "x = " + p1.getxloc() + " y = " + p1.getyloc());
        assertEquals(60.0, p1.getxloc(), 0.0001);
        assertEquals(36.0, p1.getyloc(), 0.0001);

        //TEST2: CHECK IF PLAYER PROJECTILE MOVES 

        System.out.println(" ");
        System.out.println("TEST 2: CHECK FOR PLAYER PROJECTILE MOVEVEMENT");

        LinkedList<Projectile> plistProjectiles = aGame.getPlayerProjectiles();
        Projectile p2 = new Projectile(0, -1, 100, 100, 1, "src/graphicImages/PlayerProjectile.png");
        plistProjectiles.add(p2);

        System.out.println("Initial player projectile location: " + "x = " + p2.getxloc() + " y = " + p2.getyloc());
        assertEquals(100.0, p2.getxloc(), 0.0001);
        assertEquals(100.0, p2.getyloc(), 0.0001);

        aGame.runGame();

        System.out.println("After 1 iteration, player projectile location: " + "x = " + p2.getxloc() + " y = " + p2.getyloc());
        assertEquals(100.0, p2.getxloc(), 0.0001);
        assertEquals(99.0, p2.getyloc(), 0.0001);

        aGame.runGame();
        aGame.runGame();
        aGame.runGame();
        aGame.runGame();
        aGame.runGame();

        System.out.println("After 5 more iterations, player projectile location: " + "x = " + p2.getxloc() + " y = " + p2.getyloc());
        assertEquals(100.0, p2.getxloc(), 0.0001);
        assertEquals(94.0, p2.getyloc(), 0.0001);

    }
    
}
