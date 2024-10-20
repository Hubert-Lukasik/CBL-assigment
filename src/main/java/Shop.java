package src.main.java;

import java.awt.*;
import javax.swing.*;

/**
 * Contains methods and variables relevant for in-game shop.
 */
public class Shop {
    private boolean isShown;
    private JButton buyTurretButton;

    public boolean isShown() {
        return this.isShown;
    }

    public void hideShop() {
        isShown = false;
    }

    /**
     * Let the program know shop should be shown.
     */
    public void showShop() {
        isShown = true;
        buyTurretButton = new JButton(new ImageIcon("files/turretTest.png"));
    }

    /**
     * Constructor for Shop class.
     */
    public Shop() {
        isShown = false;

    }

    /**
     * Drawing method for shop.
     * @param g - used by Swing
     */
    public void draw(Graphics g, Painter p) {
        System.out.println(this.isShown());
        if (this.isShown()) {
            Graphics2D g2d = (Graphics2D) g; 

            //draw line
            Stroke str = new BasicStroke(4f);
            g2d.setStroke(str);
            g2d.setColor(Color.GREEN);
            g2d.drawLine(0, Constants.getMapHeight(), 
                Constants.getMapWidth(), Constants.getMapHeight());
        
            buyTurretButton.setBounds(20, Constants.getMapHeight(), 
                20 + Constants.getTurretButtonWidth(), 
                    Constants.getTileWidht() + Constants.getTurretButtonHeight());
            p.add(buyTurretButton);
        } else {
            p.remove(buyTurretButton);
        }
    }
}
