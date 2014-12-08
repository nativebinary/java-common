package common.basic.geometiries;

import common.basic.facades.jsons.JsonUtil;

public class Rect {
    public static Rect empty = new Rect(Point.empty, Size.empty);

    public static Rect fromLeftTopRightBottom(int left, int top, int right, int bottom) {
        return new Rect(new Point(left, top), new Size(right - left, bottom - top));
    }

    public final Point point;
    public final Size size;

    public Rect(Point point, Size size) {
        this.point = point;
        this.size = size;
    }

    public Rect(int x, int y, int width, int height) {
        this(new Point(x, y), new Size(width, height));
    }


    public int width() {
        return size.width;
    }

    public int height() {
        return size.height;
    }

    public int left() {
        return point.x;
    }

    public int top() {
        return point.y;
    }

    public int right() {
        return point.x + size.width;
    }

    public int bottom() {
        return point.y + size.height;
    }

    public Point center() {
        final Size half = size.divideBy(2);
        return point.offset(half);
    }


    public Rect deflate(int full) {
        final int half = full / 2;
        final Point pointNew = new Point(point.x + half, point.y + half);
        final Size sizeNew = new Size(size.width - full, size.height - full);
        return new Rect(pointNew, sizeNew);
    }


    public boolean contains(PointF point) {
        return
                (left() <= point.x && point.x <= right()) &&
                (top() <= point.y && point.y <= bottom());
    }


    public Rect offset(Size size) {
        return new Rect(point.offset(size), this.size);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rect)) return false;

        Rect rect = (Rect) o;

        if (!point.equals(rect.point)) return false;
        if (!size.equals(rect.size)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = point.hashCode();
        result = 31 * result + size.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return JsonUtil.stringify(this);
    }
}
