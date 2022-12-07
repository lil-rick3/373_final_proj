package graphics;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/***
* This is the screen that is used to set the highscores. Where hte player enters their name. THis is called after the win screen or game over
* screen if the user chooses to save their score.
*/

public class Set_High_Score extends GUI_Panel_Class {
	JButton submitButton;
	JLabel placeHolder;
	JPanel mainView;
    JTextArea nameEntrySpace;
	
	/***
	* This is the constuctor. The inputs are the main card layout (for switching between cards), and the name of the background image file.
	* This calls all the functions to actually create the GUI panel. It is also passed in the frame and highscore object, the frame for
	* making exceptions, and hte highscore object for actually updating saved values.
	*/
	public Set_High_Score(String stringIn, JPanel mainViewIn, High_Scores_Object HsIn, JFrame frameIn) {
		super(stringIn);
		this.mainView = mainViewIn;
		//Uses the BoxLayout so we have all elements displayed vertically
		this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
		this.addTextElement(" ");
		this.addTextElement("GIVE ME YOUR NAME");
		this.addTextElement(" ");
		this.addTextElement(" ");
		this.addTextElement(" ");
		this.addTextElement(" ");

		//Adding the name entry field to the created high score screen
        nameEntrySpace = new JTextArea(5, 5);
		//Add all of the desired GUI elements
		this.addTextElement(" ");
		this.getPanel().add(nameEntrySpace);
		this.addTextElement(" ");
		this.addTextElement(" ");
		this.addTextElement(" ");
		this.addTextElement(" ");
		submitButton = this.addButton("SUBMIT");
		this.addTextElement(" ");
		this.addVerticalSpacing();
		//Add a new button listener to detect button clicks
		submitButton.addActionListener(new ButtonListener(this.mainView, HsIn, frameIn));
	}



	/***
	* This is the custom ButtonListener class. This listens for the back button being clicked, and re-directs the user to the main menu.
	*/
	private class ButtonListener implements ActionListener
	{
		JPanel mainView;
		High_Scores_Object HSO;
		JFrame frame;
		ButtonListener(JPanel mainViewIn, High_Scores_Object HSO, JFrame frameIn) {
			this.mainView = mainViewIn;
			this.HSO = HSO;
			this.frame = frameIn;
		}
		/***
		* Catches button click and flips to the corresponding card page
		*/
		public void actionPerformed(ActionEvent e) 
		{
			JButton source = (JButton)(e.getSource());
			
			if(source.equals(submitButton))
			{
				String text = nameEntrySpace.getText();
				//Do exception handling/error validation on entry
				if (!this.validateNameEntry(text)) {

				}
				else {
					//Add the inputed values to the array list
                    String tempString = String.valueOf(this.HSO.getHighScore()) + ":" + text;
					this.HSO.addAndSave(tempString);
					CardLayout temp = (CardLayout)(mainView.getLayout());
					temp.show(mainView, "HOME");
				}
			}		
		}

		// This method is used to validate the entered name, and handle if it's invalid.
		private boolean validateNameEntry(String nameIn) { //Validates the user inputed name. If it's valid, returns true. If it's invalid, returns false
			if (nameIn.length() == 0 || nameIn.length() > 15) {
				JOptionPane.showMessageDialog(this.frame, "Invalid entry. Must be between 1 and 15 characters.");
				return false;
			}
			else {
				return true;
			}
		}
	}



}
