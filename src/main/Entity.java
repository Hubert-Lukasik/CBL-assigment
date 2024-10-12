package src.main;

/**
 * Class defines all methods for mechanics shared by all entities in the game.
 */
public abstract class Entity {
    private int healthPoints = 0;


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

    
}
