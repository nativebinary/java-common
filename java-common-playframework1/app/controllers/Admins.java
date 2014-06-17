package controllers;


import common.play1.utils.JobUtil;
import jobs.JobHourly;
import logics.Check;
import logics.GcmLogic;
import models.Account;
import models.Notice;
import models.Permission;

import java.util.List;

@Check(Permission.Admin)
public class Admins extends ControllerBase {

    public static void index() {
        render();
    }

    public static void accountList(){
        render();
    }

    public static void accountEdit(Account account){
        if(account == null)
        {
            flash.error("invalid account.id");
            accountList();
        }

        render(account);
    }

    public static void runJobHourly(){
        JobUtil.runAsync(new JobHourly());
        Admins.index();
    }

    public static void test(){

        render();
    }

    public static void noticeList(){
        final List<Notice> list = Notice.all().order("-id").fetch();
        render(list);
    }

    public static void noticeWriteProc(Notice notice){
        notice.insert();
        GcmLogic.sendNotice(notice);
        renderJsonTrue(notice);
    }
}
