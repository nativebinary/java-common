package common.basic.geometiries;

import common.basic.geometiries.LeftTopRightBottom;

public class Margin extends LeftTopRightBottom<Integer> {
    public Margin(Integer padding)
    {
        super(padding);
    }

    public Margin(Integer left, Integer top, Integer right, Integer bottom) {
        super(left, top, right, bottom);
    }
}
