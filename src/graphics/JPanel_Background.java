package graphics;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;
import javax.swing.JPanel;

//supporting class for setting the backgrounds of JPanels. Overwrites the paintComponent to paint on a background
class JPanel_Background extends JPanel {
    private Image image;
    public JPanel_Background(Image image) {
        this.image = image;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}