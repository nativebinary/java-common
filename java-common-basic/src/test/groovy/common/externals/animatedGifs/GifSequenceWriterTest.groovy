package common.externals.animatedGifs

import common.basic.utils.StreamUtil
import groovy.transform.TypeChecked
import spock.lang.Specification

import javax.imageio.ImageIO
import javax.imageio.stream.MemoryCacheImageOutputStream

@TypeChecked
class GifSequenceWriterTest extends Specification {
    def test() {
        def array = [
                "000.png", "001.png", "002.png", "003.png", "004.png",
                "005.png", "006.png", "007.png", "008.png", "009.png",
                "010.png", "011.png", "012.png", "013.png", "014.png",
                "015.png", "016.png", "017.png", "018.png", "019.png",
        ];

        def byteArrayOutputStream = new ByteArrayOutputStream()
        def memoryCacheImageOutputStream = new MemoryCacheImageOutputStream(byteArrayOutputStream);
        def gifSequenceWriter = new GifSequenceWriter(memoryCacheImageOutputStream, ImageIO.read(getInputStream(array[0])).getType(), 1, true);

        array.each({
            String name -> gifSequenceWriter.writeToSequence(ImageIO.read(getInputStream(name)));
        });

        gifSequenceWriter.close();
        memoryCacheImageOutputStream.close();
        byteArrayOutputStream.close();

        expect:
        byteArrayOutputStream.toByteArray() == StreamUtil.getArrayByteAll(getInputStream("result.gif"))
    }

    def getInputStream(String s) {
        getClass().getClassLoader().getResourceAsStream(s)
    }
}

