package controllers.api.v1;

import common.basic.databases.Query;
import common.basic.logs.Logger;
import controllers.ControllerBase;
import logics.ApplicationConf;
import logics.Check;
import logics.Json;
import models.Permission;

import java.sql.SQLException;
import java.util.List;

@Json
public class Accounts extends ControllerBase {

    @Check(Permission.Admin)
    public static void list(long until) throws SQLException {// List<Account> Account 목록을 가져옵니다.
        if(until == 0)
            until = Long.MAX_VALUE;

        List<models.Account> list = models.Account
                .all()
                .filter("id<", until)
                .order("-id")
                .limit(ApplicationConf.api_result_limit())
                .fetch();

        renderJsonTrue(list);
    }

    public static void get(long id){ // Account 해당 Account를 가져옵니다.
        models.Account account = models.Account.findById(id);

        if (null == account)
        {
            renderJsonFalse("Not Found");
            return;
        }

        renderJsonTrue(account);
    }

    public static void profile(long id){ // File 해당 Account의 프로필 이미지를 가져옵니다.
        models.Account account = models.Account.findById(id);

        if (null == account)
        {
            renderJsonFalse("Not Found");
            return;
        }

        if(account.attachmentAccountProfile == null)
        {
            renderJsonFalse("Not Found");
            return;
        }

        renderBinary(account.attachmentAccountProfile.getFile());
    }

    @Check(Permission.Admin)
    public static void update(long id, String nickname, String description) { // void 해당 사용자의 닉네임과 설명을 변경합니다.
        try {
            final int countAffectedRow = new Query().eq("id", id).set("nickname", nickname).set("description", description).update(Accounts.class);
            if(countAffectedRow != 1)
            {
                renderJsonFalse(String.format("countAffectedRow(%d) != 1", countAffectedRow));
                return;
            }

            renderJsonTrue();
        }
        catch (SQLException e) {
            Logger.e(e);
            renderJsonFalse(e);
        }

    }
}
