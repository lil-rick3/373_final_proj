package game_engine;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.ListIterator;

import components.powerup.HealthUp;
import components.powerup.Nuke;
import components.powerup.Powerup;
import components.powerup.WeaponUp;
import components.projectile.Projectile;
import components.ships.EnemyShip;
import components.ships.PlayerShip;
import components.ships.Ship;
import graphics.Space_Gui;
import java.lang.Math;



/***
 * 
 * @author Jaret Rickel
 *
 *
 *This is the game that drives the whole thing
 */
public class Space_Game {
	public final static int gameHeight = 600;
	public final static int gameWidth = 600;
	public final static int playerHeight = 400;
	
	Space_Gui curGraphics;
	PlayerShip player;
	LinkedList<EnemyShip> enemies;
	LinkedList<Projectile> playerProjectiles;
	LinkedList<Projectile> enemyProjectiles;
	LinkedList<Powerup> powerups;
	MovementPattern moveStuff;
	
	
	public Space_Game(Space_Gui curGraphics) {
		
		
		player = new PlayerShip((double)100,(double)100,"src/graphicImages/ship2.png");
		moveStuff = new MovementPattern();
		enemies = new LinkedList<EnemyShip>(); //do we not need to individually construct each of the enemyships?
		playerProjectiles = new LinkedList<Projectile>();
		enemyProjectiles = new LinkedList<Projectile>();
		powerups = new LinkedList<Powerup>();
		this.curGraphics = curGraphics;
				
	}
	
	public void runGame() {
		
		Projectile tempProj;
		while(true) {
			//TODO organize this function into smaller sub functions
			curGraphics.repaint();
			player.move();
			detectPowerupCollision();
			detectProjectileCollision();
			moveStuff.increment();
			tempProj = player.shoot();
			if(tempProj != null) {
				playerProjectiles.add(tempProj);
				player.setWillShoot(false);
			}
			for (EnemyShip aEnemyShip: enemies) {
				aEnemyShip.move();
				tempProj = aEnemyShip.shoot();
				if(tempProj != null) {
					enemyProjectiles.add(tempProj);
				
				}
				detectProjectileCollision();
			}
			deleteProjectiles();
			moveProjectiles();
			deletePowerups();
			movePowerups();
			try {
				Thread.sleep(5);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void detectProjectileCollision() {
		double MIN_EQUAL_DIFF = 0.0001;

		//check for collision with player
		ListIterator<Projectile> enemyProjIterator = enemyProjectiles.listIterator();
		while(enemyProjIterator.hasNext()) {
			Projectile aEnemyProj = enemyProjIterator.next();
			if (Math.abs(aEnemyProj.getxLoc() - player.getXloc()) < MIN_EQUAL_DIFF  && Math.abs(aEnemyProj.getyLoc() - player.getYloc()) < MIN_EQUAL_DIFF) {
				//player and projectile collision
				enemyProjIterator.remove(); //delete the projectile
				//handle health
				handleHealth(true, 1);
			}
		}
		//check for collision with enemies
		ListIterator<EnemyShip> enemyShipIterator = enemies.listIterator();
		ListIterator<Projectile> playerProjIterator = playerProjectiles.listIterator();
		while (enemyShipIterator.hasNext()) {
			EnemyShip aEnemyShip = enemyShipIterator.next();
			while(playerProjIterator.hasNext()) {
				Projectile aPlayerProj = playerProjIterator.next();
				if (Math.abs(aPlayerProj.getxLoc() - aEnemyShip.getXloc()) < MIN_EQUAL_DIFF  && Math.abs(aPlayerProj.getyLoc() - aEnemyShip.getYloc()) < MIN_EQUAL_DIFF) {
					//enemy and projectile collision
					playerProjIterator.remove(); //delete the projectile
					//handle health
					if (handleHealth(1, aEnemyShip)) {
						//enemy is dead
						enemyShipIterator.remove();
						//TODO: randomly generate powerup 
						generatePowerup(aEnemyShip.getXloc(), aEnemyShip.getYloc());
					}
					
				}
			}
			aEnemyShip = enemyShipIterator.next();
		}
	}

	public void detectPowerupCollision() {
		double MIN_EQUAL_DIFF = 0.0001;

		//check for collision with player
		ListIterator<Powerup> powerIterator = powerups.listIterator();
		while(powerIterator.hasNext()) {
			Powerup aPowerup = powerIterator.next();
			if (Math.abs(aPowerup.getxLoc() - player.getXloc()) < MIN_EQUAL_DIFF  && Math.abs(aPowerup.getyLoc() - player.getYloc()) < MIN_EQUAL_DIFF) {
				//player and projectile collision
				if (aPowerup.getClass().getName() == "Nuke") {
					//kill all enemies
					ListIterator<EnemyShip> enemyShipIterator = enemies.listIterator();
					while (enemyShipIterator.hasNext()) {
						EnemyShip aEnemyShip = enemyShipIterator.next();
						enemyShipIterator.remove();
					}
				}
				else if (aPowerup.getClass().getName() == "WeaponUp") {
					//TODO: upgrade player weapon
				}
				else if (aPowerup.getClass().getName() == "HealthUp") {
					//restore 1 health
					handleHealth(false, 1);

				}
				powerIterator.remove(); //delete the projectile
			}
		}
	}


	private void generatePowerup(double xloc, double yloc) {
		int ranNum = (int) (Math.random() * 1000.0);

		if (ranNum <= 50) { //5% chance
			//generate healthup
			HealthUp aHealthUp = new HealthUp(xloc, yloc, null);
			powerups.add(aHealthUp);
		}
		else if (ranNum > 50 && ranNum <= 150) { //5% chance
			//generate weapon upgrade
			WeaponUp aWeaponUp = new WeaponUp(xloc, yloc, null);
			powerups.add(aWeaponUp);
		}
		else if (ranNum > 150 && ranNum <= 160) { //1% chance
			//generate nuke
			Nuke aNuke = new Nuke(xloc, yloc, null);
			powerups.add(aNuke);
		}

	}
	private void movePowerups() {
		for(Powerup aPowerup: powerups) {
			aPowerup.move();
		}
		for(Powerup aPowerup: powerups) {
			aPowerup.move();
		}
		detectPowerupCollision();
	}
	//handle health for enemy. Return true if enemy dies
	private boolean handleHealth(int damage, EnemyShip aEnemyShip) {

		aEnemyShip.setHealth(aEnemyShip.getHealth() - damage);
		if (aEnemyShip.getHealth() <= 0) {
			return true;
		}
		return false;
	}

	private void deletePowerups() {
		ListIterator<Powerup> powerIterator = powerups.listIterator();

		while(powerIterator.hasNext()) {
			
			Powerup tempPowerup = powerIterator.next();
			if(tempPowerup.getyLoc() < 10.0) {
				powerIterator.remove();			
			}
		}
		
	}

	//handle health for player
	private void handleHealth(boolean takeDamage, int healthChange) {
		//if takeDamage == false, ship should gain that amount of health
		if (takeDamage) {
			player.setHealth(player.getHealth() - healthChange);
		}
		else {
			player.setHealth(player.getHealth() + healthChange);
		}
		if (player.getHealth() <= 0) {
			//TODO: kill player
			
		}

	}
	private void moveProjectiles() {
		// TODO Auto-generated method stub
		for(Projectile aProj: playerProjectiles) {
			aProj.move();
		}
		for(Projectile aProj: enemyProjectiles) {
			aProj.move();
		}
		detectProjectileCollision(); //this may have to be called each time
	}
	/***
	 * this function will run through all the projectiles and delete the ones 
	 * that are out of bounds, that way we aren't storing memory for projectiles
	 * that are not on the map
	 * 
	 * 
	 * TODO delete the enemy projectiles as well
	 */
	private void deleteProjectiles() {		
		ListIterator<Projectile> projIterator = playerProjectiles.listIterator();
		

		
		while(projIterator.hasNext()) {
			
			Projectile tempProj = projIterator.next();
			if(tempProj.getyLoc() < 10.0) {
				projIterator.remove();			
			}
		}
		
		
		for(Projectile aProj: playerProjectiles) {
			if(aProj.getyLoc() < 10.0) {
				
			}
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
	public LinkedList<EnemyShip> getEnemyShips() {
		return enemies;
	}
	public LinkedList<Projectile> getPlayerProjectiles(){
		return playerProjectiles;
		
	}
	public LinkedList<Projectile> getEnemyProjectiles(){
		return enemyProjectiles;
		
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
				System.out.println(playerProjectiles.size());
				
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
