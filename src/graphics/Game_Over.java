package graphics;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/***
* This is the game over screen class
*/

public class Game_Over extends GUI_Panel_Class {
	JLabel titleLabel;
	JButton backButton;
    JButton setHighScore;
	JLabel placeHolder;
	JPanel mainView;
	
	/***
	* This is the constuctor. The inputs are the main card layout (for switching between cards), and the name of the background image file.
	* This calls all the functions to actually create the GUI panel.
	*/
	public Game_Over(String stringIn, JPanel mainViewIn) {
		super(stringIn);
		this.mainView = mainViewIn;
		//Uses the BoxLayout so we have all elements displayed vertically
		this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
		//Add all of the desired GUI elements
		titleLabel = this.addTextElement("GAME OVER");
		this.addVerticalSpacing();
        setHighScore = this.addButton("ADD HIGH SCORE");
		this.addVerticalSpacing();
        backButton = this.addButton("BACK");
		//Add a new button listener to detect button clicks
		backButton.addActionListener(new ButtonListener());
        setHighScore.addActionListener(new ButtonListener());
		
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
            if (source.equals(setHighScore)) 
            {

            }
			
		}
		
	}

}