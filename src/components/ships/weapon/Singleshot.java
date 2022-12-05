package components.ships.weapon;

import java.util.LinkedList;

import components.projectile.Projectile;
import components.ships.Ship;

/**
 * this weapon class is what the player initially starts with. It shoots one projectile, alternating from the left and right
 * side of the ship. This weapon upgrades into the double shot weapon
 */
public class Singleshot extends Weapon {
    //
    private boolean shootOnLeft; //variable used to allow for alternation of where the projectile comes from on the ship graphic

    public Singleshot(Ship ship, int speed) {
        super(ship, speed);
        //this.speed = 3;
        this.shootOnLeft = true;
        //System.out.println(this.speed);
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
            tempProj = new Projectile(0,-1*this.speed, xloc, yloc, ImageFilePath);
            list.add(tempProj);
            
        }
        else {
            //shoot from right side
            shootOnLeft = !shootOnLeft;
            tempProj = new Projectile(0,-1* this.speed, xloc + offset, yloc, ImageFilePath);
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
