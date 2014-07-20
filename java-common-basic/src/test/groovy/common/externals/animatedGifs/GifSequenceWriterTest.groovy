package common.externals.animatedGifs
import common.basic.externals.animatedGifs.GifSequenceWriter
import common.basic.logs.Logger
import common.basic.utils.DateUtil
import common.basic.utils.StreamUtil
import spock.lang.Specification

import javax.imageio.ImageIO
import javax.imageio.stream.MemoryCacheImageOutputStream

class GifSequenceWriterTest extends Specification {
    def "writeToSequence"() {
        Logger.e(DateUtil.yyyyMMddHHmmssSSS(new Date()))

        def array = [
                "000.png", "001.png", "002.png", "003.png", "004.png",
                "005.png", "006.png", "007.png", "008.png", "009.png",
                "010.png", "011.png", "012.png", "013.png", "014.png",
                "015.png", "016.png", "017.png", "018.png", "019.png",
        ];

        def byteArrayOutputStream = new ByteArrayOutputStream()
        def memoryCacheImageOutputStream = new MemoryCacheImageOutputStream(byteArrayOutputStream);
        def gifSequenceWriter = new GifSequenceWriter(memoryCacheImageOutputStream, ImageIO.read(getInputStream(array[0])).getType(), 1, true);

        array.each({ String name -> gifSequenceWriter.writeToSequence(ImageIO.read(getInputStream(name))); });

        gifSequenceWriter.close();
        memoryCacheImageOutputStream.close();
        byteArrayOutputStream.close();

        expect:
        byteArrayOutputStream.toByteArray() == StreamUtil.getArrayByteAll(getInputStream("result.gif"))

        Logger.e(DateUtil.yyyyMMddHHmmssSSS(new Date()))

//
//        BufferedImage bufferedImage = ImageIO.read(getInputStream("000.png"));
//        Graphics graphics = bufferedImage.getGraphics();
//        Graphics2D graphics2D = (Graphics2D)graphics;
//        graphics2D.rotate(45);
//        graphics2D.drawImage(ImageIO.read(getInputStream("001.png")), 10, 10, 100, 100, new ImageObserver() {
//            @Override
//            boolean imageUpdate(Image image, int i, int i2, int i3, int i4, int i5) {
//                return false;
//            }
//        })
//        bufferedImage.
//



    }

    def getInputStream(String s) {
        getClass().getClassLoader().getResourceAsStream(s)
    }

}

