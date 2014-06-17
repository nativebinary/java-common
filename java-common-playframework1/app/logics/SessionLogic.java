package logics;

import common.basic.utils.LongUtil;
import models.Account;

import static play.mvc.Scope.Session;

/**
 * Created with IntelliJ IDEA.
 * User: aha00a
 * Date: 12. 10. 15.
 * Time: PM 9:42
 * To change this template use File | Settings | File Templates.
 */
public class SessionLogic {
    static enum Key {
        AccountId {
            @Override
            protected String getValue(Account account) {
                return String.valueOf(account.id);
            }
        },
        AccountEmail {
            @Override
            protected String getValue(Account account) {
                return account.email;
            }
        },
        AccountArrayPermission {
            @Override
            protected String getValue(Account account) {
                return account.getJsonArrayPermission();
            }
        },;

        protected String getKey() {
            return this.toString();
        }

        protected abstract String getValue(Account account);

        public void put(Session session, Account account) {
            session.put(this.getKey(), this.getValue(account));
        }

        public String getValue(Session session) {
            return session.get(this.getKey());
        }

        public void remove(Session session) {
            session.remove(this.toString());
        }

    }

    public static long getAccountId(Session session) {
        return LongUtil.parseLong(Key.AccountId.getValue(session), 0);
    }

    public static Account getAccount(Session session)
    {
        final long accountId = getAccountId(session);
        if(0 == accountId)
            return null;

        return Account.findById(accountId);
    }

    public static void login(Session session, Account account) {
        for (Key key : Key.values()) {
            key.put(session, account);
        }
    }

    public static void logout(Session session) {
        for (Key key : Key.values()) {
            key.remove(session);
        }
    }
}
