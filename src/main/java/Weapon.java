package src.main.java;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Weapon implements ActionListener {
    private static Painter gamePanel;

    private java.awt.image.BufferedImage image = new BufferedImage(1, 1, 1);
    private Entity userEntity;
    private int damage;
    private Timer animationTimer =  new Timer(100, this);
    private String direction = "s";
    private boolean swing;
    private ArrayList<Entity> ignoreList = new ArrayList<Entity>();

    boolean up;
    boolean left;
    boolean down;
    boolean right;



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
    ArrayList<Entity> hitEntities = new ArrayList<Entity>();
    
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
            hitEntities.clear();
            return;
        }
        if ((t.getSource() == animationTimer) && i < 4) {
            this.setImage("swing_" + direction + "_" + i);
            Rectangle hitRectangle = getHitbox(up, down, left, right);
            ArrayList<Entity> hitEntity = userEntity.checkHit(hitRectangle);
            for (Entity e: hitEntity) {
                if (!hitEntities.contains(e) && !ignoreList.contains(e)) {
                    e.takeDamage(damage);
                    hitEntities.add(e);
                }
            }            
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

        Rectangle hitboxRectangle;
        int eWidth = userEntity.getImage().getWidth();
        int eHeight = userEntity.getImage().getHeight();
        int[] pos = userEntity.getPosition();

        if (up && right) {
            hitboxRectangle = new Rectangle(pos[0] + eWidth - 15, pos[1] - 20, 35, 35);  
            return hitboxRectangle;        
        }

        if (right && down) {
            hitboxRectangle = new Rectangle(pos[0] + eWidth - 15, pos[1] + eHeight - 15, 35, 35);
            return hitboxRectangle;
        }

        if (down && left) {
            hitboxRectangle = new Rectangle(pos[0] - 20, pos[1] + eHeight - 15, 35, 35);
            return hitboxRectangle;
        }

        if (left && up) {
            hitboxRectangle = new Rectangle(pos[0] - 20, pos[1] - 20, 35, 35);
            return hitboxRectangle;
        }

        if (up) {
            hitboxRectangle = new Rectangle(pos[0] - 2, pos[1] - 20, 32, 20);
            return hitboxRectangle;
        }

        if (left) {
            hitboxRectangle = new Rectangle(pos[0] - 20, pos[1] - 2, 25, 40);
            return hitboxRectangle;
        }

        if (down) {
            hitboxRectangle = new Rectangle(pos[0] - 2, pos[1] + eHeight, 32, 20);
            return hitboxRectangle;
        }

        if (right) {
            hitboxRectangle = new Rectangle(pos[0] + eWidth - 5, pos[1] - 2, 25, 40);
            return hitboxRectangle;
        }
        return null;
    }

    public void swingWeapon(boolean up, boolean right, boolean down, boolean left) {
        if (swing) {
            return;
        }
        this.up = up;
        this.right = right;
        this.down = down;
        this.left = left;
        animationTimer.start();
        userEntity.setIsAttacking(true);
        if (!Entity.getDirection(up, right, down, left).equals("")) {
            direction = Entity.getDirection(up, right, down, left);
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
        ignoreList.add(e);
    }
}
