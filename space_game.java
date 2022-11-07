
public class space_game {

	space_gui curGraphics;
	int xloc;
	int yloc;
	int xmovement;
	int ymovement;
	boolean upOn;
	boolean downOn;
	boolean rightOn;
	boolean leftOn;
	
	public space_game() {
		curGraphics = new space_gui(this);
		
		initPlayer();
		
	}
	
	public void runGame() {
		
		
		while(true) {
			
			curGraphics.repaint();
			movePlayer();
			
			try {
				Thread.sleep(5);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	private void initPlayer(){
		xloc = 20;
		yloc = 20;
		xmovement = 0;
		ymovement = 0;
	}
	private void movePlayer() {
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
		
	}
	
	public int getXLoc() {
		return xloc;
	}
	public int getYLoc() {
		return yloc;
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
