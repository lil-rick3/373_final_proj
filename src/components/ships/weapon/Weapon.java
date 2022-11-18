package components.ships.weapon;

import java.util.LinkedList;

import components.projectile.Projectile;

/**
 * this class provides PlayerShip the capability to shoot and upgrade.
 */
public abstract class Weapon {
    protected int speed;

    public Weapon() {
        speed = 0;
    }

    public int getSpeed() {
        return this.speed;
    }
    public abstract LinkedList<Projectile> shoot(double xloc, double yloc, String ImageFilePath);
}
