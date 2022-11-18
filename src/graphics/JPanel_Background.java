package graphics;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/***
*This is a supporting class that overrides a JPanel function to allow for panels with custom image backgrounds
*/

class JPanel_Background extends JPanel {
    private Image image;
    /***
    *This is a constructor that takes a passed in image (generated elsewhere)
    */
    public JPanel_Background(Image image) {
        this.image = image;
    }
    
    /***
    *This overrides the paint method to actually paint the background onto the panel
    */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}