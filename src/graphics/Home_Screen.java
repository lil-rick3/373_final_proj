package graphics;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;



public class Home_Screen extends GUI_Panel_Class {
	JButton newGame;
	JButton tutorial;
	JButton highScore;
	JPanel mainView;
	
	public Home_Screen(String backgroundIn,  JPanel mainViewIn, Thread threadIn) { //Pass in the name of the background image
		super(backgroundIn);
		this.mainView = mainViewIn;
		//Set the desired layout
		this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
		//Add desired GUI elements
		this.addVerticalSpacing();
		this.addTextElement("GALACTIC");
		this.addTextElement("PEACEMAKER");
		this.addVerticalSpacing();
		newGame = this.addButton("NEW GAME");
		this.addVerticalSpacing();
		highScore = this.addButton("VIEW HIGH SCORE");
		this.addVerticalSpacing();
		tutorial = this.addButton("TUTORIAL");
		this.addVerticalSpacing();
		
		//register buttons with action listener
		newGame.addActionListener(new ButtonListener(threadIn));
		tutorial.addActionListener(new ButtonListener(threadIn));
		highScore.addActionListener(new ButtonListener(threadIn));
	}
	
	private class ButtonListener implements ActionListener
	{
		private Thread gameThread;
		
		public ButtonListener(Thread threadIn) {
			this.gameThread = threadIn;
		}
		
		public void actionPerformed(ActionEvent e) //this is the method MenuListener must implement, as it comes from the ActionListener interface.
		{
			JButton source = (JButton)(e.getSource());
			
			if(source.equals(tutorial))
			{
			    CardLayout temp = (CardLayout)(mainView.getLayout());
			    temp.show(mainView, "TUTORIAL");
			}
			else if (source.equals(highScore)) {
				CardLayout temp = (CardLayout)(mainView.getLayout());
			    temp.show(mainView, "HIGHSCORE");
			}
			else if (source.equals(newGame)) {
				CardLayout temp = (CardLayout)(mainView.getLayout());
				temp.show(mainView, "GAME");
				this.gameThread.start();
			}
			
		}
		
	}

}
