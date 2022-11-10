package game_engine;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

import components.PlayerShip;
import components.Projectile;
import components.Ship;
import graphics.Space_Gui;


public class Space_Game {
	public final static int gameHeight = 600;
	public final static int gameWidth = 600;
	Space_Gui curGraphics;
	PlayerShip player;
	LinkedList<Projectile> playerProjectiles;
	
	public Space_Game(Space_Gui curGraphics) {
		
		
		player = new PlayerShip((double)100,(double)100,"src/graphicImages/ship2.png");
		playerProjectiles = new LinkedList<Projectile>();
		this.curGraphics = curGraphics;
				
	}
	
	public void runGame() {
		
		Projectile tempProj;
		while(true) {
			
			curGraphics.repaint();
			player.move();
			
			tempProj = player.shoot();
			if(tempProj != null) {
				playerProjectiles.add(tempProj);
				player.setWillShoot(false);
			}
			
			moveProjectiles();
			try {
				Thread.sleep(5);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	
	
	
	private void moveProjectiles() {
		// TODO Auto-generated method stub
		for(Projectile aProj: playerProjectiles) {
			aProj.move();
		}
	}

	private void startMotion(char c) {
		if(c == 'd') {
			player.setRightOn(true);
		}
		else if(c == 'a') {
			player.setLeftOn(true);
		}
		else if(c == 'w') {
			player.setUpOn(true);
		}
		else if(c == 's') {
			player.setDownOn(true);
		}
	}

	public Ship getPlayerShip() {
		return player;
	}

	public LinkedList<Projectile> getPlayerProjectiles(){
		return playerProjectiles;
		
	}
	

	private void stopMotion(char c) {
		if(c == 'd') {
			player.setRightOn(false);
		}
		else if(c == 'a') {
			player.setLeftOn(false);
		}
		else if(c == 'w') {
			player.setUpOn(false);
		}
		else if(c == 's') {
			player.setDownOn(false);
		}
	}

	public void processKeyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void processKeyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		char c = e.getKeyChar();
		if((c == 'w') || (c == 'a') || (c == 's') || (c == 'd') || ( c == 'j'))
    		  startMotion(c);
		if(c == ' ') {
			if(!player.isShooting()) {
				player.setShooting(true);
				player.setWillShoot(true);
				
			}
		}
			
	}

	public void processKeyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		char c = e.getKeyChar();
		if((c == 'w') || (c == 'a') || (c == 's') || (c == 'd') || ( c == 'j'))
  		  	stopMotion(c);
		if(c == ' ') {
			player.setShooting(false);
		}
	}
	
	
}
