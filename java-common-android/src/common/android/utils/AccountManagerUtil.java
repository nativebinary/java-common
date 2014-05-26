package common.android.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

public class AccountManagerUtil {

    public static final String AccountTypeGoogle = "com.google";

    public static Account[] getGoogleAccounts(Context context) {
        AccountManager manager = getAccountManager(context);
        return manager.getAccountsByType(AccountTypeGoogle);
    }

    public static AccountManager getAccountManager(Context context) {
        return (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);
    }
}
