package common.basic.externals.jsoup;

import common.basic.htmlFetcher.MaxSelector;
import common.basic.htmlFetcher.Retryable;
import common.basic.logs.Logger;
import common.basic.utils.StringUtil;
import common.basic.utils.URLUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HtmlFetcher
{
    public final String url;

    public Map<String, Document> mapDocument;

    public String title;
    public String content;

    public List<String> listImageUrl = new ArrayList<String>();
    public List<String> listVideoUrl = new ArrayList<String>();

    public HtmlFetcher(String url)
    {
        this.url = URLUtil.prependHttpIfNoProtocol(url);
    }

    public void fetch(boolean loadAll)
    {
        Logger.i(url);
        this.mapDocument = getMapDocumentFrameSrc(url);
        this.mapDocument = HtmlDocumentFilter.filter(url, mapDocument);
        this.title = getTitleParsed();
        this.content = getDescriptionParsed();

        parseListImageUrl(loadAll);
        parseListVideoUrl();


        if (StringUtil.isNullOrEmpty(this.content))
        {
            this.content = "";
            for (Document document : mapDocument.values())
            {
                String bodyDescription = HtmlParser.Body.getDescription(document);

                if (this.content.length() < bodyDescription.length())
                    this.content = bodyDescription;
            }

            this.content = StringUtil.left(this.content, 200);
        }
    }

    private String getTitleParsed() {
        for (Document document : mapDocument.values()) {
            String title = HtmlParser.OpenGraph.getTitle(document);
            if (!StringUtil.isNullOrEmpty(title))
                return title;

            title = HtmlParser.Meta.getTitle(document);
            if (!StringUtil.isNullOrEmpty(title))
                return title;
        }


        MaxSelector<String> maxSelector = new MaxSelector<String>();

        for (Document document : mapDocument.values()) {
            String title = document.title();
            if(StringUtil.isNullOrEmpty(title.trim()))
                continue;

            maxSelector.increase(title);
        }

        return maxSelector.getMax();
    }

    private String getDescriptionParsed() {
        for (Document document : mapDocument.values()) {
            String description = HtmlParser.OpenGraph.getDescription(document);
            if (!StringUtil.isNullOrEmpty(description))
                return description;

            description = HtmlParser.Meta.getDescription(document);
            if (!StringUtil.isNullOrEmpty(description))
                return description;
        }

        return null;
    }

    private void addImageUrlUnique(String imageUrl) {
        if (StringUtil.isNullOrEmpty(imageUrl))
            return;

        if(listImageUrl.contains(imageUrl))
            return;

        listImageUrl.add(imageUrl);
    }

    private void parseListImageUrl(boolean loadAll) {

        for (Document document : mapDocument.values()) {

            for (String imageUrl : HtmlParser.OpenGraph.getListImageUrl(document))
            {
                addImageUrlUnique(imageUrl);
            }

            for (String imageUrl : HtmlParser.Meta.getListImageUrl(document))
            {
                addImageUrlUnique(imageUrl);
            }

            if (!loadAll && listImageUrl.size() == 1) return;

            for (String imageUrl : HtmlParser.Body.getListImageUrl(document))
            {
                addImageUrlUnique(imageUrl);
            }
        }
    }

    private void parseListVideoUrl() {

        for (Document document : mapDocument.values()) {

            listVideoUrl.addAll(HtmlParser.OpenGraph.getListVideoUrl(document));
            listVideoUrl.addAll(HtmlParser.Meta.getListVideoUrl(document));
            listVideoUrl.addAll(HtmlParser.Body.getListVideoUrl(document));

        }
    }

    public static Document getDocument(final String url) throws IOException {
        return new Retryable<Document>() {
            @Override
            public Document action() throws Exception {
                return Jsoup.connect(url).timeout(30 * 1000).get();
            }
        }.run(10, 1000);
    }

    public static Map<String, Document> getMapDocumentFrameSrc(String url) {
        Map<String, Document> map = new HashMap<String, Document>();

        try {
            Document document = getDocument(url);
            map.put(url, document);

            //Logger.info("%s - %s", document.title(), document.outerHtml());
            Elements elementsFrame = document.getElementsByTag("frame");
            Elements elementsIFrame = document.getElementsByTag("iframe");

            elementsFrame.addAll(elementsIFrame);
            for(Element element : elementsFrame){

                String src = element.attr("src");

                if(null == src || src.equals(""))
                    continue;

                String srcAbs = element.attr("abs:src");
                if(StringUtil.isNullOrEmpty(srcAbs))
                    continue;


                if(isCrossDomain(url, srcAbs)){
                    if(!isAlternateDomainNaver(element))
                        continue;
                }

                Map<String, Document> listChild  = getMapDocumentFrameSrc(srcAbs);
                map.putAll(listChild);
            }

        } catch (IOException e) {
            Logger.e(e);
        }
        return map;
    }

    private static boolean isCrossDomain(String urlString1, String urlString2) throws MalformedURLException {

        URL url1 = new URL(urlString1);
        URL url2 = new URL(urlString2);

        if(!url1.getProtocol().equals(url2.getProtocol()))
            return true;

        if(!url1.getHost().equals(url2.getHost()))
            return true;

        if(url1.getPort() != url2.getPort())
            return true;

        return false;
    }

    private static boolean isAlternateDomainNaver(Element element)
    {
        String id = element.attr("id");
        String name = element.attr("name");
        if("screenFrame".equals(id) && "screenFrame".equals(name))
            return true;

        return false;
    }


    public String getImageUrlFirst() {
        return listImageUrl.size() > 0 ? listImageUrl.get(0) : "";
    }
}


