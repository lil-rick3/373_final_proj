package components.powerup;

import components.Entity;
import components.ships.Ship;
/**
 * nuke powerup wipes all enemies when picked up
 */
public class Nuke extends Powerup {
    public Nuke(double xLoc, double yLoc, String imagePath) {
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
        return "Nuke";
    }
}
