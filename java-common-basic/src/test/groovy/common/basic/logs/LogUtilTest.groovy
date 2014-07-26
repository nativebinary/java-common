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

    static class NoToString {
        final int i;
        final String s;

        NoToString(int i, String s) {
            this.i = i;
            this.s = s
        }
    }

    static class ClassHasOwnToString extends NoToString {
        ClassHasOwnToString(int i, String s) {
            super(i, s)
        }

        @Override
        public String toString() {
            return String.format("%d%s", i, s);
        }
    }

    def "ToListStringForLog"() {

        Object[] array = [null, 1, "A", new Rect(new Point(10, 10), new Size(10, 10)), new ClassHasOwnToString(20, "C")];
        List<String> list = LogUtil.toListStringForLog(array);

        expect:
        list == ["null", "1", "A", "{\"point\":{\"x\":10,\"y\":10},\"size\":{\"width\":10,\"height\":10}}", "20C"]
    }
}
