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
    private static ArrayList<Entity> collisionEntities = new ArrayList<Entity>();
    private Weapon weapon = new Weapon(this);
    private boolean[] currentDirection = {false, false, false, false};

    private boolean isAttacking = false;

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

    public static void kill(Entity e) {
        collisionEntities.remove(e);
    }

    public static void kill(ArrayList<Entity> e) {
        collisionEntities.removeAll(e);
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
        int accuracy = Constants.getPlayerStep();
        hitbox[0] = new Rectangle(posX, posY, width, height);
        hitbox[1] = new Rectangle(posX, posY - accuracy, width, accuracy);
        hitbox[2] = new Rectangle(posX + width, posY, accuracy, height);
        hitbox[3] = new Rectangle(posX, posY + height, width, accuracy);
        hitbox[4] = new Rectangle(posX - accuracy, posY, accuracy, height);
        return hitbox;
    }

    public void addCollision() {
        collisionEntities.add(this);
    }


    /**
     * Checks for collision between the current enitities hitboxes 
     * and every other entity's base hitbox.
     * @return boolean array with true in each direction it is colliding with a collisionEntity.
     */
    public boolean[] checkCollision() {
        boolean[] collision = new boolean[5];
        Rectangle[] r1 = this.getHitbox();
        for (Entity e: collisionEntities) {
            if (e != this) {
                Rectangle[] r2 = e.getHitbox();
                for (int j = 0; j <= 4; j++) {
                    boolean isOverlapping = r1[j].intersects(r2[0]);
                    if (isOverlapping) {
                        collision[j] = true;
                    }
                }
            }
        }
        return collision;
    }


    /**
     * Checks if a rectangle intersects (hits) an object which has collision.
     * @param r1 - Rectangle you want to check.
     * @return The object the rectangle hits (null if no object is hit).
     */
    public ArrayList<Entity> checkHit(Rectangle r1) {
        ArrayList<Entity> hitEntities = new ArrayList<Entity>();
        for (int i = 0; i < collisionEntities.size(); ++i) {
            if (collisionEntities.get(i) != this) {
                Rectangle[] r2 = collisionEntities.get(i).getHitbox();
                if (r1.intersects(r2[0])) {
                    hitEntities.add(collisionEntities.get(i));
                }
            }
        }
        return hitEntities;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setIsAttacking(boolean a) {
        isAttacking = a;
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    /**
     * Determine geographic direction of the object as a string from booleans with directions.
     * @param up - is object facing up.
     * @param right - is object facing right.
     * @param down - is object facing down.
     * @param left - is object facing left.
     * @return - String containing letters corresponding to 
     *     the direction of the player character movement 
     *     (empty if direction is undetermined).
     */
    public static String getDirection(boolean up, boolean right, boolean down, boolean left) {
        String direction = "";

        //exclude situation when two opposing keys are pressed

        if (up && !down) {
            direction += 'n';
        } 
        
        if (down && !up) {
            direction += 's';
        }

        if (left && !right) {
            direction += 'w';
        }

        if (right && !left) {
            direction += 'e';
        }

        return direction;
    }

    public void setCurrentDirection(boolean up, boolean right, boolean down, boolean left) {
        currentDirection[0] = up;
        currentDirection[1] = right;
        currentDirection[2] = down;
        currentDirection[3] = left;
    }

    public boolean[] getCurrentDirection() {
        return currentDirection;
    }

}
