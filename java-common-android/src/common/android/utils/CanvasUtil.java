package common.android.utils;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

public class CanvasUtil {
    public static void drawText(Canvas canvas,String s, TextPaint textPaint, Rect rect, Layout.Alignment alignment) {
        StaticLayout staticLayout = new StaticLayout(s, textPaint, rect.width(), alignment, 1, 1, false);
        canvas.save();
        canvas.translate(rect.left, rect.top);
        staticLayout.draw(canvas);
        canvas.restore();
    }
}
