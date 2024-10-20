package src.main.java;

import java.awt.*;
import javax.swing.*;

/**
 * Contains methods and variables relevant for in-game shop.
 */
public class Shop {
    private boolean isShown;
    private JButton buyTurretButton;
    private Player player;

    public boolean isShown() {
        return this.isShown;
    }

    public void hideShop() {
        isShown = false;
    }

    /**
     * Let the program know shop should be shown.
     * @param p - Player object
     */
    public void showShop() {
        isShown = true;
    }

    /**
     * Constructor for Shop class.
     */
    public Shop(Player p) {
        isShown = false;
        buyTurretButton = new JButton(new ImageIcon("files/turretTest.png"));
        player = p;
    }

    /**
     * Drawing method for shop.
     * @param g - used by Swing
     */
    public void draw(Graphics g, Painter p) {
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

            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 15));
            g2d.drawString("Your current balance: " + player.getCurrency() , 300, Constants.getMapHeight() + 20);
        
        } else {
            p.remove(buyTurretButton);
        }
    }
}
