package graphics;

import java.util.concurrent.TimeUnit;

/***
 *This is a thread that starts a delay and then runs the game loop at the end of that delay. This is usesd to bypass issues with the
card layout, so the GUI transisitons are smoth
 */

public class Delay_Thread extends Thread {

    private Space_Gui game;
    //Constructor, sets the uppest level of game to be contained in the thread.
    Delay_Thread(Space_Gui gameIn) {
        this.game = gameIn;
    }

    public void run(){ //Method that is run when the thread is started
        try { //Sleep for one second then launch the monitoring function
            TimeUnit.NANOSECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.game.run();
    }
    
  }
