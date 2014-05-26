package common.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

 public class ScrollViewObservable extends ScrollView {
    public interface OnScrollChangedListener  {
        void onScrollChanged(ScrollViewObservable scrollView, int x, int y, int oldx, int oldy);
    }

    private OnScrollChangedListener onScrollChangedListener = null;

    public ScrollViewObservable(Context context) {
        super(context);
    }

    public ScrollViewObservable(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollViewObservable(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener) {
        this.onScrollChangedListener = onScrollChangedListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if(onScrollChangedListener != null) {
            onScrollChangedListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }
}