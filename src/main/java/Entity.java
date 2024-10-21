package src.main.java;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * Class defines all methods for mechanics shared by all entities in the game.
 */
public abstract class Entity {
    private int healthPoints = 0;
    private int posX = 0;
    private int posY = 0;
    private static ArrayList<Entity> collisionEntities = new ArrayList();

    private java.awt.image.BufferedImage image = new BufferedImage(1, 1, 1);


    /**
     * Initialise amount of health points the entity has.
     * @param hp - starting amount of health points of the entity
     */
    public void setHealthPoints(int hp) {
        healthPoints = hp;
    }

    public int getHealthPoints() {
        return healthPoints; 
    }

    /**
     * Change health points of the entity.
     * @param damage - damage taken (negavite damage increases hps)
     */
    public void takeDamage(int damage) {
        healthPoints = healthPoints - damage;
    }

    public boolean isDead() {
        return (healthPoints <= 0);
    }

    /**
     * Sets position of the entity.
     * @param x - coordinate x of the new position
     * @param y - coordinate y of the new position
     */
    public void setPosition(int x, int y) {
        posX = x;
        posY = y;
    }

    /**
     * Get the entity position.
     * @return - two-element array with coordinates (X, Y)
     */
    public int[] getPosition() {
        int[] pos = new int[2];
        pos[0] = posX;
        pos[1] = posY;
        return pos;
    }

    /**
     * Sets current image of an entity to specified image.
     * @param filename - name of the file containing new image (without extension, png assumed)
     */
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

    public java.awt.image.BufferedImage getImage() {
        return image;
    }


    /**
     * Get a rectangle with the size and the current position of the image of the entity.
     * @return - a rectangle with a size and posstion.
     */
    public Rectangle[] getHitbox() {
        Rectangle[] hitbox = new Rectangle[5];
        int width = image.getWidth();
        int height = image.getHeight();
        hitbox[0] = new Rectangle(posX, posY, width, height);
        hitbox[1] = new Rectangle(posX, posY -4, width, 4);
        hitbox[2] = new Rectangle(posX + width, posY, 4, height);
        hitbox[3] = new Rectangle(posX, posY + height, width, 4);
        hitbox[4] = new Rectangle(posX - 4, posY, 4, height);
        return hitbox;
    }

    public void addCollision() {
        collisionEntities.add(this);
    }

    public boolean[] checkCollision() {
        boolean[] collision = new boolean[5];
        Rectangle[] r1 = this.getHitbox();
        for (int i = 0; i < collisionEntities.size(); ++i) {
        
            if (collisionEntities.get(i) != this) {
                Rectangle[] r2 = collisionEntities.get(i).getHitbox();
                for (int j = 0; j <= 4; j++) {
                    boolean isOverlapping = r1[j].intersects(r2[0]);
                    if (isOverlapping) {
                        System.out.println(j);
                        collision[j] = true;
                    }
                }
            }
        }
        return collision;
    }

}
