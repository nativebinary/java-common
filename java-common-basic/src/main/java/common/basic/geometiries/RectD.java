package common.basic.geometiries;

import common.basic.facades.jsons.JsonUtil;

public class RectD {
    public static RectD empty = new RectD(PointD.empty, SizeD.empty);

    public static RectD fromLeftTopRightBottom(double left, double top, double right, double bottom) {
        return new RectD(new PointD(left, top), new SizeD(right - left, bottom - top));
    }

    public final PointD point;
    public final SizeD size;

    public RectD(PointD point, SizeD size) {
        this.point = point;
        this.size = size;
    }

    public RectD(int x, int y, int width, int height) {
        this(new PointD(x, y), new SizeD(width, height));
    }


    public double width() {
        return size.width;
    }

    public double height() {
        return size.height;
    }

    public double left() {
        return point.x;
    }

    public double top() {
        return point.y;
    }

    public double right() {
        return point.x + size.width;
    }

    public double bottom() {
        return point.y + size.height;
    }

    public PointD center() {
        final SizeD half = size.divideBy(2);
        return point.offset(half);
    }


    public RectD deflate(double full) {
        final double half = full / 2;
        final PointD pointNew = new PointD(point.x + half, point.y + half);
        final SizeD sizeNew = new SizeD(size.width - full, size.height - full);
        return new RectD(pointNew, sizeNew);
    }


    public boolean contains(PointF point) {
        return
                (left() <= point.x && point.x <= right()) &&
                (top() <= point.y && point.y <= bottom());
    }


    public RectD offset(SizeD size) {
        return new RectD(point.offset(size), this.size);
    }


    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
