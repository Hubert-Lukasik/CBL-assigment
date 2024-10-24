package src.main.java;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Weapon implements ActionListener {
    private static Painter gamePanel;

    private java.awt.image.BufferedImage image = new BufferedImage(1, 1, 1);
    private Entity userEntity;
    private int damage;
    private Timer animationTimer =  new Timer(100, this);
    private String direction = "n";
    private boolean swing;

    public static void informAboutGamePanel(Painter p) {
        gamePanel = p;
    }

    public void setImage(String filename) { 
        filename = filename + ".png";
        try {  
            String filePath = new File("files", filename).getAbsolutePath();
            image = ImageIO.read(new File(filePath));
        } catch (FileNotFoundException e) {
            System.err.println("File " + filename + " not found!");
        } catch (IOException e2) {
            System.err.println("Can't save image" + " " + filename);
        }
    }


    public void setDamage(int d) {
        damage = d;
    }

    private int i = 1;
    
    @Override
    public void actionPerformed(ActionEvent t) {
        if (i == 1) {
            swing = true;
        }
        if (i == 4) {
            i = 1;
            swing = false;
            userEntity.setIsAttacking(false);
            animationTimer.stop();
            return;
        }
        if (t.getSource() == animationTimer && i < 4) {
            this.setImage("swing_" + direction + "_" + i);
            gamePanel.update();
            i++;
        }

    }

    private Rectangle getHitbox(boolean up, boolean down, boolean left, boolean right) {
        if (up && down) {
            up = false;
            down = false;
        }
        if (left && right) {
            left = false;
            right = false;
        }

        if (up && right) {
            
        }

        if (right && down) {

        }

        if (down && left) {

        }

        if (left && up) {

        }

        if (up) {

        }

        if (left) {

        }

        if (down) {

        }

        if (right) {

        }
        Rectangle hitboxRectangle = new Rectangle();
        return hitboxRectangle;
    }

    public void swingWeapon(boolean up, boolean right, boolean down, boolean left) {
        if (swing) {
            return;
        }
        animationTimer.start();
        userEntity.setIsAttacking(true);
        if (!Entity.getDirection(up, right, down, left).equals("")) {
            direction = Entity.getDirection(up, right, down, left);
        }
        Rectangle hitboxRectangle = getHitbox(up, down, left, right);
        ArrayList<Entity> hitEntity = userEntity.checkHit(hitboxRectangle);
        for (Entity e: hitEntity) {
            e.takeDamage(damage);
        }
    }

    public void draw(Graphics g, Painter p) {
        if (swing) {
            int[] position = userEntity.getPosition();
            g.drawImage(image, position[0] - 20, position[1] - 20, p);
        }
    } 

    public Weapon(Entity e) {
        userEntity = e;
    }
}
