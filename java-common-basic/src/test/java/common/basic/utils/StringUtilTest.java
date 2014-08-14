package common.basic.utils;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilTest extends Assert {


    @Test(expected = InstantiationException.class)
    public void testConstructor() throws Exception {
        new StringUtil();
    }

    @Test
    public void testIsNullOrEmpty(){

        assertTrue(StringUtil.isNullOrEmpty(null));
        assertTrue(StringUtil.isNullOrEmpty(""));

        assertFalse(StringUtil.isNullOrEmpty(" "));
        assertFalse(StringUtil.isNullOrEmpty("test"));

    }

    @Test
    public void testIsNullOrWhitespace(){

        assertTrue(StringUtil.isNullOrWhitespace(null));
        assertTrue(StringUtil.isNullOrWhitespace(""));
        assertTrue(StringUtil.isNullOrWhitespace(" "));

        assertFalse(StringUtil.isNullOrWhitespace("test"));

    }

    @Test
    public void testIsNull(){

        assertEquals(StringUtil.isNull(null), "");

        assertEquals(StringUtil.isNull(""), "");
        assertEquals(StringUtil.isNull("  "), "  ");
        assertEquals(StringUtil.isNull("test"), "test");



        assertEquals(StringUtil.isNull(null,"default"), "default");
        assertEquals(StringUtil.isNull("","default"), "default");
        assertEquals(StringUtil.isNull("  ","default"), "  ");
        assertEquals(StringUtil.isNull("test","default"), "test");


    }

    @Test
    public void testEquals(){

        assertTrue(StringUtil.equals(null,null));
        assertTrue(StringUtil.equals("test","test"));
        assertTrue(StringUtil.equals("test ","test "));
        assertTrue(StringUtil.equals("", ""));

        assertFalse(StringUtil.equals(null,"test"));
        assertFalse(StringUtil.equals("test",null));
        assertFalse(StringUtil.equals("test", "t"));
        assertFalse(StringUtil.equals("t", "test"));
        assertFalse(StringUtil.equals("test",""));
        assertFalse(StringUtil.equals("","test"));

    }

    @Test
    public void testEqualsIgnoreCase(){

        assertTrue(StringUtil.equalsIgnoreCase(null, null));
        assertFalse(StringUtil.equalsIgnoreCase(null, "test"));
        assertFalse(StringUtil.equalsIgnoreCase("test", null));

        assertTrue(StringUtil.equalsIgnoreCase("test","test"));
        assertTrue(StringUtil.equalsIgnoreCase("",""));
        assertTrue(StringUtil.equalsIgnoreCase("test","TEST"));
        assertTrue(StringUtil.equalsIgnoreCase("test", "Test"));
        assertTrue(StringUtil.equalsIgnoreCase("TEST", "test"));
        assertTrue(StringUtil.equalsIgnoreCase("Test", "test"));
        assertTrue(StringUtil.equalsIgnoreCase("Test", "tesT"));

        assertFalse(StringUtil.equalsIgnoreCase("tEST", "t"));
        assertFalse(StringUtil.equalsIgnoreCase("t", "tEST"));
        assertFalse(StringUtil.equalsIgnoreCase("test", ""));
        assertFalse(StringUtil.equalsIgnoreCase("", "test"));

    }


    @Test
    public void testStartsWithIgnoreCase() throws Exception {

        assertTrue(StringUtil.startsWithIgnoreCase(null,null));
        assertFalse(StringUtil.startsWithIgnoreCase(null,"test"));
        assertFalse(StringUtil.startsWithIgnoreCase("test",null));

        assertTrue(StringUtil.startsWithIgnoreCase("test","test"));
        assertTrue(StringUtil.startsWithIgnoreCase("test","t"));
        assertTrue(StringUtil.startsWithIgnoreCase("test",""));
        assertTrue(StringUtil.startsWithIgnoreCase("",""));

        assertFalse(StringUtil.startsWithIgnoreCase("t","test"));
        assertFalse(StringUtil.startsWithIgnoreCase("","t"));

    }



    @Test
    public void testJoin(){
        assertEquals("a/b/c/d/e",StringUtil.join("/", "a", "b", "c", "d", "e"));
    }

    @Test
    public void testPadLeft(){
        assertEquals("     ",StringUtil.padLeft(null, 5, ' '));
        assertEquals("     ",StringUtil.padLeft("", 5, ' '));
        assertEquals("    a",StringUtil.padLeft("a", 5, ' '));
        assertEquals("testString",StringUtil.padLeft("testString", 5, ' '));
    }

    @Test
    public void testToString(){
        assertEquals("null", StringUtil.toString(null));

        String testString = "test";
        assertEquals("test", StringUtil.toString(testString));
    }

   @Test
    public void testCount(){
       assertEquals(0,StringUtil.count("test",'/'));
       assertEquals(1,StringUtil.count("/test",'/'));
       assertEquals(2,StringUtil.count("/test/",'/'));
    }

    @Test
    public void testReplaceFirstCharacterToLower(){
        assertEquals("test", StringUtil.replaceFirstCharacterToLower("Test"));
    }

}
