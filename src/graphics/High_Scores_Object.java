package graphics;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Collections;


/***
 *This is the highscore object. It contains logic for saving and fetching high scores, to be displayed on the highscore screen.
 */

public class High_Scores_Object{
    private ArrayList<String> list =  new ArrayList<String>();
    private int highScore = 0;

    //Method for setting the current highscore from inside the game loop logic
    public void setHighScore(int hsIn) {
        this.highScore = hsIn;
    }

    //Method for returning the highscore
    public int getHighScore() {
        return this.highScore;
    }
    
    //Returns the highest 5 elements from the de-serialzed object
    public ArrayList<String> getTop5Elements() { //Note, strings saved as SCORE:NAME
        this.updateList();
        ArrayList<String> outStrings = new ArrayList<String>();
        ArrayList<Integer> scoreValues = new ArrayList<Integer>();

        for (String sItem: this.list) { //Add all the score values to the list
            String[] temp = sItem.split(":");
            scoreValues.add(Integer.parseInt(temp[0]));
        }

        //Sort the list and make corresonding swaps in the list elements
        int i, j;
        int n = scoreValues.size();
        System.out.println(this.list);

        for (i = 0; i < n - 1; i++){
            for (j = 0; j < n - i - 1; j++) {
                if (scoreValues.get(j) < scoreValues.get(j+1)) {
                    Integer tempInt = scoreValues.get(j);
					String temp = this.list.get(j);
					scoreValues.set(j, scoreValues.get(j+1));
					scoreValues.set(j+1, tempInt);
					this.list.set(j, this.list.get(j+1));
					this.list.set(j+1, temp);
                }
            }
        }
        for (int y = 0; y < 5; y++) {
            outStrings.add(this.list.get(y)); //Add the biggest 5 
            
        }
        return outStrings;
    }

    //This method adds a new highscore to the object and then serializes it to save it.
    public void addAndSave(String inString) {
        //Import the object
        try
		{
			FileInputStream file = new FileInputStream("highscores.ser");
			ObjectInputStream object = new ObjectInputStream(file);
			try {
                this.list = (ArrayList)object.readObject();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
			object.close();
			file.close();
		} 
		catch (IOException ioe) 
		{
			ioe.printStackTrace();
		}
        //Add the new entry to the imported list
        this.list.add(inString);

        //Export the object
        try
        {
            FileOutputStream fos = new FileOutputStream("highscores.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.list);
            oos.close();
            fos.close();
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
        }
    }

    //This method updates the current highscore list to reflect the one that's saved
    public void updateList() {
        try
		{
			FileInputStream file = new FileInputStream("highscores.ser");
			ObjectInputStream object = new ObjectInputStream(file);
			try {
                this.list = (ArrayList)object.readObject();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
			object.close();
			file.close();
		} 
		catch (IOException ioe) 
		{
			ioe.printStackTrace();
		}
    }



}
