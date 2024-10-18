package src.main.java;

import java.awt.event.*;
import java.util.Random;
import javax.swing.*; 

/**
 * Class contains methods and variables connected to managing in-game phases.
 */
public class Phases implements ActionListener {
    private static String currentPhase = "";
    private static long level = 0;
    private static Timer planPhaseTimer; 
    private static Random randomGen = new Random();

    public String getPhase() {
        return currentPhase;
    }

    public long getLevel() {
        return level;
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
        currentPhase = "Defend";
        for (long i = 0; i < level; ++i) {
            Opponent.addOpponent();
        }
    }

    /**
     * Start the "Plan" phase when enemies are defeated.
     */
    public void startPlanPhase() {
        currentPhase = "Plan";
        Opponent.clearOpponentsArray();
        level += 1;
        planPhaseTimer = new Timer(2000 + randomGen.nextInt(1000), this);
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

    
}