package src.main.java;

import java.awt.event.*;
import javax.swing.*;

/**
 * Responsible for maintaining game running and calling methods.
 */
public class SelfDefenceGame implements ActionListener {
    private JFrame frame;
    private Painter gamePanel;
    private Map map;
    private Player player;
    private Shop shop;
    private Phases phasesManager;
    private PlayerAnimation playerAnimator;
    private Timer updateTimer;

    /**
     * ActionListener for updating the content on the screen.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateTimer) {
            gamePanel.update();
        }
    }

    private void setup() {
        //Define game map
        map = new Map();
        map.buildMap("map_defend.txt");
        
        //Define player instance
        player = new Player("player_s", 200, 200);

        //Define shop
        shop = new Shop(player);

        //Define phases manager
        phasesManager = new Phases(shop);

        phasesManager.startDefendPhase();

        //Define Painter instance, responsible for drawing
        gamePanel = new Painter(player, map, shop);

        //Define PlayerAnimation object, responsible for animating player character
        playerAnimator = new PlayerAnimation(player);
        
        //detect pressing keys
        gamePanel.addKeyListener(playerAnimator);

        
        Opponent.informAboutPlayer(player);

        updateTimer = new Timer(15, this);
        updateTimer.start();
    }

    private void runGame() {
        SwingUtilities.invokeLater(() -> {
            setup();
            frame = new JFrame("Self-defence Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 600);

            frame.add(gamePanel); 
            
            gamePanel.setFocusable(true);
            gamePanel.setFocusTraversalKeysEnabled(false);  

            phasesManager.startPlanPhase();

            frame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        (new SelfDefenceGame()).runGame();
    }
}



