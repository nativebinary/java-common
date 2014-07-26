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

    private float right() {
        return point.x + size.width;
    }

    private float bottom() {
        return point.y + size.height;
    }

    public RectF deflate(float full) {
        final float half = full / 2;
        final PointF pointNew = new PointF(point.x + half, point.y + half);
        final SizeF sizeNew = new SizeF(size.width - full, size.height - full);
        return new RectF(pointNew, sizeNew);
    }

    public boolean contains(PointF point) {
        return
                (left() <= point.x && point.x <= right()) &&
                (top() <= point.y && point.y <= bottom());
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
