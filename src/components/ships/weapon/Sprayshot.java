package components.ships.weapon;

import java.util.LinkedList;

import components.projectile.Projectile;

public class Sprayshot extends Weapon {
   


    public Sprayshot() {
        this.speed = 4;
        
    }

    public LinkedList<Projectile> shoot(double xloc, double yloc,String ImageFilePath) {
        LinkedList<Projectile> projList = new LinkedList<>();
        
        projList.add(new Projectile(true, true, false, this.getSpeed(), xloc + 18, yloc,ImageFilePath));
        projList.add(new Projectile(true, true, true, this.getSpeed(), xloc, yloc,ImageFilePath));
        projList.add(new Projectile(true, false, false, this.getSpeed(), xloc, yloc,ImageFilePath));
        projList.add(new Projectile(true, false, false, this.getSpeed(), xloc + 18, yloc,ImageFilePath));
        return projList;
    }

    @Override
    public String toString() {
        return "SprayShot";
    }
}
