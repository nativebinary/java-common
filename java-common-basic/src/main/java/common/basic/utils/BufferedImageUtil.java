package common.basic.utils;

import common.basic.geometiries.PointD;
import common.basic.geometiries.RectD;
import common.basic.geometiries.Size;
import common.basic.geometiries.SizeD;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class BufferedImageUtil {
    public BufferedImageUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static BufferedImage rotate(BufferedImage image, double angleDegree) {
        double angleRadians = Math.toRadians(angleDegree);
        double sin = Math.abs(Math.sin(angleRadians));
        double cos = Math.abs(Math.cos(angleRadians));
        int width = image.getWidth();
        int height = image.getHeight();
        int newWidth = (int) Math.floor(width * cos + height * sin);
        int newHeight = (int) Math.floor(height * cos + width * sin);
        BufferedImage result = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = result.createGraphics();
        g.translate((newWidth - width) / 2, (newHeight - height) / 2);
        g.rotate(angleRadians, width / 2, height / 2);
        g.drawRenderedImage(image, null);
        g.dispose();
        return result;
    }

    public static BufferedImage scale(BufferedImage bufferedImage, double scale){
        final int height = bufferedImage.getHeight();
        final int width = bufferedImage.getWidth();
        final int scaledWidth = (int) (width * scale);
        final int scaledHeight = (int) (height * scale);
        BufferedImage bufferedImageScaled = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bufferedImageScaled.createGraphics();
        g.drawImage(bufferedImage, 0, 0, scaledWidth, scaledHeight, 0, 0, width, height, null);
        g.dispose();
        return bufferedImageScaled;
    }

    public static BufferedImage offset(BufferedImage bufferedImage, int offsetX, int offsetY){
        final int height = bufferedImage.getHeight();
        final int width = bufferedImage.getWidth();
        BufferedImage bufferedImageScaled = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bufferedImageScaled.createGraphics();
        g.drawImage(bufferedImage, offsetX, offsetY, width + offsetX, height + offsetY, 0, 0, width, height, null);
        g.dispose();
        return bufferedImageScaled;
    }

    public static BufferedImage rotateScaleOffsetForTemplate(BufferedImage bufferedImage, BufferedImage bufferedImageTemplate, double angleDegree, double scale, double offsetX, double offsetY) {
        final BufferedImage bufferedImageResult = new BufferedImage(bufferedImageTemplate.getWidth(), bufferedImageTemplate.getHeight(), BufferedImage.TYPE_INT_ARGB);
        final BufferedImage bufferedImageRotate = rotate(bufferedImage, angleDegree);
        final BufferedImage bufferedImageRotateScale = scale(bufferedImageRotate, scale);

        final Graphics graphics = bufferedImageResult.getGraphics();
        final int x = (int) ((bufferedImage.getWidth() - bufferedImageRotateScale.getWidth()) / 2 + offsetX);
        final int y = (int) ((bufferedImage.getHeight() - bufferedImageRotateScale.getHeight()) / 2 + offsetY);
        graphics.drawImage(bufferedImageRotateScale, x, y, null);
        graphics.drawImage(bufferedImageTemplate, 0, 0, null);
        return bufferedImageResult;
    }


    public static BufferedImage rotateScaleOffsetForTemplateWithWeight(BufferedImage bufferedImage, BufferedImage bufferedImageTemplate, double angleDegree, double scale, double offsetX, double offsetY, double weight) {
        final BufferedImage bufferedImageResult = new BufferedImage(bufferedImageTemplate.getWidth(), bufferedImageTemplate.getHeight(), BufferedImage.TYPE_INT_ARGB);
        final BufferedImage bufferedImageRotate = rotate(bufferedImage, angleDegree);
        final BufferedImage bufferedImageRotateWeight = scale(bufferedImageRotate, 1 / weight);
        final BufferedImage bufferedImageRotateWeightScale = scale(bufferedImageRotateWeight, scale);

        final Graphics graphics = bufferedImageResult.getGraphics();
        final int x = (int) (((bufferedImageRotateWeight.getWidth() - bufferedImageRotateWeightScale.getWidth()) / 2 + offsetX) / weight);
        final int y = (int) (((bufferedImageRotateWeight.getHeight() - bufferedImageRotateWeightScale.getHeight()) / 2 + offsetY) / weight);
        graphics.drawImage(bufferedImageRotateWeightScale, x, y, null);
        graphics.drawImage(bufferedImageTemplate, 0, 0, null);
        return bufferedImageResult;
    }


    public static BufferedImage drawTranslateRotateScale(BufferedImage bufferedImage, Size sizeTarget, double angleDegree, double scale, double offsetX, double offsetY, double weight) {
        final double angleRadian = Math.toRadians(angleDegree);
        final PointD pointOffset = new PointD(offsetX, offsetY);
        final PointD pointOffsetWeight = pointOffset.multiply(weight);
        final double scaleWeight = scale * weight;

        final BufferedImage bufferedImageResult = new BufferedImage(sizeTarget.width, sizeTarget.height, BufferedImage.TYPE_INT_ARGB);

        final RectD rectBufferedImage = new RectD(PointD.empty, new SizeD(bufferedImage.getWidth(), bufferedImage.getHeight()));
        final RectD rectBufferedImageResult = new RectD(PointD.empty, new SizeD(bufferedImageResult.getWidth(), bufferedImageResult.getHeight()));

        final Graphics2D graphics2D = bufferedImageResult.createGraphics();

        graphics2D.translate(pointOffsetWeight.x + rectBufferedImageResult.width()/2, pointOffsetWeight.y + rectBufferedImageResult.height()/2);
        graphics2D.rotate(angleRadian, 0, 0);
        graphics2D.scale(scaleWeight, scaleWeight);

        graphics2D.drawImage(bufferedImage,
                (int)(0 - rectBufferedImage.center().x) , (int)(0 - rectBufferedImage.center().y),
                (int)(rectBufferedImage.width() - rectBufferedImage.center().x), (int)(rectBufferedImage.height() - rectBufferedImage.center().y),
                0, 0, (int)rectBufferedImage.width(), (int)rectBufferedImage.height(),
                null);

        return bufferedImageResult;
    }

    public static BufferedImage clone(BufferedImage bufferedImage) {
        ColorModel colorModel = bufferedImage.getColorModel();
        boolean isAlphaPremultiplied = colorModel.isAlphaPremultiplied();
        WritableRaster raster = bufferedImage.copyData(null);
        return new BufferedImage(colorModel, raster, isAlphaPremultiplied, null);
    }
}
