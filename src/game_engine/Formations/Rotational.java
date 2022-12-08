package game_engine.Formations;

import components.ships.EnemyShip;

/***
 * produces three circles of rotating ships
 */
public class Rotational extends MovementPattern{

    int radius = 50;
    int noShipsInCircle = 8;
    int centerOffSet = 80;
    int separation = 200;
    double cycleRadians;
    public Rotational(){
        super();
        initialOffset = -200;
        yOffSet = 100;
        cycleRadians = 0;

    }


    @Override
    public void moveShip(EnemyShip aShip) {
    
        int id = aShip.getId();

        int circleNo = id/8;
        int circleOrder = id%8;     
        double angle = cycleRadians + (Math.PI/4)*circleOrder;
        aShip.setxloc(centerOffSet + circleNo * separation + Math.cos(angle)*radius);
        aShip.setyloc(yOffSet + Math.sin(angle)*radius + initialOffset);

    }

    @Override
    public void increment() {
        // TODO Auto-generated method stub
        if(initial){
			initialOffset ++;
			if(initialOffset > 0){
				initial = false;
			}
		}
        cycleRadians += 0.01;
        if(cycleRadians > (Math.PI * 2)){
            cycleRadians -= Math.PI * 2;
        }

        
    }
    
}
