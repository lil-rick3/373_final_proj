package components.ships.weapon;

import java.util.LinkedList;

import components.projectile.Projectile;

public class Singleshot extends Weapon {
    //this weapon class is what the player initially starts with. It shoots one projectile, alternating from the left and right
    //side of the ship. This weapon upgrades into the double shot weapon
    private boolean shootOnLeft; //variable used to allow for alternation of where the projectile comes from on the ship graphic

    public Singleshot() {
        super();
        this.speed = 3;
        this.shootOnLeft = true;
    }

    public LinkedList<Projectile> shoot(double xloc, double yloc, String ImageFilePath) {
        LinkedList<Projectile> list = new LinkedList<>();
        Projectile tempProj;
        if(shootOnLeft) {
            //shoot from left side
            shootOnLeft = !shootOnLeft;
            tempProj = new Projectile(true, false, false, this.getSpeed(), xloc, yloc, ImageFilePath);
            list.add(tempProj);
            
        }
        else {
            //shoot from right side
            shootOnLeft = !shootOnLeft;
            tempProj = new Projectile(true, false, false, this.getSpeed(), xloc + 18, yloc, ImageFilePath);
            list.add(tempProj);
        }
        return list;
    }

    @Override
    public String toString() {
        return "SingleShot";
    }
}
