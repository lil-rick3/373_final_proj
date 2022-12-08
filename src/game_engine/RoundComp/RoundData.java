package game_engine.RoundComp;


/***
 * this is a struct used to store round data, allowing for this struct to 
 * be passed as a parameter and nothing else
 */
public class RoundData {
    
    public int formation,health,shiptype,weapontype;
    public double aggression;

    public RoundData(int formation, int health, int shiptype, int weapontype, double aggression){
        this.formation = formation;
        this.health = health;
        this.shiptype = shiptype;
        this.weapontype = weapontype;
        this.aggression = aggression;

    }




}
