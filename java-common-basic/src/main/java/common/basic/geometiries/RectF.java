package common.basic.geometiries;

import common.basic.facades.jsons.JsonUtil;

public class RectF {
    public static RectF empty = new RectF(PointF.empty, SizeF.empty);

    public static RectF fromLeftTopRightBottom(float left, float top, float right, float bottom) {
        return new RectF(new PointF(left, top), new SizeF(right - left, bottom - top));
    }

    public final PointF point;
    public final SizeF size;

    public RectF(PointF point, SizeF size) {
        this.point = point;
        this.size = size;
    }

    public RectF(int x, int y, int width, int height) {
        this(new PointF(x, y), new SizeF(width, height));
    }


    public float width() {
        return size.width;
    }

    public float height() {
        return size.height;
    }

    public float left() {
        return point.x;
    }

    public float top() {
        return point.y;
    }

    public float right() {
        return point.x + size.width;
    }

    public float bottom() {
        return point.y + size.height;
    }

    public PointF center() {
        final SizeF half = size.divideBy(2);
        return point.offset(half);
    }


    public RectF deflate(float full) {
        final float half = (float)full / 2;
        final PointF pointNew = new PointF(point.x + half, point.y + half);
        final SizeF sizeNew = new SizeF((float)(size.width - full), (float)(size.height - full));
        return new RectF(pointNew, sizeNew);
    }


    public boolean contains(PointF point) {
        return
                (left() <= point.x && point.x <= right()) &&
                        (top() <= point.y && point.y <= bottom());
    }


    public RectF offset(SizeF size) {
        return new RectF(point.offset(size), this.size);
    }


    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
