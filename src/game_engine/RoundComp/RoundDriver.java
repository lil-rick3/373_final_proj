package game_engine.RoundComp;

import java.util.ArrayList;
import java.util.LinkedList;


/***
 * This class generates a queue of rounds, that can then be called to
 * get the next round
 */
public class RoundDriver{
    int numRounds = 21;

    private LinkedList<RoundData> roundList;
    public RoundDriver(){
        roundList = new LinkedList<RoundData>();
        
        int health;
        double aggression;
        int i = 0;
      
        for(int shipType = 1; shipType <= 2 ; shipType++){
            for(int formation = 0; formation < 1; formation++){
                for(int weapon = 0; weapon < 1; weapon ++){
                    health = 3 + (i/3);
                    aggression = 0.001 + 0.0001 * i;
                    roundList.add(new RoundData(formation, health, shipType, weapon, aggression));

                    i++;
                }
            }
            

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