package jobs;

import common.basic.databases.DatabaseManager;
import common.basic.logs.Logger;
import common.play1.logs.LoggerPlay;
import common.play1.utils.JobUtil;
import logics.ApplicationConf;
import models.Account;
import models.AccountPermission;
import models.Permission;
import play.db.DB;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import siena.Id;

import java.sql.Connection;

@OnApplicationStart
public class JobOnApplicationStart extends Job {

    public void doJob() {
        Logger.setLogger(new LoggerPlay());
        Logger.setDebug(true);

        initDatabaseManager();
        initAccount();
        initTestData();

        JobUtil.runAsync(new JobHourly());
    }

    private void initDatabaseManager() {
        DatabaseManager.initialize(new DatabaseManager.IProvider() {
            @Override
            public Connection getConnection() {
                return DB.getConnection();
            }

            @Override
            public Class getKeyAnnotation() {
                return Id.class;
            }
        });
    }

    private void initAccount() {
        if (Account.count() != 0)
            return;

        Logger.e();

        Account account = new Account();
        account.email = ApplicationConf.initial_admin_email();
        account.setPasswordToEncrypt(ApplicationConf.initial_admin_password());
        account.insert();

        AccountPermission accountPermission = new AccountPermission();
        accountPermission.account = account;
        accountPermission.permission = Permission.Admin;
        accountPermission.insert();
    }

    private void initTestData() {

    }
}