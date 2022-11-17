package components.powerup;

import components.Entity;
import components.ships.Ship;

public class WeaponUp extends Powerup{
    public WeaponUp(double xLoc, double yLoc, String imagePath) {
        super(xLoc, yLoc, imagePath);
    }
    
    @Override
	protected void collisionAction(Entity crashedInto) {
		if(crashedInto instanceof Ship){
			toBeDestroyed = true;
		}
		
	}

	@Override
    public String toString() {
        return "WeaponUp";
    }
    
}
