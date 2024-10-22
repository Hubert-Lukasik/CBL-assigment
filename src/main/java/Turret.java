package src.main.java;

/**
 * Class describing all methods and variables relating to turrets.
 */
public class Turret extends Entity {
    private boolean online;

    /**
     * Sets turrets online or offline.
     * @param isOnline - true if turret should be online, false otherwise
     */
    public void setOnline(boolean isOnline) {
        online = isOnline;
    }

    /**
     * Checks whether the turret is online.
     * @return true if turret is online, false otherwise
     */
    public boolean isOnline() {
        return online;
    }

    /**
     * Constructor for Turret class.
     * @param x - coordinate x of the turret
     * @param y - coordinate y of the turret
     */
    public Turret(int x, int y) {
        this.setPosition(x, y);
        this.setImage("turret.png");
        this.setHealthPoints(Constants.getTurretHealthPoints());
    }
}
