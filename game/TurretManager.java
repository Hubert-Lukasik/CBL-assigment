package game;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Class for managing all the turrets on the screen.
 */
public class TurretManager implements ActionListener {

    private ArrayList<Turret> turrets;
    private ArrayList<Bullet> bullets;
    private Timer shootingTimer;
    private Timer updateBulletPosition;

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
    * Add new bullet targeting the nearest opponent.  
    */
    private void addBullet(int x, int y) {

        int[] nearestOpponent = Opponent.findNearestOpponent(x, y);
        
        //there is at least one opponent to target
        if (nearestOpponent[0] > -1) {
            Bullet b = new Bullet(x, y, nearestOpponent[0], nearestOpponent[1]);
            bullets.add(b);
        }
    } 


    /**
     * ActionListener for genereting new bullets 
     * in the game and updating position of existing ones.
     * @param t - ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent t) {
        if (t.getSource() == shootingTimer) {
            //shoot
            for (int i = 0; i < turrets.size(); ++i) {
                if (i >= turrets.size()) {
                    break;
                }

                Turret tur = turrets.get(i);
                
                if (tur.isDead()) {
                    turrets.remove(tur);
                    Entity.kill(tur);
                } else {
                    addBullet(tur.getPosition()[0], tur.getPosition()[1]);
                }
            }
        }

        if (t.getSource() == updateBulletPosition) {
            ArrayList<Bullet> bulletsStaying = new ArrayList<Bullet>();
            for (Bullet b : bullets) {
                if (b.updatePosition()) {
                    bulletsStaying.add(b);
                }
            }

            bullets = bulletsStaying;
        }
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
        shootingTimer.stop();
        updateBulletPosition.stop();
        bullets.clear();

        for (Turret t : turrets) {
            t.setOnline(false);
        }
    }

    /**
     * Turn on all turrets.
     */
    public void startDefendPhase() {
        shootingTimer.start();
        updateBulletPosition.start();

        for (Turret t : turrets) {
            t.setOnline(true);
            t.setHealthPoints(Constants.getTurretHealthPoints());
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
     * Draw method for the bullet.
     * @param g - used by Swing
     * @param p - game panel
     */
    public void draw(Graphics g, JPanel p) {
        for (int i = 0; i < turrets.size(); ++i) {
            if (i >= turrets.size()) {
                break;
            }

            turrets.get(i).draw(g, p);
        }
        
        for (int i = 0; i < bullets.size(); ++i) {
            if (i >= bullets.size()) {
                break;
            }
            bullets.get(i).draw(g, p);
        }
    }

    

    /**
    * Constructor for TurretManager class. 
    */
    public TurretManager() {
        this.turrets = new ArrayList<Turret>();
        this.bullets = new ArrayList<Bullet>();
        this.shootingTimer = new Timer(Constants.getTurretDelay(), this);
        this.updateBulletPosition = new Timer(Constants.getBulletPositionUpdateDelay(), this);
    }

    
}
