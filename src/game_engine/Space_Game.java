package game_engine;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.ListIterator;

import components.Entity;
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
	public final static int gameHeight = 500;
	public final static int gameWidth = 600;
	public final static int playerHeight = 400;
	public boolean testFlag; //this is used to indicated whether we are running a test or not
	private boolean nukeFlag;

	
	Space_Gui curGraphics;
	PlayerShip player;
	LinkedList<EnemyShip> enemies;
	LinkedList<Projectile> playerProjectiles;
	LinkedList<Projectile> enemyProjectiles;
	LinkedList<Powerup> powerups;
	MovementPattern moveStuff;
	boolean currentlyModifying = true;//used to determine if a list is being added/removed to
	

	public Space_Game(Space_Gui curGraphics) {
		
		Entity.setSize(gameWidth, gameHeight);
		player = new PlayerShip((double)100,(double)100,"src/graphicImages/ship2.png", this);
		moveStuff = new MovementPattern();
		enemies = new LinkedList<EnemyShip>(); //do we not need to individually construct each of the enemyships?
		playerProjectiles = new LinkedList<Projectile>();
		enemyProjectiles = new LinkedList<Projectile>();
		powerups = new LinkedList<Powerup>();
		this.curGraphics = curGraphics;
		currentlyModifying = false;
		nukeFlag = false;
		testFlag = false;

				
	}
	
	public void runGame() {
		waitForTurn();
		currentlyModifying = true;
		LinkedList<Projectile> tempProjList = new LinkedList<>();
		Round round = new Round(null, this, moveStuff);
		currentlyModifying = false;
		while(true) {
			//TODO organize this function into smaller sub functions
			curGraphics.repaint();
			player.move();
			moveStuff.increment();
			waitForTurn();
			currentlyModifying = true;
			if(enemies.size() == 0){
				round = new Round(null, this, moveStuff);
			}
			tempProjList = player.shoot();
			if(!tempProjList.isEmpty()) {
				playerProjectiles.addAll(tempProjList);
				player.setWillShoot(false);
			}
			
			for (EnemyShip aEnemyShip: enemies) {
				aEnemyShip.move();
				tempProjList = aEnemyShip.shoot();
				if(!tempProjList.isEmpty()) {
					enemyProjectiles.addAll(tempProjList);
				}
			}
			currentlyModifying = false;
			detectCollisions();
			waitForTurn();
			currentlyModifying = true;
			if(nukeFlag) {
				NukeEnemies();
			}
			purgeComponents();
			currentlyModifying = false;
			moveProjectiles();
			movePowerups();
			try {
				Thread.sleep(5);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			if (this.testFlag) {
				//only want to run once if test
				return;
			}
		}
			
	}
	public void detectCollisions(){

		for(Projectile aProj: enemyProjectiles){
			Entity.CheckCollision(player, aProj);			
		}
		for(Projectile aProj: playerProjectiles){
			for(EnemyShip aEnemy: enemies){
				Entity.CheckCollision(aEnemy, aProj);
			}
		}
		for(Powerup aPowerup: powerups) {
			Entity.CheckCollision(player, aPowerup);
		}
	}
	/***
	 * all deletion of game entities should be done in here
	 */
	private void purgeComponents(){
		ListIterator<Projectile> projIterator = enemyProjectiles.listIterator();

		while(projIterator.hasNext()) {
			
			Projectile tempProj = projIterator.next();
			if(tempProj.getToBeDestroyed()) {
				projIterator.remove();			
			}
		}
		projIterator = playerProjectiles.listIterator();
		while(projIterator.hasNext()) {
			
			Projectile tempProj = projIterator.next();
			if(tempProj.getToBeDestroyed()) {
				projIterator.remove();			
			}
		}
		ListIterator<EnemyShip> enemyIterator = enemies.listIterator();
		while(enemyIterator.hasNext()){
			EnemyShip tempEnemy = enemyIterator.next();
			if(tempEnemy.getToBeDestroyed()){
				enemyIterator.remove();
				generatePowerup(tempEnemy.getxloc(), tempEnemy.getyloc());
			}
		}
		ListIterator<Powerup> powerupIterator = powerups.listIterator();
		while(powerupIterator.hasNext()) {
			Powerup tempPowerup = powerupIterator.next();
			if(tempPowerup.getToBeDestroyed()) {
				powerupIterator.remove();
			}
		}

	}


	private void generatePowerup(double xloc, double yloc) {
		int ranNum = (int) (Math.random() * 1000.0);

		if (ranNum <= 150) { //5% chance
			//generate healthup
			powerups.add(new HealthUp(xloc, yloc,"src/graphicImages/HealthUp.png"));
		}
		else if (ranNum > 500) { //5% chance
			//generate weapon upgrade
			powerups.add(new WeaponUp(xloc, yloc, "src/graphicImages/WeaponUp.png"));
		}
		else if (ranNum > 150 && ranNum <= 500) { //1% chance
			//generate nuke
			powerups.add(new Nuke(xloc, yloc, "src/graphicImages/Nuke.png"));
		}

	}

	public void NukeEnemies() {
		for (EnemyShip aEnemyShip: enemies) {
			aEnemyShip.setToBeDestroyed(true);
			
		}
		curGraphics.setNukeGraphics();
		nukeFlag = false;
	}

	private void movePowerups() {
		for(Powerup aPowerup: powerups) {
			aPowerup.move();
			aPowerup.checkBounds();
		}
		for(Powerup aPowerup: powerups) {
			aPowerup.move();
			aPowerup.checkBounds();
		}
	}
	private void moveProjectiles() {
		// TODO Auto-generated method stub
		for(Projectile aProj: playerProjectiles) {
			aProj.move();
			aProj.checkBounds();
		}
		for(Projectile aProj: enemyProjectiles) {
			aProj.move();
			aProj.checkBounds();
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
	private void waitForTurn(){
		while(curGraphics.getCurrentlyPainting()){
			try {
				Thread.sleep(0, 1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public PlayerShip getPlayerShip() {
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

	public LinkedList<Powerup> getPowerups() {
		return powerups;
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

	public void triggerNuke(){
		nukeFlag = true;
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
	public boolean getCurrentlyModifying(){
		return currentlyModifying;
	}
	
}
