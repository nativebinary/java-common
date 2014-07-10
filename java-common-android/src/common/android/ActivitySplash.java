package common.android;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import com.google.android.gcm.GCMRegistrar;
import common.CommonInit;
import common.android.extensions.ActivityBase;
import common.android.helpers.CacheHelper;
import common.android.helpers.MemoryWatcher;
import common.android.logs.LoggerAndroid;
import common.android.utils.HandlerUtil;
import common.android.utils.MetaData;
import common.basic.jsons.JsonEngineGson;
import common.basic.logs.Logger;
import common.basic.utils.DateUtil;

public class ActivitySplash extends ActivityBase {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CommonInit.init(new LoggerAndroid("NB"), MetaData.isDev(this), new JsonEngineGson());

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        MemoryWatcher.start(this.getApplicationContext());


        initTextViewVersion();
        initGcm();

        CacheHelper.deleteExpiredCache(this, DateUtil.Day * 7);

        launchActivityMain();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void initTextViewVersion() {
        try {
            TextView textViewVersion = (TextView)findViewById(R.id.textViewVersion);
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            textViewVersion.setText(packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(e);
        }
    }

    private void initGcm() {
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


    private void launchActivityMain() {
        HandlerUtil.postDelayed(500, new Runnable() {
            @Override
            public void run() {
                try {
                    startActivity(new Intent(ActivitySplash.this, ActivityMain.class));
                }
                finally {
                    finish();
                }
            }
        });
    }
}
