package graphics;



import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.*;

/***
* This is the parent class for the three primary GUI pages (Main_Menu, Tutorial, High score screen). Supports methods for actually creating the GUI
* from custom function calls, and retrieving the created panel.
*/


public class GUI_Panel_Class { //Parent class for the three primary GUI pages
	protected JPanel_Background mainPanel;
	
	/***
	*Constructor, sets the actual background image
	*/
	GUI_Panel_Class(String backgroundName) { //Input the background image name, assumes it's located in the graphic images directory. String in should be (FILENAME.filetype) EX: starbackground.jpg 
		BufferedImage background = null;
		//Get the correct path for the inputed background image string
    	Path currentPath = Paths.get("");
    	String pathString = currentPath.toAbsolutePath().toString();
    	pathString += "\\src\\graphicImages\\" + backgroundName;
    	
		//Attempt to read in the image, raise an exception if we cannot find the correct image
    	try {
         	background = ImageIO.read(new File(pathString)); //need to make relative
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
    	
		//Set the mainPanel JPanel to be the custom JPanel_Background class so we get the background image painted on 
    	mainPanel = new JPanel_Background(background);
    	
	}

	/***
	*Method you call to add a text label to the main panel
	*/
    public JLabel addTextElement(String text) { //Input the text you want displayed
    	 JLabel textLabel = new JLabel(text);
		 //Default font we set to everything so we have consistent style
    	 textLabel.setFont(new Font("Monospace", Font.PLAIN, 30));
    	 textLabel.setForeground(Color.RED);
    	 textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    	 this.mainPanel.add(textLabel);
    	 return textLabel; //returns label if you want to modify it further in child functions
    	
    }
	/***
	*Method you call to add buttons to the main panel
	*/
    public JButton addButton(String text) { //Input the text on the button
        JButton button = new JButton(text);
		//Default font we set to everything so we have consistent style
        button.setFont(new Font("Monospace", Font.PLAIN, 30));
        button.setPreferredSize(new Dimension(120,40));
        //coloring
        button.setBackground(Color.GRAY);
        button.setForeground(Color.RED);
        
        
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.mainPanel.add(button);
        return button; //Return the button out so action parsing can be done in child classes
    }

	/***
	*Method you call to add vertical spacing to the main panel
	*/
    public void addVerticalSpacing() {
    	this.mainPanel.add(Box.createVerticalGlue());
    	
    }
    
    /***
	*Getter method for actually putting the created panel into the card layout
	*/
    public JPanel getPanel() {
    	return this.mainPanel;
    }

}