package common.basic.utils;

import common.basic.logs.Logger;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GrepTest {

    @Test
    public void testExecute() throws Exception {

        Grep grep = new Grep();
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("aa\nbb\ncc\ndd\naa\ncc");
        strings.add("aa");
        strings.add("cc");

        assertEquals("aa\naa", grep.execute(strings.get(0), strings.get(1)));

        assertNotEquals("aa\naa", grep.execute(strings.get(0), strings.get(2)));
    }

    @Test
    public void testExecuteInvert() throws Exception {

        Grep grep = new Grep();
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("aa\nbb\ncc\ndd\naa\ncc");
        strings.add("aa");
        strings.add("cc");

        assertEquals("bb\ncc\ndd\ncc", grep.executeInvert(strings.get(0), strings.get(1)));

        assertNotEquals("bb\ncc\ndd\ncc", grep.executeInvert(strings.get(0), strings.get(2)));
    }
}