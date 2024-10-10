package src.main.java;

import javax.swing.*;

/**
 * Responsible for maintaining game running and calling methods.
 */
public class SelfDefenceGame {
    private static JFrame frame;

    private static void setup() {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Self-defence Game");
            JPanel startMap = new Map();

            frame.add(startMap);
            Map.buildMap("map_defend.txt");


            frame.setSize(600, 600);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
        });
    }


    public static void main(String[] args) {
        setup();
    }
    
}
