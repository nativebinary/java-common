package controllers;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import common.basic.logs.Logger;
import common.basic.utils.HashMapStringObject;
import common.play1.utils.GcmUtil;
import javapns.Push;
import play.mvc.Controller;

public class PushNotifications extends Controller {

    public static void index() {

        try {
            Push.alert("Dev Hello World!", "conf/aps_development.p12", "airbusan", false, "5fa0d87d916a671c921c948d2bfd24e677da6f4d004215665a2c28e41ddafbc4");
            Push.alert("Prod Hello World!", "conf/aps_production.p12", "airbusan", true, "bf1c4c0857e10fcac1df1c46aa4e8084ee68b1b98c764501e7aa48e56c45b6cb");

            // Android
            // APA91bHb_TaC5nMk_QvOw1slo7LD9BjX-BbsdDHPVnTsnl3jlZQ4_zd78mO3SiKnDztWHiA4rQjWTdj-Mu8o6AvSG-M9te24QltUkyMJ1-1iAaq7wDwauZKQp0M071wbbHwkfvnPRsWF
            String gcmApiKey = "AIzaSyCUat9Rr3EyXtcBFGKHt9fssaNNOdqKJj4";// "6259066503";
            String gcmUdid = "APA91bHb_TaC5nMk_QvOw1slo7LD9BjX-BbsdDHPVnTsnl3jlZQ4_zd78mO3SiKnDztWHiA4rQjWTdj-Mu8o6AvSG-M9te24QltUkyMJ1-1iAaq7wDwauZKQp0M071wbbHwkfvnPRsWF";
            HashMapStringObject mapData = new HashMapStringObject();
            mapData.and("message", "메세지");
            mapData.and("messageUrl", "http://m.airbusan.com/?aaaa");

            Result data = GcmUtil.send(gcmApiKey, gcmUdid, new HashMapStringObject()
                    .and("data", "hhhhhhhhhhhhhhhhhhhh")
            );

            Logger.e(data);
        } catch (Exception ex) {
            Logger.e(ex);
        }

        render();
    }

    public static void register(String udid) {
        Logger.e(udid);
    }
}
