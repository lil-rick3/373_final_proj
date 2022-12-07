package graphics;

import java.util.concurrent.TimeUnit;

public class Delay_Thread extends Thread {

    private Game game;
    Delay_Thread(Game gameIn) {
        this.game = gameIn;
    }

    public void run(){
        try { //Sleep for one second then launch the monitoring function
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.game.gameThreadMonitoring();
    }
  }
