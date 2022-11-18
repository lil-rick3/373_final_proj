package components.ships.weapon;

import java.util.LinkedList;

import components.projectile.Projectile;

/**
 * this weapon class allows the user to shoot two projectiles simulatenously.
    this weapon upgrades into the spray shot weapon.
 */
public class Doubleshot extends Weapon{
    public Doubleshot() {
        this.speed = 3; //projectile speed
    }


    
    /** 
     * @param xloc
     * @param yloc
     * @param ImageFilePath
     * @return LinkedList<Projectile>
     * Shoot two projectiles from the left and right of the ship simultaneously
     */
    public LinkedList<Projectile> shoot(double xloc, double yloc, String ImageFilePath) {
        LinkedList<Projectile> list = new LinkedList<>();
        //shoot from left side 
        list.add(new Projectile(true, false, false, this.getSpeed(), xloc, yloc, ImageFilePath));
        //shoot from right side
        list.add(new Projectile(true, false, false, this.getSpeed(), xloc + 18, yloc, ImageFilePath));
        return list;

    }
    
    @Override
    public String toString() {
        return "DoubleShot";
    }
}
