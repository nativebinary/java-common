package common.android.extensions;

import android.app.Activity;
import android.os.AsyncTask;

public abstract class AsyncTaskWithActivity<TActivity extends Activity, TParams, TProgress, TResult> extends AsyncTask<TParams, TProgress, TResult> {

    protected TActivity activity;

    public AsyncTaskWithActivity() { }

    public void setActivity(Activity activity) {
        this.activity = (TActivity)activity;
    }
}
