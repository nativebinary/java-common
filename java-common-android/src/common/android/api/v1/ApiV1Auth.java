package common.android.api.v1;

import android.content.Context;
import common.android.extensions.AsyncTaskCallback;
import common.basic.interfaces.ICallback;
import common.basic.utils.HashMapStringObject;

public class ApiV1Auth extends ApiV1 {

    public static void login(final Context context, final String email, final String password, final ICallback<String> callback) {
        new AsyncTaskCallback<String>(callback) {
            @Override
            protected String doInBackground() throws Exception {
                return httpGetReturnsString(context, "Auth/login", new HashMapStringObject()
                                .and("email", email)
                                .and("password", password)
                );
            }
        };
    }
}
