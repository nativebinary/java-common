package common.basic.geometiries;

public class Padding extends LeftTopRightBottom<Integer> {
    public Padding(Integer padding)
    {
        super(padding);
    }

    public Padding(Integer left, Integer top, Integer right, Integer bottom) {
        super(left, top, right, bottom);
    }
}
