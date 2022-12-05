package components.powerup;


/**
 * Healthup restores 1 health for player when picked up
 */
public class HealthUp extends Powerup {

    public HealthUp(double xLoc, double yLoc) {
        super(xLoc, yLoc, "src/graphicImages/HealthUp.png");
    }

  
    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Healthup";
    }

}
