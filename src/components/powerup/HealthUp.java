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
     * @return String
     */
    @Override
    public String toString() {
        return "Healthup";
    }

}
