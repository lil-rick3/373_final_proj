package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


import components.powerup.Powerup;
import components.projectile.Projectile;
import components.ships.EnemyShip;
import components.ships.PlayerShip;
import components.ships.Ship;
import game_engine.Space_Game;



public class Space_Gui extends JPanel implements KeyListener{
	
	

	
	Space_Game currentGame;
	boolean currentlyPainting = false;
	int nukeCounter;

	public Space_Gui(){
		nukeCounter = 0;
		this.currentGame = new Space_Game(this);
		
		//JFrame  graphic = new JFrame("space Game");
		
		addKeyListener(this);
		this.setFocusable(true);
		/*
		graphic.setSize(700,700);
		graphic.setLocation(100, 0);
						
		graphic.add(this);
		*/
		this.setSize(600,600);
		//graphic.setBackground(Color.RED);
		
		//repaint();
		//graphic.setVisible(true);
		
		
		
		
	}
	public void run() {
		currentGame.runGame();
	}
	
	public void paintComponent(Graphics g){	
		while(currentGame.getCurrentlyModifying()){
			try {
				Thread.sleep(0, 1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		currentlyPainting = true;	
		paintBackground(g);
		paintShip(g);
		paintProjectiles(g);
		paintPowerups(g);
		paintDetails(g);
		currentlyPainting = false;
		
	}
	
	public void setNukeGraphics(){
		nukeCounter = 1000;
	}
	private void paintBackground(Graphics g) {
		if(nukeCounter == 0){
			g.setColor(new Color(0,0,0));
			g.fillRect(0, 0, 600, 500);
			
		}
		else{
			g.setColor(new Color(nukeCounter/10,nukeCounter/10,
			nukeCounter/10));
			g.fillRect(0, 0, 600, 600);
			nukeCounter--;
		}
	}
	private void paintDetails(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(0,500, 600, 100);

		PlayerShip player = currentGame.getPlayerShip();

		g.setColor(Color.black);
		String statusStr = "Lives: " + player.getLives();
		g.drawString(statusStr, 0, 550);

	}
	private void paintShip(Graphics g) {
		
		PlayerShip currentShip = currentGame.getPlayerShip();
		
		currentShip.paintEntity(g, this);
		
		for(Ship aEnemyShip: currentGame.getEnemyShips()) {
		
			aEnemyShip.paintEntity(g, this);
		
		
		}
	}
	
	private void paintProjectiles(Graphics g) {
		LinkedList<Projectile> playerProjectiles = currentGame.getPlayerProjectiles();
		
		
		for(Projectile aProjectile: playerProjectiles) {
			aProjectile.paintEntity(g, this);
		}
		
		
		LinkedList<Projectile> enemyProjectiles = currentGame.getEnemyProjectiles();
		
		
		for(Projectile aProjectile: enemyProjectiles) {
			aProjectile.paintEntity(g, this);
		}
	}

	private void paintPowerups(Graphics g) {
		LinkedList<Powerup> powerups = currentGame.getPowerups();

		for(Powerup aPowerup: powerups) {
			aPowerup.paintEntity(g, this);
		}
	}

	public boolean getCurrentlyPainting(){
		return currentlyPainting;
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		currentGame.processKeyTyped(e);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		currentGame.processKeyPressed(e);
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		currentGame.processKeyReleased(e);
		
		
			
		
		
	}
	
	

}
