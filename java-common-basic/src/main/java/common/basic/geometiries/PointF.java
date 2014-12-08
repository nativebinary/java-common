package common.basic.geometiries;

import common.basic.facades.jsons.JsonUtil;

public class PointF {
    public static PointF empty = new PointF(0, 0);

    public final float x;
    public final float y;

    public PointF() {
        this(0, 0);
    }

    public PointF(float x, float y) {
        this.x = x;
        this.y = y;
    }


    public PointF absolute() {
        return new PointF(Math.abs(x), Math.abs(y));
    }

    public PointF negate() {
        return new PointF(-x, -y);
    }


    public PointF multiply(float v) {
        return new PointF(x * v, y * v);
    }

    public PointF divideBy(float division) {
        return new PointF(x / division, y / division);
    }


    public SizeF delta(PointF point) {
        return new SizeF((float)Math.abs(this.x - point.x), (float)Math.abs(this.y - point.y));
    }

    public PointF median(PointF point) {
        return offset(delta(point).divideBy(2));
    }

    public PointF offset(SizeF size) {
        return new PointF(x + size.width, y + size.height);
    }

    public PointF advance(PointF point) {
        return new PointF(x + point.x, y + point.y);
    }

    public SizeD differ(PointF point) {
        return new SizeD(x - point.x, y - point.y);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PointF)) return false;

        PointF pointF = (PointF) o;

        if (Float.compare(pointF.x, x) != 0) return false;
        if (Float.compare(pointF.y, y) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        return result;
    }

    @Override
    public String toString() {
        return JsonUtil.stringify(this);
    }
}
