package common.android;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import com.google.android.gcm.GCMBaseIntentService;
import common.android.api.v1.ApiV1AccountDevices;
import common.android.utils.NotificationManagerUtil;
import common.basic.facades.jsons.JsonUtil;
import common.basic.interfaces.ICallback;
import common.basic.logs.Logger;
import common.basic.utils.EnumUtil;
import common.basic.utils.RandomUtil;
import models.Notice;
import models.NotificationType;
import models.OS;

public class GCMIntentService extends GCMBaseIntentService {
    public static final String PROJECT_ID = "662295485384";

    int notificationId = RandomUtil.nextInt();

    //public 기본 생성자를 무조건 만들어야 한다.
    @SuppressWarnings("UnusedDeclaration")
    public GCMIntentService() {
        this(PROJECT_ID);

        Logger.i();
    }

    public GCMIntentService(String project_id) {
        super(project_id);

        Logger.i();
    }

    /**
     * 푸시로 받은 메시지
     */
    @Override
    protected void onMessage(Context context, Intent intent) {
        Logger.i();

        Bundle b = intent.getExtras();

        assert b != null;
        final String type = b.getString("notificationType");
        final String data = b.getString("data");

        onMessage(type, data);
    }

    private void onMessage(String type, String data) {
        Logger.i(type, data);

        NotificationType notificationType = EnumUtil.parse(NotificationType.class, type);
        if(null == notificationType)
        {
            Logger.e("null == notificationType", type, data);
            return;
        }

        switch (notificationType) {
            case Notice:
            {
                final Notice notice = JsonUtil.fromJson(data, Notice.class);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(notice.url));
                notify(notice.ticker, notice.contentTitle, notice.contentText, intent);
                break;
            }
        }
        Logger.e(notificationType);
    }

    public void notify(String ticker, String contentTitle, String contentText, Intent intent) {
        NotificationManagerUtil.notify(this, ++notificationId, System.currentTimeMillis(), R.drawable.logo, ticker, contentTitle, contentText, intent);
    }


    @Override
    protected void onError(Context context, String errorId) {
        Logger.d("on_error. errorId : " + errorId);
    }

    /**
     * 단말에서 GCM 서비스 등록 했을 때 등록 id를 받는다
     */
    @Override
    protected void onRegistered(Context context, String regId) {
        Logger.d(regId);

        registerToWebServer(this, regId);
    }

    /**
     * 단말에서 GCM 서비스 등록 해지를 하면 해지된 등록 id를 받는다
     */
    @Override
    protected void onUnregistered(Context context, String regId) {
        Logger.d("onUnregistered. regId : " + regId);

        ApiV1AccountDevices.deregister(context, regId, new ICallback<String>() {
            @Override
            public void onSuccess(String s) {
                Logger.i(s);
            }

            @Override
            public void onFail(Exception e) {
                Logger.e(e);
            }
        });
    }

    public static void registerToWebServer(Context context, String regId) {
        ApiV1AccountDevices.register(context, regId, OS.Android, Build.VERSION.SDK_INT, new ICallback<String>() {
            @Override
            public void onSuccess(String s) {
                Logger.i(s);
            }

            @Override
            public void onFail(Exception e) {
                Logger.e(e);
            }
        });
    }

}
