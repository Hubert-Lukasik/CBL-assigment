package src.main.java;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

public class HealthBar implements ActionListener {
    
    private int startHealth;
    private Player player;
    private Timer updateTimer;
    image HealthBar

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == updateTimer) {
            int currentHealth = player.getHealthPoints();
            int healthBarStage = startHealth/currentHealth;


        }
    }

    public void draw() {

    }

    public HealthBar(Player p) {
        player = p;
        startHealth = p.getHealthPoints();
        updateTimer = new Timer(15, this);
    }

}
