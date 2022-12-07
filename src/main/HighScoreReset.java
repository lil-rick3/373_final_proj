package main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import graphics.High_Scores_Object;

public class HighScoreReset {
    
    public static void main(String[] args) throws IOException{

        High_Scores_Object thing = new High_Scores_Object();
        ArrayList<String> list = new ArrayList<>();

        list.add("99999:DR.MAREFAT");
        list.add("3000:DR.THARP");
        list.add("30:ELON MUSK");
        list.add("2:UR MOM");
        list.add("1:bob");
        FileOutputStream fos = new FileOutputStream("highscores.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.close();
        fos.close();

    }



}
