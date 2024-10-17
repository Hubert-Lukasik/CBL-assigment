package src.main.java;

/**
 * Class contains methods and variables connected to managing in-game phases.
 */
public class Phases {
    private static String currentPhase = "";
    private static long level = 0; 

    public static String getPhase() {
        return currentPhase;
    }

    public static long getLevel() {
        return level;
    }

    /**
    * Set level to new non-negative value.
    * @param l - new valuer for level  
    */
    public static void setLevel(Long l) {
        if (l >= 0L) {
            level = l;
        }
    }

    public static void increaseLevel() {
        level += 1L;
    }

    /**
     * Start the "Defend" phase when enemiec appears.
     */
    public static void startDefendPhase() {
        currentPhase = "Defend";
        for (long i = 0; i < level; ++i) {
            Opponent.addOpponent();
        }
    }

    /**
     * Start the "Plan" phase when enemiec appears.
     */
    public static void startPlanPhase() {
        currentPhase = "Plan";
        Opponent.clearOpponentsArray();
        level += 1;
    }
    
}
