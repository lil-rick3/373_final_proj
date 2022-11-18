package graphics;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Audio.AudioPlayer;

/***
 *This is the main entry point class that creates the GUI elements (including the game panel), and sound objects. It puts these elemnts
 into a card layout, so we can easily flip between them to display what we want to at the correct times.
 */

public class Game {
	//Create the main GUI control panel
	JPanel mainView = new JPanel(new CardLayout());
	
	//Creates the game. A seperate thread class is used so we don't run into garbage collection issues when running the game from child classes
	Space_GUI_Thread game = new Space_GUI_Thread();
	Thread gameThread = new Thread(game);

	//Creates the GUI Pages
	Home_Screen homeScreen; 
	Tutorial_Screen tutorial =  new Tutorial_Screen("starbackground.jpg", mainView);
	High_Score_Screen highScoreScreen = new High_Score_Screen("starbackground.jpg", mainView); //inherits form GUI_Panel_Class

	//Sound player
	AudioPlayer musicPlayer;
	
	public Game() {
		
		//Initialize the sound player class on game creation
		try
	    {
	    	System.out.println("test");
	        musicPlayer = new AudioPlayer();
			musicPlayer.pause();
	    } 
	    catch (Exception ex) 
	    {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
		}

		//Create the main home_screen panel
		homeScreen = new Home_Screen("starbackground.jpg", mainView, gameThread, musicPlayer);
		//Call the rest of the GUI startup functions
		this.createGame();
	}

	/***
 	* Sets up the rest of the GUI including the frame, and putting all the panels into the correct card layout
 	*/
	public void createGame() {
		
		//create main game frame
		JFrame frame = new JFrame("GALACTIC PEACEMAKER");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);

		//add cards to the card panel, the string is used to identify what card we want to show
		mainView.add(homeScreen.getPanel(), "HOME");
		//The below panels were all created in the variable declerations
		mainView.add(tutorial.getPanel(), "TUTORIAL");
		mainView.add(highScoreScreen.getPanel(), "HIGHSCORE");
		mainView.add(this.game.getPanel(), "GAME");
		
		//add card panel to the frame
		frame.add(mainView);
		
		//make frame visible
		frame.setVisible(true);
	}
}
