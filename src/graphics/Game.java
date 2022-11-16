package graphics;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Audio.SimpleAudioPlayer;

public class Game {
	//Create the main GUI control panel
	JPanel mainView = new JPanel(new CardLayout());
	
	//Creates the game
	Space_GUI_Thread game = new Space_GUI_Thread();
	Thread gameThread = new Thread(game);

	//Creates the GUI Pages
	Home_Screen homeScreen; 
	Tutorial_Screen tutorial =  new Tutorial_Screen("starbackground.jpg", mainView);
	High_Score_Screen highScoreScreen = new High_Score_Screen("starbackground.jpg", mainView); //inherits form GUI_Panel_Class

	//Sound player
	SimpleAudioPlayer musicPlayer;
	
	public Game() {
		
		try
	    {
	    	System.out.println("test");
	        musicPlayer = new SimpleAudioPlayer();
			musicPlayer.pause();
	        //musicPlayer.play(); 
	    } 
	    catch (Exception ex) 
	    {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
		}
		homeScreen = new Home_Screen("starbackground.jpg", mainView, gameThread, musicPlayer);
		this.createGame();
	}

	
	public void createGame() {
		
		//create main game frame
		JFrame frame = new JFrame("GALACTIC PEACEMAKER");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);

		//add sound to the frame
		
		
		
		//add cards to the card panel, the string is used to identify what card we want to show
		mainView.add(homeScreen.getPanel(), "HOME");
		mainView.add(tutorial.getPanel(), "TUTORIAL");
		mainView.add(highScoreScreen.getPanel(), "HIGHSCORE");
		mainView.add(this.game.getPanel(), "GAME");
		
		//add card panel to the frame
		frame.add(mainView);
		
		//make frame visible
		frame.setVisible(true);
	}
}
