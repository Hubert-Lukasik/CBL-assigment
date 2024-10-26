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
    private TurretManager turretManager;
    private Timer checkWhetherGameEnded;
    private Ending endGame;
    private Timer updateEndingTimer;
    private Timer hasEndingFinished;

    /**
     * ActionListener for updating the content on the screen.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateTimer) {
            gamePanel.update();
        }

        if (e.getSource() == checkWhetherGameEnded && shop.hasPlayerWon()) {
            checkWhetherGameEnded.stop();
            updateTimer.stop();
            updateEndingTimer = new Timer(15, this);
            updateEndingTimer.start();
            this.runEnding();
        }

        if (e.getSource() == updateEndingTimer) {
            endGame.update();
        }

        if (e.getSource() == hasEndingFinished && endGame.hasEndingFinished()) {
            updateEndingTimer.stop();
            showScore();
        }
    }

    private void setup() {
        //Define game map
        map = new Map();
        map.buildMap("map_defend.txt");
        
        //Define player instance
        player = new Player("player_s", 200, 200);

        player.giveCurrency(60);

        //Define TurretManager instance
        turretManager = new TurretManager();

        //Add collision to player
        player.addCollision();
        
        
        //Define shop
        shop = new Shop(player, turretManager);

        //Define phases manager
        phasesManager = new Phases(shop, turretManager);

        phasesManager.startDefendPhase();
        phasesManager.increaseLevel();
        phasesManager.increaseLevel();
        phasesManager.startPlanPhase();
        

        //Define Painter instance, responsible for drawing
        gamePanel = new Painter(player, map, shop, turretManager);

        //Define PlayerAnimation object, responsible for animating player character
        playerAnimator = new PlayerAnimation(player);
        
        //detect pressing keys
        gamePanel.addKeyListener(playerAnimator);

        
        Opponent.informAboutPlayer(player);
        Opponent.informAboutGamePanel(gamePanel);

        Weapon.informAboutGamePanel(gamePanel);

        updateTimer = new Timer(15, this);
        updateTimer.start();

        checkWhetherGameEnded = new Timer(100, this);
        checkWhetherGameEnded.start();
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

    /**
     * Section responsible for running game ending.
     */
    public void runEnding() {
        SwingUtilities.invokeLater(() -> {
            frame.remove(gamePanel); 

            endGame = new Ending(map, player, turretManager);

            frame.add(endGame);
            frame.setVisible(true);

            hasEndingFinished = new Timer(100, this);
            hasEndingFinished.start();
        });
    }

    /**
     * Section repsonsible for showing score player obtained.
     */
    public void showScore() {
        SwingUtilities.invokeLater(() -> {
            frame.remove(endGame); 

            JPanel scoreScreen = new Scorescreen(player, phasesManager);
                        
            frame.setSize(Constants.getScoreScreenWidth(), Constants.getScoreScreenHeight());
            frame.add(scoreScreen);

            frame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        (new SelfDefenceGame()).runGame();
    }
}



