package components.ships;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import components.powerup.Powerup;
import components.projectile.Projectile;
import game_engine.Space_Game;


/***
 * 
 * @author Jaret Rickel
 * 
 * The player ship is an extension of ship, and will use keyboard inputs
 *
 */
public class PlayerShip extends Ship{
	
	
	
	
	boolean upOn;
	boolean downOn;
	boolean rightOn;
	boolean leftOn;
	/*
	 * These variables tell which buttons are currently being
	 * pressed, so the move function can work properly
	 */
	boolean isShooting;
	//tells if the user is currently pressing the shoot button, prevents keeping spacebar pressed
	boolean shootOnLeft;
	private boolean willShoot;
	//tells if the user has pressed spacebar this cycle, helps prevent 
	//multithreading errors

	boolean hasShot;

	
	public PlayerShip(double xloc, double yloc, String imagePath ) {
		
		super(imagePath);
		width = projection.getWidth();
		height = projection.getHeight();
		this.xloc = xloc;
		this.yloc = yloc;
		upOn = false;
		downOn = false;
		rightOn = false;
		leftOn = false;
		
		isShooting = false;//says if the user has spacebard constantly pressed
		shootOnLeft = true;
		willShoot = false;// says that during next game cycle, the player will
		// shoot a projectile 
		
	}
	
	
	public void setWillShoot(boolean input) {
		willShoot = input;
	}
	public boolean isShooting() {
		return isShooting;
	}


	public void setShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}


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
	
	public Projectile shoot() {
		if(willShoot) {
			if(shootOnLeft) {
				shootOnLeft = !shootOnLeft;
				return new Projectile(true, 1, xloc, yloc);
				
			}
			else {
				shootOnLeft = !shootOnLeft;
				return new Projectile(true, 1, xloc + 18, yloc);
			}
			
		}
		else {
			return null;
		}
	}
	

	


	public void setUpOn(boolean upOn) {
		this.upOn = upOn;
	}


	public void setDownOn(boolean downOn) {
		this.downOn = downOn;
	}


	public void setRightOn(boolean rightOn) {
		this.rightOn = rightOn;
	}


	public void setLeftOn(boolean leftOn) {
		this.leftOn = leftOn;
	}


	public void startMotion(char c) {
		if(c == 'd') {
			rightOn = true;
		}
		else if(c == 'a') {
			leftOn = true;
		}
		else if(c == 'w') {
			upOn = true;
		}
		else if(c == 's') {
			downOn = true;
		}
	}

	public void stopMotion(char c) {
		if(c == 'd') {
			rightOn = false;
		}
		else if(c == 'a') {
			leftOn = false;
		}
		else if(c == 'w') {
			upOn = false;
		}
		else if(c == 's') {
			downOn = false;
		}
	}
}
