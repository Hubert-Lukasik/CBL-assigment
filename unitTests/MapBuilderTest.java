package unitTests;

import game.Map;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;


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

        Map m = new Map();
        ArrayList<ArrayList<String>> res = m.readMap("testMap.txt");
        Assert.assertEquals(expected, res);
    }

    @Test
    public void testReadMapNonExistingFile() {
        Map m = new Map();
        ArrayList<ArrayList<String>> expected = new ArrayList<ArrayList<String>>();
        Assert.assertEquals(expected, m.readMap("notExistingMap.txt"));
    }

    @Test
    public void testReadMapNoFileSpecified() {
        Map m = new Map();
        ArrayList<ArrayList<String>> expected = new ArrayList<ArrayList<String>>();
        Assert.assertEquals(expected, m.readMap(""));
    }

    @Test
    public void testReadMapWithSpace() {
        ArrayList<ArrayList<String>> expected = new ArrayList<ArrayList<String>>();
        expected.add(new ArrayList<String>());

        expected.get(0).add("1");
        expected.get(0).add("-");
        expected.get(0).add("1");

        Map m = new Map();

        ArrayList<ArrayList<String>> res = m.readMap("testMapSpace.txt");
        Assert.assertEquals(expected, res);
    }
 
    
    @Test
    @Ignore("Disabled, because buildMap no longer returns elements")
    public void testBuildMapEmptyDescription() {
        //ArrayList<String> expected = new ArrayList<String>();
        //ArrayList<ArrayList<String>> emp = new ArrayList<ArrayList<String>>();
        //Assert.assertEquals(expected, Map.buildMap(emp));
    }

    @Test
    @Ignore("Disabled, because buildMap no longer returns elements")
    public void testBuildMapCornerPoints() {
        ArrayList<ArrayList<String>> desc = new ArrayList<ArrayList<String>>();
        desc.add(new ArrayList<String>());
        desc.get(0).add("testTile");
        desc.get(0).add("testTile");
        desc.get(0).add("testTile");

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("testTile.png");
        expected.add("0");
        expected.add("0");
        expected.add("testTile.png");
        expected.add("40");
        expected.add("0");
        expected.add("testTile.png");
        expected.add("80");
        expected.add("0");

        //Assert.assertEquals(expected, Map.buildMap(desc));
    }

    @Test
    @Ignore("Disabled, because buildMap no longer returns elements")
    public void testBuildMapDifferentTiles() {
        ArrayList<ArrayList<String>> desc = new ArrayList<ArrayList<String>>();
        desc.add(new ArrayList<String>());
        desc.get(0).add("testTile");
        desc.get(0).add("testTile2");

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("testTile.png");
        expected.add("0");
        expected.add("0");
        expected.add("testTile2.png");
        expected.add("40");
        expected.add("0");

        //Assert.assertEquals(expected, Map.buildMap(desc));
    }

    @Test
    @Ignore("Disabled, because buildMap no longer returns elements")
    public void testBuildMapMoreRows() {
        ArrayList<ArrayList<String>> desc = new ArrayList<ArrayList<String>>();
        desc.add(new ArrayList<String>());
        desc.get(0).add("testTile");
        desc.add(new ArrayList<String>());
        desc.get(1).add("testTile");

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("testTile.png");
        expected.add("0");
        expected.add("0");
        expected.add("testTile.png");
        expected.add("0");
        expected.add("40");

        //Assert.assertEquals(expected, Map.buildMap(desc));
    }

    @Test
    @Ignore("Disabled, because buildMap no longer returns elements")
    public void testBuildMapDifferentLengthRows() {
        ArrayList<ArrayList<String>> desc = new ArrayList<ArrayList<String>>();
        desc.add(new ArrayList<String>());
        desc.get(0).add("testTile");
        desc.add(new ArrayList<String>());
        desc.get(1).add("testTile");
        desc.get(1).add("testTile");

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("testTile.png");
        expected.add("0");
        expected.add("0");
        expected.add("testTile.png");
        expected.add("0");
        expected.add("40");
        expected.add("testTile.png");
        expected.add("40");
        expected.add("40");

        //Assert.assertEquals(expected, Map.buildMap(desc));
    }

    @Test
    @Ignore("Disabled, because buildMap no longer returns elements")
    public void testBuildMapNullValues() {
        ArrayList<ArrayList<String>> desc = new ArrayList<ArrayList<String>>();
        desc.add(new ArrayList<String>());
        desc.get(0).add("testTile");
        desc.add(new ArrayList<String>());
        desc.get(1).add("testTile");
        desc.get(1).add(" ");
        desc.get(1).add("testTile");

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("testTile.png");
        expected.add("0");
        expected.add("0");
        expected.add("testTile.png");
        expected.add("0");
        expected.add("40");
        expected.add("testTile.png");
        expected.add("80");
        expected.add("40");

        //Assert.assertEquals(expected, Map.buildMap(desc));
    }






}
