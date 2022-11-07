import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class space_game {

	space_gui curGraphics;
	Ship playerShip;
	LinkedList<Projectile> playerProjectiles;
	
	public space_game() {
		curGraphics = new space_gui(this);
		
		playerShip = new Ship(this);
		playerProjectiles = new LinkedList<Projectile>();
		
	}
	
	public void runGame() {
		
		
		while(true) {
			
			curGraphics.repaint();
			playerShip.move();
			moveProjectiles();
			try {
				Thread.sleep(5);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	
	
	
	private void moveProjectiles() {
		// TODO Auto-generated method stub
		for(Projectile aProj: playerProjectiles) {
			aProj.move();
		}
	}

	private void startMotion(char c) {
		if(c == 'd') {
			playerShip.setRightOn(true);
		}
		else if(c == 'a') {
			playerShip.setLeftOn(true);
		}
		else if(c == 'w') {
			playerShip.setUpOn(true);
		}
		else if(c == 's') {
			playerShip.setDownOn(true);
		}
	}

	public Ship getPlayerShip() {
		return playerShip;
	}

	public LinkedList<Projectile> getPlayerProjectiles(){
		return playerProjectiles;
		
	}
	public void setPlayerShip(Ship playerShip) {
		this.playerShip = playerShip;
	}

	private void stopMotion(char c) {
		if(c == 'd') {
			playerShip.setRightOn(false);
		}
		else if(c == 'a') {
			playerShip.setLeftOn(false);
		}
		else if(c == 'w') {
			playerShip.setUpOn(false);
		}
		else if(c == 's') {
			playerShip.setDownOn(false);
		}
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
			if(!playerShip.isShooting()) {
				playerShip.setShooting(true);
				playerProjectiles.add(playerShip.shoot());
			}
		}
			
	}

	public void processKeyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		char c = e.getKeyChar();
		if((c == 'w') || (c == 'a') || (c == 's') || (c == 'd') || ( c == 'j'))
  		  	stopMotion(c);
		if(c == ' ') {
			playerShip.setShooting(false);
		}
	}
	
	
}
