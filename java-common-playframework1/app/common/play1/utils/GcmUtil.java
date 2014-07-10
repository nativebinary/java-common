package common.play1.utils;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import common.basic.facades.jsons.gson.GsonUtil;
import common.basic.utils.HashMapStringObject;

import java.io.IOException;

public class GcmUtil {
    public static class GcmException extends Exception {
        public final Result result;

        public GcmException(Result result) {
            this.result = result;
        }

        public boolean isGoogleServerError() {
            return (Constants.ERROR_INTERNAL_SERVER_ERROR.equals(result.getErrorCodeName()));
        }
    }

    public static Result send(String gcmApiKey, String udid, Message message) throws IOException, GcmException {
        Sender sender = new Sender(gcmApiKey);

        Result result = sender.send(message, udid, 5);
        if (result.getMessageId() != null)
            return result;

        throw new GcmException(result);
    }

    public static Result send(String gcmApiKey, String udid, HashMapStringObject hashMapStringObject) throws IOException, GcmException {
        return send(gcmApiKey, udid, getMessage(hashMapStringObject));
    }

    public static Message getMessage(HashMapStringObject hashMapStringObject) {
        final Message.Builder builder = new Message.Builder();
        for (String key : hashMapStringObject.keySet()) {
            final Object o = hashMapStringObject.get(key);
            if(o instanceof String)
            {
                builder.addData(key, String.valueOf(o));
                continue;
            }

            builder.addData(key, GsonUtil.toJson(o));
        }

        return builder.build();
    }
}
