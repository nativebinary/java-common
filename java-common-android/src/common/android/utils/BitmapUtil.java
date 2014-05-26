package common.android.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.widget.ImageView;
import common.basic.geometiries.Size;
import common.basic.logs.Logger;
import common.basic.utils.CloseableUtil;
import common.basic.utils.ICallback;
import common.basic.utils.StreamUtil;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class BitmapUtil {
    public static Bitmap resize(Bitmap bitmap, int newWidth, int newHeight)
    {
        Bitmap scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);

        float ratioX = newWidth / (float) bitmap.getWidth();
        float ratioY = newHeight / (float) bitmap.getHeight();
        float middleX = newWidth / 2.0f;
        float middleY = newHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap, middleX - bitmap.getWidth() / 2, middleY - bitmap.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

        return scaledBitmap;
    }

    public static byte[] toArrayByte(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
        finally {
            CloseableUtil.close(byteArrayOutputStream);
        }
    }

    public static byte[] toArrayByteJpeg(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
        finally {
            CloseableUtil.close(byteArrayOutputStream);
        }
    }

    public static byte[] resize(byte[] arrayByteCover, int height, int width) {
        final Bitmap bitmap = BitmapFactoryUtil.decode(arrayByteCover, new Size(width, height));
        return BitmapUtil.toArrayByte(bitmap);
    }
}
