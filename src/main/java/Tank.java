package src.main.java;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Class defining methods for Tank object (used only in the ending).
 */
public class Tank extends Entity implements ActionListener {
    private Player player;
    private Timer updatePosition;
    private int playerX; 
    private boolean playerHasEnteredTank; 
    private String directionPlayerHasToEnter = "s";
    private ArrayList<Integer> overrunnedX; 
    private ArrayList<Integer> overrunnedY; 
    private Entity overrunedTile;

    public String getDirectionOfPlayerEnteringTank() {
        return directionPlayerHasToEnter;
    }

    private void addOverrunnedTile(int x, int y) {
        overrunnedX.add(x);
        overrunnedY.add(y + this.getImage().getHeight() - 30);
    }

    /**
     * ActionListener for updating tank position.
     */
    public void actionPerformed(ActionEvent t) {
        if (t.getSource() == updatePosition) {
            int[] currentTankPosition = this.getPosition();

            //Go to the player, wait a moment for the player to "enter" and continue going
            if (currentTankPosition[0] == playerX && !playerHasEnteredTank) {
                player.hide();
                playerHasEnteredTank = true;
            } else if (!playerHasEnteredTank) {
                if (playerX - currentTankPosition[0] <= this.getImage().getWidth()) {
                    player.setImage("player_" + directionPlayerHasToEnter);
                }

                this.setPosition(currentTankPosition[0] + Math.min(Constants.getTankStep(), 
                    playerX - currentTankPosition[0]), currentTankPosition[1]);
            } else {
                for (int i = 0; i < 5; ++i) {
                    Opponent.endingAddOpponent();
                }

                this.setPosition(currentTankPosition[0] + Constants.getTankStep(), 
                    currentTankPosition[1]);
                
                player.moveTogetherWithTank(this.getPosition()[0], this.getPosition()[1]);
            }

            addOverrunnedTile(this.getPosition()[0], this.getPosition()[1]);

        }
    }

    /**
     * Draw method for tank and overrunned tiles.
     * @param g - used by Swing
     * @param p - JPanel currently displayed in JFrame
     */
    public void draw(Graphics g, JPanel p) {
        for (int ind = 0; ind < overrunnedX.size(); ++ind) {
            g.drawImage(overrunedTile.getImage(), overrunnedX.get(ind), overrunnedY.get(ind), p);
        }
    
        g.drawImage(this.getImage(), this.getPosition()[0], this.getPosition()[1], p);
    }

    private void initialisePosition() {
        int[] playerPos = player.getPosition();
        playerX = playerPos[0] - player.getImage().getWidth() / 2;

        //start on the left side of the screen
        
        if (playerPos[1] > 200) {
            this.setPosition(0, 
                playerPos[1] - player.getImage().getHeight() - 20);
            directionPlayerHasToEnter = "n";
        } else {
            this.setPosition(0, playerPos[1]);
            directionPlayerHasToEnter = "s";
        }

        addOverrunnedTile(this.getPosition()[0], this.getPosition()[1]);
    }

    /**
     * Constructor for Tank class.
     * @param p - Player object
     */
    public Tank(Player p) {
        player = p;
        this.setImage("tank");
        playerHasEnteredTank = false;
        

        //overrunedTile is not really an opponent, I just needed some object inheriting from Entity
        overrunedTile = new Opponent();
        overrunedTile.setImage("runnedOver");
        overrunnedX = new ArrayList<Integer>();
        overrunnedY = new ArrayList<Integer>();

        this.initialisePosition();
        updatePosition = new Timer(Constants.getTankDelay(), this);
        updatePosition.start();
    }
    
}
