package common.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import common.android.helpers.ActivityLauncher;
import common.android.utils.ToastUtil;
import common.basic.logs.Logger;
import common.basic.utils.StringUtil;
import common.basic.utils.URLUtil;

public class ActivityReceiver extends Activity implements ActivityLauncher.IProvider {
    final ActivityLauncher activityLauncher;

    public ActivityReceiver() {
        activityLauncher = new ActivityLauncher(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Logger.i();

        Intent intent = getIntent();
        String action = intent.getAction();
        if (StringUtil.isNullOrEmpty(action)) {
            Logger.e("StringUtil.isNullOrEmpty(action)");
            toastFailedAndFinish();
            return;
        }

        String type = intent.getType();
        if (StringUtil.isNullOrEmpty(type)) {
            Logger.e("StringUtil.isNullOrEmpty(type)");
            toastFailedAndFinish();
            return;
        }

        if (Intent.ACTION_SEND.equals(action) && !StringUtil.isNullOrEmpty(type)  && type.contains("image/")) {
            Uri imageUri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
            if(imageUri == null) {
                toastFailedAndFinish();
                return;
            }

            try {
                if(imageUri.getScheme().contains("content")){
                }
                if(imageUri.getScheme().contains("file")) {
                }
            } catch (Exception e) {
                Logger.e(e);
                toastFailedAndFinish();
            }
            return;
        }


        if (!Intent.ACTION_SEND.equals(action) || !"text/plain".equals(type)) {
            Logger.e("!Intent.ACTION_SEND.equals(action) || !\"text/plain\".equals(type)");
            return;
        }


        final String text = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (StringUtil.isNullOrEmpty(text)) {
            Logger.e("StringUtil.isNullOrEmpty(text)");
            toastFailedAndFinish();
            return;
        }

        if (!URLUtil.isHttpOrHttps(text)) {
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Logger.i();

        activityLauncher.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Logger.i();

        activityLauncher.onDestroy();
    }

    private void toastFailedAndFinish() {
        ToastUtil.showShort(this, R.string.failed);
        finish();
    }

    private void toastCanceledAndFinish() {
        ToastUtil.showShort(this, R.string.canceled);
        finish();
    }


    @Override
    public Activity getActivity() {
        return this;
    }
}