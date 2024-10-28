package game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
* MapBuilder class contains method to build a game map. 
*/
public class Map {
    private ArrayList<String> tilePaths;
    private ArrayList<Integer> tileX; 
    private ArrayList<Integer> tileY;
    private BufferedImage buffer;

    /**
     * Read map decription from the text file.
     * @param filename - name of the file with map description
     * @return ArrayList of ArrayList containing strings - ids of the tiles from specified file 
     */
    public ArrayList<ArrayList<String>> readMap(String filename) {
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

    /**
    * Take filename of the file with mape and change it to list of tiles (.png format) to draw.
    * @param filename - name of the file containing map
    */
    public void buildMap(String filename) {
        ArrayList<ArrayList<String>> mapDescription = this.readMap(filename);

        tilePaths.clear();
        tileX.clear();
        tileY.clear();

        //it'll be necessary to draw whole map again
        buffer = null;

        for (int row = 0; row < mapDescription.size(); ++row) {
            for (int column = 0; column < mapDescription.get(row).size(); ++column) {

                // '-'acts as an information that no tile should be placed in particular place 
                if (mapDescription.get(row).get(column).equals("-")) {
                    continue;
                }
                
                String filePath;
                File file = new File("files", mapDescription.get(row).get(column) + ".png");
                
                if (!file.exists()) {
                    continue;
                }

                filePath = file.getAbsolutePath();

                tilePaths.add(filePath);
                tileX.add(column * Constants.getTileWidht());
                tileY.add(row * Constants.getTileHeight());
            }
        }
    }

    /**
     * Draw map tiles.
     * @param g - used by Swing
    */
    public void draw(Graphics g, JPanel p) {

        //There is a need to draw everything
        if (buffer == null) {
            buffer = new BufferedImage(Constants.getMapWidth(), Constants.getMapHeight(), 1);
            Graphics g2 = buffer.createGraphics();
            for (int i = 0; i < tilePaths.size(); ++i) {
                java.awt.image.BufferedImage image = new BufferedImage(1, 1, 1);

                try {
                    image = ImageIO.read(new File(tilePaths.get(i)));
                } catch (IOException e) {
                    System.err.println("Something wrong happend while reading image");
                    continue;
                }

                g2.drawImage(image, tileX.get(i), tileY.get(i), p);
            }

            g2.dispose();
        }

        g.drawImage(buffer, 0, 0, p);
    }

    /**
     * Constructor for Map instance.
     */
    public Map() {
        tilePaths = new ArrayList<String>();
        tileX = new ArrayList<Integer>();
        tileY = new ArrayList<Integer>();
        buffer = null;
    }


}
