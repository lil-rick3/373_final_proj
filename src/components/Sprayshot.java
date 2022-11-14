package components;

import java.util.LinkedList;

import components.projectile.Projectile;

public class Sprayshot extends Weapon {
    private boolean shootOnLeft;


    public Sprayshot() {
        this.speed = 2;
        this.shootOnLeft = true;
    }

    public LinkedList<Projectile> shoot(double xloc, double yloc) {
        LinkedList<Projectile> projList = new LinkedList<>();
        Projectile tempProj;
        projList.add(new Projectile(true, true, false, this.getSpeed(), xloc + 18, yloc));
        projList.add(new Projectile(true, true, true, this.getSpeed(), xloc, yloc));
        projList.add(new Projectile(true, false, false, this.getSpeed(), xloc, yloc));
        projList.add(new Projectile(true, false, false, this.getSpeed(), xloc + 18, yloc));
        return projList;
    }
}
