package src.main.java;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import org.junit.internal.RealSystem;

/**
 * Contains methods and variables relevant for in-game shop.
 */
public class Shop implements ActionListener, MouseListener {
    private boolean isShown;
    private JButton buyTurretButton;
    private JButton unlockEndingButton;
    private Player player;
    private TurretManager turretManager;
    private String messageToUser;
    private Timer showMessageTimer = new Timer(1500, this);
    private boolean isPlacingDefences;
    private boolean survivedMoreThanThreeWaves;
    private boolean hasPlayerWon;

    public boolean isShown() {
        return this.isShown;
    }

    public void hideShop() {
        isShown = false;
    }

    /**
     * Let the program know shop should be shown.
     */
    public void showShop() {
        isShown = true;
    }

    private void setMessageToUser(String msg) {
        messageToUser = msg;
        showMessageTimer.start();
    }

    private void setPlacingDefencesValue(boolean v) {
        this.isPlacingDefences = v;
    }

    private boolean isPlacingDefences() {
        return this.isPlacingDefences;
    }

    public void playerSurvivedMoreThanThreeWaves() {
        survivedMoreThanThreeWaves = true;
    }

    public boolean hasPlayerWon() {
        return hasPlayerWon;
    }

    /**
     * Constructor for Shop class.
     * @param p - Player object
     * @param t - TurretManager object
     */
    public Shop(Player p, TurretManager t) {
        isShown = false;
        buyTurretButton = new JButton(new ImageIcon("files/turret.png"));
        unlockEndingButton = new JButton(new ImageIcon("files/tank.png"));
        player = p;
        turretManager = t;
        messageToUser = "";
        isPlacingDefences = false;
        survivedMoreThanThreeWaves = false;
        hasPlayerWon = false;
    }

    /**
     * ActionListener for buying turrets.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showMessageTimer) {
            setMessageToUser("");
            showMessageTimer.stop();
        }

        if (e.getSource() == buyTurretButton && !this.isPlacingDefences()) {
            if (player.buy(Constants.getTurretPrice())) {
                this.hideShop();
                this.setMessageToUser("Use your mouse to choose a location for the turret");
                this.setPlacingDefencesValue(true);
                Phases.stopPlanPhaseTimer();
            } else {
                this.setMessageToUser(
                    "Your balance is not sufficient! The turret costs " 
                        + Constants.getTurretPrice());
            }
        }

        if (e.getSource() == unlockEndingButton && !this.hasPlayerWon()) {
            if (player.buy(Constants.getEndingPrice())) {
                this.hideShop();
                Phases.stopPlanPhaseTimer();
                this.hasPlayerWon = true;
            } else {
                this.setMessageToUser("Your balance is not sufficient! Unlocking the ending costs " 
                        + Constants.getEndingPrice());
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

        //react when player is only placing defences
        if (this.isPlacingDefences()) {
            //left-upper corner
            int x = e.getX(); 
            int y = e.getY();

            //don't allow to place turret beyond game screen
            if (x >= Constants.getTileWidht()
                && x + Constants.getTurretTileWidth() 
                    <= Constants.getMapWidth() - Constants.getTileWidht() 
                && y >= Constants.getTileHeight()
                && y + Constants.getTurretTileHeight() 
                    <= Constants.getMapHeight() - Constants.getTileHeight()) {

                Rectangle r = new Rectangle(x, y, Constants.getTurretTileWidth(), 
                    Constants.getTurretTileHeight());
                
                if (player.checkHit(r).isEmpty()) { 
                    turretManager.addTurret(x, y);
                } else {
                    this.setMessageToUser(
                        "The turret collides with something and cannot be put here!");
                    //return currency to the player
                    player.giveCurrency(Constants.getTurretPrice());
                }

            } else {
                this.setMessageToUser("The turret cannot be placed here!");
                //return currency to the player
                player.giveCurrency(Constants.getTurretPrice());
            }

            this.setPlacingDefencesValue(false);
            this.showShop();
            Phases.resumePlanPhaseTimer();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    /**
     * Drawing method for shop.
     * @param g - used by Swing
     */
    public void draw(Graphics g, JPanel p) {

        Graphics2D g2d = (Graphics2D) g;

        if (this.isShown()) {

            //draw line
            Stroke str = new BasicStroke(4f);
            g2d.setStroke(str);
            g2d.setColor(Color.GREEN);
            g2d.drawLine(0, Constants.getMapHeight(), 
                Constants.getMapWidth(), Constants.getMapHeight());
        
            buyTurretButton.setBounds(20, Constants.getMapHeight() + 5, 
                Constants.getTurretButtonWidth(), Constants.getTurretButtonHeight());
            buyTurretButton.addActionListener(this);
            p.add(buyTurretButton);

            if (survivedMoreThanThreeWaves) {
                unlockEndingButton.setBounds(150, Constants.getMapHeight() + 5,
                    Constants.getEndingButtonWidth(), Constants.getEndingButtonHeight());
                unlockEndingButton.addActionListener(this);
                p.add(unlockEndingButton);
            }

            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 15));
            g2d.drawString("Your current balance: " + player.getCurrency(), 
                300, Constants.getMapHeight() + 20);
            
        } else {
            p.remove(buyTurretButton);
            p.remove(unlockEndingButton);
        }

        if (!"".equals(messageToUser)) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 15));
            g2d.drawString(messageToUser, 
                300, Constants.getMapHeight() + 100);   
        }
    }
}
