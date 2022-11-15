package components;

import java.util.LinkedList;

import components.projectile.Projectile;

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
