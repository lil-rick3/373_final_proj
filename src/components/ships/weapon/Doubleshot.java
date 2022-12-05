package components.ships.weapon;

import java.util.LinkedList;

import components.projectile.Projectile;
import components.ships.Ship;

/**
 * this weapon class allows the user to shoot two projectiles simulatenously.
    this weapon upgrades into the spray shot weapon.
 */
public class Doubleshot extends Weapon{
    public Doubleshot(Ship ship, int speed, int damage) {
        super(ship,speed, damage);
        //this.speed = 3; //projectile speed
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
        list.add(new Projectile(0,-1 * this.speed, xloc, yloc,weaponDamage, ImageFilePath));
        //shoot from right side
        list.add(new Projectile(0, -1 * this.speed, xloc + offset, yloc,weaponDamage ,ImageFilePath));
        return list;
    }


    
    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "DoubleShot";
    }
}
