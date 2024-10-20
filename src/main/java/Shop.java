package src.main.java;

/**
 * Contains methods and variables relevant for in-game shop.
 */
public class Shop {
    private boolean isShown = false;

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
        Phases.informAboutShop(this);
    }
}
