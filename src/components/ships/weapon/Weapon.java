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
    protected int offset;
    public Weapon(Ship ship, int scalar_speed) {
        this.ship = ship;
        this.speed = scalar_speed;
        offset = ship.getWidth() - 2;
        if(!ship.getIsUp()){
            System.out.println("hi");
            speed = speed * -1;
            

        }

    }

    
    /** 
     * @return int
     */
    public int getSpeed() {
        return this.speed;
    }
    public abstract LinkedList<Projectile> shoot(double xloc, double yloc, String ImageFilePath);
}
