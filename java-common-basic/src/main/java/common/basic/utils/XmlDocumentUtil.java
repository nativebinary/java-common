package common.basic.utils;

import common.basic.logs.Logger;
import org.w3c.dom.Document;

import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;

public class XmlDocumentUtil {
    static final DocumentBuilderFactory builderFactory;
    static DocumentBuilder builder;

    static {
        builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setNamespaceAware(false);
        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            Logger.e(e);
        }
    }
    public static Document createDocument(InputStream inputStream) {
        try {
            return builder.parse(inputStream);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getXPathValue(Document document, XPathExpression xPathExpression, String defaultValue) {
        try {
            return (String)xPathExpression.evaluate(document, XPathConstants.STRING);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static boolean getXPathValue(Document document, XPathExpression xPathExpression, boolean defaultValue) {
        String v = getXPathValue(document, xPathExpression, BooleanUtil.convert01(defaultValue));
        return BooleanUtil.convert01(v);
    }

    public static int getXPathValue(Document document, XPathExpression xPathExpression, int defaultValue) {
        String v = getXPathValue(document, xPathExpression, Integer.toString(defaultValue));
        return Integer.parseInt(v);
    }
}
