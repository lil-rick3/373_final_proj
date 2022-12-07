package graphics;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import Audio.AudioPlayer;


/***
* This is the home screen class. This has 3 buttons that re-direct to the other GUI elements. All GUI elements are created here, along with
* triggering the main game thread and activating the music player.
*/
public class Home_Screen extends GUI_Panel_Class {
	JButton newGame;
	JButton tutorial;
	JButton highScore;
	JPanel mainView;
	JPanel highScoreScreen;
	AudioPlayer musicPlayer;

	/***
	* This is the constuctor. The inputs are the main card layout (for switching between cards), the name of the background image file,
	* the game thread, and the audioplayer.
	* This calls all the functions to actually create the GUI panel. 
	* It also passes the correct things to the action listener so we can initialize the game and music
	*/
	public Home_Screen(String backgroundIn,  JPanel mainViewIn, Thread threadIn, AudioPlayer musicPlayer, Space_GUI_Thread spaceGameIn, Game gameIn, Delay_Thread delayThreadIn) { //Pass in the name of the background image
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
		this.musicPlayer = musicPlayer;

		//Register buttons with action listener
		newGame.addActionListener(new ButtonListener(threadIn, spaceGameIn, gameIn, delayThreadIn));
		tutorial.addActionListener(new ButtonListener(threadIn, spaceGameIn, gameIn, delayThreadIn));
		highScore.addActionListener(new ButtonListener(threadIn, spaceGameIn, gameIn, delayThreadIn));
	}
	
	/***
	* This is the custom ButtonListener class. This listens for any of the buttons being clicked, and calls the corresponding function.
	*/
	private class ButtonListener implements ActionListener
	{
		private Thread gameThread;
		private Space_GUI_Thread game;
		private Game gameAbove;
		private Delay_Thread delayThread;
		
		/***
		* This is the constructor, just updates the inputed thread so we can actually start running the game
		*/

		public ButtonListener(Thread threadIn, Space_GUI_Thread spaceIn, Game gameIn, Delay_Thread  delayThreadIn) {
			this.gameThread = threadIn;
			this.game = spaceIn;
			this.gameAbove = gameIn;
			this.delayThread = delayThreadIn;
		}
		
		/***
		* Catches button click and flips to the corresponding card page, calling nescessary functions in the case of game start
		*/
		public void actionPerformed(ActionEvent e) //this is the method MenuListener must implement, as it comes from the ActionListener interface.
		{
			JButton source = (JButton)(e.getSource());
			
			if(source.equals(tutorial))
			{
			    //Flip to the tutorial page
				CardLayout temp = (CardLayout)(mainView.getLayout());
			    temp.show(mainView, "TUTORIAL");
			}
			else if (source.equals(highScore)) {
				//Flip to the high score page
				CardLayout temp = (CardLayout)(mainView.getLayout());
			    temp.show(mainView, "HIGHSCORE");
			}
			else if (source.equals(newGame)) {
				//Init the new game
				this.game.initNewGame();
				//Add it to the main layout
				mainView.add(this.game.getPanel(), "GAME");
				//Flip to the active game panel
				CardLayout temp = (CardLayout)(mainView.getLayout());
				temp.show(mainView, "GAME");
				//Start the music player
				musicPlayer.play();
				//Start the game thread running
				this.gameThread.start();
				this.delayThread.start();
			}
		}
	}
}
