package src.test.java;


import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import org.junit.Assert;
import org.junit.Test;
import src.main.java.Map;


/**
 * Classes for testing MapBuilder.
 */
public class MapBuilderTest {
    @Test
    public void testReadMap() {
        ArrayList<ArrayList<String>> expected = new ArrayList<ArrayList<String>>();
        expected.add(new ArrayList<String>());
        expected.add(new ArrayList<String>());
        expected.add(new ArrayList<String>());

        expected.get(0).add("1");
        expected.get(0).add("2");
        expected.get(0).add("3");
        expected.get(1).add("-4");
        expected.get(1).add("-5");
        expected.get(1).add("-6");
        expected.get(2).add("0");
        expected.get(2).add("2");
        expected.get(2).add("22");
        ArrayList<ArrayList<String>> res = Map.readMap("testMap.txt");
        Assert.assertEquals(expected, res);
    }

    @Test
    public void testReadMapNonExistingFile() {
        ArrayList<ArrayList<String>> expected = new ArrayList<ArrayList<String>>();
        Assert.assertEquals(expected, Map.readMap("notExistingMap.txt"));
    }

    @Test
    public void testReadMapNoFileSpecified() {
        ArrayList<ArrayList<String>> expected = new ArrayList<ArrayList<String>>();
        Assert.assertEquals(expected, Map.readMap(""));
    }
 
    @Test
    public void testBuildMapEmptyDescription() {
        JPanel expected = new JPanel();
        ArrayList<ArrayList<String>> emp = new ArrayList<ArrayList<String>>();
        Assert.assertEquals(expected, Map.buildMap(emp));
    }

    @Test
    public void testBuildMapCornerPoints() {
        ArrayList<ArrayList<String>> desc = new ArrayList<ArrayList<String>>();
        desc.add(new ArrayList<String>());
        desc.get(0).add("tileTest");
        desc.get(0).add("tileTest");
        desc.get(0).add("tileTest");

        JPanel expected = new JPanel();


        Assert.assertEquals(expected, Map.buildMap(desc));
    }
}
