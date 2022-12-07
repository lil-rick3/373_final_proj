package graphics;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/***
* This is the high score screen class. This makes the high score screen, and will handle high score logic in the future.
*/

public class Set_High_Score extends GUI_Panel_Class {
	JButton submitButton;
	JLabel placeHolder;
	JPanel mainView;
    JTextField nameEntrySpace;
	High_Scores_Object HSO;
	
	/***
	* This is the constuctor. The inputs are the main card layout (for switching between cards), and the name of the background image file.
	* This calls all the functions to actually create the GUI panel.
	*/
	public Set_High_Score(String stringIn, JPanel mainViewIn, High_Scores_Object HsIn) {
		super(stringIn);
		this.HSO = HsIn;
		this.mainView = mainViewIn;
		//Uses the BoxLayout so we have all elements displayed vertically
		this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
		//Adding the name entry field to the created high score screen
        nameEntrySpace = new JTextField("GIVE ME YOUR NAME"); 
		//Add all of the desired GUI elements
		this.addVerticalSpacing();
		submitButton = this.addButton("SUBMIT");
		this.getPanel().add(nameEntrySpace);
		//Add a new button listener to detect button clicks
		submitButton.addActionListener(new ButtonListener(this.mainView));
	}



	/***
	* This is the custom ButtonListener class. This listens for the back button being clicked, and re-directs the user to the main menu.
	*/
	private class ButtonListener implements ActionListener
	{
		JPanel mainView;
		ButtonListener(JPanel mainViewIn) {
			this.mainView = mainViewIn;
		}
		/***
		* Catches button click and flips to the corresponding card page
		*/
		public void actionPerformed(ActionEvent e) 
		{
			JButton source = (JButton)(e.getSource());
			
			if(source.equals(submitButton))
			{
				//Do exception handling/error validation on entry
				if (!this.validateNameEntry(nameEntrySpace.getText())) {

				}
				else {
					//Add the inputed values to the array list
                    // this.HSO.


					CardLayout temp = (CardLayout)(mainView.getLayout());
					temp.show(mainView, "HOME");
				}

			}		
		}

		private boolean validateNameEntry(String nameIn) { //Validates the user inputed name. If it's valid, returns true. If it's invalid, returns false
			if (nameIn.length() == 0 || nameIn.length() > 15) {
				JOptionPane.showMessageDialog(this.mainView, "Invalid entry. Must be between 0 and 15 characters.");
				return false;
			}
			else {
				return true;
			}
		}
	}



}
