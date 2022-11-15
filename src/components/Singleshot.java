package components;

import java.util.LinkedList;

import components.projectile.Projectile;

public class Singleshot extends Weapon {
    private boolean shootOnLeft;

    public Singleshot() {
        super();
        this.speed = 1;
        this.shootOnLeft = true;
    }

    public LinkedList<Projectile> shoot(double xloc, double yloc, String ImageFilePath) {
        LinkedList<Projectile> list = new LinkedList<>();
        Projectile tempProj;
        if(shootOnLeft) {
            shootOnLeft = !shootOnLeft;
            tempProj = new Projectile(true, false, false, this.getSpeed(), xloc, yloc, ImageFilePath);
            list.add(tempProj);
            
        }
        else {
            shootOnLeft = !shootOnLeft;
            tempProj = new Projectile(true, false, false, this.getSpeed(), xloc + 18, yloc, ImageFilePath);
            list.add(tempProj);
        }
        return list;
    }
}