package common.android.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import common.android.enums.GravityEnum;
import common.basic.geometiries.Padding;
import common.basic.geometiries.Size;
import common.basic.logs.Logger;
import common.basic.utils.Cast;
import common.basic.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class ViewUtil {

    public static View findView(View view, int id) {
        return view.findViewById(id);
    }

    public static View findView(Activity activity, int id) {
        return activity.findViewById(id);
    }

    public static <T extends View> T findView(View view, int id, Class<T> clazz) {
        final View viewChild = view.findViewById(id);

        T t = Cast.as(viewChild, clazz);
        if (t == null) {
            assert false;
            Logger.e(id);
        }

        return t;

    }

    public static <T extends View> T findView(Activity activity, int id, Class<T> clazz) {
        final View view = activity.findViewById(id);

        T t = Cast.as(view, clazz);
        if (t == null) {
            assert false;
            Logger.e(id);
        }

        return t;

    }



    public static TextView findTextView(Activity activity, int id) {
        return findView(activity, id, TextView.class);
    }

    public static TextView findTextView(View view, int id) {
        return findView(view, id, TextView.class);
    }

    public static CheckBox findCheckBox(Activity activity, int id) {
        return findView(activity, id, CheckBox.class);
    }

    public static CheckBox findCheckBox(View view, int id) {
        return findView(view, id, CheckBox.class);
    }



    public static ImageView findImageView(View view, int id) {
        return findView(view, id, ImageView.class);
    }

    public static ImageView findImageView(Activity activity, int id) {
        return findView(activity, id, ImageView.class);
    }


    public static ImageButton findImageButton(View view, int id) {
        return findView(view, id, ImageButton.class);
    }

    public static Button findButton(Activity activity, int id) {
        return findView(activity, id, Button.class);
    }

    public static Button findButton(View view, int id) {
        return findView(view, id, Button.class);
    }

    public static ViewGroup findViewGroup(Activity activity, int id) {
        return findView(activity, id, ViewGroup.class);
    }

    public static ListView findListView(Activity activity, int id) {
        return findView(activity, id, ListView.class);
    }

    public static ListView findListView(View view, int id) {
        return findView(view, id, ListView.class);
    }

    public static GridView findGridView(Activity activity, int id) {
        return findView(activity, id, GridView.class);
    }

    public static GridView findGridView(View view, int id) {
        return findView(view, id, GridView.class);
    }

    public static ViewPager findViewPager(Activity activity, int id) {
        return findView(activity, id, ViewPager.class);
    }


    // relativeScreen
    public static Point getPointLocationOnScreen(View view) {
        int[] arrayInt = new int[2];
        view.getLocationOnScreen(arrayInt);
        return PointUtil.create(arrayInt);
    }

    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static Rect getWindowVisibleDisplayFrame(View view) {
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        return rect;
    }


    public static EditText findEditText(Activity activity, int resourceId) {
        return (EditText) activity.findViewById(resourceId);
    }

    public static ImageButton findImageButton(Activity activity, int id) {
        return findView(activity, id, ImageButton.class);
    }

    public static LinearLayout findLinearLayout(Activity activity, int id) {
        return findView(activity, id, LinearLayout.class);
    }


    public static LinearLayout findLinearLayout(View view, int id) {
        return findView(view, id, LinearLayout.class);
    }



    public static void visible(View view){
        view.setVisibility(View.VISIBLE);
    }

    public static void visible(Activity activity, int id) {
        visible(findView(activity, id));
    }

    public static void gone(View view){
        view.setVisibility(View.GONE);
    }

    public static void gone(Activity activity, int id) {
        gone(findView(activity, id));
    }

    public static void invisible(View view){
        view.setVisibility(View.INVISIBLE);
    }

    public static void invisible(Activity activity, int id) {
        invisible(findView(activity, id));
    }



    public static void setVisibilityVisibleGone(View view, boolean visibility) {
        view.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    public static void setVisibilityVisibleGone(Activity activity, int id, boolean visibility) {
        setVisibilityVisibleGone(findView(activity, id), visibility);
    }

    public static void setVisibilityVisibleInvisible(View view, boolean visibility) {
        view.setVisibility(visibility ? View.VISIBLE : View.INVISIBLE);
    }

    public static Size getSizeMeasured(View view) {
        return new Size(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    public static void setBackgroundColorChildForDebug(View view) {
        List<View> listView = new ArrayList<View>();

        addListViewChildRecursiveDfs(listView, view);

        final int size = listView.size();
        for (int i = 0; i < size; i++) {
            listView.get(i).setBackgroundColor(ColorUtil.fromHSV(0x7f, i * 360f / size, 0.5f, 0.5f));
        }
    }

    private static void addListViewChildRecursiveDfs(List<View> listView, View view) {
        listView.add(view);

        ViewGroup viewGroup = Cast.as(view, ViewGroup.class);
        if (null == viewGroup) {
            return;
        }

        final int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View viewChild = viewGroup.getChildAt(i);
            if (viewChild == null) {
                continue;
            }

            addListViewChildRecursiveDfs(listView, viewChild);
        }

    }

    public static Padding getPadding(View view) {
        return new Padding(
                view.getPaddingLeft(),
                view.getPaddingTop(),
                view.getPaddingRight(),
                view.getPaddingBottom()
        );
    }

    public static void setPadding(View view, Padding padding) {
        view.setPadding(padding.left, padding.top, padding.right, padding.bottom);
    }

    public static boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    public static void setTextOrGone(TextView textView, String s) {
        if(StringUtil.isNullOrEmpty(s))
        {
            gone(textView);
            return;
        }

        visible(textView);
        textView.setText(s);
    }

    public static void setTextOrGone(View viewParent, int id, String s) {
        final TextView textView = findTextView(viewParent, id);
        setTextOrGone(textView, s);
    }

    public static void smallSizeWhenDebug(Context context, TextView textView) {
        if(!MetaData.isDev(context))
            return;

        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textView.getTextSize() / 2);
    }

    public static void enableDrawingCache(View view) {
        view.setDrawingCacheEnabled(true);
    }

    public static Bitmap getBitmapDrawingCache(View view) {
        if(!view.isDrawingCacheEnabled())
        {
            Logger.e();
            return null;
        }

        try {
            view.buildDrawingCache();
            final Bitmap drawingCache = view.getDrawingCache();
            if(drawingCache == null)
                return null;
            return Bitmap.createBitmap(drawingCache);
        }
        finally {
            view.destroyDrawingCache();
        }
    }

    public static void setFontAll(View view, String font) {
        List<View> listView = new ArrayList<View>();
        addListViewChildRecursiveDfs(listView, view);

        Typeface typeface = Typeface.createFromAsset(view.getContext().getAssets(), font);

        for (View viewChild : listView) {
            TextView textView = Cast.as(TextView.class, viewChild);
            if(null == textView)
                continue;

            textView.setTypeface(typeface);
        }
    }

    public static void setOnClickListener(Activity activity, int id, View.OnClickListener onClickListener) {
        findView(activity, id).setOnClickListener(onClickListener);
    }

    public static void setChecked(View view, int id, boolean checked) {
        findCheckBox(view, id).setChecked(checked);
    }

    public static void setFrameLayoutLayoutParamsGravity(View view, GravityEnum gravityEnum) {
        final ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        FrameLayout.LayoutParams frameLayoutLayoutParams = Cast.as(FrameLayout.LayoutParams.class, layoutParams);
        if(null != frameLayoutLayoutParams)
        {
            frameLayoutLayoutParams.gravity = gravityEnum.gravity;
            view.setLayoutParams(frameLayoutLayoutParams);
            return;
        }

        LinearLayout.LayoutParams linearLayoutLayoutParams = Cast.as(LinearLayout.LayoutParams.class, layoutParams);
        if(null != linearLayoutLayoutParams)
        {
            linearLayoutLayoutParams.gravity = gravityEnum.gravity;
            view.setLayoutParams(linearLayoutLayoutParams);
            return;
        }

        Logger.e(gravityEnum);
    }
}
