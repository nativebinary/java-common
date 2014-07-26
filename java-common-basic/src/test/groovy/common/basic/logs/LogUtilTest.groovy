package common.basic.logs

import common.basic.geometiries.Point
import common.basic.geometiries.Rect
import common.basic.geometiries.Size
import spock.lang.Specification

class LogUtilTest extends Specification {
    def "ctor"() {
        when: new LogUtil()
        then: thrown(InstantiationException)
    }

    public static class ClassDoesNotHaveToString {
        final int i;
        final String s;

        public ClassDoesNotHaveToString(int i, String s) {
            this.i = i;
            this.s = s
        }
    }

    public static class ClassDoesHaveToString extends ClassDoesNotHaveToString {
        public ClassDoesHaveToString(int i, String s) {
            super(i, s)
        }

        @Override
        public String toString() {
            return String.format("%d%s", i, s);
        }
    }

    def "ToListStringForLog"() {

        def nullRef = null
        def integer = 1
        def string = "A"
        def rect = new Rect(new Point(10, 10), new Size(10, 10))
        ClassDoesHaveToString classDoesHaveToString = new ClassDoesHaveToString(20, "C")
        Object[] array = [nullRef, integer, string, rect, classDoesHaveToString];
        List<String> list = LogUtil.toListStringForLog(array);

        expect:
        list == ["null", "1", "A", "{\"point\":{\"x\":10,\"y\":10},\"size\":{\"width\":10,\"height\":10}}", "20C"]
    }
}
