//package common.android.twitters;
//
//import android.content.ContextWrapper;
//import android.net.Uri;
//import android.view.View;
//import android.webkit.CookieManager;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import common.android.utils.SharedPreferencesUtil;
//import common.android.utils.ThreadUtil;
//import common.basic.logs.Logger;
//import common.basic.utils.StringUtil;
//import common.basic.twitters.TwitterUtil;
//import twitter4j.Paging;
//import twitter4j.Query;
//import twitter4j.QueryResult;
//import twitter4j.Status;
//import twitter4j.Twitter;
//import twitter4j.TwitterException;
//import twitter4j.auth.AccessToken;
//import twitter4j.auth.RequestToken;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TwitterHelper {
//    public interface IProvider {
//        ContextWrapper getContextWrapper();
//    }
//
//    public interface ICallbackAuth {
//        void succeed();
//        void failed();
//    }
//
//
//    public static final String sharedPreferencesName = "TwitterHelper";
//    public static final String sharedPreferencesKeyTwitterRequestToken = "sharedPreferencesKeyTwitterRequestToken";
//    public static final String sharedPreferencesKeyTwitterRequestTokenSecret = "sharedPreferencesKeyTwitterRequestTokenSecret";
//    public static final String sharedPreferencesKeyTwitterAccessToken = "sharedPreferencesKeyTwitterAccessToken";
//    public static final String sharedPreferencesKeyTwitterAccessTokenSecret = "sharedPreferencesKeyTwitterAccessTokenSecret";
//
//
//    public static final String urlCallback = "oauth://twitter";
//
//    final WebView webView;
//    final IProvider provider;
//    final ICallbackAuth callbackAuth;
//
//    public TwitterHelper(WebView webView, IProvider provider, final ICallbackAuth callbackAuth) {
//        this.webView = webView;
//        this.provider = provider;
//        this.callbackAuth = callbackAuth;
//
//        if(null != webView)
//        {
//            webView.setWebViewClient(new WebViewClient() {
//                @Override
//                public boolean shouldOverrideUrlLoading(WebView view, String url) {
//
//                    try
//                    {
//                        final Uri uriTwitterCallback = Uri.parse(TwitterHelper.urlCallback);
//                        final Uri uri = Uri.parse(url);
//
//                        if (!uriTwitterCallback.getScheme().equals(uri.getScheme())) {
//                            callbackAuth.failed();
//                            return false;
//                        }
//
//                        final String strOAuthVerifier = uri.getQueryParameter("oauth_verifier");
//                        if (null == strOAuthVerifier) {
//                            callbackAuth.failed();
//                            return false;
//                        }
//
//                        common.basic.utils.ThreadUtil.create("TwitterHelper", new Runnable() {
//                            @Override
//                            public void run() {
//                                try {
//                                    RequestToken requestToken = requestTokenLoad();
//                                    AccessToken accessToken = get().getOAuthAccessToken(requestToken, strOAuthVerifier);
//                                    accessTokenSave(accessToken);
//                                    callbackAuth.succeed();
//                                }
//                                catch (TwitterException te) {
//                                    Logger.e(te);
//                                }
//                            }
//                        }).start();
//
//                        Logger.i(url);
//                        return true;
//                    }
//                    finally
//                    {
//                        view.setVisibility(View.GONE);
//                    }
//                }
//            });
//        }
//    }
//
//    public Twitter get() {
//        return TwitterUtil.get(accessTokenLoad());
//    }
//
//    private AccessToken accessTokenLoad() {
//        String token = SharedPreferencesUtil.get(provider.getContextWrapper(), TwitterHelper.sharedPreferencesName, TwitterHelper.sharedPreferencesKeyTwitterAccessToken, "");
//        String secret = SharedPreferencesUtil.get(provider.getContextWrapper(), TwitterHelper.sharedPreferencesName, TwitterHelper.sharedPreferencesKeyTwitterAccessTokenSecret, "");
//
//        if (StringUtil.isNullOrEmpty(token) || StringUtil.isNullOrEmpty(secret)) {
//            return null;
//        }
//
//        return new AccessToken(token, secret);
//    }
//
//    private void accessTokenSave(AccessToken accessToken) {
//        SharedPreferencesUtil.put(provider.getContextWrapper(), TwitterHelper.sharedPreferencesName, TwitterHelper.sharedPreferencesKeyTwitterAccessToken, accessToken.getToken());
//        SharedPreferencesUtil.put(provider.getContextWrapper(), TwitterHelper.sharedPreferencesName, TwitterHelper.sharedPreferencesKeyTwitterAccessTokenSecret, accessToken.getTokenSecret());
//    }
//
//    private void accessTokenRemove() {
//        SharedPreferencesUtil.remove(provider.getContextWrapper(), TwitterHelper.sharedPreferencesName, TwitterHelper.sharedPreferencesKeyTwitterAccessToken);
//        SharedPreferencesUtil.remove(provider.getContextWrapper(), TwitterHelper.sharedPreferencesName, TwitterHelper.sharedPreferencesKeyTwitterAccessTokenSecret);
//    }
//
//
//
//    private RequestToken requestTokenLoad() {
//        String token = SharedPreferencesUtil.get(provider.getContextWrapper(), TwitterHelper.sharedPreferencesName, TwitterHelper.sharedPreferencesKeyTwitterRequestToken, "");
//        String secret = SharedPreferencesUtil.get(provider.getContextWrapper(), TwitterHelper.sharedPreferencesName, TwitterHelper.sharedPreferencesKeyTwitterRequestTokenSecret, "");
//
//        if (StringUtil.isNullOrEmpty(token) || StringUtil.isNullOrEmpty(secret)) {
//            return null;
//        }
//
//        return new RequestToken(token, secret);
//    }
//
//    private void requestTokenSave(RequestToken requestToken) {
//        SharedPreferencesUtil.put(provider.getContextWrapper(), TwitterHelper.sharedPreferencesName, TwitterHelper.sharedPreferencesKeyTwitterRequestToken, requestToken.getToken());
//        SharedPreferencesUtil.put(provider.getContextWrapper(), TwitterHelper.sharedPreferencesName, TwitterHelper.sharedPreferencesKeyTwitterRequestTokenSecret, requestToken.getTokenSecret());
//    }
//
//    private void requestTokenRemove() {
//        SharedPreferencesUtil.remove(provider.getContextWrapper(), TwitterHelper.sharedPreferencesName, TwitterHelper.sharedPreferencesKeyTwitterRequestToken);
//        SharedPreferencesUtil.remove(provider.getContextWrapper(), TwitterHelper.sharedPreferencesName, TwitterHelper.sharedPreferencesKeyTwitterRequestTokenSecret);
//    }
//
//
//    public List<Status> getListStatusSearch(String s, long maxId) {
//        try {
//            final Twitter twitter = get();
//            if (isLoggedIn()) {
//                final Query query = new Query(s);
//                query.setCount(limit);
//                if(0 < maxId)
//                    query.setMaxId(maxId);
//
//                QueryResult result = twitter.search(query);
//                final List<Status> tweets = result.getTweets();
//                return tweets;
//            }
//            else {
//                requestAuth();
//            }
//        }
//        catch (TwitterException twitterException) {
//            Logger.e(twitterException);
//        }
//        return new ArrayList<Status>();
//    }
//
//    public static final int limit = 10;
//    public List<Status> getListStatusTimeline(long maxId) {
//        try {
//            Paging paging = new Paging();
//            paging.setCount(limit);
//            if(0 < maxId)
//                paging.maxId(maxId);
//
//            final Twitter twitter = get();
//            if (isLoggedIn()) {
//                return twitter.getHomeTimeline(paging);
//            }
//            else {
//                requestAuth();
//            }
//        }
//        catch (TwitterException twitterException) {
//            Logger.e(twitterException);
//        }
//        return new ArrayList<Status>();
//    }
//
//    public Status getStatusById(long id) {
//        final Twitter twitter = get();
//
//        if(isLoggedIn()) {
//            try {
//                return twitter.showStatus(id);
//            } catch (TwitterException e) {
//                Logger.e(e);
//            }
//        } else {
//            requestAuth();
//        }
//
//        return null;
//    }
//
//    public List<Status> getListStatusFavorite(long maxId) {
//        try {
//            Logger.e(maxId);
//
//            Paging paging = new Paging();
//            paging.setCount(limit);
//            if(0 < maxId)
//                paging.maxId(maxId);
//
//            final Twitter twitter = get();
//            if (isLoggedIn()) {
//                return twitter.getFavorites(paging);
//            }
//            else {
//                requestAuth();
//            }
//        }
//        catch (TwitterException twitterException) {
//            Logger.e(twitterException);
//        }
//        return new ArrayList<Status>();
//    }
//
//    public void requestAuth() {
//        ThreadUtil.runOnNotUiThread("twitterGetOAuthRequestToken", new Runnable() {
//            @Override
//            public void run() {
//                final RequestToken requestToken;
//                try {
//                    requestToken = get().getOAuthRequestToken(urlCallback);
//                    requestTokenSave(requestToken);
//                    ThreadUtil.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            webView.loadUrl(requestToken.getAuthorizationURL());
//                            webView.setVisibility(View.VISIBLE);
//                        }
//                    });
//                }
//                catch (TwitterException e) {
//                    Logger.e(e);
//                    logout();
//                }
//            }
//        });
//
//    }
//
//    public boolean isLoggedIn() {
//        final AccessToken accessToken = accessTokenLoad();
//        return null != accessToken;
//
//    }
//
//    public void logout() {
//        Logger.e();
//        final Twitter twitter = get();
//        twitter.setOAuthAccessToken(null);
//        twitter.shutdown();
//        accessTokenRemove();
//        requestTokenRemove();
//        if(null != webView)
//            webView.clearCache(true);
//        CookieManager.getInstance().removeAllCookie();
//    }
//
//
//}
