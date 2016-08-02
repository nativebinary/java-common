package common.android.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import common.basic.geometiries.Size;
import common.basic.logs.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ImageUtil {

    public static Bitmap createCircleMaskImage(int width, int height, float radius)
    {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Paint paintTransparent = new Paint();
        paintTransparent.setColor(Color.TRANSPARENT);
        canvas.drawRect(new Rect(0 , 0, width, height), paintTransparent);

        Paint paintWhite = new Paint();
        paintWhite.setColor(Color.WHITE);
        paintWhite.setAntiAlias(true);

        canvas.drawCircle(width / 2f, height/2f, radius, paintWhite);

        return bitmap;
    }

    public static Bitmap masking(Bitmap bitmap, Bitmap bitmapMask)
    {
        Bitmap result = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(bitmap, 0, 0, null);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(bitmapMask, 0, 0, paint);

        paint.setXfermode(null);

        return result;
    }

    public static byte[] resizeDisplaySize(File file, Size size, int quality) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            Bitmap bitmap = BitmapFactoryUtil.decode(file, size);
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream);
            return stream.toByteArray();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                Logger.e(e);

            }
        }

    }
}
