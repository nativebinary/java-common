package common.practices2014;

import org.junit.Assert;
import org.junit.Test;

public class StringInterningTest extends Assert {
    @SuppressWarnings({"RedundantStringConstructorCall", "StringEquality"})
    @Test
    public void testParseHex() throws Exception {
        String a1 = "A";
        String a2 = "A";
        String a3 = new String("A");
        String a4 = new String("A");

        assertTrue(a1.equals("A"));
        assertTrue(a2.equals("A"));
        assertTrue(a3.equals("A"));
        assertTrue(a4.equals("A"));

        assertTrue(a1.equals(a2));
        assertTrue(a2.equals(a3));
        assertTrue(a3.equals(a4));
        assertTrue(a4.equals(a1));

        assertTrue((a1 == "A"));
        assertTrue((a2 == "A"));
        assertTrue(!(a3 == "A"));
        assertTrue(!(a4 == "A"));

        assertTrue((a1 == a2));
        assertTrue(!(a2 == a3));
        assertTrue(!(a3 == a4));
        assertTrue(!(a4 == a1));

        String b = "";
        for (int i = 0; i < 10; i++) {
            b = b + String.valueOf(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(String.valueOf(i));
        }

        assertTrue(b.equals(sb.toString()));
    }
}