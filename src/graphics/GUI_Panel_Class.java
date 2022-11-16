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


public class GUI_Panel_Class { //Parent class for the three primary GUI pages
	protected JPanel_Background mainPanel;
	
	
	GUI_Panel_Class(String backgroundName) { //Input the background image name, assumes it's located in the graphic images directory. String in should be (FILENAME.filetype) EX: starbackground.jpg 
		BufferedImage background = null;
    	Path currentPath = Paths.get("");
    	String pathString = currentPath.toAbsolutePath().toString();
    	pathString += "\\src\\graphicImages\\" + backgroundName;
    	
    	 try {
         	background = ImageIO.read(new File(pathString)); //need to make relative
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    	
    	mainPanel = new JPanel_Background(background);
    	
	}
	
    public JLabel addTextElement(String text) { //Input the text you want displayed
    	 JLabel textLabel = new JLabel(text);
    	 textLabel.setFont(new Font("Monospace", Font.PLAIN, 30));
    	 textLabel.setForeground(Color.RED);
    	 textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    	 this.mainPanel.add(textLabel);
    	 return textLabel; //returns label if you want to modify it further in child functions
    	
    }
    public JButton addButton(String text) { //Input the text on the button
        JButton button = new JButton(text);
        button.setFont(new Font("Monospace", Font.PLAIN, 30));
        button.setPreferredSize(new Dimension(120,40));
        //coloring
        button.setBackground(Color.GRAY);
        button.setForeground(Color.RED);
        
        
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.mainPanel.add(button);
        return button; //Return the button out so action parsing can be done in child classes
    }
    
    public void addVerticalSpacing() {
    	this.mainPanel.add(Box.createVerticalGlue());
    	
    }
    
        
    public JPanel getPanel() {
    	return this.mainPanel;
    }

}