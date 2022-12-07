package graphics;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/***
* This is the high score screen class. This makes the high score screen, and handles some highscore logic.
*/

public class High_Score_Screen extends GUI_Panel_Class {
	ArrayList<Integer> Scores = new ArrayList<Integer>(); //Stores the high score number, gets serialized and deserialized

	JLabel titleLabel;
	JButton backButton;
	JLabel placeHolder;
	JPanel mainView;
	ArrayList<String> scores;
	High_Scores_Object HSObject;
	
	/***
	* This is the constuctor. The inputs are the main card layout (for switching between cards), and the name of the background image file.
	* This calls all the functions to actually create the GUI panel. It also is passed the custom high score object for interfacing/
	* populating the highscores.
	*/

	public High_Score_Screen(String stringIn, JPanel mainViewIn, High_Scores_Object HSOIn) {
		super(stringIn);
		this.HSObject = HSOIn;
		this.mainView = mainViewIn;
		this.makeHighScore();
	}

	public void makeHighScore() {
		//Uses the BoxLayout so we have all elements displayed vertically
		this.mainPanel.removeAll();
		this.mainPanel.revalidate();
		this.mainPanel.repaint();
		this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
		
		//Add all of the desired GUI elements
		titleLabel = this.addTextElement("TOP 5 HIGH SCORES");
		this.addVerticalSpacing();

		//Add the scores to the list, when they are added we only let the top 5 be added
		System.out.println(this.HSObject.getTop5Elements());
		for (String sItem:this.HSObject.getTop5Elements()) {
			this.addTextElement(sItem);
			this.addVerticalSpacing();
		}

		this.addVerticalSpacing();
		backButton = this.addButton("BACK");
		this.addVerticalSpacing();
		//Add a new button listener to detect button clicks
		backButton.addActionListener(new ButtonListener());
		this.mainPanel.revalidate();
		this.mainPanel.repaint();
		
	}

	/***
	* This is the custom ButtonListener class. This listens for the back button being clicked, and re-directs the user to the main menu.
	*/
	private class ButtonListener implements ActionListener
	{
		/***
		* Catches button click and flips to the corresponding card page
		*/
		public void actionPerformed(ActionEvent e) 
		{
			JButton source = (JButton)(e.getSource());
			
			if(source.equals(backButton))
			{
				 CardLayout temp = (CardLayout)(mainView.getLayout());
				 temp.show(mainView, "HOME");
			}
			
		}
		
	}

}