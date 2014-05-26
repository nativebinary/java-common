package common.basic.htmlFetcher;

//import org.jsoup.nodes.Document;

public enum HtmlDocumentFilter {

//    Daum {
//
//        @Override
//        protected boolean match(String url) {
//            return url.matches("https?://blog\\.daum\\.net");
//        }
//
//        @Override
//        protected boolean matchFrame(String url) {
//            return 0 <= url.indexOf("ArticleContentsView.do");
//        }
//    },
//    Naver {
//        @Override
//        protected boolean match(String url) {
//            return url.matches("https?://blog\\.naver\\.com");
//        }
//
//        @Override
//        protected boolean matchFrame(String url) {
//            return 0 <= url.indexOf("PostView.nhn");
//        }
//    };
//
//    protected abstract boolean match(String url);
//    protected abstract boolean matchFrame(String url);
//
//    public static Map<String, Document> filter(String url, Map<String, Document> mapDocument) {
//
//        for (HtmlDocumentFilter filter : HtmlDocumentFilter.values())
//        {
//            if (!filter.match(url))
//                continue;
//
//            for(String key : mapDocument.keySet())
//            {
//                if (filter.matchFrame(key))
//                {
//                    Document document = mapDocument.get(key);
//
//                    Map<String, Document> mapDocumentNew = new HashMap<String, Document>();
//
//                    mapDocumentNew.put(key, document);
//                    return mapDocumentNew;
//                }
//            }
//        }
//
//        return mapDocument;
//    }
}
