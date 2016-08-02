package logics;

import common.basic.logs.Logger;
import common.basic.utils.HashMapStringObject;
import common.play1.extensions.JobBase;
import common.play1.utils.GcmUtil;
import common.play1.utils.JobUtil;
import models.Account;
import models.AccountDevice;
import models.Notice;
import models.NotificationType;

import java.io.IOException;
import java.util.List;

public class GcmLogic {
    public static void send(final Account account, final NotificationType notificationType, final Object object) {
        JobUtil.runAsync(new JobBase() {
            @Override
            protected void doDoJob() throws Exception {
                final String gcmApiKey = ApplicationConf.getGcmApiKey();

                // TODO: Convert to DirectQuery
                final List<AccountDevice> listAccount = AccountDevice.findByAccount(account);

                for (AccountDevice accountDevice : listAccount) {
                    accountDevice.device.get();

                    try {
                        GcmUtil.send(gcmApiKey, accountDevice.device.udid, new HashMapStringObject()
                                        .and("notificationType", notificationType.toString())
                                        .and("data", object)
                        );
                    }
                    catch (IOException e) {
                        Logger.e(e);
                    }
                    catch (GcmUtil.GcmException e) {
                        Logger.e(e);
                    }
                }
            }
        });
    }
    public static void sendNotice(Notice notice){
        // TODO: Convert To Job
        // TODO: Maintain Deliver History
        final List<Account> list = Account.all().fetch();
        for (Account account : list) {
            GcmLogic.send(account, NotificationType.Notice, notice);
        }
    }
}
