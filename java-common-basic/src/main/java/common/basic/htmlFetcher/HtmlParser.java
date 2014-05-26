package common.basic.htmlFetcher;

//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;

public enum HtmlParser {
//    OpenGraph {
//        @Override
//        public String getTitle(Document document) {
//            return JsoupDocumentUtil.selectFirstValue(document, "meta[property=og:title]", "content");
//        }
//
//        @Override
//        public String getDescription(Document document) {
//            return JsoupDocumentUtil.selectFirstValue(document, "meta[property=og:description]", "content");
//        }
//
//        @Override
//        public List<String> getListImageUrl(Document document) {
//            return JsoupDocumentUtil.selectAllValue(document, "meta[property=og:image]", "content");
//        }
//
//        @Override
//        public List<String> getListVideoUrl(Document document) {
//            return JsoupDocumentUtil.selectAllValue(document, "meta[property=og:video]", "content");
//        }
//    },
//    Meta {
//        @Override
//        public String getTitle(Document document) {
//            return JsoupDocumentUtil.selectFirstValue(document, "meta[name=title]", "content");
//        }
//
//        @Override
//        public String getDescription(Document document) {
//            return JsoupDocumentUtil.selectFirstValue(document, "meta[name=description]", "content");
//        }
//
//        @Override
//        public List<String> getListImageUrl(Document document) {
//            return JsoupDocumentUtil.selectAllValue(document, "link[rel=image_src]", "href");
//        }
//
//        @Override
//        public List<String> getListVideoUrl(Document document) {
//            return new ArrayList<String>();
//        }
//    },
//    Body {
//        @Override
//        public String getTitle(Document document) {
//            return document.title();
//        }
//
//        @Override
//        public String getDescription(Document document) {
//            return document.select("p").text().replaceAll("[\\s\\u00A0]+", " ");
//        }
//
//        @Override
//        public List<String> getListImageUrl(Document document) {
//            List<String> list = new ArrayList<String>();
//
//            Elements elements = document.select("img[src]");
//            for(Element element : elements)
//            {
//                list.add(element.attr("abs:src"));
//            }
//            return list;
//        }
//
//        @Override
//        public List<String> getListVideoUrl(Document document) {
//            List<String> list = new ArrayList<String>();
//
//            {
//                Elements elements = document.select("video > source[src]");
//                for(Element element : elements)
//                {
//                    list.add(element.attr("abs:src"));
//                }
//            }
//            {
//                Elements elements = document.select("embed[src*=youtube.com/]");
//                for(Element element : elements)
//                {
//                    list.add(element.attr("abs:src"));
//                }
//            }
//            {
//                Elements elements = document.select("embed[src*=naver.com/]");
//                for(Element element : elements)
//                {
//                    list.add(element.attr("abs:src"));
//                }
//            }
//            {
//                Elements elements = document.select("embed[src*=daum.com/]");
//                for(Element element : elements)
//                {
//                    list.add(element.attr("abs:src"));
//                }
//            }
//
//
//            return list;
//        }
//    };
//
//
//
//    public abstract String getTitle(Document document);
//    public abstract String getDescription(Document document);
//    public abstract List<String> getListImageUrl(Document document);
//    public abstract List<String> getListVideoUrl(Document document);
}
