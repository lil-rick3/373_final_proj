package graphics;

import java.util.concurrent.TimeUnit;

public class Delay_Thread extends Thread {

    private Space_Gui game;
    Delay_Thread(Space_Gui gameIn) {
        this.game = gameIn;
    }

    public void run(){
        try { //Sleep for one second then launch the monitoring function
            TimeUnit.NANOSECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.game.run();
    }
  }
