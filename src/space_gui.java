import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class space_gui extends JPanel implements KeyListener{
	
	
static final int PIXEL_SIZE = 10;
	
	space_game currentGame;
	boolean isFirstMove = true;
	
	public space_gui(space_game currentGame){
		
		this.currentGame = currentGame;
		JFrame  graphic = new JFrame("space Game");
		
		addKeyListener(this);
		this.setFocusable(true);
		
		graphic.setSize(700,700);
		graphic.setLocation(100, 0);
						
		graphic.add(this);
		
		this.setSize(600,600);
		graphic.setBackground(Color.RED);
		
		repaint();
		graphic.setVisible(true);
				
	}
	
	public void paintComponent(Graphics g){		
		paintBackground(g);
		paintShip(g);
		paintProjectiles(g);
		
	}
	
	
	private void paintBackground(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 600, 600);
	}
	
	private void paintShip(Graphics g) {
		Color ship = new Color(200,50,0);
		
		g.setColor(ship);
		Ship currentShip = currentGame.getPlayerShip();
		int xloc = (int)currentShip.getXloc();
		int yloc = (int)currentShip.getYloc();
		
		g.fillRect(xloc, yloc, 10, 10);
	}
	
	private void paintProjectiles(Graphics g) {
		LinkedList<Projectile> playerProjectiles = currentGame.getPlayerProjectiles();
		
		Color porjColor = Color.GREEN;
		g.setColor(porjColor);
		for(Projectile aProjectile: playerProjectiles) {
			g.fillRect((int)aProjectile.getxLoc(), (int)aProjectile.getyLoc(), 2, 5);
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		currentGame.processKeyTyped(e);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		currentGame.processKeyPressed(e);
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		currentGame.processKeyReleased(e);
		
		
			
		
		
	}
	
	

}
