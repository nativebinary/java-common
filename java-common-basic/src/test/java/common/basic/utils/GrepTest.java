package common.basic.utils;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GrepTest {

    String input;
    ArrayList<String> executeResult;
    ArrayList<String> executeInvertResult;

    @Before
    public void setup() throws Exception {

        input = "I am a boy!\n" +
                "You are a girl?\n" +
                "He is my father.\n" +
                "She is her mother~\n" +
                "GrepTDDCode";

        executeResult = new ArrayList<String>();
        executeResult.add(Grep.execute(input, "girl"));
        executeResult.add(Grep.execute(input, "He is"));
        executeResult.add(Grep.execute(input, "~"));
        executeResult.add(Grep.execute(input, " "));

        executeInvertResult = new ArrayList<String>();
        executeInvertResult.add(Grep.executeInvert(input, "girl"));
        executeInvertResult.add(Grep.executeInvert(input, "He is"));
        executeInvertResult.add(Grep.executeInvert(input, "~"));
        executeInvertResult.add(Grep.executeInvert(input, " "));
    }

    @Test
    public void testExecute() throws Exception {

        ArrayList<String> toFind = new ArrayList<String>();
        toFind.add("girl");
        toFind.add("He is");
        toFind.add("~");
        toFind.add(" ");

        for(int i = 0; i < toFind.size(); i++) {

            assertEquals(executeResult.get(i), Grep.execute(input, toFind.get(i)));
        }

        toFind.clear();
        toFind.add("He is");
        toFind.add("~");
        toFind.add(" ");
        toFind.add("girl");

        for(int i = 0; i < toFind.size(); i++) {

            assertNotEquals(executeResult.get(i), Grep.execute(input, toFind.get(i)));
        }
    }

    @Test
    public void testExecuteInvert() throws Exception {

        ArrayList<String> toFind = new ArrayList<String>();
        toFind.add("girl");
        toFind.add("He is");
        toFind.add("~");
        toFind.add(" ");

        for(int i = 0; i < toFind.size(); i++) {

            assertEquals(executeInvertResult.get(i), Grep.executeInvert(input, toFind.get(i)));
        }

        toFind.clear();
        toFind.add("He is");
        toFind.add("~");
        toFind.add(" ");
        toFind.add("girl");

        for(int i = 0; i < toFind.size(); i++) {

            assertNotEquals(executeInvertResult.get(i), Grep.executeInvert(input, toFind.get(i)));
        }
    }
}