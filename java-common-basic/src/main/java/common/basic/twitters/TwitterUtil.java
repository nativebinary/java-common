package common.basic.twitters;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterUtil {
    public static final String consumerKey = ""; // TODO: Refactor to logic
    public static final String consumerSecret = ""; // TODO: Refactor to logic


    public static String getUrlProfileImageOriginal(String screenName) {
        return "https://api.twitter.com/1/users/profile_image?screen_name=" + screenName + "&size=original";
    }

    public static Twitter get(AccessToken accessToken) {
        Twitter twitter = TwitterFactory.getSingleton();
        try {
            twitter.setOAuthConsumer(consumerKey, consumerSecret);
        }
        catch (IllegalStateException ignored) { }

        if (null != accessToken) {
            twitter.setOAuthAccessToken(accessToken);
        }

        return twitter;
    }
}
