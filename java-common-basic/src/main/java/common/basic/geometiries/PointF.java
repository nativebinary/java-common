package common.basic.geometiries;

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

    public PointF multiply(float v) {
        return new PointF(x * v, y * v);
    }

    public PointF divideBy(float division) {
        return new PointF(x / division, y / division);
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
        return "PointF{" +
                "super=" + super.toString() +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public SizeF delta(PointF pointCurrent) {
        return new SizeF(Math.abs(this.x - pointCurrent.x), Math.abs(this.y - pointCurrent.y));
    }
}
