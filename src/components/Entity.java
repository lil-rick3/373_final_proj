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
	public void checkCollision(Entity otherEntity) {
		
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

