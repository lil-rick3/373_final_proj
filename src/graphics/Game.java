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
	JFrame frame = new JFrame("GALACTIC PEACEMAKER"); //main frame
	//Highscore object, for managing highscores
	High_Scores_Object HSObject = new High_Scores_Object();
	

	//Create the main GUI control panel
	JPanel mainView = new JPanel(new CardLayout());
	//Creates the GUI Pages
	Home_Screen homeScreen; 
	Tutorial_Screen tutorial =  new Tutorial_Screen("starbackground.jpg", mainView);
	High_Score_Screen highScoreScreen = new High_Score_Screen("starbackground.jpg", mainView, HSObject); //inherits form GUI_Panel_Class
	Game_Over gameOverScreen = new Game_Over("starbackground.jpg", mainView); //inherits form GUI_Panel_Class
	You_Win youWinScreen = new You_Win("starbackground.jpg", mainView); //inherits form GUI_Panel_Class
	Set_High_Score setScore = new Set_High_Score("starbackground.jpg", mainView, HSObject, frame);

	//Sound player
	AudioPlayer musicPlayer;
	Space_Gui game;
	int score = 0;


	
	
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
		homeScreen = new Home_Screen("starbackground.jpg", mainView, musicPlayer,this);
		//Call the rest of the GUI startup functions
		this.createGame();
	}

	/***
 	* Sets up the rest of the GUI including the frame, and putting all the panels into the correct card layout
 	*/
	public void createGame() {
		
		//create main game frame
	
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);

		//add cards to the card panel, the string is used to identify what card we want to show
		mainView.add(homeScreen.getPanel(), "HOME");
		//The below panels were all created in the variable declerations
		mainView.add(tutorial.getPanel(), "TUTORIAL");
		mainView.add(highScoreScreen.getPanel(), "HIGHSCORE");
	

		mainView.add(gameOverScreen.getPanel(), "OVER");
		mainView.add(youWinScreen.getPanel(), "WIN");
		mainView.add(setScore.getPanel(), "SET");
		
		//add card panel to the frame
		frame.add(mainView);
		
		//make frame visible
		frame.setVisible(true);

	}

	//Handling for launching the game. Done up here to not have to pass tons of variables down
	public void startGame() {
		this.game = new Space_Gui(this, this.musicPlayer);
		// this.musicPlayer.play();
		mainView.add(game, "GAME");
		CardLayout temp = (CardLayout)(mainView.getLayout());
		temp.show(mainView, "GAME");
		Delay_Thread delayThread = new Delay_Thread(game);
		delayThread.start();
		game.requestFocusInWindow();
	}

	public void loseScreen() {
		CardLayout temp = (CardLayout)(mainView.getLayout());
		this.HSObject.setHighScore(this.game.currentGame.getScore());
		temp.show(mainView, "OVER");
	}

	public void winScreen() {
		CardLayout temp = (CardLayout)(mainView.getLayout());
		this.HSObject.setHighScore(this.game.currentGame.getScore());
		temp.show(mainView, "WIN");
	}

	public void updateHighScores() {
		this.highScoreScreen.makeHighScore();
	}

}

