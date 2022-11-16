package graphics;

//Needs to be on it's own thread so we don't get conflicts with GUI listeners.
//This should help later too when we're adding a pause menu.

public class Space_GUI_Thread implements Runnable {
    private Space_Gui panel;
    public Space_GUI_Thread(){
        this.panel = new Space_Gui(); //temporary placeholder for display
    }

    public Space_Gui getPanel() {
        return this.panel;
    }

    @Override
    public void run() {
        this.panel.requestFocusInWindow();
        this.panel.run();
    }
 }