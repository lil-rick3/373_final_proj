package game_engine.RoundComp;

import java.util.ArrayList;
import java.util.LinkedList;

public class RoundDriver{
    int numRounds = 21;

    private LinkedList<RoundData> roundList;
    public RoundDriver(){
        roundList = new LinkedList<RoundData>();
        int weapon= 0;
        int health;
        
        for(int shipType = 1; shipType <= 2 ;shipType++){
            for(int formation = 0; formation < 3; formation ++){
            if(shipType < 3)
                weapon = 0;
            else if(shipType < 5){
                weapon = 1;
            }
            else
                weapon = 2;
            health = shipType + 2;
            RoundData tempRound = new RoundData(formation, health, shipType, weapon);
            roundList.add(tempRound);
            

            }
        }
        
        

    }

    public Round getNextRound(){
        if(roundList.size() == 0){
            return null;
        }
        return new Round(roundList.removeFirst());
    }

    





}