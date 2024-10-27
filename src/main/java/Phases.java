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
    private static Timer checkWhetherOpponentsDead;
    private Random randomGen;
    private Shop shop;
    private TurretManager turretManager;
    private Map map;
    private Player player;

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

        //make a map
        map.buildMap("map_defend.txt");

        //increase level
        this.increaseLevel();

        //hide shop
        shop.hideShop();

        //make turrets online
        turretManager.startDefendPhase();
        
        long numOfOpponentsToSpawn = level;
        if (level % 2 == 0) {
            numOfOpponentsToSpawn += 2 * level;
        } else {
            numOfOpponentsToSpawn += level + randomGen.nextInt(5, 10);
        }

        for (long i = 0; i < Math.min(numOfOpponentsToSpawn, 200L); ++i) {
            Opponent.addOpponent();
        }

        checkWhetherOpponentsDead = new Timer(60, this);
        checkWhetherOpponentsDead.start(); 
    }

    /**
     * Start the "Plan" phase when enemies are defeated.
     */
    public void startPlanPhase() {
        //set name of the phase
        currentPhase = "Plan";

        //make a map
        map.buildMap("map_plan.txt");
        
        //kills all opponents
        Opponent.killOpponents();

        //award currency to the player
        player.giveCurrency(level * 13 + (level) * 7);

        //reset health of player
        player.setHealthPoints(100);

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

        if (t.getSource() == checkWhetherOpponentsDead && Opponent.howManyOpponents() == 0) {
            checkWhetherOpponentsDead.stop();
            startPlanPhase();
        }
    }


    /**
     * Constructor for Phases instance.
     * @param s - shop
     */
    public Phases(Shop s, TurretManager t, Map m, Player p) {
        currentPhase = "Plan";
        map = m;
        level = 0;
        randomGen = new Random();
        shop = s;
        turretManager = t;
        player = p;
        this.startPlanPhase();
    }
}
