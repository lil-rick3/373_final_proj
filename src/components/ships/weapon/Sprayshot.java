package components.ships.weapon;

import java.util.LinkedList;

import components.projectile.Projectile;

/**
 * this weapon allows the player to shoot two projectiles simultaneously like the double shot but
 * also two diagonal projectiles from either side of the ship. This is the final weapon upgrade
 */
public class Sprayshot extends Weapon {
   
    public Sprayshot() {
        this.speed = 4;
        
    }

    
    /** 
     * @param xloc
     * @param yloc
     * @param ImageFilePath
     * @return LinkedList<Projectile>
     * Shoot 4 projectiles simulatenously. Two diagonal projectiles from the left and right of the ship in
     * addition to the double shot projectiles
     */
    public LinkedList<Projectile> shoot(double xloc, double yloc,String ImageFilePath) {
        LinkedList<Projectile> projList = new LinkedList<>();
        
        //shoot a right diagonal projectile
        projList.add(new Projectile(true, true, false, this.getSpeed(), xloc + 18, yloc,ImageFilePath));
        //shoot a left diagonal projectile
        projList.add(new Projectile(true, true, true, this.getSpeed(), xloc, yloc,ImageFilePath));
        //shoot a straight projectile from left side
        projList.add(new Projectile(true, false, false, this.getSpeed(), xloc, yloc,ImageFilePath));
        //shoot a straight projectile from right side
        projList.add(new Projectile(true, false, false, this.getSpeed(), xloc + 18, yloc,ImageFilePath));
        return projList;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "SprayShot";
    }
}
