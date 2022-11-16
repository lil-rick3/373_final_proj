package graphics;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Tutorial_Screen extends GUI_Panel_Class {
	JPanel mainView;
	JLabel titleLabel;
	JLabel instructionsLabel1;
	JLabel instructionsLabel2;
	JButton backButton;
	
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
		
		backButton = this.addButton("BACK");
		this.addVerticalSpacing();
		
		//Add the action listener for the back button
		backButton.addActionListener(new ButtonListener());
		
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) //this is the method MenuListener must implement, as it comes from the ActionListener interface.
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



