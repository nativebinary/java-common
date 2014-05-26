package common.android.extensions;

import android.app.Activity;

public class FragmentAsyncTaskSimple<TActivity extends Activity> extends FragmentAsyncTask<TActivity, Object, Integer, Object> {

    public FragmentAsyncTaskSimple(AsyncTaskWithActivity<TActivity, Object, Integer, Object> asyncTaskWithActivity) {
        super(asyncTaskWithActivity);
    }
}
