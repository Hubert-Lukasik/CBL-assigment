package src.main.java;

import java.util.ArrayList;

/**
 * Class for managing all the turrets on the screen.
 */
public class TurretManager {

    private ArrayList<Turret> turrets;
    
    /**
     * Get amount of all turrets in the game.
     * @return - amount of turrets
     */
    public int getTurretsAmount() {
        return turrets.size();
    }

    /**
     * Add new turret at position (x,y).
     * @param x - x coordinate
     * @param y - y coodinate
     */
    public void addTurret(int x, int y) {
        Turret t = new Turret(x, y);
        turrets.add(t);
    }


    /**
     * Return 2-element array containing coordinates of the nearest turret to position (x, y). 
     * @param x - coordinate x of the given position
     * @param y - coordinate y of the given position
     * @return - array containing coordinate x, y of the nea
     */
    public int[] nearestTurret(int x, int y) {
        int[] closestTurret = new int[2];
        closestTurret[0] = -1;
        closestTurret[1] = -1;
        int minimalDist = Integer.MAX_VALUE;
        
        for (Turret t : turrets) {
            int[] turPos = t.getPosition();
            int actDist = (turPos[0] - x) * (turPos[0] - x) + (turPos[1] - y) * (turPos[1] - y);
            if (actDist <= minimalDist) {
                closestTurret = turPos;
            }
        }

        return closestTurret;
    }

    /**
     * Turn off all turrets.
     */
    public void startPlanPhase() {
        for (Turret t : turrets) {
            t.setOnline(false);
        }
    }

    /**
     * Turn on all turrets.
     */
    public void startDefendPhase() {
        for (Turret t : turrets) {
            t.setOnline(true);
        }
    }

    /**
     * Checks how many turrets are turned on.
     * @return how many turret are online
     */
    public int howManyTurretsOnline() {
        int counter = 0;

        for (Turret t : turrets) {
            if (t.isOnline()) {
                counter += 1;
            }
        }

        return counter;
    }

    /**
    * Constructor for TurretManager class. 
    */
    public TurretManager() {
        this.turrets = new ArrayList<Turret>();
    }
}
