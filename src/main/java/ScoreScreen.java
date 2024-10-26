package src.main.java;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Class for score screen (screen showing obtained points to the player).
 */
public class Scorescreen extends JPanel {
    private Phases phasesManager;
    private Player player; 
    private java.awt.image.BufferedImage endImg = new BufferedImage(1, 1, 1);
    private long score;

    private void calculateScore() {
        score = phasesManager.getLevel() * 17 + player.getCurrency() * 39;
    }

    private void showScore(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; 
        g.setColor(new Color(117, 249, 77));
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Congratulations!", 300, 100);
        g.setFont(new Font("Arial", Font.BOLD, 18));   
        g2d.drawString("You helped Lars survive!", 
            300, 200);
        g.setFont(new Font("Arial", Font.BOLD, 18));   
        g2d.drawString("Lars may now finally enjoy his pancakes in peace.", 
            300, 300);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Your score: " + score, 400, 400);
    }

    @Override
    public void paintComponent(Graphics g) {
        this.setBackground(new Color(46, 200, 204));
        super.paintComponent(g);
        g.drawImage(endImg, 0, 0, this);
        showScore(g);
    }

    /**
     * Constructor for ScoreScreen.
     * @param p - Player object
     * @param pm - Phases object
     */
    public Scorescreen(Player p, Phases pm) {
        player = p;
        phasesManager = pm;
        calculateScore();

        try {  
            String filePath = new File("files", "scoreScreen.png").getAbsolutePath();
            endImg = ImageIO.read(new File(filePath));
        } catch (FileNotFoundException e) {
            System.err.println("File scoreScreen.png not found!");
        } catch (IOException e2) {
            System.err.println("Can't save image scoreScreen.png");
        }
    }
    
}
