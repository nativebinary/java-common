package common.basic.utils;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilTest extends Assert {

    @Test(expected = InstantiationException.class)
    public void testConstructor() throws Exception {
        new StringUtil();
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
    public void testEquals(){
        assertTrue(StringUtil.equals(null,null));
        assertFalse(StringUtil.equals(null,"test"));
        assertFalse(StringUtil.equals("test",null));

        assertTrue(StringUtil.equals("test","test"));
        assertTrue(StringUtil.equals("", ""));

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
    public void testJoin(){
        String testString[]={"a","b","c","d","e"};
        assertEquals("a/b/c/d/e",StringUtil.join("/",testString));
    }

    @Test
    public void testPadLeft(){
        assertEquals("     ",StringUtil.padLeft(null, 5, ' '));
        assertEquals("     ",StringUtil.padLeft("", 5, ' '));
        assertEquals("testString",StringUtil.padLeft("testString", 5, ' '));
    }

    @Test
    public void testToString(){
        assertEquals("[null]",StringUtil.toString(null));

        String testString = "test";
        assertEquals("test",StringUtil.toString(testString));
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
