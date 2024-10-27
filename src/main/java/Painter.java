package src.main.java;

import java.awt.*;
import javax.swing.*;

/**
 * Class responsible for drawing everything on the screen.
 */
public class Painter extends JPanel {

    private Player player;
    private Map map;
    private Shop shop;
    private TurretManager turretManager;

    public void update() {
        this.repaint();
    }
    

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        map.draw(g, this);
        shop.draw(g, this);
        turretManager.draw(g, this);
        Opponent.draw(g, this);
        player.draw(g, this);
        shop.draw(g, this);
        player.getWeapon().draw(g, this);
    }

    /**
     * Contructor for Painter instance.
     * @param p - Player instance
     * @param m - Map instance
     * @param s - Shop instance
     * @param t - TurretManager instance
     */
    public Painter(Player p, Map m, Shop s, TurretManager t) {
        player = p;
        map = m;
        shop = s;
        turretManager = t;
        this.addMouseListener(shop);
    }
    
}
