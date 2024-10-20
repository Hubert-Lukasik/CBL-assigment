package src.main.java;

/**
 * Contains all constants import for the game.
 */
public class Constants {
    private static final int TILES_IN_COLUMN = 20;
    private static final int TILES_IN_ROW = 30;
    private static final int TILE_HEIGHT = 40;
    private static final int TILE_WIDTH = 40;

    public static int getMapHeight() {
        return TILES_IN_COLUMN * TILE_HEIGHT;
    }

    public static int getMapWidth() {
        return TILES_IN_ROW * TILE_WIDTH;
    }
    
}
