package game;

//import game.Constants;
import java.awt.*;
import javax.swing.*;

/**
 * Class responsible for drawing the bad ending.
 */
public class Badending extends JPanel {

    public void update() {
        this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        Graphics2D g2d = (Graphics2D) g; 
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g2d.drawString("You are dead, not big surprise", 
            200, Constants.getScreenHeight() / 2);
    }
}
