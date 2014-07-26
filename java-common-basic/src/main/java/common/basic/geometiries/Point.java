package common.basic.geometiries;

import common.basic.facades.jsons.JsonUtil;

public class Point {
    public static Point empty = new Point(0, 0);

    public final int x;
    public final int y;

    public Point() {
        this(0, 0);
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point multiply(int v) {
        return new Point(x * v, y * v);
    }

    public Point divideBy(int division) {
        return new Point(x / division, y / division);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        if (y != point.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }

    public Size delta(Point pointCurrent) {
        return new Size(Math.abs(this.x - pointCurrent.x), Math.abs(this.y - pointCurrent.y));
    }
}
