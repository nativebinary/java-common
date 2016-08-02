package controllers.api.v1;

import controllers.ControllerBase;
import logics.Check;
import logics.Json;
import models.Permission;
import logics.SessionLogic;
import models.Account;
import play.i18n.Messages;

@Json
public class Auth extends ControllerBase {

    public static void login(String email, String password){ // Account 로그인 합니다.
        final Account account = Account.findByEmail(email);
        if(account == null)
        {
            renderJsonFalse(Messages.get("not_exist_account"));
            return;
        }

        if(!account.isPasswordCorrect(password))
        {
            renderJsonFalse(Messages.get("incorrect_password"));
            return;
        }

        SessionLogic.login(session, account);
        renderJsonTrue(account);
    }

    public static void logout() { // void 로그아웃 합니다.
        SessionLogic.logout(session);
        renderJsonTrue();
    }

    public static void check(){ // void api가 동작하는지 확인합니다.
        renderJsonTrue();
    }

    @Check
    public static void checkLogin(){ // void 로그인한 사용자인지 확인합니다.
        renderJsonTrue();
    }

    @Check(Permission.Admin)
    public static void checkAdmin(){ // void 로그인한 사용자이며, Admin권한이 있는지 확인합니다.
        renderJsonTrue();
    }
}
