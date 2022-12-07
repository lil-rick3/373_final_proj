package graphics;

/***
*This is a supporting thread class we put the main game on, so when we activate the game from the Home_Screen thread garbage collection doesn't turn
*it off. The game thread runs in parallel to the GUI thread
*/

public class Space_GUI_Thread implements Runnable {
    private Space_Gui panel;
    private Game gameAbove;
    
    /***
    *This is the constructor, creates the new Space_Gui, which contains a Space_Game which handles all of the game logic
    */
    public Space_GUI_Thread(Game gameIn){
        this.panel = new Space_Gui(); //temporary placeholder for display
        this.gameAbove = gameIn;
        this.initNewGame();
    }


    public void initNewGame() { //Constructs a new game, letting us start over from the defeat main menu
        this.panel.newSpaceGame();
    }
    /***
    *Getter method to get the panel to add to the card layout display
    */
    public Space_Gui getPanel() {
        return this.panel;
    }

    /***
    *Override the run method of the runnable class, so when we start the thread running we call our own custom functions
    */
    @Override
    public void run() {
        //This line changes the focus to the currently active panel, so the key listener correctly picks up key input
        this.panel.requestFocusInWindow();
        //Start the game logic running
        this.panel.run();
    }
 }