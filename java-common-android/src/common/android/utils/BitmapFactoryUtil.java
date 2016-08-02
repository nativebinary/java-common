package common.android.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import common.basic.geometiries.Size;
import common.basic.logs.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class BitmapFactoryUtil {
    public static Size getSize(byte[] arrayByte) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(arrayByte, 0, arrayByte.length, options);
        return new Size(options.outWidth, options.outHeight);
    }

    public static Bitmap decode(byte[] arrayByte, Size sizeRequest) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(arrayByte, 0, arrayByte.length, options);

        options.inSampleSize = calculateInSampleSize(options, sizeRequest.width, sizeRequest.height);
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeByteArray(arrayByte, 0, arrayByte.length, options);
    }

    public static Bitmap decode(File file, Size sizeRequest) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getPath(), options);

        options.inSampleSize = calculateInSampleSize(options, sizeRequest.width, sizeRequest.height);
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(file.getPath(), options);
    }

    public static Bitmap decode(
            Size sizeRequest, InputStream inputStreamForSizeCalculation, InputStream... arrayInputStreamForActualDecode) throws Exception {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStreamForSizeCalculation, null, options);

        options.inSampleSize = calculateInSampleSize(options, sizeRequest.width, sizeRequest.height);
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inDither = true;

        for (InputStream inputStreamForActualDecode : arrayInputStreamForActualDecode) {
            try {
                return BitmapFactory.decodeStream(inputStreamForActualDecode, null, options);
            }
            catch (OutOfMemoryError outOfMemoryError) {
                Logger.e(outOfMemoryError);
                System.gc();
                ThreadUtil.sleep(100);
            }
        }

        throw new Exception("Failed");
    }

    public static BitmapFactory.Options getAdjustOption(Size sizeRequest, InputStream inputStreamForSizeCalculation){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStreamForSizeCalculation, null, options);

        options.inSampleSize = calculateInSampleSize(options, sizeRequest.width, sizeRequest.height);
        options.inJustDecodeBounds = false;

        return options;
    }

    public static Bitmap decode(BitmapFactory.Options options, InputStream... arrayInputStreamForActualDecode) throws Exception {
        for (InputStream inputStreamForActualDecode : arrayInputStreamForActualDecode) {
            try {
                return BitmapFactory.decodeStream(inputStreamForActualDecode, null, options);
            }
            catch (OutOfMemoryError outOfMemoryError) {
                Logger.e(outOfMemoryError);
                System.gc();
                ThreadUtil.sleep(100);
            }
        }

        throw new Exception("Failed");
    }


    @Deprecated
    public static Bitmap decode(byte[] arrayByte) {
        return BitmapFactory.decodeByteArray(arrayByte, 0, arrayByte.length);
    }

    @Deprecated
    public static Bitmap decode(Resources resources, int id) {
        return BitmapFactory.decodeResource(resources, id);
    }

    @Deprecated
    public static Bitmap decode(InputStream inputStream) throws IOException {
        return BitmapFactory.decodeStream(inputStream);
    }


    //<editor-fold desc="http://developer.android.com/training/displaying-bitmaps/load-bitmap.html">
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    @SuppressWarnings("UnusedDeclaration")
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
    //</editor-fold>


}
