package common.basic.xmls;

import common.basic.utils.StringUtil;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.Stack;

public class XmlWriter {
    final Writer writer;
    Stack<String> stackTag = new Stack<String>();

    public XmlWriter(Writer writer) {
        this.writer = writer;
    }

    public XmlWriter declaration(String declaration) throws IOException {
        writer.write(declaration);
        return this;
    }

    public XmlWriter declarationDocType(String s) throws IOException {
        return declaration("<!DOCTYPE " + s + ">");
    }

    public XmlWriter declarationHtml() throws IOException {
        return declarationDocType("html");
    }

    public XmlWriter declarationXmlVersion1() throws IOException {
        return declaration("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
    }


    public XmlWriter startTag(String name) throws IOException {

        stackTag.push(name);

        writer.write("<");
        writer.write(name);
        writer.write(">");

        return this;
    }

    public XmlWriter startTag(String name, Map<String, String> mapAttribute) throws IOException {

        stackTag.push(name);

        writer.write("<");
        writer.write(name);

        writeAttribute(mapAttribute);
        writer.write(">");

        return this;
    }

    public XmlWriter tag(String name, Map<String, String> mapAttribute) throws IOException {

        writer.write("<");
        writer.write(name);
        writeAttribute(mapAttribute);
        writer.write("/>");

        return this;

    }

    public XmlWriter endTag() throws IOException {

        final String name = stackTag.pop();

        writer.write("</");
        writer.write(name);
        writer.write(">");

        return this;
    }

    public XmlWriter text(String text) throws IOException {
        writer.write(escapeXml(text));
        return this;
    }

    public XmlWriter cdata(String text) throws IOException {
        writer.write("<![CDATA[");
        writer.write(text);
        writer.write("]]>");

        return this;
    }

    public int getTagDepth() {
        return stackTag.size();
    }

    private void writeAttribute(Map<String, String> mapAttribute) throws IOException {
        for (String key : mapAttribute.keySet()) {
            writer.write(" ");
            writer.write(key);
            writer.write("=\"");
            writer.write(escapeXml(mapAttribute.get(key))); // escape
            writer.write("\"");
        }
    }

    private String escapeXml(String text) {

        if (StringUtil.isNullOrEmpty(text))
            return "";

        return text
                .replaceAll("&", "&amp;")
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("'", "&apos;")
                .replaceAll("\"", "&quot;");
    }

    public void throwIfNotEnd() throws ExceptionXmlWriter {
        if(getTagDepth() != 0)
            throw new ExceptionXmlWriter();
    }
}
