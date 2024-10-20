package src.main.java;

/**
 * Contains all constants import for the game.
 */
public class Constants {
    //MAP
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

    public static int getTileWidht() {
        return TILE_WIDTH;
    }

    public static int getTileHeight() {
        return TILE_HEIGHT;
    }

    //PLAYER
    private static final int PLAYER_STEP = 10;
    private static final int HOW_OFTEN_PLAYER_POSITION_UPDATED = 20;

    public static int getPlayerStep() {
        return PLAYER_STEP;
    }

    public static int howOftenPlayerPositionIsUpdated() {
        return HOW_OFTEN_PLAYER_POSITION_UPDATED;
    }
    
}