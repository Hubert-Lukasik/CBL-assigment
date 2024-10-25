package src.main.java;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Contains methods and variables relevant for in-game shop.
 */
public class Shop implements ActionListener, MouseListener {
    private boolean isShown;
    private JButton buyTurretButton;
    private Player player;
    private TurretManager turretManager;
    private String messageToUser;
    private Timer showMessageTimer = new Timer(1500, this);
    private boolean isPlacingDefences;
    //private Timer checkMouseLocation = new Timer(50, this);


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

    /**
     * Constructor for Shop class.
     * @param p - Player object
     * @param t - TurretManager object
     */
    public Shop(Player p, TurretManager t) {
        isShown = false;
        buyTurretButton = new JButton(new ImageIcon("files/turret.png"));
        player = p;
        turretManager = t;
        messageToUser = "";
        isPlacingDefences = false;
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
    }

    @Override
    public void mousePressed(MouseEvent e) {

        //react when player is only placing defences
        if (this.isPlacingDefences()) {
            int x = e.getX() - Constants.getTileWidht();
            int y = e.getY() - Constants.getTileHeight();

            /*
            * TODO: now allow to put turret beyond the game window.
            * TODO: collision detection with player.
            */

            turretManager.addTurret(x, y);

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
    public void draw(Graphics g, Painter p) {

        Graphics2D g2d = (Graphics2D) g;

        if (this.isShown()) {

            //draw line
            Stroke str = new BasicStroke(4f);
            g2d.setStroke(str);
            g2d.setColor(Color.GREEN);
            g2d.drawLine(0, Constants.getMapHeight(), 
                Constants.getMapWidth(), Constants.getMapHeight());
        
            buyTurretButton.setBounds(20, Constants.getMapHeight(), 
                20 + Constants.getTurretButtonWidth(), 
                    Constants.getTileWidht() + Constants.getTurretButtonHeight());
            buyTurretButton.addActionListener(this);
            p.add(buyTurretButton);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 15));
            g2d.drawString("Your current balance: " + player.getCurrency(), 
                300, Constants.getMapHeight() + 20);
        
        } else {
            p.remove(buyTurretButton);
        }

        if (!"".equals(messageToUser)) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 15));
            g2d.drawString(messageToUser, 
                300, Constants.getMapHeight() + 100);   
        }
    }
}
