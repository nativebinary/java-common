package common.basic.geometiries;

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
}
