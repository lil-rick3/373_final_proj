package components.powerup;

import components.Entity;
import components.ships.Ship;

//weaponup powerup upgrades player weapon when picked up
public class WeaponUp extends Powerup{
    public WeaponUp(double xLoc, double yLoc, String imagePath) {
        super(xLoc, yLoc, imagePath);
    }
    
    @Override
    //delete the powerup
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
