package components.powerup;

import components.Entity;
import components.ships.PlayerShip;
import components.ships.Ship;

public class HealthUp extends Powerup {

    public HealthUp(double xLoc, double yLoc, String imagePath) {
        super(xLoc, yLoc, imagePath);
    }

    @Override
	protected void collisionAction(Entity crashedInto) {
		if(crashedInto instanceof Ship){
			toBeDestroyed = true;
		}
    }
		

}
