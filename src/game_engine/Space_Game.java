package game_engine;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Audio.AudioPlayer;
import components.Entity;
import components.Explosion;
import components.powerup.DamageUp;
import components.powerup.HealthUp;
import components.powerup.Nuke;
import components.powerup.Powerup;
import components.powerup.Slow;
import components.powerup.WeaponUp;
import components.projectile.Projectile;
import components.ships.EnemyShip;
import components.ships.PlayerShip;
import components.ships.Ship;
import components.star.Star;
import game_engine.RoundComp.Round;
import game_engine.RoundComp.RoundDriver;
import graphics.Game;
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
	public final static int playerHeight = 250;
	public static int finalScore;
	public boolean testFlag; //this is used to indicated whether we are running a test or not
	private boolean nukeFlag; //this is used to indicate whether all enemies should be deleted

	
	private Space_Gui curGraphics;
	private PlayerShip player;
	private LinkedList<EnemyShip> enemies;
	private LinkedList<Projectile> playerProjectiles;
	private LinkedList<Projectile> enemyProjectiles;
	private LinkedList<Powerup> powerups;
	private LinkedList<Star> stars;
	private LinkedList<Explosion> explosions;
	//private MovementPattern moveStuff; //used to determine the movement pattern of the enemy ships
	private boolean currentlyModifying = true;//used to determine if a list is being added/removed to
	private int score;	
	private double starSpawnRate;
	private int slowCounter;
	private int timeDelay = 6;
	private Round curRound;
	private RoundDriver allRounds;
	private boolean isPaused;
	private boolean winFlag;
	private boolean loseFlag;
	private long lastFrame;
	private long currentFrame;

	private AudioPlayer audio;



	private Game controller;


	public Space_Game(Space_Gui curGraphics, Game controllerIn, AudioPlayer aIn) {
		this.audio = aIn;
		this.controller = controllerIn;
		Entity.setSize(gameWidth, gameHeight); //set the dimensions for the game window
		player = new PlayerShip((double)300,(double)300,"src/graphicImages/ship2.png", this);
		//moveStuff = new MovementPattern();
		enemies = new LinkedList<EnemyShip>();
		playerProjectiles = new LinkedList<Projectile>();
		enemyProjectiles = new LinkedList<Projectile>();
		powerups = new LinkedList<Powerup>();
		stars = new LinkedList<Star>();
		explosions = new LinkedList<Explosion>();
		try {
			Explosion.setImageForAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.curGraphics = curGraphics;
		currentlyModifying = false;
		nukeFlag = false;
		testFlag = false;
		winFlag = false;
		loseFlag = false;
		slowCounter = 0;
		score = 0;
		isPaused = false;
		//spawnStars();
		starSpawnRate = 0.05;
		allRounds = new RoundDriver();			
	}
	
	

	public void runGame() {
		try {
			audio.play(0);
		} catch (UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		waitForTurn();
		currentlyModifying = true;
		LinkedList<Projectile> tempProjList = new LinkedList<>();
		if(testFlag) {
			curRound = new Round(allRounds.getRoundList().getFirst());
		}
		if(!testFlag) {
			curRound = allRounds.getNextRound();
			if(curRound == null){
				finalScore = score;
				audio.pause();
				controller.winScreen();
				return;
			}
			enemies = curRound.startRound();
		}
		currentlyModifying = false;
		lastFrame = System.currentTimeMillis();
		while(true) {
			//TODO organize this function into smaller sub functions
			curGraphics.repaint();
			//move player
			player.move();
			//move enemies
			curRound.increment();
			waitForTurn();
			currentlyModifying = true;
			
				
			
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
			createStars();
			currentlyModifying = false;
			detectCollisions();
			waitForTurn();
			currentlyModifying = true;
			if(nukeFlag) {
				NukeEnemies();
			}
			purgeComponents();


			if(enemies.size() == 0 && !testFlag){
				curRound = allRounds.getNextRound();
				if(curRound == null){
					finalScore = score;
					try {
						audio.play(1);
					} catch (UnsupportedAudioFileException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (LineUnavailableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					controller.winScreen();
					break;
				}
				enemies = curRound.startRound();
			}
			currentlyModifying = false;


			moveProjectiles();
			movePowerups();
			moveStars();
			moveExplosions();
			
			if(player.getHealth() <= 0){
				finalScore = score;
				try {
					audio.play(2);
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.controller.loseScreen();
				break;
			}
			try {
				


				if(slowCounter <= 0){
					
					while(System.currentTimeMillis() - lastFrame < timeDelay){
						
						Thread.sleep(0, 1);
					}

					
				}
				else{
					while(System.currentTimeMillis() - lastFrame < (timeDelay*2)){
						Thread.sleep(0, 1);
					}
					slowCounter --;
				}
				lastFrame = System.currentTimeMillis();
				
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			if (this.testFlag) {
				//only want to run once if test
				return;
			}
			while(isPaused){
				curGraphics.repaint();
				try {
					Thread.sleep(0, 1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
			
	}




	private void startNextRound() {
		enemies = curRound.startRound();
	}



	/*private void spawnStars() {
		int type;
		double xLoc;
		for(int i = 0; i < 50; i++){
			type = (int) ((Math.random() * 3) + 1);
			xLoc = ((Math.random() * gameWidth));
			//System.out.println(xLoc);
			stars.add(new Star(xLoc, type));
		}

	}
	*/
	/***
	 * creates a number of stars each round
	 */
	private void createStars() {
		int type;
		double xLoc;
		if(Math.random()< starSpawnRate){
			type = (int) ((Math.random() * 3) + 1);
			xLoc = ((Math.random() * gameWidth));
			//System.out.println(xLoc);
			stars.add(new Star(xLoc, type));
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
				if(tempProj.generateExplosion){
					explosions.add(new Explosion(tempProj.getxloc() - 10, tempProj.getyloc()));
				}
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
				explosions.add(new Explosion(tempEnemy.getxloc(), tempEnemy.getyloc()));
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
		ListIterator<Star> starIterator = stars.listIterator();
		while(starIterator.hasNext()) {
			Star tempStar = starIterator.next();
			if(tempStar.getToBeDestroyed()) {
				starIterator.remove();
			}
		}
		ListIterator<Explosion> exploIterator = explosions.listIterator();
		while(exploIterator.hasNext()) {
			Explosion tempExplo = exploIterator.next();
			if(tempExplo.getToBeDestroyed()) {
				exploIterator.remove();
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
				powerups.add(new HealthUp(xloc, yloc));
			}
			else if (ranNum > 100 && ranNum <= 200) { //5% chance
				//generate weapon upgrade
				powerups.add(new WeaponUp(xloc, yloc));
			}
			else if (ranNum > 200 && ranNum <= 250) { //1% chance
				//generate nuke
				powerups.add(new Nuke(xloc, yloc));
			}
			else if(ranNum > 250 && ranNum <= 300){
				powerups.add(new Slow(xloc, yloc));
			}
			else if(ranNum > 300 && ranNum <= 310){
				powerups.add(new DamageUp(xloc, yloc));
			}
		}
		//FOR TESTING
		else {
			if (ranNum % 3 == 0) {
				//generate healthup
				powerups.add(new HealthUp(xloc, yloc));
			}
			else if (ranNum % 3 == 1) {
				//generate weapon upgrade
				powerups.add(new WeaponUp(xloc, yloc));
			}
			else {
				//generate nuke
				powerups.add(new Nuke(xloc, yloc));
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
	public int getSlowCounter(){
		return slowCounter;
	}


	
	private void moveExplosions() {
		for(Explosion aExplosion:explosions){
			aExplosion.move();
		}
	}

	private void moveStars(){
		for(Star aStar: stars) {
			aStar.move();
			aStar.checkBounds();
		}
	}
	/**
	 * move powerups down the screen towards the user
	 */
	private void movePowerups() {
		for(Powerup aPowerup: powerups) {
			aPowerup.move();
			aPowerup.checkBounds();
		}
		/*for(Powerup aPowerup: powerups) {
			aPowerup.move();
			aPowerup.checkBounds();
		}
		*/
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
	
	public LinkedList<Star> getStars(){
		return stars;
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

	public LinkedList<Explosion> getExplosions(){
		return explosions;
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
	public void triggerSlow() {
		slowCounter = 2000;
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
				//System.out.println(playerProjectiles.size());
				
			}
		}
		if((c == 'p')){

			isPaused = !isPaused;
			if(isPaused){
				audio.pause();
			}
			else{
				audio.play();
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

	public boolean getPauseState(){
		return isPaused;
	}

	public boolean getWinFlag(){
		return winFlag;
	}
	public boolean getLoseFlag(){
		return loseFlag;
	}
	
}
