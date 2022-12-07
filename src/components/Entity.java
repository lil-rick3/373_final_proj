package components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import components.ships.PlayerShip;
import components.ships.Ship;

/**
 * this class is the super class to essentially all moving/functional parts of the game
 */
public abstract class Entity {

	protected double xloc;
	protected double yloc;
	protected int width; 
	protected int height;
	//these variables are used for collision detection
	protected BufferedImage projection;
	protected boolean toBeDestroyed; //entity will be deleted on the next game iteration
	private static int XSIZE;
	private static int YSIZE;
	
	/**
	 * reads in the file path used for the graphic of the entity and then
	 *dynamically determines the width and height of the entity based
	 *on how many pixels the .png file is
	 * @param imagePath
	 */
	public Entity(String imagePath) {
		toBeDestroyed = false;
		try {
			projection = ImageIO.read(new File(imagePath));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		width = projection.getWidth();
		height = projection.getHeight();
	}
	
	
	public Entity(BufferedImage image) {
		projection = image;
		width = projection.getWidth();
		height = projection.getHeight();
	}
	


	/** 
	 * @param g
	 * @param spaceGui
	 */
	public void paintEntity(Graphics g, JPanel spaceGui){
		g.drawImage(projection, (int)xloc, (int)yloc, spaceGui);		
		
	}
	/***
	 * this function checks if two entities are colliding
	 * @param otherEntity
	 */
	public static boolean CheckCollision(Entity e1, Entity e2) {

		if(checkIntersection(e1,e2)){
			//System.out.println("collision");
			e1.collisionAction(e2);
			e2.collisionAction(e1);
			return true;
		}		
		return false;
	}
	/***
	 * returns true if the inner entitiy has a corner within the outer entity
	 * @param outer
	 * @param inner
	 * @return
	 */
	private static boolean checkIntersection(Entity outer, Entity inner){
		

		double xLower = inner.xloc;
		double xUpper = inner.xloc + (double)inner.width;
		double yLower = inner.yloc;
		double yUpper = inner.yloc + (double)inner.height;
		
		if(checkPoint(xLower, yLower, outer)){

			return true;
		}
		else if(checkPoint(xLower, yUpper, outer)){
			return true;
		}
		else if(checkPoint(xUpper, yLower, outer)){
			return true;
		}
		else if(checkPoint(xUpper, yUpper, outer)){
			return true;
		}
		else{
			return false;
		}
	}
	
	/** 
	 * @param pointx
	 * @param pointy
	 * @param outer
	 * @return boolean
	 */
	private static boolean checkPoint(double pointx, double pointy, Entity outer){
		double xLowerBound = outer.xloc;
		double xUpperBound = outer.xloc + (double)outer.width;
		double yLowerBound = outer.yloc;
		double yUpperBound = outer.yloc + (double)outer.height;

		return (((pointx >= xLowerBound) && (pointx <= xUpperBound)) && ((pointy >= yLowerBound) && (pointy <= yUpperBound)));

	}

	protected abstract void collisionAction(Entity crashedInto);
	/***
	 * checks bounds for an entity and sets the tobedestroyed 
	 */
	//TODO: fix bounds
	public void checkBounds(){
		if((xloc < 0 - 10) || (xloc > XSIZE + 10)
		|| (yloc < 0 - 10)|| (yloc > YSIZE + 10)){
			toBeDestroyed = true;
		}
		
	}
	
	/** 
	 * @return double
	 */
	public double getxloc() {
		return xloc;
	}
	
	/** 
	 * @return double
	 */
	public double getyloc() {
		return yloc;
	}
	public abstract void move();

	
	
	/** 
	 * @return boolean
	 */
	public boolean getToBeDestroyed(){
		return toBeDestroyed;
	}

	
	/** 
	 * @param destroy
	 */
	public void setToBeDestroyed(boolean destroy) {
		toBeDestroyed = destroy;
	}
	/**
	 * set the size of the game window
	 * @param xsize
	 * @param ysize
	 */
	public static void setSize(int xsize, int ysize){
		XSIZE = xsize;
		YSIZE = ysize;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
}

