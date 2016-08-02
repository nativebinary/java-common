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


    public Point absolute() {
        return new Point(Math.abs(x), Math.abs(y));
    }

    public Point negate() {
        return new Point(-x, -y);
    }



    public Point multiply(int v) {
        return new Point(x * v, y * v);
    }

    public Point divideBy(int division) {
        return new Point(x / division, y / division);
    }


    public Size delta(Point pointCurrent) {
        return new Size(Math.abs(this.x - pointCurrent.x), Math.abs(this.y - pointCurrent.y));
    }

    public Point median(Point point) {
        return offset(delta(point).divideBy(2));
    }

    public Point offset(Size size) {
        return new Point(x + size.width, y + size.height);
    }

    public Point advance(Point point) {
        return new Point(x + point.x, y + point.y);
    }

    public Size differ(Point point) {
        return new Size(x - point.x, y - point.y);
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
        return JsonUtil.stringify(this);
    }
}
