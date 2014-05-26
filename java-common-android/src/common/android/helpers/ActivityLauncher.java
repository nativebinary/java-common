package common.android.helpers;

import android.app.Activity;
import android.content.Intent;
import common.basic.logs.Logger;

import java.util.HashMap;
import java.util.Map;

public class ActivityLauncher {
    public static final int start = 0x00aabb00;

    public interface IProvider {
        Activity getActivity();
    }

    final Map<Integer, ActivityResultCallback> map;
    final IProvider provider;

    public ActivityLauncher(IProvider provider) {
        this.map = new HashMap<Integer, ActivityResultCallback>();
        this.provider = provider;
    }

    private int generateRequestCode() {
        int requestCode = start;
        while(map.containsKey(requestCode))
        {
            requestCode++;
        }
        return requestCode;
    }

    public void startActivityForResult(Intent intent, ActivityResultCallback activityResultCallback) {
        final int requestCode = generateRequestCode();
        map.put(requestCode, activityResultCallback);
        if(1 < map.size())
            Logger.e("1 < map.size()", map.size());

        provider.getActivity().startActivityForResult(intent, requestCode);
    }

    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        if(!map.containsKey(requestCode))
        {
            Logger.e(requestCode, resultCode, data);
            return false;
        }

        final ActivityResultCallback activityResultCallback = map.get(requestCode);
        activityResultCallback.onActivityResult(resultCode, data);
        map.remove(requestCode);
        return true;
    }

    public void onDestroy() {
        if(map.size() == 0)
            return;

        Logger.e("0 != map.size()", map.size());
    }
}
