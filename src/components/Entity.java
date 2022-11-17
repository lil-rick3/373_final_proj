package components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import components.ships.PlayerShip;
import components.ships.Ship;

public abstract class Entity {
	
	protected double xloc;
	protected double yloc;
	protected int width;
	protected int height;
	protected BufferedImage projection;
	protected boolean toBeDestroyed;
	private static int XSIZE;
	private static int YSIZE;
	
	
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
	
	public void paintEntity(Graphics g, JPanel spaceGui){
		g.drawImage(projection, (int)xloc, (int)yloc, spaceGui);		
		
	}
	/***
	 * this function checks if two entities are colliding
	 * @param otherEntity
	 */
	public static boolean CheckCollision(Entity e1, Entity e2) {

		if(checkForOverlap(e1,e2)){
			System.out.println("collision");
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
	private static boolean checkForOverlap(Entity e1, Entity e2){

		double lowerXbound1 = e1.getxloc();
		double upperXbound1 = e1.getxloc()+e1.width;
		double lowerYbound1 = e1.getyloc();
		double upperYbound1 = e1.getyloc()+e1.height;
		
		double lowerXbound2 = e2.getxloc();
		double upperXbound2 = e2.getxloc()+e2.width;
		double lowerYbound2 = e2.getyloc();
		double upperYbound2 = e2.getyloc()+e2.height;

		
		//e2 xloc is in between the xloc of e1
		if((lowerXbound2 >= lowerYbound1) && (lowerXbound2 <= upperXbound1)) {
			//e2 yloc is in between the yloc of e1
			if((lowerYbound2 >= lowerYbound1) && (lowerYbound2 <= upperYbound1)){
				return true;
			}
		}
		//e1 xloc is in between the xloc of e2
		else if((e1.getxloc() >= e2.getxloc()) && (e1.getxloc() <= (e2.getxloc()+e2.width))){
			//e1 yloc is in between the yloc of e2
			if((e1.getyloc() >= e2.getyloc()) && (e1.getyloc() <= (e2.getyloc()+e2.height))){
				return true;
			}
		}
			return false;
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
	public double getxloc() {
		return xloc;
	}
	public double getyloc() {
		return yloc;
	}
	public abstract void move();

	
	public boolean getToBeDestroyed(){
		return toBeDestroyed;
	}

	public void setToBeDestroyed(boolean destroy) {
		toBeDestroyed = destroy;
	}
	public static void setSize(int xsize, int ysize){
		XSIZE = xsize;
		YSIZE = ysize;
	}
}

