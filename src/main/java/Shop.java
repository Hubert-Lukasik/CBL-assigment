package src.main.java;

import java.awt.*;

/**
 * Contains methods and variables relevant for in-game shop.
 */
public class Shop {
    private boolean isShown;

    public boolean isShown() {
        return this.isShown;
    }

    public void hideShop() {
        isShown = false;
    }

    public void showShop() {
        isShown = true;
    }

    public Shop() {
        isShown = false;
    }

    /**
     * Drawing method for shop.
     * @param g - used by Swing
     */
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; 
        Stroke str = new BasicStroke(4f);
        g2d.setStroke(str);
        g2d.setColor(Color.GREEN);
        g2d.drawLine(0, Constants.getMapHeight(), 
            Constants.getMapWidth(), Constants.getMapHeight());
    }
}
