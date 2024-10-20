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
        g.drawLine(Constants.getMapHeight(), 0, Constants.getMapHeight(), Constants.getMapWidth());
    }
}
