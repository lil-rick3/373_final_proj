import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class space_game {

	space_gui curGraphics;
	PlayerShip player;
	LinkedList<Projectile> playerProjectiles;
	
	public space_game(space_gui curGraphics) {
		
		
		player = new PlayerShip();
		playerProjectiles = new LinkedList<Projectile>();
		this.curGraphics = curGraphics;
				
	}
	
	public void runGame() {
		
		
		while(true) {
			
			curGraphics.repaint();
			player.move();
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

	public Ship getPlayerShip() {
		return player;
	}

	public LinkedList<Projectile> getPlayerProjectiles(){
		return playerProjectiles;
		
	}
	

	private void stopMotion(char c) {
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

	public void processKeyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void processKeyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		char c = e.getKeyChar();
		if((c == 'w') || (c == 'a') || (c == 's') || (c == 'd') || ( c == 'j'))
    		  startMotion(c);
		if(c == ' ') {
			if(!player.isShooting()) {
				player.setShooting(true);
				playerProjectiles.add(player.shoot());
			}
		}
			
	}

	public void processKeyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		char c = e.getKeyChar();
		if((c == 'w') || (c == 'a') || (c == 's') || (c == 'd') || ( c == 'j'))
  		  	stopMotion(c);
		if(c == ' ') {
			player.setShooting(false);
		}
	}
	
	
}
