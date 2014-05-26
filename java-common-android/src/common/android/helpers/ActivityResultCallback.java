package common.android.helpers;

import android.app.Activity;
import android.content.Intent;

public abstract class ActivityResultCallback {
    public boolean isOk(int resultCode){
        return resultCode == Activity.RESULT_OK;
    }

    public abstract void onActivityResult(int resultCode, Intent data);
}
