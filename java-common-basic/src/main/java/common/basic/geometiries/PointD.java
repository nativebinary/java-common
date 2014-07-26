package common.basic.geometiries;

import common.basic.facades.jsons.JsonUtil;

public class PointD {
    public static PointD empty = new PointD(0, 0);

    public final double x;
    public final double y;

    public PointD() {
        this(0, 0);
    }

    public PointD(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public PointD absolute() {
        return new PointD(Math.abs(x), Math.abs(y));
    }

    public PointD negate() {
        return new PointD(-x, -y);
    }


    public PointD multiply(double v) {
        return new PointD(x * v, y * v);
    }

    public PointD divideBy(double division) {
        return new PointD(x / division, y / division);
    }


    public SizeD delta(PointD point) {
        return new SizeD((double)Math.abs(this.x - point.x), (double)Math.abs(this.y - point.y));
    }

    public PointD median(PointD point) {
        return offset(delta(point).divideBy(2));
    }

    public PointD offset(SizeD size) {
        return new PointD(x + size.width, y + size.height);
    }

    public PointD advance(PointD point) {
        return new PointD(x + point.x, y + point.y);
    }

    public SizeD differ(PointD point) {
        return new SizeD(x - point.x, y - point.y);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PointD)) return false;

        PointD pointD = (PointD) o;

        if (Double.compare(pointD.x, x) != 0) return false;
        if (Double.compare(pointD.y, y) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }

}
