package components.ships.weapon;

import java.util.LinkedList;

import components.projectile.Projectile;

public class Doubleshot extends Weapon{
    public Doubleshot() {
        this.speed = 3;
    }

    public LinkedList<Projectile> shoot(double xloc, double yloc, String ImageFilePath) {
        LinkedList<Projectile> list = new LinkedList<>();
        list.add(new Projectile(true, false, false, this.getSpeed(), xloc, yloc, ImageFilePath));
        list.add(new Projectile(true, false, false, this.getSpeed(), xloc + 18, yloc, ImageFilePath));
        return list;

    }
    
    @Override
    public String toString() {
        return "DoubleShot";
    }
}
