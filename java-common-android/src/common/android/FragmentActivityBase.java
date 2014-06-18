package common.android;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import common.basic.logs.Logger;

public abstract class FragmentActivityBase extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Logger.ri(1);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Logger.ri(1);
    }

    @Override
    protected void onPause() {
        super.onPause();

        Logger.ri(1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Logger.ri(1);
    }
}
