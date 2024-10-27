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
    private static final int PLAYER_STEP = 8;
    private static final int PLAYER_STEP_ATTACK = 2;
    private static final int HOW_OFTEN_PLAYER_POSITION_UPDATED = 20;
    private static final int PLAYER_HEALTH_POINTS = 20;

    public static int getPlayerStep() {
        return PLAYER_STEP;
    }

    public static int getPlayerAttackStep() {
        return PLAYER_STEP_ATTACK;
    }

    public static int howOftenPlayerPositionIsUpdated() {
        return HOW_OFTEN_PLAYER_POSITION_UPDATED;
    }

    public static int getPlayerHealthPoints() {
        return PLAYER_HEALTH_POINTS;
    }
    
    //OPPONENT
    private static final int OPPONENT_STEP = 2;
    private static final int MINIMUN_DELAY_FOR_CHECKING_PLAYER_POSITION = 100;

    public static int getOpponentStep() {
        return OPPONENT_STEP;
    }

    public static int getMinimumDelayForCheckingPlayerPosition() {
        return MINIMUN_DELAY_FOR_CHECKING_PLAYER_POSITION;
    }


    //SHOP
    private static final int TURRET_BUTTON_WIDTH = 100;
    private static final int TURRET_BUTTON_HEIGHT = 100;
    private static final int ENDING_BUTTON_WIDTH = 100;
    private static final int ENDING_BUTTON_HEIGHT = 100;

    public static int getTurretButtonWidth() {
        return TURRET_BUTTON_WIDTH;
    }

    public static int getTurretButtonHeight() {
        return TURRET_BUTTON_HEIGHT;
    }

    public static int getEndingButtonHeight() {
        return ENDING_BUTTON_HEIGHT;
    }

    public static int getEndingButtonWidth() {
        return ENDING_BUTTON_WIDTH;
    }

    //GAME SCREEN
   
    public static int getScreenWidth() {
        return getMapWidth();
    }

    public static int getScreenHeight() {
        return getMapHeight() + 150; 
    }

    //CURRENCY

    private static final long CURRENCY_FOR_SURVIVING_DEFEDN_PHASE = 10;

    public static long getCurrencyForSurviving() {
        return CURRENCY_FOR_SURVIVING_DEFEDN_PHASE;
    }

    //TURRETS

    private static final long TURRET_PRICE = 10;
    private static final int TURRET_HEALTH_POINTS = 20;
    private static final int TURRET_DELAY = 10000;


    public static long getTurretPrice() {
        return TURRET_PRICE;
    }

    public static int getTurretHealthPoints() {
        return TURRET_HEALTH_POINTS;
    }

    public static int getTurretDelay() {
        return TURRET_DELAY;
    }

    //BULLETS
    private static final int BULLET_STEP = 20;
    private static final int BULLET_POSITION_UPDATE_DELAY = 150;

    public static int getBulletStep() {
        return BULLET_STEP;
    }

    public static int getBulletPositionUpdateDelay() {
        return BULLET_POSITION_UPDATE_DELAY;
    }

    //ENDING
    private static final int ENDING_PRICE = 30;
    
    public static int getEndingPrice() {
        return ENDING_PRICE;
    }

    //TANK
    private static final int TANK_MOVEMENT_DELAY = 100;
    private static final int TANK_STEP = 11;

    public static int getTankDelay() {
        return TANK_MOVEMENT_DELAY;
    }

    public static int getTankStep() {
        return TANK_STEP;
    }

    //SCORE SCREEN
    private static final int SCORE_SCREEN_WIDTH = 800;
    private static final int SCORE_SCREEN_HEIGHT = 700;

    public static int getScoreScreenWidth() {
        return SCORE_SCREEN_WIDTH;
    }

    public static int getScoreScreenHeight() {
        return SCORE_SCREEN_HEIGHT;
    }

}
