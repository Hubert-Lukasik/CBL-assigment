package src.main.java;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

/**
* MapBuilder class contains method to build a game map. 
*/
public class Map extends JPanel {
    /**
     * Read map decription from the text file.
     * @param filename - name of the file with map description
     * @return ArrayList of ArrayList containing strings - ids of the tiles from specified file 
     */
    public static ArrayList<ArrayList<String>> readMap(String filename) {
        ArrayList<ArrayList<String>> obtainedMap = new ArrayList<ArrayList<String>>();

        Scanner input;

        try {  
            String filePath = new File("files", filename).getAbsolutePath();
            File file = new File(filePath);
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            return obtainedMap;
        }

        int rowNr = -1;
        
        while (input.hasNextLine()) {
            String lineFromFile = input.nextLine();
            String tileNr = "";

            obtainedMap.add(new ArrayList<String>());
            ++rowNr;

            for (int ind = 0; ind < lineFromFile.length(); ++ind) {
                if (lineFromFile.charAt(ind) == ' ') {
                    obtainedMap.get(rowNr).add(tileNr);
                    tileNr = "";
                } else {
                    tileNr += lineFromFile.charAt(ind);
                }
            }

            //don't forget to at the last element from the row
            obtainedMap.get(rowNr).add(tileNr);
        }

        input.close();
       
        
        return obtainedMap;
    }


    //DRAWING SECTION

    private static ArrayList<String> tileNames = new ArrayList<String>();
    private static ArrayList<Integer> tileX = new ArrayList<Integer>();
    private static ArrayList<Integer> tileY = new ArrayList<Integer>();
   
    private static final int TILE_WIDTH = 40;
    private static final int TILE_HEIGHT = 40;

       
    /** 
    * First.
    * TODO : write drawing tiles for all tiles in ArrayLists.
    * END TODO  
    * 
    */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    

    /**
    * Take description of the map and change it to list of tiles to draw.
    * Use readMap method to get required input type 
    * @param mapDescription - ArrayList of ArrayList containing Strings describing tiles to draw
    */
    public static ArrayList<String> buildMap(ArrayList<ArrayList<String>> mapDescription) {
        
        for (int row = 0; row < mapDescription.size(); ++row) {
            for (int column = 0; column < mapDescription.get(row).size(); ++column) {
                tileNames.add(mapDescription.get(row).get(column) + ".png");
                tileX.add(column * TILE_WIDTH);
                tileY.add(row * TILE_HEIGHT);
            }
        } 

        ArrayList<String> res = new ArrayList<String>();

        for (int i = 0; i < tileNames.size(); ++i) {
            res.add(tileNames.get(i));
            res.add(String.valueOf(tileX.get(i)));
            res.add(String.valueOf(tileY.get(i)));
        }

        return res;
    } 
}
