package common.basic.utils;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GrepTest {

    @Test
    public void testExecute() throws Exception {

        String input = "I am a boy!\n" +
                "You are a girl?\n" +
                "He is my father.\n" +
                "She is her mother~\n" +
                "GrepTDDCode";

        ArrayList<String> toFind = new ArrayList<String>();
        toFind.add("girl");     //단어 체크
        toFind.add("He is");    // 문장 체크
        toFind.add("~");        // 특수문자 체크
        toFind.add(" ");        // 공백 체크

        ArrayList<String> result = new ArrayList<String>();
        result.add("You are a girl?");
        result.add("He is my father.");
        result.add("She is her mother~");
        result.add("I am a boy!\n" +
                "You are a girl?\n" +
                "He is my father.\n" +
                "She is her mother~");

        for(int i = 0; i < toFind.size(); i++) {

            assertEquals(result.get(i), Grep.execute(input, toFind.get(i)));
        }

        toFind.clear();
        toFind.add("He is");
        toFind.add("~");
        toFind.add(" ");
        toFind.add("girl");

        for(int i = 0; i < toFind.size(); i++) {

            assertNotEquals(result.get(i), Grep.execute(input, toFind.get(i)));
        }
    }

    @Test
    public void testExecuteInvert() throws Exception {

        String input = "I am a boy!\n" +
                "You are a girl?\n" +
                "He is my father.\n" +
                "She is her mother~\n" +
                "GrepTDDCode";

        ArrayList<String> toFind = new ArrayList<String>();
        toFind.add("girl");     //단어 체크
        toFind.add("He is");    // 문장 체크
        toFind.add("~");        // 특수문자 체크
        toFind.add(" ");        // 공백 체크

        ArrayList<String> result = new ArrayList<String>();

        result.add("I am a boy!\n" +
                "He is my father.\n" +
                "She is her mother~\n" +
                "GrepTDDCode");
        result.add("I am a boy!\n" +
                "You are a girl?\n" +
                "She is her mother~\n" +
                "GrepTDDCode");
        result.add("I am a boy!\n" +
                "You are a girl?\n" +
                "He is my father.\n" +
                "GrepTDDCode");
        result.add("GrepTDDCode");

        for(int i = 0; i < toFind.size(); i++) {

            assertEquals(result.get(i), Grep.executeInvert(input, toFind.get(i)));
        }

        toFind.clear();
        toFind.add("He is");
        toFind.add("~");
        toFind.add(" ");
        toFind.add("girl");

        for(int i = 0; i < toFind.size(); i++) {

            assertNotEquals(result.get(i), Grep.executeInvert(input, toFind.get(i)));
        }
    }
}