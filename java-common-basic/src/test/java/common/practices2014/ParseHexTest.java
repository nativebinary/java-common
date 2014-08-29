package common.practices2014;

import org.junit.Assert;
import org.junit.Test;

public class ParseHexTest {

    @Test(expected = InstantiationException.class)
    public void testConstructor() throws Exception {
        new ParseHex();
    }

    @Test
    public void testParseHex() throws Exception {
        Assert.assertEquals(0x0, ParseHex.parseHex("0"));
        Assert.assertEquals(0x1, ParseHex.parseHex("1"));
        Assert.assertEquals(0xa, ParseHex.parseHex("a"));
        Assert.assertEquals(0xA, ParseHex.parseHex("A"));
        Assert.assertEquals(0x10, ParseHex.parseHex("10"));
        Assert.assertEquals(0x7fffffffffffffffL, ParseHex.parseHex("7fff" + "ffff" + "ffff" + "ffff"));
        Assert.assertEquals(-9223372036854775808L, ParseHex.parseHex("8000" + "0000" + "0000" + "0000"));
    }

    @Test(expected = ParseHex.ExceptionInvalidArgument.class)
    public void testParseHexForEmptyString() throws Exception {
        ParseHex.parseHex("");
    }

    @Test(expected = ParseHex.ExceptionInvalidArgument.class)
    public void testParseHexForNull() throws Exception {
        ParseHex.parseHex(null);
    }

    @Test(expected = ParseHex.ExceptionInvalidArgument.class)
    public void testParseHexForTooLongString() throws Exception {
        ParseHex.parseHex("8000" + "0000" + "0000" + "0000" + "8000" + "0000" + "0000" + "0000");
    }

    @Test(expected = ParseHex.ExceptionInvalidArgument.class)
    public void testParseHexForInvalidHexString() throws Exception {
        Assert.assertEquals(16, ParseHex.parseHex("GG"));
    }

    @Test
    public void testToHex() throws Exception {

        Assert.assertEquals('0', 48);
        Assert.assertEquals('0', 0x30);

        Assert.assertEquals("400", ParseHex.toHex(1024));

        Assert.assertEquals("0", ParseHex.toHex(0));
        Assert.assertEquals("10", ParseHex.toHex(16));

        Assert.assertEquals(Long.toHexString(0 - 0xffffffffffffffffL), ParseHex.toHex(0 - 0xffffffffffffffffL));
        Assert.assertEquals(Long.toHexString(0 - 0xffffffffffff0000L), ParseHex.toHex(0 - 0xffffffffffff0000L));
        Assert.assertEquals(Long.toHexString(0 - 0xefffffffffff0000L), ParseHex.toHex(0 - 0xefffffffffff0000L));
        Assert.assertEquals(Long.toHexString(0 - 0x7fffffffffff0000L), ParseHex.toHex(0 - 0x7fffffffffff0000L));
        Assert.assertEquals(Long.toHexString(0 - 0xfffffffffff0000L), ParseHex.toHex(0 - 0xfffffffffff0000L));
        Assert.assertEquals(Long.toHexString(-2), ParseHex.toHex(-2));
        Assert.assertEquals(Long.toHexString(-1), ParseHex.toHex(-1));
        Assert.assertEquals("8000000000000000", ParseHex.toHex(-9223372036854775808L));
    }

}
