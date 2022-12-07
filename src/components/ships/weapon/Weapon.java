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
    protected int weaponDamage;
    public Weapon(Ship ship, int scalar_speed, int damage) {
        this.ship = ship;
        this.speed = scalar_speed;
        this.weaponDamage = damage;
        offset = ship.getWidth() - 2;
        if(!ship.getIsUp()){
            speed = speed * -1;
            

        }

    }

    
    /** 
     * @return int
     */
    public int getSpeed() {
        return this.speed;
    }

    public void increaseDamage(){
        weaponDamage++;
    }
    public int getWeaponDamage() {
        return this.weaponDamage;
    }
    public abstract LinkedList<Projectile> shoot(double xloc, double yloc, String ImageFilePath);
}
