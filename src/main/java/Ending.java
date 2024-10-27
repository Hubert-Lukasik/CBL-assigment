package src.main.java;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Class gives the methods to run the ending of the game.
 */
public class Ending extends JPanel implements ActionListener {
    private Map map;
    private Player player;
    private TurretManager turretManager;
    private Tank tank;
    private Timer hasTankReachedTheEndTimer;
    private boolean endingHasFinished;
    private Timer drawOpponents;
    boolean opponentsAreDrawn;

    public boolean hasEndingFinished() {
        return endingHasFinished;
    }

    public void update() {
        this.repaint();
    }

    /**
     * ActionListener for checking whether the tank reached the end of the screen.
     */
    public void actionPerformed(ActionEvent t) {
        if (t.getSource() == hasTankReachedTheEndTimer) {
            if (tank.getPosition()[0] > Constants.getMapWidth() + tank.getImage().getWidth()) {
                hasTankReachedTheEndTimer.stop();
                endingHasFinished = true;
            }
        }

        if (t.getSource() == drawOpponents) {
           opponentsAreDrawn = true;
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        map.draw(g, null);
        turretManager.draw(g, this);

        if (tank.getDirectionOfPlayerEnteringTank().equals("s")) {
            player.draw(g, this);
            tank.draw(g, this);
        } else {
            tank.draw(g, this);
            player.draw(g, this);
        }

        if (opponentsAreDrawn) {
            Opponent.drawEndingOpponents(g, this);
        }
    }

    /**
     * Constructor for Ending.
     * @param m - Map object
     * @param p - Player object
     * @param tm - TurretManager object
     */
    public Ending(Map m, Player p, TurretManager tm) {
        this.map = m;
        this.player = p;
        this.turretManager = tm;
        player.setImage("player/player_w");
        tank = new Tank(p);
        hasTankReachedTheEndTimer = new Timer(100, this);
        hasTankReachedTheEndTimer.start();
        endingHasFinished = false;
        drawOpponents = new Timer(200, this);
        drawOpponents.start();
        opponentsAreDrawn = true;
    }   
}
