package graphics;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class High_Score_Screen extends GUI_Panel_Class {
	JLabel titleLabel;
	JButton backButton;
	JLabel placeHolder;
	JPanel mainView;
	
	
	public High_Score_Screen(String stringIn, JPanel mainViewIn) {
		super(stringIn);
		this.mainView = mainViewIn;
		this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
		
		titleLabel = this.addTextElement("HIGH SCORES");
		this.addVerticalSpacing();
		placeHolder = this.addTextElement("TO DO");
		this.addVerticalSpacing();
		backButton = this.addButton("BACK");
		this.addVerticalSpacing();
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
