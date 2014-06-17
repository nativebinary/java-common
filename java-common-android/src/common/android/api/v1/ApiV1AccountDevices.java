package common.android.api.v1;

import android.content.Context;
import common.android.extensions.AsyncTaskCallback;
import common.basic.interfaces.ICallback;
import common.basic.utils.HashMapStringObject;
import models.OS;

public class ApiV1AccountDevices extends ApiV1 {

    public static void register(final Context context, final String udid, final OS os, final int version, final ICallback<String> callback) {
        new AsyncTaskCallback<String>(callback) {
            @Override
            protected String doInBackground() throws Exception {
                return httpGetReturnsString(context, "AccountDevices/register", new HashMapStringObject()
                        .and("udid", udid)
                        .and("os", os)
                        .and("version", version)
                );
            }
        };
    }

    public static void deregister(final Context context, final String udid, ICallback<String> callback) {
        new AsyncTaskCallback<String>(callback) {
            @Override
            protected String doInBackground() throws Exception {
                return httpGetReturnsString(context, "AccountDevices/deregister", new HashMapStringObject()
                        .and("udid", udid)
                );
            }
        };
    }
}
