package components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public abstract class Entity {
	
	protected double xloc;
	protected double yloc;
	protected int width;
	protected int height;
	protected BufferedImage projection;
	
	
	
	public Entity(String imagePath) {
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

		if(checkIntersection(e1,e2)){
			System.out.println("collision");
			return true;
		}
		if(checkIntersection(e2, e2)){
			System.out.println("collision");
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
	private static boolean checkPoint(double pointx, double pointy, Entity outer){
		double xLowerBound = outer.xloc;
		double xUpperBound = outer.xloc + (double)outer.width;
		double yLowerBound = outer.yloc;
		double yUpperBound = outer.yloc + (double)outer.height;

		return (((pointx > xLowerBound) && (pointx < xUpperBound)) && ((pointy > yLowerBound) && (pointy < yUpperBound)));

		

	}
	public double getxloc() {
		return xloc;
	}
	public double getyloc() {
		return yloc;
	}
	public abstract void move();
	public BufferedImage getImage() {
		return projection;
	}
	
	
	
}

