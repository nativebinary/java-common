package common.android.utils;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class InputMethodManagerUtil {


    public static void showSoftInput(View view) {

        final InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    public static void showSoftInputWithHandler(final View view, long millisecondAfter)
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManagerUtil.showSoftInput(view);
            }
        }, millisecondAfter);
    }

    public static void hideSoftInput(View view) {
        final InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
