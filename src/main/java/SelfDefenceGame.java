package src.main.java;

import java.awt.event.*;
import javax.swing.*;

/**
 * Responsible for maintaining game running and calling methods.
 */
public class SelfDefenceGame {
    private static JFrame frame;
    private static Player player;
    private static Painter gamePanel;

    private static void setup() {
        //Define game map
        Map map = new Map();
        map.buildMap("map_defend.txt");
        
        //Define player instance
        player = new Player("player_s", 200, 200);

        //Define shop
        Shop shop = new Shop();

        //Define phases manager
        Phases phasesManager = new Phases(shop);

        phasesManager.startDefendPhase();

        //Define Painter instance, responsible for drawing
        gamePanel = new Painter(player, map, shop);

        Opponent.informAboutPlayer(player);
        Opponent.informAboutGamePanel(gamePanel);
        PlayerAnimation.informAboutGamePanel(gamePanel);
        PlayerAnimation.informAboutPlayer(player);

        ActionListener listenerForMovement = new PlayerAnimation();
        Timer checkPlayerMovement = new Timer(50, listenerForMovement);
        PlayerAnimation.informAboutTimerForMovement(checkPlayerMovement);
        checkPlayerMovement.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            setup();
            frame = new JFrame("Self-defence Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 600);

            PlayerAnimation playerAnimation = new PlayerAnimation();
            gamePanel.addKeyListener(playerAnimation);

            frame.add(gamePanel); 
            
            gamePanel.setFocusable(true);
            gamePanel.setFocusTraversalKeysEnabled(false);  

            frame.setVisible(true);
        });
    }
}
