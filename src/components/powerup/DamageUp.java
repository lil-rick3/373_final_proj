package components.powerup;


/***
 * when the powerup touches the player, the player will increment their damage by 1
 */
public class DamageUp extends Powerup{

    public DamageUp(double xloc, double yloc) {
        super(xloc, yloc, "src/graphicImages/DamageUp.png");
        //TODO Auto-generated constructor stub
    }
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "DamageUp";
    }
}
