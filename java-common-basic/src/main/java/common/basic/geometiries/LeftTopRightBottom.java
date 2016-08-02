package common.basic.geometiries;

public class LeftTopRightBottom<T> {
    public final T left;
    public final T top;
    public final T right;
    public final T bottom;

    public LeftTopRightBottom(T all) {
        this.left = all;
        this.top = all;
        this.right = all;
        this.bottom = all;
    }

    public LeftTopRightBottom(T left, T top, T right, T bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LeftTopRightBottom)) return false;

        LeftTopRightBottom<?> that = (LeftTopRightBottom<?>) o;

        if (bottom != null ? !bottom.equals(that.bottom) : that.bottom != null) return false;
        if (left != null ? !left.equals(that.left) : that.left != null) return false;
        if (right != null ? !right.equals(that.right) : that.right != null) return false;
        if (top != null ? !top.equals(that.top) : that.top != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = left != null ? left.hashCode() : 0;
        result = 31 * result + (top != null ? top.hashCode() : 0);
        result = 31 * result + (right != null ? right.hashCode() : 0);
        result = 31 * result + (bottom != null ? bottom.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "left=" + left +
                ", top=" + top +
                ", right=" + right +
                ", bottom=" + bottom +
                '}';
    }
}
