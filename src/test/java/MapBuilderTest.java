package src.test.java;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;
import src.main.java.MapBuilder;


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
        ArrayList<ArrayList<String>> res = MapBuilder.readMap("testMap.txt");
        Assert.assertEquals(expected, res);
    }

    @Test
    public void testReadMapNonExistingFile() {
        ArrayList<ArrayList<String>> expected = new ArrayList<ArrayList<String>>();
        Assert.assertEquals(expected, MapBuilder.readMap("notExistingMap.txt"));
    }

    @Test
    public void testReadMapNoFileSpecified() {
        ArrayList<ArrayList<String>> expected = new ArrayList<ArrayList<String>>();
        Assert.assertEquals(expected, MapBuilder.readMap(""));
    }
}
