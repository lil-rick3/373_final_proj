package game_engine.Formations;

import java.util.ArrayList;

import components.ships.EnemyShip;
//import components.ships.Ship;



public class Freestyle extends MovementPattern{
    private static final int XSIZE = 590;
    private static final int YArea = 200;
    ArrayList<ShipData> shipVelocities;
    public Freestyle(){
        shipVelocities = new ArrayList<ShipData>();
        yOffSet = 30;
		xOffSet = 30;
		

		initial = true;
		initialOffset = 200;
    }


    @Override
    public void moveShip(EnemyShip aShip) {
        int id = aShip.getId();
        if(initial){
           
            aShip.setxloc( (id%10)*xOffSet);
		    aShip.setyloc(30 + (id/10)*yOffSet - initialOffset);
        }
        else{
            double prevXloc = aShip.getxloc();
            double prevYLoc = aShip.getyloc();

            

            double newXloc = prevXloc + shipVelocities.get(id).xVel;
            double newYloc = prevYLoc + shipVelocities.get(id).yVel;
            if((newXloc < 0)){
                newXloc = 0;
                shipVelocities.get(id).xVel = -shipVelocities.get(id).xVel;
            }
            if((newXloc + aShip.getWidth() > XSIZE)){
                newXloc = XSIZE - aShip.getWidth();
                shipVelocities.get(id).xVel = -shipVelocities.get(id).xVel;
            }
            if((newYloc < 0)){
                newYloc = 0;
                shipVelocities.get(id).yVel = -shipVelocities.get(id).yVel;
            }
            if((newYloc + aShip.getHeight() > YArea)){
                newYloc = YArea - aShip.getHeight();
                shipVelocities.get(id).yVel = -shipVelocities.get(id).yVel;
            }
            aShip.setxloc(newXloc);
            aShip.setyloc(newYloc);

        }
        
    }

    @Override
    public void increment() {
        // TODO Auto-generated method stub
        if(initial){
			initialOffset --;
			if(initialOffset == 0){
				initial = false;
                for(int i = 0; i < 30; i ++){
                    double xVel = Math.random()*2 - 1;
                    double yVel = Math.random()* 2 - 1;
                    ShipData tempData = new ShipData(xVel, yVel);
                    shipVelocities.add(tempData);
                }
			}
		}
        
    }
    private class ShipData{
        double xVel;
        double yVel;
        
        ShipData(double xVel, double yVel){
            this.xVel = xVel;
            this.yVel = yVel;
            
        }



    }
    
}
