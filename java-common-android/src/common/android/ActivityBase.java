package common.android;

import android.app.Activity;
import android.os.Bundle;
import common.basic.logs.Logger;

public abstract class ActivityBase extends Activity {
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
