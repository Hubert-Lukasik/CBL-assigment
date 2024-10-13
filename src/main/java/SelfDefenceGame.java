package src.main.java;

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
    }

    public static void main(String[] args) {
        setup();
        SwingUtilities.invokeLater(() -> {
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
