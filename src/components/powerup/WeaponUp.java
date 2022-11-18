package components.powerup;

import components.Entity;
import components.ships.Ship;

/**
 * Weaponup powerup upgrades the player weapon upon pickup
 * singleshot > doubleshot > sprayshot
 */
public class WeaponUp extends Powerup{
    public WeaponUp(double xLoc, double yLoc, String imagePath) {
        super(xLoc, yLoc, imagePath);
    }
    
    
    /** 
     * @param crashedInto
     * Delete the Powerup
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
        return "WeaponUp";
    }
    
}
