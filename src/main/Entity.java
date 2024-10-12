package src.main;

/**
 * Class defines all methods for mechanics shared by all entities in the game.
 */
public abstract class Entity {
    private int healthPoints = 0;
    private double posX = 0;
    private double posY = 0;

    /**
     * Initialise amount of health points the entity has.
     * @param hp - starting amount of health points of the entity
     */
    public void setHealthPoints(int hp) {
        healthPoints = hp;
    }

    public int getHealthPoints() {
        return healthPoints; 
    }

    /**
     * Change health points of the entity.
     * @param damage - damage taken (negavite damage increases hps)
     */
    public void takeDamage(int damage) {
        healthPoints = healthPoints - damage;
    }

    public boolean isDead() {
        return (healthPoints <= 0);
    }

    /**
     * Sets position of the entity.
     * @param x - coordinate x of the new position
     * @param y - coordinate y of the new position
     */
    public void setPosition(double x, double y) {
        posX = x;
        posY = y;
    }

    /**
     * Get the entity position.
     * @return - two-element array with coordinates (X, Y)
     */
    public double[] getPosition() {
        double[] pos = new double[2];
        pos[0] = posX;
        pos[1] = posY;
        return pos;
    }


}
