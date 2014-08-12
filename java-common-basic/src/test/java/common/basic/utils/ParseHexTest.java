package common.basic.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jaebeomi on 8/12/14.
 */
public class ParseHexTest {

    @Test
    public void testCheckHex() throws Exception {

        Assert.assertEquals("1", ParseHex.getHex("1"));
        Assert.assertEquals("2", ParseHex.getHex("2"));
        Assert.assertEquals("3", ParseHex.getHex("3"));
        Assert.assertEquals("4", ParseHex.getHex("4"));
        Assert.assertEquals("5", ParseHex.getHex("5"));
        Assert.assertEquals("6", ParseHex.getHex("6"));
        Assert.assertEquals("7", ParseHex.getHex("7"));
        Assert.assertEquals("8", ParseHex.getHex("8"));
        Assert.assertEquals("9", ParseHex.getHex("9"));
        Assert.assertEquals("A", ParseHex.getHex("10"));
        Assert.assertEquals("B", ParseHex.getHex("11"));
        Assert.assertEquals("C", ParseHex.getHex("12"));
        Assert.assertEquals("D", ParseHex.getHex("13"));
        Assert.assertEquals("E", ParseHex.getHex("14"));
        Assert.assertEquals("F", ParseHex.getHex("15"));
        Assert.assertEquals("10", ParseHex.getHex("16"));
        Assert.assertEquals("11", ParseHex.getHex("17"));
        Assert.assertEquals("12", ParseHex.getHex("18"));
        Assert.assertEquals("13", ParseHex.getHex("19"));
        Assert.assertEquals("14", ParseHex.getHex("20"));
        Assert.assertEquals("15", ParseHex.getHex("21"));
        Assert.assertEquals("16", ParseHex.getHex("22"));
        Assert.assertEquals("17", ParseHex.getHex("23"));
        Assert.assertEquals("18", ParseHex.getHex("24"));
        Assert.assertEquals("19", ParseHex.getHex("25"));
        Assert.assertEquals("1A", ParseHex.getHex("26"));
        Assert.assertEquals("1B", ParseHex.getHex("27"));
        Assert.assertEquals("1C", ParseHex.getHex("28"));
        Assert.assertEquals("1D", ParseHex.getHex("29"));
        Assert.assertEquals("1E", ParseHex.getHex("30"));
        Assert.assertEquals("1F", ParseHex.getHex("31"));
        Assert.assertEquals("20", ParseHex.getHex("32"));
        Assert.assertEquals("557", ParseHex.getHex("1367"));
        Assert.assertEquals("E7319B", ParseHex.getHex("15151515"));
    }
}
