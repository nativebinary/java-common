package common.android.utils;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class TranslateAnimationUtil {
    public static Animation createAnimationInFromRight(final int durationMillis) {

        Animation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        animation.setDuration(durationMillis);
        animation.setInterpolator(new AccelerateInterpolator());
        return animation;
    }

    public static Animation createAnimationOutToLeft(final int durationMillis) {
        Animation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        animation.setDuration(durationMillis);
        animation.setInterpolator(new AccelerateInterpolator());
        return animation;
    }

    public static Animation createAnimationInFromLeft(final int durationMillis) {
        Animation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        animation.setDuration(durationMillis);
        animation.setInterpolator(new AccelerateInterpolator());
        return animation;
    }

    public static Animation createAnimationOutToRight(final int durationMillis) {
        Animation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        animation.setDuration(durationMillis);
        animation.setInterpolator(new AccelerateInterpolator());
        return animation;
    }
}
