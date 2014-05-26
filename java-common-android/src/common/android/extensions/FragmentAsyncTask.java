package common.android.extensions;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import common.basic.logs.Logger;

public class FragmentAsyncTask<TActivity extends Activity, TParams, TProgress, TResult> extends Fragment {

    private AsyncTaskWithActivity<TActivity, TParams, TProgress, TResult> asyncTaskWithActivity;

    public FragmentAsyncTask(AsyncTaskWithActivity<TActivity, TParams, TProgress, TResult> asyncTaskWithActivity) {
        Logger.i();

        this.asyncTaskWithActivity = asyncTaskWithActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Logger.i();

        setRetainInstance(true);

        asyncTaskWithActivity.execute();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Logger.v();

        asyncTaskWithActivity.setActivity(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Logger.i();

        asyncTaskWithActivity.setActivity(null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Logger.i();

        asyncTaskWithActivity.cancel(true);
    }

    public void cancel() {
        Logger.i();

        asyncTaskWithActivity.cancel(true);
    }
}
