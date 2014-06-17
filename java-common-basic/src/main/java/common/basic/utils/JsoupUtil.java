package common.basic.utils;

import common.basic.logs.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class JsoupUtil {

    JsoupUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static String getTitle(File file) {
        try {
            final Document document = Jsoup.parse(file, "utf-8");
            return document.title();
        } catch (IOException e) {
            Logger.e();
            return file.getName();
        }
    }

    public static Element selectFirstElement(Element element, String cssQuery) {

        final Elements elements = element.select(cssQuery);
        if (0 == elements.size())
            return null;

        return elements.get(0);
    }
    public static String getText(Element element) {

        if (null == element)
            return "";

        return element.text();
    }

    public static String getFirstImgSrc(InputStream inputStream, String baseUri) throws IOException {
        final Document document = Jsoup.parse(inputStream, "UTF-8", baseUri);
        final Element elementImg = selectFirstElement(document, "img[src]");
        if(elementImg == null)
            return null;

        return elementImg.attr("src");
    }
}
