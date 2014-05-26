package common.android.utils;

import android.content.Context;
import android.util.TypedValue;
import android.widget.TextView;
import common.basic.logs.Logger;

public class TextViewUtil {
    public static void setTextSizePx(TextView textView, float fontSizePx) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSizePx);
    }

    public static void setTypeface(Context context, TextView textView, String fontName) {
        Logger.e("TODO");
//        Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontName);
//        textView.setTypeface(typeface);
    }
}
