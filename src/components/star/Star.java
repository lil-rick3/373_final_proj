package components.star;

import components.Entity;

public class Star extends Entity {

    double speed;

    Star(int xloc, int yloc, int type, String filePath){
        super(filePath);
        speed = type;

    }
    


    @Override
    protected void collisionAction(Entity crashedInto) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        
    }
    
}
