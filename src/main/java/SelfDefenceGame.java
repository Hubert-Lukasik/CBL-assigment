package src.main.java;

import javax.swing.*;

/**
 * Responsible for maintaining game running and calling methods.
 */
public class SelfDefenceGame extends Thread {
    private JFrame frame;
    private Painter gamePanel;
    private Map map;
    private Player player;
    private Shop shop;
    private Phases phasesManager;
    private PlayerAnimation playerAnimator;
    private Timer updateTimer;
    private TurretManager turretManager;



    @Override
    public void run() {
        while (true) {
            gamePanel.update();
        }
    }

    private void setup() {
        //Define game map
        map = new Map();
        map.buildMap("map_defend.txt");
        
        //Define player instance
        player = new Player("player_s", 200, 200);

        //Define TurretManager instance
        turretManager = new TurretManager();

        //Add collision to player
        player.addCollision();
        
        
        //Define shop
        shop = new Shop(player, turretManager);

        //Define phases manager
        phasesManager = new Phases(shop);

        phasesManager.startDefendPhase();

        //Define Painter instance, responsible for drawing
        gamePanel = new Painter(player, map, shop, turretManager);

        //Define PlayerAnimation object, responsible for animating player character
        playerAnimator = new PlayerAnimation(player);
        playerAnimator.start();
        
        //detect pressing keys
        gamePanel.addKeyListener(playerAnimator);

        
        Opponent.informAboutPlayer(player);
        Opponent.informAboutGamePanel(gamePanel);

        Weapon.informAboutGamePanel(gamePanel);
        this.setPriority(10);
        this.start();

        
    }

    private void runGame() {
        SwingUtilities.invokeLater(() -> {
            setup();
            frame = new JFrame("Self-defence Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(Constants.getScreenWidth(), Constants.getScreenHeight());
            frame.setResizable(false);

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



