package components;


public class Explosion extends Entity{

   
    int duration;
    public Explosion(double xLoc, double yLoc) {
        
        super("src/graphicImages/explosion");
        duration = 5;
        
        //TODO Auto-generated constructor stub

        this.xloc = xLoc;
        this.yloc = yLoc;

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