package components.ships;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import components.Entity;
import components.powerup.HealthUp;
import components.powerup.Nuke;
import components.powerup.Powerup;
import components.powerup.WeaponUp;
import components.projectile.Projectile;
import components.ships.weapon.*;
import game_engine.Space_Game;


/***
 * 
 * @author Jaret Rickel
 * 
 * The player ship is an extension of ship, and will use keyboard inputs
 *
 */
public class PlayerShip extends Ship{
	
	private boolean upOn; 
	private boolean downOn; 
	private boolean rightOn; 
	private boolean leftOn; 

	/*
	 * These variables tell which buttons are currently being
	 * pressed, so the move function can work properly
	 */

	private boolean isShooting;
	//tells if the user is currently pressing the shoot button, prevents keeping spacebar pressed
	
	private boolean willShoot;
	//tells if the user has pressed spacebar this cycle, helps prevent 
	//multithreading errors

	private Weapon weapon;

	private Space_Game curGame;
	public PlayerShip(double xloc, double yloc, String imagePath, Space_Game curGame) {
		
		super(imagePath);
		health = 3;
		this.xloc = xloc;
		this.yloc = yloc;
		this.curGame = curGame;
		upOn = false;
		downOn = false;
		rightOn = false;
		leftOn = false;
		weapon = new Singleshot(this);
		isShooting = false;//says if the user has spacebard constantly pressed
		//shootOnLeft = true;
		willShoot = false;// says that during next game cycle, the player will
		// shoot a projectile 
		projectileFilePath = "src/graphicImages/PlayerProjectile.png";


	}
	
	
	/** 
	 * @param input
	 */
	public void setWillShoot(boolean input) {
		willShoot = input;
	}

	
	/** 
	 * @return boolean
	 */
	public boolean isShooting() {
		return isShooting;
	}

	
	/** 
	 * @param isShooting
	 */
	public void setShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}


	/**
	 * move player ship based on key input
	 */
	public void move() {
		if(rightOn) {
			xloc++;
		}
		if(leftOn) {
			xloc --;
		}
		if(downOn) {
			yloc ++;
		}
		if(upOn) {
			yloc --;
		}
		//check bounds
		if(xloc < 0) {
			xloc = 0;
		}
		else if(xloc > Space_Game.gameWidth - width) {
			xloc = Space_Game.gameWidth - width;
		}
		if(yloc < Space_Game.playerHeight) {
			yloc = Space_Game.playerHeight;
		}
		else if(yloc > Space_Game.gameHeight - height) {
			yloc = Space_Game.gameHeight - height;
		}
	}
	
	/**
	 * player shoots a projectile upwards
	 */
	public LinkedList<Projectile> shoot() {
		LinkedList<Projectile> projList = new LinkedList<>();
		if(willShoot) {
			projList = this.weapon.shoot(xloc,yloc, projectileFilePath);
		}
		return projList;
	}

	
	/** 
	 * @return int
	 */
	public int getLives(){
		return health;
	}
	
	/** 
	 * @return Weapon
	 */
	public Weapon getWeapon() {
		return this.weapon;
	}
	
	
	/** 
	 * @param upOn
	 */
	public void setUpOn(boolean upOn) {
		this.upOn = upOn;
	}


	
	/** 
	 * @param downOn
	 */
	public void setDownOn(boolean downOn) {
		this.downOn = downOn;
	}


	
	/** 
	 * @param rightOn
	 */
	public void setRightOn(boolean rightOn) {
		this.rightOn = rightOn;
	}


	
	/** 
	 * @param leftOn
	 */
	public void setLeftOn(boolean leftOn) {
		this.leftOn = leftOn;
	}

	
	/** 
	 * @param crashedInto
	 * carries out the action of each entity upon collision
	 * healthup: player gains one health
	 * weaponup: upgrade player weapon
	 * nuke: wipe all enemies
	 * projectile: remove one health
	 */
	@Override
	protected void collisionAction(Entity crashedInto) {
		// TODO Auto-generated method stub
		if(crashedInto instanceof HealthUp) {
			health += 1;
		}
		else if (crashedInto instanceof Nuke) {
			curGame.triggerNuke();
		}
		else if (crashedInto instanceof WeaponUp) {
			//upgrade player weapon
			if (weapon instanceof Singleshot) {
				weapon = new Doubleshot(this);
			}
			else if (weapon instanceof Doubleshot) {
				weapon = new Sprayshot(this);
			}
		}
		else if (crashedInto instanceof Projectile) {
			//remove corresponding health from player health bar
			Projectile crashedProjectile = (Projectile)crashedInto;
			health -= crashedProjectile.getDamage();
		}
		
	}
}
