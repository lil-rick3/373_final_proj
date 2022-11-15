package graphics;

import java.awt.Color;
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

import Audio.SimpleAudioPlayer;
import components.projectile.Projectile;
import components.ships.EnemyShip;
import components.ships.Ship;
import game_engine.Space_Game;



public class Space_Gui extends JPanel implements KeyListener{
	
	
static final int PIXEL_SIZE = 10;
	
	Space_Game currentGame;
	boolean isFirstMove = true;
	
	public Space_Gui(){
		
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
		
		repaint();
		//graphic.setVisible(true);
		
		
		
		
	}
	public void run() {
		currentGame.runGame();
	}
	
	public void paintComponent(Graphics g){		
		paintBackground(g);
		paintShip(g);
		paintProjectiles(g);
		
	}
	
	
	private void paintBackground(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 600, 600);
	}
	private void paintShip(Graphics g) {
		
		Ship currentShip = currentGame.getPlayerShip();
		
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
