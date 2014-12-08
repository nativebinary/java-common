package common.basic.geometiries;

import common.basic.facades.jsons.JsonUtil;

public class SizeF {
    public static SizeF empty = new SizeF(0, 0);

    public final float width;
    public final float height;

    public SizeF(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public SizeF divideBy(float division) {
        return new SizeF(width / division, height / division);
    }

    public float getWidthAspectRatioForHeight(float height) {
        return this.width * height / this.height;
    }

    public float getHeightAspectRatioForWidth(float width) {
        return this.height * width / this.width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SizeF)) return false;

        SizeF sizeF = (SizeF) o;

        if (Float.compare(sizeF.height, height) != 0) return false;
        if (Float.compare(sizeF.width, width) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (width != +0.0f ? Float.floatToIntBits(width) : 0);
        result = 31 * result + (height != +0.0f ? Float.floatToIntBits(height) : 0);
        return result;
    }

    @Override
    public String toString() {
        return JsonUtil.toJsonString(this);
    }
}
