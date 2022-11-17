package components.ships.weapon;

import java.util.LinkedList;

import components.projectile.Projectile;

public class Sprayshot extends Weapon {
    //this weapon allows the player to shoot two projectiles simultaneously like the double shot but
    //also two diagonal projectiles from either side of the ship. This is the final weapon upgrade
   
    public Sprayshot() {
        this.speed = 4;
        
    }

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

    @Override
    public String toString() {
        return "SprayShot";
    }
}
