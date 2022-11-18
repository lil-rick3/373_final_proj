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
 this class can be thought of as the game engine
 */
public class Space_Game {


	public final static int gameHeight = 500;
	public final static int gameWidth = 600;
	public final static int playerHeight = 200;
	public boolean testFlag; //this is used to indicated whether we are running a test or not
	private boolean nukeFlag; //this is used to indicate whether all enemies should be deleted

	
	private Space_Gui curGraphics;
	private PlayerShip player;
	private LinkedList<EnemyShip> enemies;
	private LinkedList<Projectile> playerProjectiles;
	private LinkedList<Projectile> enemyProjectiles;
	private LinkedList<Powerup> powerups;
	private MovementPattern moveStuff; //used to determine the movement pattern of the enemy ships
	private boolean currentlyModifying = true;//used to determine if a list is being added/removed to
	private int score;	

	public Space_Game(Space_Gui curGraphics) {
		
		Entity.setSize(gameWidth, gameHeight); //set the dimensions for the game window
		player = new PlayerShip((double)100,(double)100,"src/graphicImages/ship2.png", this);
		moveStuff = new MovementPattern();
		enemies = new LinkedList<EnemyShip>();
		playerProjectiles = new LinkedList<Projectile>();
		enemyProjectiles = new LinkedList<Projectile>();
		powerups = new LinkedList<Powerup>();
		this.curGraphics = curGraphics;
		currentlyModifying = false;
		nukeFlag = false;
		testFlag = false;
		score = 0;
				
	}
	
	public void runGame() {
		waitForTurn();
		currentlyModifying = true;
		LinkedList<Projectile> tempProjList = new LinkedList<>();
		if (!testFlag) {
			Round round = new Round(null, this, moveStuff);
		}
		currentlyModifying = false;
		while(true) {
			//TODO organize this function into smaller sub functions
			curGraphics.repaint();
			//move player
			player.move();
			//move enemies
			moveStuff.increment();
			waitForTurn();
			currentlyModifying = true;
			if(enemies.size() == 0 && !testFlag){
				//start a new round
				Round round = new Round(null, this, moveStuff);
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
	
	/**
	 * detect collisions between all possible entities
	 */
	private void detectCollisions(){

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
	/**
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
				score+=100;
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


	/**
	 * @param xloc
	 * @param yloc
	 * generate a powerup upon enemy death
	 */
	private void generatePowerup(double xloc, double yloc) {
		int ranNum = (int) (Math.random() * 2000.0);

		if (!testFlag) {
			if (ranNum <= 100) { //5% chance
				//generate healthup
				powerups.add(new HealthUp(xloc, yloc,"src/graphicImages/HealthUp.png"));
			}
			else if (ranNum > 100 && ranNum <= 200) { //5% chance
				//generate weapon upgrade
				powerups.add(new WeaponUp(xloc, yloc, "src/graphicImages/WeaponUp.png"));
			}
			else if (ranNum > 200 && ranNum <= 250) { //1% chance
				//generate nuke
				powerups.add(new Nuke(xloc, yloc, "src/graphicImages/Nuke.png"));
			}
		}
		//FOR TESTING
		else {
			if (ranNum % 3 == 0) {
				//generate healthup
				powerups.add(new HealthUp(xloc, yloc,"src/graphicImages/HealthUp.png"));
			}
			else if (ranNum % 3 == 1) {
				//generate weapon upgrade
				powerups.add(new WeaponUp(xloc, yloc, "src/graphicImages/WeaponUp.png"));
			}
			else {
				//generate nuke
				powerups.add(new Nuke(xloc, yloc, "src/graphicImages/Nuke.png"));
			}
		}

	}
	
	/** 
	 * @return int
	 */
	public int getScore(){
		return score;
	}
	private void NukeEnemies() {
		for (EnemyShip aEnemyShip: enemies) {
			aEnemyShip.setToBeDestroyed(true);
			
		}
		curGraphics.setNukeGraphics();
		nukeFlag = false;
	}

	
	/** 
	 * @return boolean
	 */
	public boolean getNukeFlag() {
		return nukeFlag;
	}
	/**
	 * move powerups down the screen towards the user
	 */
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
	/**
	 * move player projectiles up the screen
	 * move enemy projectiles down the screen
	 */
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
	/**
	 * @param c
	 * for the motion of the player
	 */
	public void startMotion(char c) {
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
	/**this is to avoid multithreading issues. When graphics are being repainted,
	*doesn't allow modification to the entities
	*/
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
	
	/** 
	 * @return PlayerShip
	 */
	public PlayerShip getPlayerShip() {
		return player;
	}
	
	/** 
	 * @return LinkedList<EnemyShip>
	 */
	public LinkedList<EnemyShip> getEnemyShips() {
		return enemies;
	}
	
	/** 
	 * @return LinkedList<Projectile>
	 */
	public LinkedList<Projectile> getPlayerProjectiles(){
		return playerProjectiles;
		
	}
	
	/** 
	 * @return LinkedList<Projectile>
	 */
	public LinkedList<Projectile> getEnemyProjectiles(){
		return enemyProjectiles;
		
	}

	
	/** 
	 * @return LinkedList<Powerup>
	 */
	public LinkedList<Powerup> getPowerups() {
		return powerups;
	}

	/**
	 * @param c
	 * stop player motion
	 */
	public void stopMotion(char c) {
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
	
	/** 
	 * @param e
	 */
	public void processKeyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param e
	 * process any user input press
	 */
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

	/**
	 * @param e
	 * process any user input release
	 */
	public void processKeyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		char c = e.getKeyChar();
		if((c == 'w') || (c == 'a') || (c == 's') || (c == 'd') || ( c == 'j'))
  		  	stopMotion(c);
		if(c == ' ') {
			player.setShooting(false);
		}
	}
	
	/** 
	 * @return boolean
	 */
	public boolean getCurrentlyModifying(){
		return currentlyModifying;
	}
	
}
