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
        int shipType;
        int formation;
        for(int i = 0; i < 9 ; i++){
            shipType = (i)%7 + 1;
            weapon = i % 3;
            formation = i % 3;
            health = i + 2;
            RoundData tempRound = new RoundData(formation, health, shipType, weapon);
            roundList.add(tempRound);
        }
       

            
        }
        
        

    

    public LinkedList<RoundData> getRoundList() {
        return this.roundList;
    }

    public Round getNextRound(){
        if(roundList.size() == 0){
            return null;
        }
        return new Round(roundList.removeFirst());
    }

    





}