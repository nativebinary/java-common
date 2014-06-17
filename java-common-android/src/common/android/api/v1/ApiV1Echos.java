package common.android.api.v1;

import android.content.Context;
import common.android.extensions.AsyncTaskCallback;
import common.basic.interfaces.ICallback;
import common.basic.utils.HashMapStringObject;

public class ApiV1Echos extends ApiV1 {

    public static void index(final Context context, final HashMapStringObject map, final ICallback<String> callback) {
        new AsyncTaskCallback<String>(callback) {
            @Override
            protected String doInBackground() throws Exception {
                return httpGetReturnsString(context, "Echos/index", map);
            }
        };
    }
}
