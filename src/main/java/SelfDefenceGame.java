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
        Map.buildMap("map_defend.txt");
        player = new Player();
        player.setImage("test_char");
        player.setPosition(200, 200);

        gamePanel = new Painter();
        gamePanel.informAboutPlayer(player);
        PlayerAnimation.informAboutGamePanel(gamePanel);
        PlayerAnimation.informAboutPlayer(player);

        ActionListener listenerForMovement = new PlayerAnimation();
        Timer checkPlayerMovement = new Timer(50, listenerForMovement);
        PlayerAnimation.informAboutTimerForMovement(checkPlayerMovement);
        checkPlayerMovement.start();

        Opponent.informAboutPlayer(player);

        Opponent.informAboutGamePanel(gamePanel);

        Phases.startDefendPhase();
        
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
