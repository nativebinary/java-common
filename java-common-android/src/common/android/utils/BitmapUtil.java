package common.android.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.widget.ImageView;
import common.basic.geometiries.Size;
import common.basic.logs.Logger;
import common.basic.utils.CloseableUtil;
import common.basic.interfaces.ICallback;
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
			// TODO: remove hard-coded 80
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
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

    public static void loadBitmap(final ImageView imageView, final InputStream inputStream, final Size size) {
        ThreadUtil.runOnNotUiThreadAndRunCallbackOnUiThread(new ThreadUtil.CallbackRunOnNotUiThreadAndRunCallbackOnUiThread<Bitmap>(new ICallback<Bitmap>() {
            @Override
            public void onSuccess(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onFail(Exception e) {
                Logger.e(e);
            }
        }) {
            @Override
            public Bitmap onNotUiThread() throws Exception {
                return BitmapFactoryUtil.decode(StreamUtil.getArrayByteAll(inputStream), size);
            }
        });
    }
    public static void loadBitmap(final InputStream inputStream, final Size size, final ICallback<Bitmap> callback) {
        ThreadUtil.runOnNotUiThreadAndRunCallbackOnUiThread(new ThreadUtil.CallbackRunOnNotUiThreadAndRunCallbackOnUiThread<Bitmap>(callback) {
            @Override
            public Bitmap onNotUiThread() throws Exception {
                return BitmapFactoryUtil.decode(StreamUtil.getArrayByteAll(inputStream), size);
            }
        });
    }

}
