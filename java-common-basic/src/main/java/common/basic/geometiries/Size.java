package common.basic.geometiries;

import common.basic.facades.jsons.JsonUtil;

public class Size {
    public static Size empty = new Size(0, 0);

    public final int width;
    public final int height;

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Size divideBy(int division) {
        return new Size(width / division, height / division);
    }

    public int getWidthAspectRatioForHeight(int height) {
        return this.width * height / this.height;
    }

    public int getHeightAspectRatioForWidth(int width) {
        return this.height * width / this.width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Size)) return false;

        Size size = (Size) o;

        if (height != size.height) return false;
        if (width != size.width) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + height;
        return result;
    }

    @Override
    public String toString() {
        return JsonUtil.toJsonString(this);
    }
}
