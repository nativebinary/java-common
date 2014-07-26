package common.basic.geometiries;

import common.basic.facades.jsons.JsonUtil;

public class SizeD {
    public static SizeD empty = new SizeD(0, 0);

    public final double width;
    public final double height;

    public SizeD(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public SizeD divideBy(double division) {
        return new SizeD(width / division, height / division);
    }

    public double getWidthAspectRatioForHeight(double height) {
        return this.width * height / this.height;
    }

    public double getHeightAspectRatioForWidth(double width) {
        return this.height * width / this.width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SizeD)) return false;

        SizeD sizeD = (SizeD) o;

        if (Double.compare(sizeD.height, height) != 0) return false;
        if (Double.compare(sizeD.width, width) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(width);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }

    public SizeD negate() {
        return new SizeD(-width, -height);
    }
}
