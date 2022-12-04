package components.star;

import components.Entity;

public class Star extends Entity {

    double speed;
    String filePath;
    public Star(double xLoc, int type){        
        super("src/graphicImages/star" + String.valueOf(type) +".png");
        speed = type * 0.1;
        this.xloc = xLoc;
        this.yloc = 0;

    }
    


    @Override
    protected void collisionAction(Entity crashedInto) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void move() {
        yloc += speed;
        
    }
    
}
