package common.basic.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListUtilTest extends Assert {

    @Test(expected = InstantiationException.class)
    public void testConstructor() throws Exception {
        new ListUtil();
    }

    @Test
    public void testEquals(){
        final List<String> list1 = new ArrayList<String>();
        list1.add("a");
        list1.add("b");
        list1.add("c");

        final List<String> list2 = new ArrayList<String>();
        list2.add("a");
        list2.add("b");

        assertFalse(ListUtil.equals(list1,list2));

        list2.add("c");
        assertTrue(ListUtil.equals(list1,list2));

        list2.clear();
        list2.add("test");
        list2.add("test");
        list2.add("test");
        assertFalse(ListUtil.equals(list1,list2));
    }

    @Test
    public void testToListString(){
        final List<String> list1 = new ArrayList<String>();
        list1.add("a");
        list1.add("b");
        list1.add("c");

        assertEquals(list1, ListUtil.toListString(new String[]{"a", "b", "c"}));
    }
    @Test
    public void testCreate(){
        final List<String> testResult = new ArrayList<String>();
        testResult.add("a");
        testResult.add("b");
        testResult.add("c");

        assertEquals(testResult, ListUtil.create(new String[]{"a", "b", "c"}));
    }

    @Test
    public void testIsValidIndex(){

        final ArrayList<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");

        assertFalse(ListUtil.isValidIndex(list,-1));
        assertTrue(ListUtil.isValidIndex(list, 0));
        assertTrue(ListUtil.isValidIndex(list, 1));
        assertTrue(ListUtil.isValidIndex(list, 2));
        assertTrue(ListUtil.isValidIndex(list, 3));
        assertTrue(ListUtil.isValidIndex(list, 4));
        assertTrue(ListUtil.isValidIndex(list, 5));
        assertTrue(ListUtil.isValidIndex(list, 6));
        assertTrue(ListUtil.isValidIndex(list, 7));
        assertTrue(ListUtil.isValidIndex(list, 8));
        assertTrue(ListUtil.isValidIndex(list, 9));
        assertFalse(ListUtil.isValidIndex(list, 10));

    }

    @Test
    public void testCanConvert(){
        final Map<Integer, Object> map = new HashMap<Integer, Object>();
        map.put(0,"test");
        map.put(1,"test");
        map.put(2,"test");
        map.put(3,"test");
        map.put(4,"test");

        assertTrue(ListUtil.canConvert(map));

        final Map<Integer, Object> map2 = new HashMap<Integer, Object>();
        map2.put(10,"test");
        map2.put(11,"test");
        map2.put(12,"test");
        map2.put(13,"test");
        map2.put(14,"test");
        assertFalse(ListUtil.canConvert(map2));
    }

    @Test
    public void testConvert(){
        final Map<Integer, Object> map = new HashMap<Integer, Object>();
        map.put(0,"test1");
        map.put(1,"test2");
        map.put(2,"test3");
        map.put(3,"test4");
        map.put(4,"test5");

        final ArrayList<String> list = new ArrayList<String>();
        list.add("test1");
        list.add("test2");
        list.add("test3");
        list.add("test4");
        list.add("test5");

        assertEquals(list, ListUtil.convert(map));

        map.put(5,null);
        assertNull(ListUtil.convert(map));
    }
}
