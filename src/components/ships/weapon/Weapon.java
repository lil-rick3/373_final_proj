package components.ships.weapon;

import java.util.LinkedList;

import components.projectile.Projectile;
import components.ships.Ship;

/**
 * this class provides PlayerShip the capability to shoot and upgrade.
 */
public abstract class Weapon {
    protected int speed;
    protected Ship ship;
    public Weapon(Ship ship) {
        this.ship = ship;
        speed = 0;
    }

    
    /** 
     * @return int
     */
    public int getSpeed() {
        return this.speed;
    }
    public abstract LinkedList<Projectile> shoot(double xloc, double yloc, String ImageFilePath);
}
