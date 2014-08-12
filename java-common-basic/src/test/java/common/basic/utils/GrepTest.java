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

        for(int i=0; i<toFind.size(); i++) {

            assertEquals(result.get(i), Grep.execute(input, toFind.get(i)));
        }

        toFind.clear();
        toFind.add("us: @MailOnline on Twitter | DailyMai");
        toFind.add("@");
        toFind.add(" ");
        toFind.add("curls");

        for(int i=0; i<toFind.size(); i++) {

            assertNotEquals(result.get(i), Grep.execute(input, toFind.get(i)));
        }
    }

    @Test
    public void testExecuteInvert() throws Exception {

        String input = "Further increasing height to her frame, the Problem hitmaker opted for nude pumps, which contrasted nicely against her bronzed complexion, while her light brown locks were worn in a voluminous high pony-tail with cascading curls.\n" +
                "Accessories were minimal – just sleek diamond studs and a delicate bracelet, while she sported a brown lip, golden eye shadow and seriously long lashes.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.dailymail.co.uk/tvshowbiz/article-2721666/Ariana-Grande-flashes-lot-leg-sexy-bejewelled-mini-dress-Teen-Choice-Awards.html#ixzz3A5IWZbNk\n" +
                "Follow us: @MailOnline on Twitter | DailyMail on Facebook\n" +
                "abcdefgaldkfjlkawejflkawjflkawjefkljaweklfjlkjffffffff";

        ArrayList<String> toFind = new ArrayList<String>();
        toFind.add("curls");
        toFind.add("http://www.dailymail.co.uk/tvshowbiz/article-2721666/Ariana-Grande-flashes-lot-leg-sexy-bejewelled-mini-dress-Teen-Choice-Awards.html");
        toFind.add("@");
        toFind.add(" ");

        ArrayList<String> result = new ArrayList<String>();

        result.add("Accessories were minimal – just sleek diamond studs and a delicate bracelet, while she sported a brown lip, golden eye shadow and seriously long lashes.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.dailymail.co.uk/tvshowbiz/article-2721666/Ariana-Grande-flashes-lot-leg-sexy-bejewelled-mini-dress-Teen-Choice-Awards.html#ixzz3A5IWZbNk\n" +
                "Follow us: @MailOnline on Twitter | DailyMail on Facebook\n" +
                "abcdefgaldkfjlkawejflkawjflkawjefkljaweklfjlkjffffffff");
        result.add("Further increasing height to her frame, the Problem hitmaker opted for nude pumps, which contrasted nicely against her bronzed complexion, while her light brown locks were worn in a voluminous high pony-tail with cascading curls.\n" +
                "Accessories were minimal – just sleek diamond studs and a delicate bracelet, while she sported a brown lip, golden eye shadow and seriously long lashes.\n" +
                "\n" +
                "\n" +
                "Follow us: @MailOnline on Twitter | DailyMail on Facebook\n" +
                "abcdefgaldkfjlkawejflkawjflkawjefkljaweklfjlkjffffffff");
        result.add("Further increasing height to her frame, the Problem hitmaker opted for nude pumps, which contrasted nicely against her bronzed complexion, while her light brown locks were worn in a voluminous high pony-tail with cascading curls.\n" +
                "Accessories were minimal – just sleek diamond studs and a delicate bracelet, while she sported a brown lip, golden eye shadow and seriously long lashes.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.dailymail.co.uk/tvshowbiz/article-2721666/Ariana-Grande-flashes-lot-leg-sexy-bejewelled-mini-dress-Teen-Choice-Awards.html#ixzz3A5IWZbNk\n" +
                "abcdefgaldkfjlkawejflkawjflkawjefkljaweklfjlkjffffffff");
        result.add("\n" +
               "\n" +
               "abcdefgaldkfjlkawejflkawjflkawjefkljaweklfjlkjffffffff");

        for(int i=0; i<toFind.size(); i++) {

            assertEquals(result.get(i), Grep.executeInvert(input, toFind.get(i)));
        }

        toFind.clear();
        toFind.add("us: @MailOnline on Twitter | DailyMai");
        toFind.add("@");
        toFind.add(" ");
        toFind.add("curls");

        for(int i=0; i<toFind.size(); i++) {

            assertNotEquals(result.get(i), Grep.executeInvert(input, toFind.get(i)));
        }
    }
}