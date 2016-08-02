package common.basic.xmls;

import common.basic.utils.MapBuilderT;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

public class XmlWriterTest extends Assert {

    @Test
    public void testXmlWriter1() throws Exception {

        StringWriter stringWriter = new StringWriter();
        XmlWriter xmlWriter = new XmlWriter(stringWriter);

        xmlWriter.declarationHtml();
        xmlWriter.startTag("html")
                .startTag("body")
                .startTag("h1")
                .text("H1TEXT")
                .endTag()
                .endTag()
                .endTag();

        assertEquals("<!DOCTYPE html><html><body><h1>H1TEXT</h1></body></html>", stringWriter.toString());
        assertEquals(0, xmlWriter.getTagDepth());
    }

    @Test
    public void testXmlWriter2() throws Exception {

        StringWriter stringWriter = new StringWriter();
        XmlWriter xmlWriter = new XmlWriter(stringWriter);

        xmlWriter.tag("img",
                new Attributes()
                    .and("class", "classValue")
                    .and("style", "styleValue"));

        assertEquals("<img style=\"styleValue\" class=\"classValue\"/>", stringWriter.toString());
        assertEquals(0, xmlWriter.getTagDepth());
    }

    @Test
    public void testXmlWriter3() throws Exception {

        StringWriter stringWriter = new StringWriter();
        XmlWriter xmlWriter = new XmlWriter(stringWriter);

        MapBuilderT<String, String> mapBuilder = new MapBuilderT<String, String>();
        mapBuilder.and("class", "classValue").and("style", "styleValue");

        xmlWriter.startTag("div", mapBuilder).text("asdfasfd").endTag();

        assertEquals("<div style=\"styleValue\" class=\"classValue\">asdfasfd</div>", stringWriter.toString());
        assertEquals(0, xmlWriter.getTagDepth());
    }

    @Test
    public void testXmlWriter4() throws Exception {

        StringWriter stringWriter = new StringWriter();
        XmlWriter xmlWriter = new XmlWriter(stringWriter);

        MapBuilderT<String, String> mapBuilder = new MapBuilderT<String, String>();
        mapBuilder.and("class", "classValue").and("style", "styleValue");

        xmlWriter.startTag("div", mapBuilder).cdata("asdfasfd").endTag();

        assertEquals("<div style=\"styleValue\" class=\"classValue\"><![CDATA[asdfasfd]]></div>", stringWriter.toString());
        assertEquals(0, xmlWriter.getTagDepth());
    }

    @Test
    public void testXmlWriter5() throws Exception {

        StringWriter stringWriter = new StringWriter();
        XmlWriter xmlWriter = new XmlWriter(stringWriter);

        MapBuilderT<String, String> mapBuilder = new MapBuilderT<String, String>();
        mapBuilder.and("class", "classValue").and("style", "styleValue");

        xmlWriter.startTag("div", mapBuilder)
                .text("divText")
                .startTag("span")
                .text("spanText")
                .text("")
                .endTag()
                .endTag();

        assertEquals("<div style=\"styleValue\" class=\"classValue\">divText<span>spanText</span></div>", stringWriter.toString());
        assertEquals(0, xmlWriter.getTagDepth());
    }

    @Test(expected = ExceptionXmlWriter.class)
    public void testXmlWriterFail() throws Exception {

        StringWriter stringWriter = new StringWriter();
        XmlWriter xmlWriter = new XmlWriter(stringWriter);
        xmlWriter.startTag("a");
        xmlWriter.throwIfNotEnd();
    }
}
