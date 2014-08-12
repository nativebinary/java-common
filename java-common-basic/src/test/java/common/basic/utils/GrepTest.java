package common.basic.utils;

import org.junit.Assert;
import org.junit.Test;

public class GrepTest {
    final String input =
            "I am a boy!\n" +
            "You are a girl?\n" +
            "He is my father.\n" +
            "She is her mother~\n" +
            "GrepTDDCode";

    @Test(expected = InstantiationException.class)
    public void testConstructor() throws Exception {
        new Grep();
    }

    @Test
    public void testExecute() throws Exception {

        Assert.assertEquals(
                "You are a girl?",
                Grep.execute(input, "girl"));

        Assert.assertEquals(
                "He is my father.\n" +
                "She is her mother~",
                Grep.execute(input, "is"));

        Assert.assertEquals(
                "He is my father.\n" +
                "She is her mother~",
                Grep.execute(input, "her"));

        Assert.assertEquals(
                "She is her mother~",
                Grep.execute(input, "~"));
    }

    @Test
    public void testExecuteInvert() throws Exception {

        Assert.assertEquals(
                "I am a boy!\n" +
                "He is my father.\n" +
                "She is her mother~\n" +
                "GrepTDDCode",
                Grep.executeInvert(input, "girl"));

        Assert.assertEquals(
                "I am a boy!\n" +
                "You are a girl?\n" +
                "GrepTDDCode",
                Grep.executeInvert(input, "is"));

        Assert.assertEquals(
                "I am a boy!\n" +
                "You are a girl?\n" +
                "GrepTDDCode",
                Grep.executeInvert(input, "her"));
    }
}