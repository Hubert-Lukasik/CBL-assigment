package src.main.java;

import javax.swing.*;

/**
 * Responsible for maintaining game running and calling methods.
 */
public class SelfDefenceGame {
    private static JFrame frame;
    private static Painter gamePanel;
    private static Map map;
    private static Player player;
    private static Shop shop;
    private static Phases phasesManager;
    private static PlayerAnimation playerAnimator;

    private static void setup() {
        //Define game map
        map = new Map();
        map.buildMap("map_defend.txt");
        
        //Define player instance
        player = new Player("player_s", 200, 200);

        //Add collision to player
        player.addCollision();

        //Define shop
        shop = new Shop();

        //Define phases manager
        phasesManager = new Phases(shop);

        phasesManager.startDefendPhase();

        //Define Painter instance, responsible for drawing
        gamePanel = new Painter(player, map, shop);

        //Define PlayerAnimation object, responsible for animating player character
        playerAnimator = new PlayerAnimation(player, gamePanel);
        
        //detect pressing keys
        gamePanel.addKeyListener(playerAnimator);

        
        Opponent.informAboutPlayer(player);
        Opponent.informAboutGamePanel(gamePanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            setup();
            frame = new JFrame("Self-defence Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 600);

            frame.add(gamePanel); 
            
            gamePanel.setFocusable(true);
            gamePanel.setFocusTraversalKeysEnabled(false);  

            frame.setVisible(true);
        });
    }
}
