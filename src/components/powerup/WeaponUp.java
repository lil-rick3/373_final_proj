package components.powerup;

import components.Entity;
import components.ships.Ship;

/**
 * Weaponup powerup upgrades the player weapon upon pickup
 * singleshot > doubleshot > sprayshot
 */
public class WeaponUp extends Powerup{
    public WeaponUp(double xLoc, double yLoc) {
        super(xLoc, yLoc, "src/graphicImages/WeaponUp.png");
    }
    
    
   

	
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "WeaponUp";
    }
    
}
