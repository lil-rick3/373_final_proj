package components.powerup;

import components.Entity;
import components.ships.PlayerShip;
import components.ships.Ship;

/**
 * Healthup restores 1 health for player when picked up
 */
public class HealthUp extends Powerup {

    public HealthUp(double xLoc, double yLoc, String imagePath) {
        super(xLoc, yLoc, imagePath);
    }

    
    /** 
     * @param crashedInto
     * delete the powerup
     */
    @Override
    
	protected void collisionAction(Entity crashedInto) {
		if(crashedInto instanceof Ship){
			toBeDestroyed = true;
		}
    }
		
    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Healthup";
    }

}
