package common.basic.practices2014;

import org.junit.Assert;
import org.junit.Test;

public class ParseHexTest {

    @Test
    public void testCheckHex() throws Exception {

        try {
            ParseHex.parseHex("");
            Assert.fail();
        } catch (Exception ignored) {

        }

        try {
            ParseHex.parseHex(null);
            Assert.fail();
        } catch (Exception ignored) {

        }

        Assert.assertEquals(0, ParseHex.parseHex("0"));
        Assert.assertEquals(1, ParseHex.parseHex("1"));
        Assert.assertEquals(10, ParseHex.parseHex("a"));
        Assert.assertEquals(10, ParseHex.parseHex("A"));
        Assert.assertEquals(16, ParseHex.parseHex("10"));
        Assert.assertEquals(0x7fffffffffffffffL, ParseHex.parseHex("7fff" + "ffff" + "ffff" + "ffff"));
        Assert.assertEquals(-9223372036854775808L, ParseHex.parseHex("8000" + "0000" + "0000" + "0000"));


        Assert.assertEquals('0', 48);
        Assert.assertEquals('0', 0x30);

        Assert.assertEquals("400", ParseHex.toHex(1024));

        Assert.assertEquals("0", ParseHex.toHex(0));
        Assert.assertEquals("10", ParseHex.toHex(16));

        Assert.assertEquals(Long.toHexString(0-0xffffffffffffffffL), ParseHex.toHex(0-0xffffffffffffffffL));
        Assert.assertEquals(Long.toHexString(0-0xffffffffffff0000L), ParseHex.toHex(0-0xffffffffffff0000L));
        Assert.assertEquals(Long.toHexString(0-0xefffffffffff0000L), ParseHex.toHex(0-0xefffffffffff0000L));
        Assert.assertEquals(Long.toHexString(0-0x7fffffffffff0000L), ParseHex.toHex(0-0x7fffffffffff0000L));
        Assert.assertEquals(Long.toHexString(0-0xfffffffffff0000L), ParseHex.toHex(0-0xfffffffffff0000L));
        Assert.assertEquals(Long.toHexString(-2), ParseHex.toHex(-2));
        Assert.assertEquals(Long.toHexString(-1), ParseHex.toHex(-1));
        Assert.assertEquals("8000000000000000", ParseHex.toHex(-9223372036854775808L));

    }
}
