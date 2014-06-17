package common.android;

import android.os.Bundle;
import com.google.android.gcm.GCMRegistrar;
import common.android.api.v1.ApiV1Auth;
import common.android.api.v1.ApiV1Echos;
import common.android.utils.ToastUtil;
import common.basic.interfaces.ICallback;
import common.basic.logs.Logger;
import common.basic.utils.HashMapStringObject;

public class ActivityMain extends ActivityBase {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        final HashMapStringObject map = new HashMapStringObject().and("A", 1).and("B", "TTT");
        ApiV1Echos.index(this, map, new ICallback<String>() {
            @Override
            public void onSuccess(String s) {
                Logger.e(s);
            }

            @Override
            public void onFail(Exception e) {
                Logger.e(e);
            }
        });

        ApiV1Auth.login(this, "admin@nativebinary.com", "nativebinaryPassword", new ICallback<String>() {
            @Override
            public void onSuccess(String s) {
                registerDeviceId();
                ToastUtil.showLong(ActivityMain.this, s);
            }

            @Override
            public void onFail(Exception e) {
                Logger.e(e);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void registerDeviceId() {
        try {
            GCMRegistrar.checkDevice(this);
            GCMRegistrar.checkManifest(this);
            final String regId = GCMRegistrar.getRegistrationId(this);
            if("".equals(regId))
                GCMRegistrar.register(this, GCMIntentService.PROJECT_ID);
            else {
                Logger.i("regId", regId);

                GCMIntentService.registerToWebServer(this, regId);
            }
        } catch (Exception e) {
            Logger.e(e);
        }
    }
}