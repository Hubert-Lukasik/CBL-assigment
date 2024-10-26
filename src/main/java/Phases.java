package src.main.java;

import java.awt.event.*;
import java.util.Random;
import javax.swing.*; 

/**
 * Class contains methods and variables connected to managing in-game phases.
 */
public class Phases implements ActionListener {
    private String currentPhase;
    private long level;
    private static Timer planPhaseTimer; 
    private Random randomGen;
    private Shop shop;
    private TurretManager turretManager;

    public String getPhase() {
        return currentPhase;
    }

    public long getLevel() {
        return level;
    }

    public static void stopPlanPhaseTimer() {
        planPhaseTimer.stop();
    }

    public static void resumePlanPhaseTimer() {
        planPhaseTimer.start();
    }

    /**
    * Set level to new non-negative value.
    * @param l - new valuer for level  
    */
    public void setLevel(Long l) {
        if (l >= 0L) {
            level = l;
        }
    }

    public void increaseLevel() {
        level += 1L;
    }

    /**
     * Start the "Defend" phase when enemies appears.
     */
    public void startDefendPhase() {
        //set name of the phase
        currentPhase = "Defend";

        //increase level
        this.increaseLevel();

        //hide shop
        shop.hideShop();

        //make turrets online
        turretManager.startDefendPhase();
        

        for (long i = 0; i < 3; ++i) {
            Opponent.addOpponent();
        }
    }

    /**
     * Start the "Plan" phase when enemies are defeated.
     */
    public void startPlanPhase() {
        //set name of the phase
        currentPhase = "Plan";
        
        //kills all opponents
        Opponent.killOpponents();

        //check whether it is possible to buy ending
        if (this.getLevel() >= 3) {
            shop.playerSurvivedMoreThanThreeWaves();
        }
        
        //show shop
        shop.showShop();

        //turn off turrets
        turretManager.startPlanPhase();


        planPhaseTimer = new Timer(10000 + randomGen.nextInt(10000), this);
        planPhaseTimer.start();
    }

    /**
    * End "Plan" phase.
    */
    public void actionPerformed(ActionEvent t) {
        if (t.getSource() == planPhaseTimer) {
            planPhaseTimer.stop();
            startDefendPhase();
        }
    }


    /**
     * Constructor for Phases instance.
     * @param s - shop
     */
    public Phases(Shop s, TurretManager t) {
        currentPhase = "Plan";
        level = 0;
        randomGen = new Random();
        shop = s;
        turretManager = t;
    }
}
