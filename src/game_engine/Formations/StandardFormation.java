package game_engine.Formations;

import components.ships.EnemyShip;

public class StandardFormation extends MovementPattern{
    private int phaseInCycle;
    protected boolean movingDown;


    public StandardFormation(){
        phaseInCycle = 0;
		yOffSet = 30;
		xOffSet = 30;
		movingDown = true;

		initial = true;
		initialOffset = 200;
    }


    public void moveShip(EnemyShip aShip) {
		int id = aShip.getId();
		//aShip.setxloc((30 + phaseInCycle/2) + (id%10)*xOffSet);
		//aShip.setyloc((30 + phaseInCycle/2) + (id/10)*yOffSet);
		aShip.setxloc((30 + phaseInCycle/2) + (id%10)*xOffSet);
		aShip.setyloc(30 + (id/10)*yOffSet - initialOffset);
		
	}
    public void increment() {
		if(initial){
			initialOffset --;
			if(initialOffset == 0){
				initial = false;
			}
		}
		else{
			if(movingDown && phaseInCycle <= 450) {
				phaseInCycle++;
			}
			else if (movingDown && phaseInCycle > 450) {
				movingDown = false;
			}
			else if (!movingDown && phaseInCycle >= 0) {
				phaseInCycle--;
			}
			else if (!movingDown && phaseInCycle < 0) {
				movingDown = true;
			}

		}
		
	}








}