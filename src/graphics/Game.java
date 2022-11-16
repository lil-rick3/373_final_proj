package graphics;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game {
	//Create the main GUI control panel
	JPanel mainView = new JPanel(new CardLayout());
	
	//Creates the GUI pages
	Space_Gui gameScreen = new Space_Gui();
	Home_Screen homeScreen = new Home_Screen("starbackground.jpg", mainView, gameScreen);
	Tutorial_Screen tutorial =  new Tutorial_Screen("starbackground.jpg", mainView);
	High_Score_Screen highScoreScreen = new High_Score_Screen("starbackground.jpg", mainView); //inherits form GUI_Panel_Class

	
	public Game() {
		this.createGame();
	}

	
	public void createGame() {

		//create main game frame
		JFrame frame = new JFrame("GALACTIC PEACEMAKER");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		
		
		//add cards to the card panel, the string is used to identify what card we want to show
		mainView.add(homeScreen.getPanel(), "HOME");
		mainView.add(tutorial.getPanel(), "TUTORIAL");
		mainView.add(highScoreScreen.getPanel(), "HIGHSCORE");
		mainView.add(gameScreen, "GAME");

		
		
		//add card panel to the frame
		frame.add(mainView);
		
		//make frame visible
		frame.setVisible(true);
		
		//Link the main panel into the rest of the game
		
	}
}
