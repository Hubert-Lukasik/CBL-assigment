package src.main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
* MapBuilder class contains method to build a game map. 
*/
public class MapBuilder {
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
}
