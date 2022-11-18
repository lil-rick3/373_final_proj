package components.ships.weapon;

import java.util.LinkedList;

import components.projectile.Projectile;

/**
 * this weapon class is what the player initially starts with. It shoots one projectile, alternating from the left and right
 * side of the ship. This weapon upgrades into the double shot weapon
 */
public class Singleshot extends Weapon {
    //
    private boolean shootOnLeft; //variable used to allow for alternation of where the projectile comes from on the ship graphic

    public Singleshot() {
        super();
        this.speed = 3;
        this.shootOnLeft = true;
    }

    
    /** 
     * @param xloc
     * @param yloc
     * @param ImageFilePath
     * @return LinkedList<Projectile>
     * Shoot one projectile at a time, alternating between right and left.
     */
    public LinkedList<Projectile> shoot(double xloc, double yloc, String ImageFilePath) {
        LinkedList<Projectile> list = new LinkedList<>();
        Projectile tempProj;
        if(shootOnLeft) {
            //shoot from left side
            shootOnLeft = !shootOnLeft;
            tempProj = new Projectile(true, false, false, this.getSpeed(), xloc, yloc, ImageFilePath);
            list.add(tempProj);
            
        }
        else {
            //shoot from right side
            shootOnLeft = !shootOnLeft;
            tempProj = new Projectile(true, false, false, this.getSpeed(), xloc + 18, yloc, ImageFilePath);
            list.add(tempProj);
        }
        return list;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "SingleShot";
    }
}
