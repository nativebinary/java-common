package common.basic.externals.jsoup;

import common.basic.utils.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class JsoupDocumentUtil {
    public static String selectFirstValue(Document document, String selector, String attrName) {
        Elements elements = document.select(selector);
        if (0 == elements.size())
            return null;

        Element element = elements.get(0);
        return element.attr(attrName);
    }

    public static List<String> selectAllValue(Document document, String selector, String attrName) {
        Elements elements = document.select(selector);

        List<String> listValue = new ArrayList<String>();
        for(Element element : elements) {
            String value = element.attr(attrName);
            if (!StringUtil.isNullOrEmpty(value))
                listValue.add(value);
        }

        return listValue;
    }
}
