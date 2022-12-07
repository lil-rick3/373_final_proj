package graphics;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/***
* This is the tutorial screen class. This makes the tutorial screen, and handles back button input
*/


public class Tutorial_Screen extends GUI_Panel_Class {
	JPanel mainView;
	JLabel titleLabel;
	JLabel instructionsLabel1;
	JLabel instructionsLabel2;
	JButton backButton;


	/***
	* This is the constuctor. The inputs are the main card layout (for switching between cards), and the name of the background image file.
	* This calls all the functions to actually create the GUI panel.
	*/
	public Tutorial_Screen(String stringIn, JPanel mainViewIn) {
		super(stringIn);
		this.mainView = mainViewIn;
		
		this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
		
		this.addVerticalSpacing();
		titleLabel = this.addTextElement("TUTORIAL");
		this.addVerticalSpacing();
		instructionsLabel1 = this.addTextElement("WASD to move!");
		this.addVerticalSpacing();
		instructionsLabel2 = this.addTextElement("Spacebar to shoot!");
		this.addVerticalSpacing();
		this.addTextElement("Deliver peace to all the alien scum! :D");
		this.addVerticalSpacing();
		
		backButton = this.addButton("BACK");
		this.addVerticalSpacing();
		
		//Add the action listener for the back button
		backButton.addActionListener(new ButtonListener());
		
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
