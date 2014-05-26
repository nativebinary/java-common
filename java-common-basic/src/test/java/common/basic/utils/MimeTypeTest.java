package common.basic.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class MimeTypeTest extends Assert {

    @Test
    public void testDetect() throws Exception {

        assertTrue(MimeType.Epub.isMatchExt("epub"));
        assertTrue(MimeType.Png.isMatchExt("png"));

        assertEquals(MimeType.Png, MimeType.detect(new File("test.png")));
        assertEquals(MimeType.Jpg, MimeType.detect(new File("test.jpg")));
        assertEquals(MimeType.Jpeg, MimeType.detect(new File("test.jpeg")));
        assertEquals(MimeType.Html, MimeType.detect(new File("test.html")));
        assertEquals(MimeType.Html, MimeType.detect(new File("test.htm")));
        assertEquals(MimeType.Txt, MimeType.detect(new File("test.txt")));

        assertEquals(MimeType.Unknown, MimeType.detect(new File("test.asdfasf")));

    }

    @Test
    public void testIsImage(){

        assertFalse(MimeType.Unknown.isImage());
        assertFalse(MimeType.Epub.isImage());
        assertFalse(MimeType.Html.isImage());
        assertFalse(MimeType.Txt.isImage());
        assertTrue(MimeType.Png.isImage());
        assertTrue(MimeType.Jpg.isImage());
        assertFalse(MimeType.Ncx.isImage());
        assertFalse(MimeType.Xhtml.isImage());
        assertFalse(MimeType.Opf.isImage());

    }
}
