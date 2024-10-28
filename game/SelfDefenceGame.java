package game;

import java.awt.event.*;
import javax.swing.*;

/**
 * Self-Defence game.
 * GAME CREATED BY:
 * 
 * Project Group 138, members:
 * @author Lars Kuppen (Student ID: 2146053)
 * @author Hubert Lukasik (Student ID: 2146541)
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
    private Badending badEnding;
    private Timer isPlayerStillAlive;
    private Timer updateBadEndingTimer;
    private JButton playerMovement;

    /**
     * ActionListener for updating the content on the screen.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateTimer) {
            gamePanel.update();
        }

        if (e.getSource() == isPlayerStillAlive && player.isDead()) {
            checkWhetherGameEnded.stop();
            updateTimer.stop();
            isPlayerStillAlive.stop();
            updateBadEndingTimer = new Timer(15, this);
            updateBadEndingTimer.start();
            runBadEnding();
        } 

        if (e.getSource() == checkWhetherGameEnded && shop.hasPlayerWon()) {
            checkWhetherGameEnded.stop();
            updateTimer.stop();
            isPlayerStillAlive.stop();
            updateEndingTimer = new Timer(15, this);
            updateEndingTimer.start();
            this.runEnding();
        }


        if (e.getSource() == updateEndingTimer) {
            endGame.update();
        }

        if (e.getSource() == updateBadEndingTimer) {
            badEnding.update();
        }

        if (e.getSource() == hasEndingFinished && endGame.hasEndingFinished()) {
            updateEndingTimer.stop();
            showScore();
        }
    }

    private void setup() {
        //Define game map
        map = new Map();

        //Define player instance
        player = new Player("player/player_n", 200, 200);

        //Define TurretManager instance
        turretManager = new TurretManager();

        //Add collision to player
        player.addCollision();
        
        //Define shop
        shop = new Shop(player, turretManager);

        //Define phases manager
        phasesManager = new Phases(shop, turretManager, map, player);
    
        //Define PlayerAnimation object, responsible for animating player character
        playerAnimator = new PlayerAnimation(player);

        //Define Painter instance, responsible for drawing
        gamePanel = new Painter(player, map, shop, turretManager);
        
        Opponent.informAboutPlayer(player);
        Opponent.informAboutGamePanel(gamePanel);
        Opponent.informAboutTurretManager(turretManager);

        Weapon.informAboutGamePanel(gamePanel);

        updateTimer = new Timer(15, this);
        updateTimer.start();

        checkWhetherGameEnded = new Timer(100, this);
        checkWhetherGameEnded.start();

        isPlayerStillAlive = new Timer(50, this);
        isPlayerStillAlive.start();
    }

    private void runGame() {
        SwingUtilities.invokeLater(() -> {
            setup();

            frame = new JFrame("Self-defence Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(Constants.getScreenWidth(), Constants.getScreenHeight());
            frame.setResizable(false);
            frame.setFocusable(true);
            frame.requestFocus();


            playerMovement = new JButton();
            playerMovement.setBounds(0, 0, 1, 1);
            frame.add(playerMovement);


            //MOVING UP
            playerMovement.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("W"), "goUp");
            playerMovement.getActionMap().put("goUp", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playerAnimator.goUp();
                }
            });

            playerMovement.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("released W"), "releaseUp");
            playerMovement.getActionMap().put("releaseUp", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playerAnimator.releaseUp();
                }
            });

            
            //MOVING DOWN
            playerMovement.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("S"), "goDown");
            playerMovement.getActionMap().put("goDown", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playerAnimator.goDown();
                }
            });

            playerMovement.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("released S"), "releaseDown");
            playerMovement.getActionMap().put("releaseDown", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playerAnimator.releaseDown();
                }
            });

            //MOVING RIGHT
            playerMovement.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("D"), "goRight");
            playerMovement.getActionMap().put("goRight", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playerAnimator.goRight();
                }
            });

            playerMovement.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("released D"), "releaseRight");
            playerMovement.getActionMap().put("releaseRight", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playerAnimator.relaseRight();
                }
            });

            //MOVING LEFT
            playerMovement.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("A"), "goLeft");
            playerMovement.getActionMap().put("goLeft", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playerAnimator.goLeft();
                }
            });

            playerMovement.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("released A"), "releaseLeft");
            playerMovement.getActionMap().put("releaseLeft", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playerAnimator.releaseLeft();
                }
            });

            //USING WEAPON
            playerMovement.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("J"), "useWeapon");
            playerMovement.getActionMap().put("useWeapon", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playerAnimator.useWeapon();
                }
            });

            
            frame.add(gamePanel); 
            
            gamePanel.setFocusTraversalKeysEnabled(false);  

            phasesManager.startPlanPhase();

            frame.setVisible(true);

        });
    }

    /**
     * Section responsible for running good game ending.
     */
    public void runEnding() {
        SwingUtilities.invokeLater(() -> {
            frame.remove(gamePanel); 
            frame.remove(playerMovement);

            endGame = new Ending(map, player, turretManager);

            frame.add(endGame);
            frame.setVisible(true);

            hasEndingFinished = new Timer(100, this);
            hasEndingFinished.start();
        });
    }

    /**
     * Runs the bad ending (when player runs out of HPs).
     */
    public void runBadEnding() {
        SwingUtilities.invokeLater(() -> {
            frame.remove(gamePanel); 
            frame.remove(playerMovement);

            badEnding = new Badending();

            frame.add(badEnding);
            frame.setVisible(true);
        });
    }

    /**
     * Section repsonsible for showing score player obtained.
     */
    public void showScore() {
        SwingUtilities.invokeLater(() -> {
            frame.remove(endGame); 

            JPanel scoreScreen = new ScoreScreen(player, phasesManager);
                        
            frame.setSize(Constants.getScoreScreenWidth(), Constants.getScoreScreenHeight());
            frame.add(scoreScreen);

            frame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        (new SelfDefenceGame()).runGame();
    }
}



